## Mẫu Thiết Kế Strategy Pattern (Tiếp Theo)

### Vấn Đề Trong Mã Nguồn Không Sử Dụng Strategy Pattern
- Lớp `PaymentService` có nhiều trách nhiệm (multiple responsibilities):
  - Quyết định loại phương thức thanh toán (credit card, debit card, v.v.).
  - Xử lý logic thanh toán tương ứng.
- Vi phạm nguyên tắc Open-Closed Principle (mở rộng mà không sửa đổi mã hiện có):
  - Thêm phương thức mới (như UPI) yêu cầu chỉnh sửa lớp `PaymentService`.
- Sử dụng chuỗi điều kiện if-else làm mã khó bảo trì:
  - Khi thêm nhiều phương thức, mã trở nên phức tạp và khó mở rộng.

### Giải Pháp Với Strategy Pattern
- **Mục tiêu**: Đóng gói logic xử lý từng phương thức thanh toán vào các lớp strategy riêng biệt.
- `PaymentService` ủy thác (delegate) nhiệm vụ xử lý thanh toán cho strategy tương ứng tại thời điểm chạy (runtime).
- Lợi ích:
  - Tuân thủ Single Responsibility Principle: Mỗi lớp chỉ chịu trách nhiệm một việc.
  - Dễ mở rộng: Thêm phương thức mới mà không sửa mã hiện có.
  - Loại bỏ if-else, tăng tính linh hoạt và đa hình (polymorphism).

### Sơ Đồ Lớp (Class Diagram)
- `PaymentService`: Chứa tham chiếu đến strategy (private field `strategy`).
  - Phương thức: `setPaymentStrategy(PaymentStrategy strategy)` để thiết lập strategy.
  - Phương thức: `pay()` gọi `strategy.processPayment()`.
- Giao diện `PaymentStrategy`: Định nghĩa phương thức trừu tượng `processPayment()`.
- Các lớp cụ thể (concrete strategies) triển khai `PaymentStrategy`:
  - `CreditCardPayment`: Xử lý thanh toán bằng thẻ tín dụng.
  - `DebitCardPayment`: Xử lý thanh toán bằng thẻ ghi nợ.
  - `UPIPayment`: Xử lý thanh toán bằng UPI (có thể thêm sau).

### Triển Khai Mã Nguồn
- Tạo lớp kiểm thử `StrategyPattern.java`.

#### Giao Diện PaymentStrategy
```java
interface PaymentStrategy {
    void processPayment();
}
```

#### Lớp PaymentService
```java
class PaymentService {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay() {
        strategy.processPayment();
    }
}
```

#### Các Lớp Cụ Thể (Concrete Strategies)
- `CreditCardPayment`:
```java
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Making payment via credit card");
    }
}
```

- `DebitCardPayment`:
```java
class DebitCardPayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Making payment via debit card");
    }
}
```

- `UPIPayment` (thêm sau để minh họa mở rộng):
```java
class UPIPayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Making payment via UPI");
    }
}
```

#### Phương Thức Main Để Kiểm Thử
```java
public class StrategyPattern {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        
        // Sử dụng thẻ tín dụng
        paymentService.setPaymentStrategy(new CreditCardPayment());
        paymentService.pay();  // Output: Making payment via credit card
        
        // Sử dụng thẻ ghi nợ
        paymentService.setPaymentStrategy(new DebitCardPayment());
        paymentService.pay();  // Output: Making payment via debit card
        
        // Thêm UPI mà không sửa mã hiện có
        paymentService.setPaymentStrategy(new UPIPayment());
        paymentService.pay();  // Output: Making payment via UPI
    }
}
```

### Ghi Chú Thêm
- **Đa hình (Polymorphism)**: Phương thức `pay()` gọi `processPayment()` trên giao diện, nhưng thực thi logic của lớp cụ thể tại runtime.
- **Mở rộng dễ dàng**: Để thêm phương thức mới (ví dụ: PayPal), chỉ cần tạo lớp mới triển khai `PaymentStrategy` và truyền đối tượng vào `setPaymentStrategy()`.
- **Kết quả**: Giải quyết toàn bộ vấn đề ban đầu, mã nguồn sạch sẽ, dễ bảo trì và kiểm thử.