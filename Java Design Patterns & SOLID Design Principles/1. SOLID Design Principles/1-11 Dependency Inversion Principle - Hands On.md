## Nguyên Lý Đảo Ngược Phụ Thuộc Trong Thực Tế (Dependency Inversion Principle in Action)

### Giới Thiệu
- Ví dụ minh họa DIP qua code Java, tập trung vào lớp `MessagePrinter` để in thông điệp dưới dạng JSON vào file.
- Mục tiêu: Chuyển từ tight coupling (kết nối chặt chẽ) với lớp cụ thể sang phụ thuộc vào abstraction (trừu tượng), tăng tính linh hoạt.

### Code Ban Đầu (Tight Coupling)
- **Lớp Message**: Lớp đơn giản chứa nội dung thông điệp (string) và timestamp (khởi tạo với thời gian hiện tại).
  ```java
  public class Message {
      private String content;
      private long timestamp;

      public Message(String content) {
          this.content = content;
          this.timestamp = System.currentTimeMillis();
      }

      // Getter methods cho content và timestamp
  }
  ```

- **Interface Formatter**: Giao diện trừu tượng với phương thức `format(Message message)` để định dạng thông điệp.
  
- **Lớp JsonFormatter**: Triển khai `Formatter`, sử dụng Jackson để serialize thành JSON.
  ```java
  public class JsonFormatter implements Formatter {
      @Override
      public String format(Message message) {
          ObjectMapper mapper = new ObjectMapper();
          return mapper.writeValueAsString(message);
      }
  }
  ```

- **Lớp MessagePrinter**: Phương thức `writeMessage(Message message, String fileName)` tạo instance `JsonFormatter` và `PrintWriter` trực tiếp, dẫn đến tight coupling.
  ```java
  public class MessagePrinter {
      public void writeMessage(Message message, String fileName) {
          JsonFormatter formatter = new JsonFormatter();  // Tight coupling
          String json = formatter.format(message);
          PrintWriter writer = new PrintWriter(new FileWriter(fileName));  // Tight coupling
          writer.println(json);
          writer.close();
      }
  }
  ```

- **Main Method**: Tạo `Message` và gọi `writeMessage`, tạo file "test-message.txt" với nội dung JSON.
  ```java
  public static void main(String[] args) {
      Message msg = new Message("Hello World");
      MessagePrinter printer = new MessagePrinter();
      printer.writeMessage(msg, "test-message.txt");
  }
  ```

- **Vấn Đề**:
  - Không thể in ra console mà không thêm phương thức mới (sử dụng `System.out` thay vì `PrintWriter`).
  - Thay đổi định dạng (ví dụ: từ JSON sang text) yêu cầu sửa trực tiếp code trong `writeMessage`, vi phạm nguyên lý mở rộng (Open/Closed Principle).
  - Phụ thuộc trực tiếp vào lớp cụ thể: `JsonFormatter` và `PrintWriter` (từ gói `java.io`).

### Áp Dụng DIP: Chuyển Sang Abstraction
- Thay đổi `writeMessage` để nhận tham số là `Formatter` và `PrintWriter` (thay vì tạo instance bên trong và truyền `fileName`).
  ```java
  public class MessagePrinter {
      public void writeMessage(Message message, Formatter formatter, PrintWriter writer) {
          String formatted = formatter.format(message);  // Sử dụng abstraction
          writer.println(formatted);
          // Không cần đóng writer ở đây (xử lý ở client)
      }
  }
  ```

- **Lý Do Thay Đổi**:
  - Loại bỏ tight coupling: Phương thức chỉ phụ thuộc vào interface `Formatter`, không phải lớp cụ thể.
  - Đảo ngược phụ thuộc: Client (main method) chịu trách nhiệm tạo và cung cấp dependency.
  - Loại bỏ `fileName`: Tên file được xử lý ở client khi tạo `PrintWriter`.

### Cập Nhật Main Method (Client Cung Cấp Dependency)
- Tạo instance `Formatter` và `PrintWriter` ở client, truyền vào phương thức.
  ```java
  import java.io.*;

  public static void main(String[] args) throws IOException {
      Message msg = new Message("Hello World");
      
      Formatter jsonFormatter = new JsonFormatter();  // Dependency từ client
      PrintWriter fileWriter = new PrintWriter(new FileWriter("test-message.txt"));  // Dependency từ client
      
      MessagePrinter printer = new MessagePrinter();
      printer.writeMessage(msg, jsonFormatter, fileWriter);
      fileWriter.close();  // Đóng ở client
  }
  ```

- **Kết Quả**: Code chạy tương tự, tạo file với JSON và timestamp cập nhật khi thay đổi message.

### Lợi Ích Và Mở Rộng
- **Thay Đổi Output**: Thay `PrintWriter` bằng `PrintWriter` cho console để in ra màn hình.
  ```java
  PrintWriter consoleWriter = new PrintWriter(System.out);  // Không cần file
  printer.writeMessage(msg, jsonFormatter, consoleWriter);
  ```
  - Kết quả: In trực tiếp ra console mà không sửa `writeMessage`.

- **Thay Đổi Định Dạng**: Tạo `TextFormatter` triển khai `Formatter` để xuất text thuần.
  ```java
  public class TextFormatter implements Formatter {
      @Override
      public String format(Message message) {
          return "Message: " + message.getContent() + " at " + new Date(message.getTimestamp());
      }
  }
  ```
  - Trong main: Truyền `new TextFormatter()` thay vì `JsonFormatter` → Output dạng text.

- **Ưu Điểm**:
  - Phương thức `writeMessage` chỉ chứa business logic (quy tắc kinh doanh), dễ test và tái sử dụng.
  - Không cần sửa code gốc khi thay đổi dependency → Linh hoạt, giảm bug.
  - Trong thực tế: Framework như Spring sử dụng DIP qua autowiring và Spring Beans để inject dependency tự động.

### Ghi Chú Thêm
- DIP giúp code dễ bảo trì bằng cách tách biệt việc tạo dependency khỏi logic chính.
- Áp dụng sớm trong thiết kế để tránh tight coupling từ đầu.