## Mẫu Thiết Kế Strategy: Các Bẫy Thường Gặp

### Các Bẫy Chính
- Mẫu thiết kế strategy có một bẫy đáng kể duy nhất, liên quan đến sự phụ thuộc giữa client code và các triển khai strategy.

### Mô Tả Bẫy
- **Ràng buộc chặt chẽ (Tight Coupling)**: Client code thường cấu hình đối tượng context với strategy phù hợp, dẫn đến client phải biết về tất cả các triển khai strategy có sẵn. Client sử dụng logic để chọn và cấu hình một triển khai cụ thể cho context.
- **Vấn đề khi mở rộng**: Nếu thêm thuật toán mới (tạo triển khai strategy mới), client code phải thay đổi để hỗ trợ lựa chọn và cấu hình.

### Ghi Chú Thêm
- Bẫy này làm giảm tính linh hoạt, vi phạm nguyên tắc Open-Closed (mở rộng mà không sửa đổi code hiện có). Để giảm thiểu, có thể sử dụng factory pattern để ẩn chi tiết triển khai khỏi client.