## Nguyên Tắc Phân Tách Giao Diện (Interface Segregation Principle - ISP) Trong SOLID

### Khái Niệm Chính
- **Định nghĩa**: Nguyên tắc ISP (nguyên tắc thứ tư trong SOLID) quy định rằng các client không nên phụ thuộc vào các giao diện (interfaces) mà chúng không sử dụng. Cụ thể, client không nên bị buộc phải triển khai các phương thức không liên quan.
- **Mục tiêu**: Tránh "ô nhiễm giao diện (interface pollution)" – tình trạng nhồi nhét nhiều phương thức không liên quan vào một giao diện lớn, buộc các lớp phải triển khai toàn bộ dù chỉ cần một phần.
- **Dấu hiệu vi phạm**:
  - Các phương thức rỗng (empty methods).
  - Các phương thức ném ngoại lệ `UnsupportedOperationException`.
  - Các phương thức không làm gì hoặc trả về `null`.
- **Lợi ích**: Các giao diện nhỏ, tập trung (cohesive), chỉ chứa các phương thức liên quan, giúp giảm sự phụ thuộc không cần thiết và tăng tính bảo trì.

### Cách Áp Dụng Và Khắc Phục Vi Phạm
- Phân tách giao diện lớn thành các giao diện nhỏ hơn, mỗi giao diện chỉ định nghĩa các hành vi (methods) liên quan chặt chẽ.
- Mỗi lớp chỉ triển khai giao diện phù hợp, tránh triển khai các phương thức không có ý nghĩa.
- **Quy trình**:
  - Xác định các nhóm phương thức liên quan.
  - Tạo giao diện riêng cho từng nhóm.
  - Lớp triển khai nhiều giao diện nếu cần (composition over inheritance).

### Ví Dụ Minh Họa
- Sẽ được trình bày qua mã Java cụ thể trong phần tiếp theo để minh họa vi phạm và cách refactor để tuân thủ ISP.

### Ghi Chú Thêm
- ISP khuyến khích thiết kế giao diện "mỏng" (thin interfaces) để tăng tính linh hoạt và giảm độ phức tạp.
- Kết hợp tốt với nguyên tắc Open-Closed (OCP) để hỗ trợ mở rộng mà không ảnh hưởng đến client hiện có.