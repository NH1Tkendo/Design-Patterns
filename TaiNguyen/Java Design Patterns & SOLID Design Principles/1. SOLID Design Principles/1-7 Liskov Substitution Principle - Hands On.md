## Nguyên Tắc Thay Thế Liskov (Liskov Substitution Principle - LSP) Trong SOLID

### Giới Thiệu Ví Dụ
- Ví dụ sử dụng các lớp `Rectangle` (hình chữ nhật) và `Square` (hình vuông) để minh họa LSP – một ví dụ kinh điển thường xuất hiện trong sách vở, tìm kiếm trực tuyến hoặc phỏng vấn.
- Lý do chọn ví dụ này: Dựa trên kiến thức hình học (hình vuông là trường hợp đặc biệt của hình chữ nhật khi chiều rộng bằng chiều cao), nhưng dễ thấy vấn đề khi áp dụng vào lập trình hướng đối tượng (OOP).
- Mục tiêu: Hiểu cách kế thừa có thể vi phạm hợp đồng hành vi (behavioral contract) của lớp cha, dẫn đến thay thế không an toàn.

### Mô Tả Các Lớp Ban Đầu (Vi Phạm LSP)
- **Lớp `Rectangle`**:
  - Thuộc tính: `width` (chiều rộng), `height` (chiều cao).
  - Constructor: Nhận `width` và `height` để khởi tạo.
  - Phương thức: Getter/setter cho `width` và `height`; `computeArea()` trả về `width * height`.
- **Mã nguồn lớp `Rectangle`**:
  ```java
  public class Rectangle {
      protected int width;
      protected int height;

      public Rectangle(int width, int height) {
          this.width = width;
          this.height = height;
      }

      public int getWidth() { return width; }
      public void setWidth(int width) { this.width = width; }
      public int getHeight() { return height; }
      public void setHeight(int height) { this.height = height; }

      public int computeArea() {
          return width * height;
      }
  }
  ```
- **Lớp `Square` kế thừa từ `Rectangle`**:
  - Constructor: Chỉ nhận một tham số `side` (cạnh), gọi constructor của `Rectangle` với `side` cho cả `width` và `height`.
  - Override `setWidth` và `setHeight`: Gọi phương thức `setSide` mới để đảm bảo `width` và `height` luôn bằng nhau (giữ tính chất hình vuông).
- **Mã nguồn lớp `Square`**:
  ```java
  public class Square extends Rectangle {
      public Square(int side) {
          super(side, side);
      }

      private void setSide(int side) {
          this.width = side;
          this.height = side;
      }

      @Override
      public void setWidth(int width) {
          setSide(width);
      }

      @Override
      public void setHeight(int height) {
          setSide(height);
      }
  }
  ```

### Kiểm Tra Vi Phạm LSP
- **Lớp test với phương thức `main`**:
  - Tạo đối tượng `Rectangle` và `Square`.
  - In diện tích ban đầu.
  - Gọi `useRectangle(rectangle)` và `useRectangle(square)` – phương thức này nhận tham số kiểu `Rectangle` (cho phép thay thế bằng `Square` do kế thừa).
- **Mã nguồn phương thức `useRectangle`**:
  - Đặt `setHeight(20)` và `setWidth(30)`.
  - Sử dụng assertion để kiểm tra: `assert rectangle.getHeight() == 20` và `assert rectangle.getWidth() == 30`.
  - (Lưu ý: Để chạy assertion trong Java, thêm flag `-ea` vào VM arguments; có thể thay bằng `if-else` để kiểm tra).
- **Kết quả chạy**:
  - Với `Rectangle`: Assertion pass (chiều cao = 20, chiều rộng = 30, diện tích = 600).
  - Với `Square`: Assertion fail (do override: `setHeight(20)` đặt cả hai = 20; sau đó `setWidth(30)` đặt cả hai = 30 → chiều cao không còn = 20).
- **Lý do vi phạm LSP**:
  - Thay thế `Rectangle` bằng `Square` (lớp con) làm thay đổi hành vi mong đợi: Hợp đồng của `Rectangle` là `setHeight` chỉ ảnh hưởng đến chiều cao, nhưng `Square` vi phạm bằng cách thay đổi cả chiều rộng.
  - Về mặt cú pháp (syntactic): Kế thừa hợp lệ. Về mặt hành vi (behavioral): Thay đổi thuộc tính không mong muốn của chương trình.

### Cách Khắc Phục Vi Phạm LSP
- **Vấn đề cốt lõi**: Mặc dù toán học hình vuông là hình chữ nhật, nhưng trong OOP, mối quan hệ kế thừa vi phạm hợp đồng hành vi của lớp cha.
- **Giải pháp**: Phá vỡ mối quan hệ kế thừa trực tiếp; sử dụng interface chung để định nghĩa hành vi chung (chỉ `computeArea()`).
- **Tạo interface `Shape`**:
  - Chỉ chứa phương thức trừu tượng `computeArea()`.
- **Mã nguồn interface `Shape`**:
  ```java
  public interface Shape {
      int computeArea();
  }
  ```
- **Refactor `Rectangle` implement `Shape`**:
  - Giữ nguyên thuộc tính và phương thức như cũ; implement `computeArea()`.
- **Refactor `Square` implement `Shape`** (không kế thừa `Rectangle`):
  - Thuộc tính: `side` (cạnh).
  - Constructor: Nhận `side`.
  - Phương thức: `setSide(int side)`, getter `getSide()`, `computeArea()` trả về `side * side`.
- **Mã nguồn lớp `Square` sau refactor**:
  ```java
  public class Square implements Shape {
      private int side;

      public Square(int side) {
          this.side = side;
      }

      public int getSide() { return side; }
      public void setSide(int side) { this.side = side; }

      @Override
      public int computeArea() {
          return side * side;
      }
  }
  ```
- **Test sau refactor**:
  - Không còn `useRectangle()`; test riêng cho `Rectangle` và `Square`.
  - Đảm bảo người dùng chỉ mong đợi `computeArea()`, không giả định hành vi setter của `Rectangle`.

### Ghi Chú Thêm
- **Bài học chính**: LSP yêu cầu lớp con không chỉ tuân thủ cú pháp mà còn duy trì hành vi chính xác của lớp cha (không thay đổi invariant hoặc hợp đồng).
- **Lợi ích**: Tránh lỗi bất ngờ khi thay thế đối tượng; tăng tính an toàn và bảo trì trong hệ thống kế thừa.
- **Mở rộng**: Trong thực tế, ưu tiên interface thay vì kế thừa sâu để tránh vi phạm LSP; kiểm tra hành vi qua unit test.