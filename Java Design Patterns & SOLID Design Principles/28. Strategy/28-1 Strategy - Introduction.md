## Mẫu Thiết Kế Strategy (Strategy Design Pattern)

### Giới Thiệu
- Mẫu thiết kế Strategy là một mẫu đơn giản, giúp đóng gói (encapsulate) một thuật toán vào một lớp riêng biệt.
- Cho phép cấu hình lớp chính (context) với đối tượng của lớp đó để sử dụng thuật toán trong các hoạt động cụ thể.
- Hữu ích khi có nhiều thuật toán có thể giải quyết cùng một vấn đề, giúp thay đổi hành vi mà không sửa đổi code chính.

### Mục Đích Và Ứng Dụng
- Đóng gói các thuật toán khác nhau vào các lớp riêng, sau đó truyền đối tượng của lớp đó vào context để thực thi.
- Không giới hạn ở sắp xếp (sorting) hoặc tìm kiếm (searching); áp dụng cho bất kỳ vấn đề nào có nhiều cách giải quyết.
- Ví dụ: Với danh sách số nguyên, có thể sử dụng bubble sort, insertion sort, merge sort – mỗi thuật toán trong một lớp riêng, truyền vào context để sắp xếp.

### Khi Nào Sử Dụng
- Kiểm tra code: Nếu thấy nhiều câu lệnh điều kiện (if-else, switch-case) thực hiện các thuật toán khác nhau dựa trên điều kiện, thì Strategy phù hợp để loại bỏ sự phức tạp.
- Giải quyết vấn đề: Thay thế các điều kiện bằng cách chọn strategy động tại runtime.

### Cấu Trúc Triển Khai
- Thường sử dụng hệ thống kế thừa (inheritance hierarchy).
- Bắt đầu bằng một interface hoặc lớp trừu tượng (abstract class) định nghĩa phương thức chung cho thuật toán.
- Triển khai nhiều lớp con (child classes) cho từng biến thể thuật toán, sử dụng interface đó.

### Sơ Đồ UML
- **Context**: Lớp chính mà client tương tác. Client gọi phương thức `operation()` trên context.
  - Context chứa một tham chiếu đến đối tượng Strategy bên trong.
- **Strategy**: Interface trừu tượng định nghĩa phương thức chung (ví dụ: `runAlgorithm()` hoặc `execute()`).
- **ConcreteStrategy**: Các lớp triển khai cụ thể của Strategy, mỗi lớp đại diện cho một thuật toán khác nhau.
  - Context sử dụng Strategy qua interface để thực thi thuật toán, không phụ thuộc vào implementation cụ thể.

### Ghi Chú Thêm
- Đây là mẫu đơn giản, dễ áp dụng sau khi học các mẫu phức tạp hơn như State.
- Lợi ích chính: Tăng tính linh hoạt, dễ mở rộng (thêm strategy mới mà không sửa context), và tuân thủ nguyên lý mở rộng (Open/Closed Principle).