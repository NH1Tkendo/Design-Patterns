## Ví Dục Về Nguyên Tắc Trách Nhiệm Đơn Lẻ (Single Responsibility Principle - SRP)

### Giới Thiệu Ví Dụ
- Đây là một ứng dụng đơn giản mô phỏng lớp điều khiển (controller) trong ứng dụng web MVC hoặc REST web service (ví dụ: sử dụng Spring).
- Tập trung vào lớp `UserController`, nhận yêu cầu từ client (ở đây là gọi phương thức), xử lý và trả về phản hồi.
- Phương thức `createUser` nhận chuỗi JSON chứa thông tin user, tạo đối tượng `User` (có 3 thuộc tính cơ bản), và trả về chuỗi phản hồi ("success" nếu thành công, "error" nếu thất bại).
- Lớp `Store` mô phỏng hệ thống lưu trữ (sử dụng `HashMap` thay vì cơ sở dữ liệu thực tế như JPA).
- Lớp `Main` dùng để kiểm tra `UserController`.

### Chạy Và Kiểm Tra Ví Dụ Ban Đầu
- Chạy phương thức `main` trong lớp `Main`:
  - Với JSON hợp lệ: Trả về "success".
  - Với JSON không hợp lệ: Trả về "error".
- Ví dụ hoạt động cơ bản để minh họa vấn đề.

### Phân Tích Vi Phạm SRP Trong `UserController`
- **Trách nhiệm của controller trong MVC**: Chỉ nhận yêu cầu từ client, chuyển giao cho phần còn lại của ứng dụng, nhận kết quả và gửi phản hồi. Không chứa logic nghiệp vụ (business logic).
- **Vấn đề hiện tại**:
  - `UserController` thực hiện nhiều nhiệm vụ: Phân tích JSON thành đối tượng `User`, xác thực (validation) đối tượng, và lưu trữ đối tượng vào `HashMap` (store).
- **Hậu quả vi phạm SRP**:
  - Nhiều lý do thay đổi lớp: 
    - Thay đổi logic xác thực (validation rules) hoặc thêm trường mới trong `User`.
    - Thay đổi cách lưu trữ (từ `HashMap` sang cơ sở dữ liệu SQL hoặc NoSQL như MongoDB).
    - Thay đổi cách nhận yêu cầu gốc.
  - Dẫn đến lớp dễ bị lỗi và khó bảo trì.

### Cách Khắc Phục Vi Phạm SRP
- **Bước 1: Chuẩn bị test case**:
  - Sử dụng phương thức `main` làm test (trong thực tế: sử dụng JUnit).
  - Chạy test trước và sau refactor để đảm bảo không giới thiệu lỗi mới.
- **Bước 2: Tách trách nhiệm xác thực (validation)**:
  - Tạo lớp mới `UserValidator` với phương thức `isValidUser(User user)` trả về `boolean`.
  - Di chuyển tất cả logic xác thực từ `UserController` sang `UserValidator` (gọi `return isValidUser(user)`).
  - Trong `UserController`: Tạo đối tượng `UserValidator`, gọi `validate(user)` và lưu kết quả vào biến `valid`. Nếu `!valid`, trả về lỗi.
- **Kiểm tra sau refactor**:
  - Chạy `main`: Kết quả giống ban đầu (success với JSON hợp lệ, error với không hợp lệ).
  - Lợi ích: `UserValidator` chỉ thay đổi khi logic xác thực thay đổi; không ảnh hưởng bởi thay đổi lưu trữ.
- **Bước 3: Tách trách nhiệm lưu trữ (persistence)**:
  - Tạo lớp mới `UserPersistenceService` với phương thức `saveUser(User user)`.
  - Trong `saveUser`: Tạo đối tượng `Store` và gọi `store.store(user)`.
  - Trong `UserController`: Tạo đối tượng `UserPersistenceService persistenceService`, gọi `persistenceService.saveUser(user)` thay vì trực tiếp dùng `Store`.
- **Kiểm tra sau refactor cuối**:
  - Chạy `main`: Test vẫn pass.
  - Lợi ích: `UserPersistenceService` chỉ thay đổi khi cách lưu trữ thay đổi; `UserController` không bị ảnh hưởng.
- **Ghi chú thêm**:
  - `UserController` giờ chỉ nhận yêu cầu và phân tích JSON (có thể tách thêm vào lớp riêng nếu cần).
  - Trong ứng dụng Spring thực tế: `UserPersistenceService` và `UserValidator` được inject tự động vào controller bean, không cần tạo thủ công.

### Kết Luận
- Quy trình kiểm tra SRP: Hỏi "Lớp này làm gì? Có nhiều lý do thay đổi không?".
- Refactor bằng cách tách từng trách nhiệm thừa vào lớp riêng, giữ test để đảm bảo tính đúng đắn.
- Áp dụng SRP giúp mã dễ bảo trì, mở rộng và giảm lỗi.