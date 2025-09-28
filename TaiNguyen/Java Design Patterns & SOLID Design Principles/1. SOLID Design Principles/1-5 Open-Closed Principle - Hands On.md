## Ví Dụ Về Nguyên Tắc Open-Closed (OCP) Trong Java

### Giới Thiệu Ví Dụ
- Ví dụ này được thiết kế đơn giản để minh họa nguyên tắc Open-Closed (OCP), dựa hoàn toàn trên kế thừa (inheritance) trong lập trình hướng đối tượng (Object-Oriented Programming - OOP).
- Bối cảnh: Giả sử phát triển hệ thống cho một công ty điện thoại, sử dụng Java để theo dõi khách hàng và nhà đăng ký (subscribers) cho các dịch vụ như điện thoại, internet.
- Không cần mã phức tạp hoặc test đa dạng; tập trung vào việc mở rộng hành vi mà không sửa đổi lớp cơ sở.

### Các Lớp Ban Đầu (Trước Khi Áp Dụng OCP)
- **Lớp `PhoneSubscriber`**:
  - Đại diện cho nhà đăng ký dịch vụ điện thoại.
  - Thuộc tính: `subscriberId` (ID duy nhất), `address` (địa chỉ), `phoneNumber` (số điện thoại), `baseRate` (phí cơ bản dùng để tính hóa đơn).
  - Phương thức chính: `calculateBill()` – triển khai đơn giản để tính hóa đơn dựa trên phí cơ bản (dùng cho demo, không liên quan đến billing engine thực tế).
  - Các phương thức khác: Getter và setter đơn giản cho thuộc tính.
- **Lớp `ISPSubscriber`**:
  - Đại diện cho nhà đăng ký dịch vụ internet.
  - Thuộc tính: `subscriberId`, `address`, `phoneNumber`, `baseRate`, `allocatedData` (dung lượng dữ liệu được phân bổ).
  - Phương thức chính: `calculateBill()` – triển khai đơn giản dựa trên dữ liệu sử dụng và dung lượng miễn phí (nếu vượt quá, tính phí thêm; dùng cho demo).
  - Các phương thức khác: Getter và setter.
- **Các lớp hỗ trợ**:
  - `InternetSessionHistory` và `CallHistory`: Lưu trữ lịch sử sử dụng dịch vụ internet và cuộc gọi (dùng như data store demo, không phải triển khai đầy đủ).

### Vấn Đề Trong Thiết Kế Ban Đầu
- **Trùng lặp mã (code duplication)**: Các lớp `PhoneSubscriber` và `ISPSubscriber` chia sẻ thuộc tính chung như `subscriberId`, `address`, `phoneNumber`, `baseRate`.
- **Khó mở rộng**: Nếu công ty thêm dịch vụ mới (ví dụ: VoIP), cần tạo lớp mới với mã trùng lặp, dẫn đến sửa đổi lớp cơ sở hoặc thêm thuộc tính riêng (như thời gian miễn phí cho dịch vụ khác).
- **Vi phạm OCP**: Phải sửa đổi lớp hiện có để thêm tính năng mới, thay vì mở rộng qua kế thừa.

### Cách Áp Dụng OCP Để Khắc Phục
- **Bước 1: Xác định phần không thay đổi**:
  - Các thuộc tính chung (`subscriberId`, `address`, `phoneNumber`, `baseRate`) và nhu cầu tính hóa đơn dựa trên loại dịch vụ.
- **Bước 2: Tạo lớp cơ sở trừu tượng `SubscriptionBase`**:
  - Đánh dấu là `abstract` để không thể tạo đối tượng trực tiếp.
  - Thuộc tính chung: `subscriberId`, `address`, `phoneNumber`, `baseRate`.
  - Phương thức: Getter/setter cho thuộc tính chung.
  - Phương thức trừu tượng: `calculateBill()` – buộc các lớp con phải triển khai, đảm bảo tính hóa đơn phù hợp với từng loại dịch vụ.
