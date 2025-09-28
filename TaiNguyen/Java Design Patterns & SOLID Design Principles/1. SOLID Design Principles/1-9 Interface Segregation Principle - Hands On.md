## Nguyên Lý Phân Tách Giao Diện (Interface Segregation Principle)

### Giới Thiệu
- Nguyên lý phân tách giao diện (Interface Segregation Principle - ISP) nhấn mạnh rằng các giao diện nên được thiết kế nhỏ, tập trung vào các phương thức liên quan đến một nhóm lớp cụ thể.
- Tránh tạo giao diện lớn chứa nhiều phương thức không áp dụng cho tất cả các lớp triển khai, dẫn đến việc phải cung cấp triển khai giả hoặc ném ngoại lệ không hỗ trợ.
- Trong các dự án thực tế, vi phạm thường xảy ra khi trích xuất giao diện từ lớp triển khai hiện có mà không xem xét tính áp dụng chung.

### Ví Dụ Mã Nguồn
- Lớp cơ sở **Entity**: Lớp cơ sở cho các thực thể JPA (Java Persistence API).
- Thực thể **User**: Chứa thuộc tính tên (name) và thời gian đăng nhập cuối (last login), kèm getter/setter.
- Thực thể **Order**: Chứa các thuộc tính như ID, ngày đặt hàng, giá trị, nhưng không có thuộc tính tên (name).

### Lớp Dịch Vụ Lưu Trữ (Persistence Service)
- **UserPersistenceService**: Xử lý lưu trữ cho thực thể User.
  - Sử dụng Map để lưu trữ các thực thể User.
  - Các phương thức: save (lưu), delete (xóa), findById (tìm theo ID), findByName (tìm theo tên).
- Trích xuất giao diện **PersistenceService** từ UserPersistenceService, chứa tất cả các phương thức trên.

### Vi Phạm ISP Khi Triển Khai Cho Order
- Tạo **OrderPersistenceService** triển khai PersistenceService cho thực thể Order.
  - Sử dụng Map để lưu trữ các thực thể Order.
  - Triển khai save, delete, findById dễ dàng bằng cách sử dụng ID làm khóa.
- Vấn đề: Phương thức findByName không áp dụng cho Order (vì Order không có thuộc tính name).
  - Buộc phải triển khai giả: Ném ngoại lệ UnsupportedOperationException với thông báo "find by name is not supported".
- Đây là vi phạm ISP vì giao diện chứa phương thức không liên quan đến một số lớp triển khai, dẫn đến code thừa và không sạch.

### Giải Pháp Áp Dụng ISP
- Phân tách giao diện: Tách PersistenceService thành các giao diện nhỏ hơn, chỉ chứa phương thức chung (save, delete, findById).
- Di chuyển phương thức cụ thể: Đặt findByName chỉ trong UserPersistenceService, không đưa vào giao diện chung.
  - Người dùng cần phương thức này có thể ép kiểu (cast) hoặc sử dụng tham chiếu trực tiếp đến UserPersistenceService.
- Lợi ích: Các lớp triển khai không bị buộc cung cấp triển khai giả; giao diện trở nên linh hoạt và dễ bảo trì hơn.

### Ghi Chú Thêm
- ISP là nguyên lý đơn giản nhưng mạnh mẽ, thường bị vi phạm ở dự án thực tế do trích xuất giao diện muộn hoặc thêm phương thức không chung.
- Khi thiết kế, đảm bảo mọi phương thức trong giao diện đều áp dụng cho tất cả lớp triển khai để tránh code không cần thiết.