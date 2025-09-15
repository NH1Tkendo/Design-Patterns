## Nguyên Tắc Open-Closed (OCP) Trong SOLID

### Định Nghĩa Chính Thức
- **Nguyên tắc Open-Closed (Open-Closed Principle - OCP)**: Các thực thể phần mềm (software entities) – bao gồm lớp (classes), mô-đun (modules) hoặc phương thức (methods) – phải **mở cho mở rộng (open for extension)** nhưng **đóng cho sửa đổi (closed for modification)**.
- Ý nghĩa: Cho phép mở rộng hành vi hiện có mà không cần thay đổi mã nguồn đã viết.

### Giải Thích Chi Tiết
- **Mở cho mở rộng**: Có thể thêm hoặc thay đổi hành vi mà không ảnh hưởng đến mã gốc.
- **Đóng cho sửa đổi**: Không được chỉnh sửa mã đã viết, đặc biệt là mã đã được kiểm thử và ổn định.
- **Áp dụng trong lập trình hướng đối tượng (Object-Oriented Programming - OOP)**:
  - Sử dụng **kế thừa (inheritance)** để mở rộng lớp cơ sở (base class).
  - **Ghi đè phương thức (override)** để tùy chỉnh hành vi mà không thay đổi lớp gốc.
- Mục tiêu: Tránh sửa đổi lớp cơ sở để giảm rủi ro lỗi và duy trì tính ổn định của mã đã kiểm thử.

### Lợi Ích Và Nguyên Tắc Áp Dụng
- Khuyến khích sử dụng kế thừa và ghi đè thay vì chỉnh sửa trực tiếp lớp cơ sở.
- Giúp mã dễ mở rộng, bảo trì và tái sử dụng.
- Trong Java: Mở rộng lớp đã viết bằng cách tạo lớp con và ghi đè phương thức cần thiết.

### Ví Dụ Minh Họa
- Sẽ được trình bày qua mã Java cụ thể trong phần tiếp theo để làm rõ cách áp dụng OCP.