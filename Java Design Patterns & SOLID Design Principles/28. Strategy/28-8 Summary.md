## Tóm Tắt Mẫu Thiết Kế Strategy

### Khái Niệm Chính
- Mẫu thiết kế strategy (Strategy pattern) cho phép đóng gói một thuật toán vào một lớp riêng biệt.
- Lớp sử dụng các thuật toán này, gọi là lớp context (context class), có thể được cấu hình với triển khai thuật toán mong muốn.
- Trách nhiệm cấu hình thuộc về client code: Client sử dụng đối tượng context để chọn và cung cấp triển khai thuật toán phù hợp.

### Cách Truyền Dữ Liệu cho Strategy
- Đối tượng strategy nhận toàn bộ dữ liệu cần thiết từ lớp context, vì context là nơi gọi và thực thi thuật toán.
- Dữ liệu có thể được truyền:
  - Qua đối số (arguments) của phương thức trong strategy.
  - Hoặc context tự truyền chính nó (self-reference) để strategy truy vấn dữ liệu cần thiết.

### Tính Không Trạng Thái (Statelessness) của Strategy
- Đối tượng strategy thường không trạng thái (stateless), vì toàn bộ thông tin được cung cấp qua đối số phương thức.
- Lợi ích: Phù hợp để áp dụng mẫu flyweight (flyweight pattern), nơi:
  - Trạng thái nội tại (intrinsic state): Các hằng số (constants) trong triển khai strategy.
  - Trạng thái ngoại tại (extrinsic state): Dữ liệu được context cung cấp.
- Kết hợp hai trạng thái này giúp thuật toán thực hiện hoạt động mong muốn.

### Nhược Điểm Chính
- Client code phải biết về tất cả các triển khai strategy, dẫn đến ràng buộc chặt chẽ (tight coupling).
- Khi thêm triển khai mới, client code cần thay đổi để tạo và sử dụng đối tượng mới.

### Sơ Đồ UML
- **Context**: Đối tượng mà client sử dụng, chứa tham chiếu đến strategy. Client cung cấp strategy trước khi gọi phương thức `operation()`.
- **Strategy**: Giao diện định nghĩa các phương thức để context thực thi thuật toán.
- **ConcreteStrategy**: Các triển khai cụ thể của Strategy, mỗi cái đại diện cho một thuật toán riêng biệt.
- Quy trình: Client gọi `operation()` trên Context → Context sử dụng Strategy để thực thi thuật toán → Có thể thêm nhiều ConcreteStrategy mà không thay đổi Context.

### Triển Khai Điển Hình trong Java
- Ví dụ: Quản lý bộ sưu tập đơn hàng (collection of orders) và in chúng theo các cách khác nhau.
  - Thuật toán 1: In tóm tắt (summary) – chỉ in số đơn hàng và tổng chi phí.
  - Thuật toán 2: In chi tiết (details) – in toàn bộ thông tin của từng đơn hàng.

#### Giao Diện Strategy: OrderPrinter
- Định nghĩa phương thức `print(collection of orders)`.

#### Lớp Context: PrintService
- Chứa trường `printer` kiểu OrderPrinter.
- Constructor nhận đối tượng OrderPrinter để cấu hình strategy.
- Phương thức `printOrders()` sử dụng strategy để in bộ sưu tập.

#### Concrete Strategies
- **SummaryPrinter**: Triển khai OrderPrinter, chỉ in tóm tắt đơn hàng.
- **DetailPrinter**: Triển khai OrderPrinter, in chi tiết từng đơn hàng.

#### Mã Nguồn Triển Khai
```java
import java.util.List;

// Giả sử lớp Order đơn giản
class Order {
    private int orderNumber;
    private double totalCost;
    // Các thuộc tính khác cho chi tiết...
    
    public Order(int orderNumber, double totalCost) {
        this.orderNumber = orderNumber;
        this.totalCost = totalCost;
    }
    
    // Getters...
    public int getOrderNumber() { return orderNumber; }
    public double getTotalCost() { return totalCost; }
    
    @Override
    public String toString() {
        return "Order " + orderNumber + ": $" + totalCost;
    }
}

// Strategy Interface
interface OrderPrinter {
    void print(List<Order> orders);
}

// Concrete Strategy: Summary Printer
class SummaryPrinter implements OrderPrinter {
    @Override
    public void print(List<Order> orders) {
        System.out.println("Summary of Orders:");
        for (Order order : orders) {
            System.out.println("Order " + order.getOrderNumber() + ": $" + order.getTotalCost());
        }
    }
}

// Concrete Strategy: Detail Printer
class DetailPrinter implements OrderPrinter {
    @Override
    public void print(List<Order> orders) {
        System.out.println("Details of Orders:");
        for (Order order : orders) {
            System.out.println(order.toString()); // Giả sử in chi tiết đầy đủ
        }
    }
}

// Context Class
class PrintService {
    private OrderPrinter printer;
    
    public PrintService(OrderPrinter printer) {
        this.printer = printer;
    }
    
    public void printOrders(List<Order> orders) {
        printer.print(orders);
    }
}

// Sử dụng (Client Code)
public class StrategyDemo {
    public static void main(String[] args) {
        List<Order> orders = List.of(
            new Order(1, 100.0),
            new Order(2, 200.0)
        );
        
        // Cấu hình với SummaryPrinter
        PrintService summaryService = new PrintService(new SummaryPrinter());
        summaryService.printOrders(orders);
        
        // Cấu hình với DetailPrinter
        PrintService detailService = new PrintService(new DetailPrinter());
        detailService.printOrders(orders);
    }
}
```

### Ghi Chú Thêm
- Mẫu strategy giúp tách biệt thuật toán khỏi context, tăng tính linh hoạt và tuân thủ nguyên tắc Open-Closed (mở rộng mà không sửa đổi code).