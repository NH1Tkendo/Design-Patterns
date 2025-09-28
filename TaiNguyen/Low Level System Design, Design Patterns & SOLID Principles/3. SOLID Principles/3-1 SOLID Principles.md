# Ghi chú học tập: Nguyên tắc SOLID trong Lập trình Hướng đối tượng

## Tổng quan về Nguyên tắc SOLID

### Khái niệm

- **SOLID** là tập hợp năm nguyên tắc thiết kế trong lập trình hướng đối tượng (Object-Oriented Programming) và thiết kế phần mềm.
- **Mục tiêu**:
  - Tăng tính **dễ hiểu (understandable)**.
  - Tăng tính **linh hoạt (flexible)**.
  - Tăng tính **dễ bảo trì (maintainable)**.
- Các nguyên tắc cung cấp cách tiếp cận có cấu trúc để xây dựng phần mềm chất lượng cao.

### Danh sách năm nguyên tắc SOLID

1. **Nguyên tắc trách nhiệm đơn nhất (Single Responsibility Principle - SRP)**.
2. **Nguyên tắc mở/đóng (Open/Closed Principle - OCP)**.
3. **Nguyên tắc thay thế Liskov (Liskov Substitution Principle - LSP)**.
4. **Nguyên tắc phân tách giao diện (Interface Segregation Principle - ISP)**.
5. **Nguyên tắc đảo ngược phụ thuộc (Dependency Inversion Principle - DIP)**.

## Chi tiết các nguyên tắc

### 1. Nguyên tắc trách nhiệm đơn nhất (Single Responsibility Principle - SRP)

- **Định nghĩa**: Một lớp chỉ nên có **một lý do để thay đổi**, tức là chỉ chịu trách nhiệm cho một chức năng duy nhất.
- **Lợi ích**:
  - Giảm sự phức tạp của lớp.
  - Dễ dàng bảo trì và kiểm tra mã nguồn.

### 2. Nguyên tắc mở/đóng (Open/Closed Principle - OCP)

- **Định nghĩa**: Các thực thể phần mềm (lớp, mô-đun, hàm, v.v.) nên **mở để mở rộng (open for extension)** nhưng **đóng để sửa đổi (closed for modification)**.
- **Lợi ích**:
  - Cho phép thêm chức năng mới mà không cần thay đổi mã hiện có.
  - Tăng tính tái sử dụng và ổn định.

### 3. Nguyên tắc thay thế Liskov (Liskov Substitution Principle - LSP)

- **Định nghĩa**: Các đối tượng của lớp con phải có thể thay thế cho đối tượng của lớp cha mà không làm phá vỡ chức năng của chương trình.
- **Lợi ích**:
  - Đảm bảo tính đúng đắn khi sử dụng tính kế thừa.
  - Tăng tính tương thích giữa các lớp.

### 4. Nguyên tắc phân tách giao diện (Interface Segregation Principle - ISP)

- **Định nghĩa**: Các lớp không nên bị buộc phải triển khai các giao diện mà chúng không sử dụng.
- **Lợi ích**:
  - Giảm sự phụ thuộc không cần thiết.
  - Tăng tính mô-đun và dễ bảo trì.

### 5. Nguyên tắc đảo ngược phụ thuộc (Dependency Inversion Principle - DIP)

- **Định nghĩa**:
  - Các mô-đun cấp cao không nên phụ thuộc vào các mô-đun cấp thấp; cả hai nên phụ thuộc vào các **trừu tượng (abstractions)**.
  - Các trừu tượng không nên phụ thuộc vào chi tiết cụ thể; chi tiết cụ thể nên phụ thuộc vào trừu tượng.
- **Lợi ích**:
  - Giảm sự ràng buộc giữa các thành phần.
  - Tăng khả năng thay thế và mở rộng.

## Ghi chú thêm

- Các nguyên tắc SOLID sẽ được giải thích chi tiết kèm theo **ví dụ mã nguồn (code examples)** trong các phần tiếp theo của khóa học.
- Việc áp dụng SOLID giúp xây dựng phần mềm có cấu trúc rõ ràng, dễ dàng mở rộng và bảo trì trong dài hạn.

---

_Ghi chú được định dạng chuẩn Markdown, tối ưu hóa cho Obsidian để dễ dàng tra cứu và liên kết chéo._