- **Mã nguồn lớp cơ sở**:
  ```java
  public abstract class SubscriptionBase {
      protected String subscriberId;
      protected String address;
      protected String phoneNumber;
      protected double baseRate;

      // Getter và setter cho các thuộc tính chung
      public String getSubscriberId() { return subscriberId; }
      public void setSubscriberId(String subscriberId) { this.subscriberId = subscriberId; }
      public String getAddress() { return address; }
      public void setAddress(String address) { this.address = address; }
      public String getPhoneNumber() { return phoneNumber; }
      public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
      public double getBaseRate() { return baseRate; }
      public void setBaseRate(double baseRate) { this.baseRate = baseRate; }

      // Phương thức trừu tượng để mở rộng
      public abstract double calculateBill();
  }
  ```

- **Bước 3: Refactor lớp con**:
  - **Lớp `PhoneSubscriber` kế thừa từ `SubscriptionBase`**:
    - Xóa thuộc tính chung (vì đã có ở lớp cha).
    - Triển khai `@Override` cho `calculateBill()` với logic tính hóa đơn điện thoại (dựa trên `baseRate`).
    - Sử dụng `subscriberId` từ lớp cha.
  - **Mã nguồn ví dụ**:
    ```java
    public class PhoneSubscriber extends SubscriptionBase {
        @Override
        public double calculateBill() {
            // Triển khai logic tính hóa đơn điện thoại đơn giản
            return baseRate;  // Demo: chỉ dùng phí cơ bản
        }
    }
    ```
  - **Lớp `ISPSubscriber` kế thừa từ `SubscriptionBase`**:
    - Xóa thuộc tính chung.
    - Giữ `allocatedData` làm thuộc tính riêng.
    - Triển khai `@Override` cho `calculateBill()` với logic tính hóa đơn internet (dựa trên dữ liệu sử dụng và dung lượng miễn phí).
  - **Mã nguồn ví dụ**:
    ```java
    public class ISPSubscriber extends SubscriptionBase {
        private double allocatedData;

        public double getAllocatedData() { return allocatedData; }
        public void setAllocatedData(double allocatedData) { this.allocatedData = allocatedData; }

        @Override
        public double calculateBill() {
            // Triển khai logic tính hóa đơn internet đơn giản
            // Ví dụ: Nếu vượt quá allocatedData, tính phí thêm
            return baseRate;  // Demo: chỉ dùng phí cơ bản
        }
    }
    ```

- **Bước 4: Mở rộng cho dịch vụ mới (ví dụ: VoIP)**:
  - Tạo lớp mới `VoipSubscriber` kế thừa từ `SubscriptionBase`.
  - Triển khai `calculateBill()` với logic riêng (ví dụ: dựa trên thời gian gọi miễn phí).
  - Không cần sửa đổi lớp cơ sở hoặc lớp hiện có.
  - **Mã nguồn ví dụ**:
    ```java
    public class VoipSubscriber extends SubscriptionBase {
        private double freeCallMinutes;

        public double getFreeCallMinutes() { return freeCallMinutes; }
        public void setFreeCallMinutes(double freeCallMinutes) { this.freeCallMinutes = freeCallMinutes; }

        @Override
        public double calculateBill() {
            // Triển khai logic tính hóa đơn VoIP đơn giản
            return baseRate;  // Demo: chỉ dùng phí cơ bản
        }
    }
    ```

### Lợi Ích Của Việc Áp Dụng OCP Trong Ví Dụ
- **Đóng cho sửa đổi**: Lớp cơ sở `SubscriptionBase` không thay đổi sau khi tạo; chỉ chứa phần ổn định và đã kiểm thử.
- **Mở cho mở rộng**: Thêm dịch vụ mới bằng cách tạo lớp con và ghi đè (override) phương thức trừu tượng, không ảnh hưởng đến mã cũ.
- **Giảm trùng lặp**: Thuộc tính chung nằm ở lớp cơ sở, dễ bảo trì.
- **Tính linh hoạt**: Mỗi lớp con chỉ thay đổi khi logic tính hóa đơn của dịch vụ đó thay đổi.

### Ghi Chú Thêm
- OCP khuyến khích xác định phần "không thay đổi" (thuộc tính chung) và phần "có thể thay đổi" (hành vi tính hóa đơn), sử dụng phương thức trừu tượng để hỗ trợ mở rộng.
- Trong thực tế, có thể kết hợp với interface để tăng tính trừu tượng hơn nữa.