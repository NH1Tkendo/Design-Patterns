## Nguyên Lý Đảo Ngược Phụ Thuộc (Dependency Inversion Principle)

### Giới Thiệu
- Đây là nguyên lý cuối cùng trong nhóm SOLID Design Principles, được công bố bởi Robert Martin.
- Nếu sử dụng framework như Spring, bạn đã áp dụng nguyên lý này một cách gián tiếp.
- Nguyên lý gồm hai phần chính:
  - Các module cấp cao (high-level modules) không nên phụ thuộc vào module cấp thấp (low-level modules). Cả hai nên phụ thuộc vào abstraction (trừu tượng).
  - Abstraction không nên phụ thuộc vào chi tiết cụ thể (details). Chi tiết cụ thể nên phụ thuộc vào abstraction.

### Khái Niệm Phụ Thuộc (Dependency)
- Phụ thuộc là một đối tượng hoặc module cần thiết để cung cấp chức năng cho code đang viết.
- Ví dụ đơn giản trong Java:
  - Để in ra console: Sử dụng `System.out.println("string")` → Phụ thuộc vào đối tượng `out` trong lớp `System`.
- Ví dụ phức tạp hơn:
  - Phương thức tạo báo cáo JSON và ghi vào đĩa:
    - Phụ thuộc vào đối tượng chuyển đổi báo cáo thành JSON (ví dụ: ObjectMapper).
    - Phụ thuộc vào lớp ghi file (ví dụ: FileWriter từ gói `java.io`).

### Vấn Đề Thường Gặp Với Phụ Thuộc Trực Tiếp
- Thường tạo instance trực tiếp trong phương thức (ví dụ: `new FileWriter()`, `new ObjectMapper()`).
- Dẫn đến tight coupling (kết nối chặt chẽ) với implementation cụ thể.
- Hậu quả:
  - Nếu yêu cầu thay đổi (ví dụ: xuất báo cáo HTML thay vì JSON, hoặc gửi báo cáo qua URL thay vì ghi đĩa), phải sửa trực tiếp code → Giới thiệu bug mới, vi phạm nguyên lý mở rộng (Open/Closed Principle).
- Module cấp cao: Triển khai business rules (quy tắc kinh doanh).
- Module cấp thấp: Chức năng cơ bản, có thể dùng ở nhiều nơi (ví dụ: ghi đĩa, chuyển đổi JSON).

### Giải Pháp Áp Dụng DIP
- Không kết nối chặt chẽ với lớp cụ thể; cả hai cấp độ phụ thuộc vào abstraction (thường là interface).
- Ví dụ abstraction:
  - Interface `Writer`: Để ghi dữ liệu (thay vì `FileWriter` cụ thể).
  - Interface `Formatter`: Để định dạng dữ liệu (thay vì ObjectMapper cụ thể).
- Thay đổi phương thức:
  - Không tạo instance bên trong; nhận tham số là interface (ví dụ: `generateReport(Formatter formatter, Writer writer)`).
  - Sử dụng interface trong toàn bộ code phương thức.
- Lợi ích:
  - Code không còn tight coupling với lớp cụ thể.
  - Người dùng có thể truyền implementation khác (ví dụ: `HtmlFormatter` cho HTML, `UrlWriter` để gửi qua server) → Linh hoạt, dễ mở rộng mà không sửa code gốc.
- Ý tưởng cốt lõi: Không tự tạo dependency; để người khác (hoặc framework như Spring) cung cấp dependency qua injection.

### Ghi Chú Thêm
- Nguyên lý này dễ hiểu qua ví dụ thực tế, nhưng thường khó với người mới vì nhầm lẫn về khái niệm dependency.
- Trong bài giảng tiếp theo, sẽ xem xét code minh họa tương tự ví dụ tạo báo cáo để củng cố DIP.