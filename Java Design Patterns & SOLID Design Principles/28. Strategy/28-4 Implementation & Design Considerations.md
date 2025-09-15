## Mẫu Thiết Kế Strategy: Các Xem Xét Triển Khai và Thiết Kế

### Lớp Context với Strategy Tùy Chọn
- Triển khai lớp context (lớp chính) sao cho đối tượng strategy là tùy chọn.
- Sử dụng thuật toán mặc định (default algorithm) trong lớp context nếu không cung cấp strategy.
- Lợi ích: Giải quyết vấn đề client code không muốn xử lý trực tiếp các đối tượng strategy cụ thể, tránh tình trạng client bị ràng buộc chặt chẽ (tightly coupled) với các triển khai concrete của strategy.
- Cách thức:
  - Client thường cung cấp đối tượng strategy cụ thể cho context, dẫn đến client phải biết tất cả các triển khai có thể.
  - Để phá vỡ ràng buộc: Đặt triển khai mặc định trong context.
  - Nếu không có strategy, sử dụng mặc định; nếu có, sử dụng logic từ strategy đó.

### Xử Lý Đối Số Đầu Vào cho Strategy
- Các đối tượng strategy thường nhận toàn bộ thông tin cần thiết qua đối số (arguments) của phương thức.
- Nếu số lượng đối số lớn, dẫn đến phương thức dài và phức tạp:
  - Thiết kế strategy sử dụng một interface để lấy dữ liệu.
  - Lớp context triển khai interface này, cung cấp các phương thức để strategy truy xuất thông tin.
- Lợi ích: Đơn giản hóa phương thức, tránh làm chúng dài dòng do cần nhiều thông tin.

### Tính Không Trạng Thái (Statelessness) của Đối Tượng Strategy
- Tương tự mẫu state pattern, các đối tượng strategy thường không trạng thái (stateless).
- Chúng nhận toàn bộ thông tin qua đối số phương thức.
- Lợi ích: Phù hợp để chia sẻ (sharing) giữa nhiều đối tượng context, giảm tài nguyên và tăng hiệu quả.

### Sử Dụng Kế Thừa (Inheritance) trong Triển Khai Strategy
- Các triển khai strategy có thể sử dụng kế thừa để đơn giản hóa việc tạo thuật toán mới hoặc đối tượng con.
- Cách thức:
  - Trích xuất chức năng chung (common functionality) từ tất cả các thuật toán, đặt vào một hoặc nhiều lớp cơ sở (base classes).
  - Định nghĩa các hoạt động chung trong lớp cơ sở để các lớp con dễ triển khai hơn.

### Kết Hợp với Mẫu Flyweight để Chia Sẻ Strategy
- Vì strategy stateless, có thể áp dụng mẫu flyweight design pattern.
- Lợi ích: Chia sẻ đối tượng strategy giữa nhiều context objects, tối ưu hóa bộ nhớ và hiệu suất.

### Ghi Chú Thêm
- Các điểm trên cần lưu ý khi làm việc với mẫu strategy design pattern để đảm bảo thiết kế linh hoạt, dễ bảo trì và mở rộng.