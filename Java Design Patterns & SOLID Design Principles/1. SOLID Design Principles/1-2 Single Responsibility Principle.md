## Nguyên Tắc Trách Nhiệm Đơn Lẻ (Single Responsibility Principle - SRP)

### Định Nghĩa Chính Thức
- SRP quy định rằng một lớp không nên có hơn một lý do để thay đổi.
- Ý nghĩa: Lớp phải cung cấp một chức năng tập trung (focused functionality), chỉ giải quyết một vấn đề cụ thể (specific concern) trong hệ thống.

### Giải Thích Chi Tiết
- Nếu lớp xử lý nhiều trách nhiệm, nó sẽ dễ bị thay đổi vì nhiều lý do khác nhau, dẫn đến mã nguồn khó bảo trì và dễ lỗi.
- Mục tiêu: Khi thiết kế lớp hoặc mô-đun, đảm bảo chỉ có một lý do thay đổi duy nhất, giúp thay đổi được tổ chức một cách có hệ thống.

### Ví Dụ Minh Họa
- Giả sử một lớp duy nhất xử lý việc tạo và gửi tin nhắn đến máy chủ từ xa (remote server) qua một cổng (port).
- Các lý do có thể khiến lớp này thay đổi:
  - Giao thức giao tiếp (communication protocol) thay đổi → Lớp cần chỉnh sửa.
  - Định dạng tin nhắn thay đổi (ví dụ: từ JSON sang XML) → Lớp cần chỉnh sửa.
  - Tham số giao tiếp thay đổi (ví dụ: thêm xác thực bảo mật - authentication) → Lớp cần chỉnh sửa.
- Kết quả: Lớp có nhiều trách nhiệm → Vi phạm SRP, khó bảo trì (phải sửa sâu vào mã lớn).

### Giải Pháp Theo SRP
- Phân tách thành các lớp hoặc mô-đun riêng biệt cho từng trách nhiệm.
  - Nếu có ba trách nhiệm (tạo tin nhắn, định dạng tin nhắn, gửi tin nhắn), tạo ba lớp riêng.
  - Lợi ích: Thay đổi một phần (ví dụ: định dạng từ JSON sang XML) chỉ ảnh hưởng đến lớp liên quan, không cần sửa toàn bộ mã.

### Ghi Chú Thêm
- SRP giúp mã nguồn dễ hiểu, dễ kiểm thử và mở rộng.
- Phần tiếp theo sẽ áp dụng nguyên tắc này vào mã nguồn cụ thể.