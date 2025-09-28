## Các Bước Triển Khai Mẫu Thiết Kế Strategy

### Giới Thiệu
- Đây là chuỗi các bước điển hình để triển khai mẫu thiết kế Strategy, giúp đóng gói các thuật toán khác nhau và cấu hình linh hoạt cho lớp chính (context).

### Các Bước Chính
- **Bước 1: Tạo Strategy Interface**
  - Interface này định nghĩa các phương thức chung cho các thuật toán giải quyết một vấn đề cụ thể.
  - Các implementation của interface sẽ là các thuật toán khác nhau.
  - Interface được sử dụng bởi lớp context (lớp chính), nơi context cung cấp dữ liệu hoặc thông tin cần thiết cho strategy.
  - Lưu ý: Strategy không nên có state (trạng thái) hoặc lưu trữ input; context sẽ cung cấp toàn bộ dữ liệu khi gọi.

- **Bước 2: Triển Khai Các Implementation**
  - Mỗi implementation là một thuật toán riêng biệt.
  - Tạo một lớp riêng cho mỗi thuật toán, triển khai strategy interface.
  - Mỗi lớp đại diện cho một biến thể thuật toán, giữ code sạch và dễ mở rộng.

- **Bước 3: Cấu Hình Context Với Strategy**
  - Lớp context cung cấp phương thức để client cấu hình strategy object cụ thể.
  - Client quyết định implementation nào sử dụng (ví dụ: qua constructor hoặc setter method).
  - Context không tự quyết định strategy; điều này đảm bảo tính linh hoạt và tuân thủ nguyên lý đảo ngược phụ thuộc (Dependency Inversion Principle).

### Ghi Chú Thêm
- Quy trình này giúp loại bỏ các câu lệnh điều kiện (if-else) trong context, thay bằng cách chọn strategy động tại runtime.
- Áp dụng khi có nhiều cách giải quyết một vấn đề, tăng khả năng tái sử dụng và bảo trì code.