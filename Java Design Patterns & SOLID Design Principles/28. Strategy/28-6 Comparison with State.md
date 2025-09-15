## So Sánh Mẫu Thiết Kế Strategy và State

### Khái Niệm Chính
- Mẫu **Strategy** (chiến lược): Tập trung vào việc tách biệt các thuật toán khác nhau để giải quyết cùng một vấn đề, cho phép thay đổi hành vi động tại runtime.
- Mẫu **State** (trạng thái): Tập trung vào việc quản lý các trạng thái khác nhau của một đối tượng, với hành vi thay đổi dựa trên trạng thái hiện tại.

### Sự Khác Biệt Về Triển Khai
- **Cấu trúc lớp**:
  - Strategy: Tạo một lớp riêng cho mỗi thuật toán (algorithm). Ví dụ: Với hai thuật toán khác nhau giải quyết cùng vấn đề, tạo hai lớp riêng biệt.
  - State: Tạo một lớp riêng cho mỗi giá trị trạng thái (state value) mà đối tượng có thể có.
- **Mối quan hệ giữa các lớp**:
  - Strategy: Các đối tượng strategy thường không cần biết về nhau, vì chúng là các thuật toán độc lập và không phụ thuộc lẫn nhau.
  - State: Các trạng thái có thể cần biết về nhau, đặc biệt nếu chúng chịu trách nhiệm kích hoạt chuyển tiếp trạng thái (state transitions). Trong trường hợp này, một trạng thái sẽ tạo đối tượng của trạng thái mới và lưu vào context.

### Ghi Chú Thêm
- Cả hai mẫu đều tuân thủ nguyên tắc Open-Closed (mở rộng mà không sửa đổi), nhưng Strategy nhấn mạnh tính linh hoạt của thuật toán, trong khi State nhấn mạnh quản lý hành vi dựa trên trạng thái.
- Sử dụng Strategy khi cần thay đổi chiến lược xử lý; sử dụng State khi hành vi phụ thuộc chặt chẽ vào trạng thái nội tại của đối tượng.