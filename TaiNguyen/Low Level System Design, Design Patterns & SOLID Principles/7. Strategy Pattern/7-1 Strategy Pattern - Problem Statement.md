# Ghi chú học tập: Mẫu thiết kế Chiến lược (Strategy Pattern)

## Tổng quan về Mẫu thiết kế Chiến lược

### Động lực (Motivation Problem)

- **Tình huống**: Xây dựng một hệ thống thanh toán hỗ trợ nhiều phương thức thanh toán khác nhau, ví dụ:
  - Thẻ tín dụng (Credit Card).
  - PayPal.
  - Thanh toán UPI (Unified Payments Interface).
- **Vấn đề khi không sử dụng mẫu thiết kế Chiến lược**:
  - Sử dụng các câu lệnh điều kiện (`if-else`) để kiểm tra loại phương thức thanh toán.
  - Dẫn đến mã nguồn:
    - **Khó bảo trì (less maintainable)**: Thêm phương thức thanh toán mới yêu cầu sửa đổi mã hiện có.
    - **Thiếu linh hoạt (less flexible)**: Khó mở rộng khi có phương thức mới.
    - **Vi phạm nguyên tắc mở/đóng (Open/Closed Principle - OCP)**: Mã hiện tại phải được chỉnh sửa để hỗ trợ phương thức mới.

## Ví dụ mã nguồn không sử dụng Chiến lược

### Mô tả mã

- **Lớp**: `PaymentService` (Dịch vụ thanh toán).
- **Phương thức**: `processPayment(String paymentMethod)` - Xử lý thanh toán dựa trên loại phương thức thanh toán.

### Mã nguồn

```java
public class WithoutStrategyPattern {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        paymentService.processPayment("debit card");
        paymentService.processPayment("credit card");
        paymentService.processPayment("UPI"); // Không được hỗ trợ
    }
}

class PaymentService {
    public void processPayment(String paymentMethod) {
        if (paymentMethod.equals("credit card")) {
            System.out.println("Making payment via credit card");
        } else if (paymentMethod.equals("debit card")) {
            System.out.println("Making payment via debit card");
        } else {
            System.out.println("Unsupported payment method");
        }
    }
}
```

### Phân tích vấn đề

- **Kết quả chạy mã**:
  - `paymentService.processPayment("debit card")`: In ra "Making payment via debit card".
  - `paymentService.processPayment("credit card")`: In ra "Making payment via credit card".
  - `paymentService.processPayment("UPI")`: In ra "Unsupported payment method".
- **Hạn chế**:
  - Khi thêm phương thức thanh toán mới (như UPI), cần sửa đổi lớp `PaymentService`:
    - Thêm câu lệnh `if-else` mới.
    - Có thể cần thêm một thuật toán phức tạp để xử lý phương thức mới (nhiều dòng mã).
  - Việc sửa đổi mã đã được kiểm thử vi phạm **nguyên tắc mở/đóng (OCP)**.
  - Mã trở nên phức tạp và khó bảo trì khi số lượng phương thức thanh toán tăng.

## Vai trò của Mẫu thiết kế Chiến lược

- **Mục tiêu**: Khắc phục các hạn chế trên bằng cách:
  - Tách biệt logic xử lý của từng phương thức thanh toán.
  - Cho phép mở rộng mà không cần sửa đổi mã hiện có.
  - Tuân thủ **nguyên tắc mở/đóng (OCP)**.
- **Cách tiếp cận**: Sử dụng mẫu thiết kế Chiến lược để định nghĩa một tập hợp các thuật toán (các phương thức thanh toán), đóng gói chúng và cho phép thay đổi linh hoạt.

## Ghi chú thêm

- Nội dung chi tiết về cách triển khai mẫu thiết kế Chiến lược sẽ được trình bày trong phần tiếp theo.
- Mẫu Chiến lược giúp:
  - Tăng tính **mô-đun (modularity)** của mã nguồn.
  - Dễ dàng thêm các phương thức thanh toán mới mà không ảnh hưởng đến mã hiện có.
  - Tăng khả năng **bảo trì** và **mở rộng** của hệ thống.

---

_Ghi chú được định dạng chuẩn Markdown, tối ưu hóa cho Obsidian để dễ dàng tra cứu và liên kết chéo._
