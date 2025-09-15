## Nguyên Tắc Thay Thế Liskov (Liskov Substitution Principle - LSP)

### Khái Niệm Chính
- **Định nghĩa**: Nguyên tắc LSP (thứ ba trong nhóm nguyên tắc SOLID) quy định rằng đối tượng của lớp con (subclass hoặc child class) phải có thể thay thế cho đối tượng của lớp cha (base class) mà không làm thay đổi các thuộc tính mong muốn của chương trình.
- **Ý nghĩa đơn giản**: Chương trình hoạt động đúng như mong đợi khi sử dụng lớp con thay vì lớp cha.
- **Không chỉ là thay thế kiểu dữ liệu**: Tuân thủ quy tắc ngôn ngữ (ví dụ: Java), mà còn bao gồm **thay thế hành vi (behavioral subtyping)** – hành vi của lớp cha phải được duy trì chính xác ở lớp con, không gây ra thay đổi bất ngờ.
- **Nguồn gốc**: Được giới thiệu bởi Barbara Liskov năm 1987 trong bài phát biểu "Data Abstraction".

### Tầm Quan Trọng
- Đảm bảo tính tương thích ngữ nghĩa (semantic interoperability) trong hệ thống kế thừa.
- Hỗ trợ nguyên tắc Open-Closed (OCP) bằng cách tránh tác dụng phụ khi mở rộng lớp.
- Không thể kiểm tra tự động bởi compiler (chỉ kiểm tra cấu trúc); cần kiểm tra thủ công hành vi.

### Ví Dụ Minh Họa Trong Java
- **Ví dụ vi phạm LSP**: Lớp `Square` kế thừa từ `Rectangle`. Trong `Rectangle`, có thể đặt chiều rộng và chiều cao độc lập, nhưng `Square` buộc chúng bằng nhau, dẫn đến hành vi bất ngờ.
- **Mã nguồn lớp cha (Rectangle)**:
  ```java
  public class Rectangle {
      protected int width;
      protected int height;

      public void setWidth(int width) {
          this.width = width;
      }

      public void setHeight(int height) {
          this.height = height;
      }

      public int getArea() {
          return width * height;
      }
  }
  ```
- **Mã nguồn lớp con vi phạm (Square)**:
  ```java
  public class Square extends Rectangle {
      @Override
      public void setWidth(int width) {
          this.width = width;
          this.height = width;  // Buộc chiều cao bằng chiều rộng
      }

      @Override
      public void setHeight(int height) {
          this.height = height;
          this.width = height;  // Gây bất ngờ: thay đổi cả chiều rộng
      }
  }
  ```
- **Kiểm tra vi phạm**:
  - Tạo `Rectangle r = new Square();`.
  - `r.setWidth(5); r.setHeight(4);` → Diện tích mong đợi là 20, nhưng thực tế là 25 (do `height` bị thay thành 5).
  - **Hậu quả**: Thay thế làm thay đổi hành vi mong đợi của `Rectangle`, vi phạm LSP.

- **Ví dụ tuân thủ LSP**: Sử dụng interface hoặc thiết kế lại để tránh kế thừa không phù hợp (ví dụ: `Square` không kế thừa `Rectangle`, mà cả hai implement interface `Shape` với phương thức `getArea()` riêng).

### Ghi Chú Thêm
- **Lợi ích**: Giúp mã dễ bảo trì, tái sử dụng và mở rộng mà không gây lỗi.
- **Mẹo áp dụng**: Kiểm tra xem lớp con có làm thay đổi invariant (thuộc tính không đổi) của lớp cha không; tránh override làm thay đổi hợp đồng (contract) hành vi.
- Để hiểu đầy đủ, cần xem mã Java cụ thể trong phần tiếp theo của bài học.