## Triển Khai Mẫu Thiết Kế Strategy Trong Java

### Giới Thiệu
- Ví dụ minh họa việc triển khai mẫu thiết kế Strategy qua Eclipse, sử dụng lớp `Order` để quản lý đơn hàng.
- Lớp `PrintService` đóng vai trò là context, được cấu hình với các strategy khác nhau để in đơn hàng theo cách tóm tắt hoặc chi tiết.
- Mục tiêu: Thay đổi thuật toán in (algorithm) mà không sửa đổi context, dựa trên lựa chọn từ client.

### Các Lớp Hỗ Trợ
- **Lớp Order**: Đại diện cho đơn hàng trong ứng dụng.
  - Các trường: `orderId` (ID đơn hàng), `date` (ngày đặt hàng), `items` (Map chứa các mặt hàng), `total` (tổng chi phí).
  - Sử dụng để tạo dữ liệu test trong client.

- **Lớp Client**: Chứa collection tĩnh của các đơn hàng (LinkedList<Order>).
  - Phương thức `main()` gọi `createOrders()` để tạo dữ liệu test (3 đơn hàng với các mặt hàng mẫu).
  - Yêu cầu in collection đơn hàng qua `PrintService`.

### Interface Strategy
- **OrderPrinter**: Interface đại diện cho strategy, định nghĩa phương thức `print(Collection<Order> orders)`.
  - Các implementation sẽ triển khai thuật toán in khác nhau.
  - Lưu ý: Thuật toán ở đây là logic in đơn hàng (không chỉ sorting/searching), nhận input từ context và sản xuất output.

### Triển Khai Concrete Strategy
- **SummaryPrinter**: Triển khai `OrderPrinter`, in tóm tắt đơn hàng.
  ```java
  public class SummaryPrinter implements OrderPrinter {
      @Override
      public void print(Collection<Order> orders) {
          System.out.println("=== SUMMARY REPORT ===");
          Iterator<Order> iterator = orders.iterator();
          double total = 0;
          int index = 1;
          for (; iterator.hasNext(); index++) {
              Order order = iterator.next();
              System.out.println(index + ". " + order.getId() + "\t" + order.getDate() + "\t" + 
                                 order.getItems().size() + "\t" + order.getTotal());
              total += order.getTotal();
          }
          System.out.println("------------------------");
          System.out.println("\t\t\tTOTAL: " + total);
      }
  }
  ```
  - Thuật toán: In header, lặp qua đơn hàng với serial number, hiển thị ID, ngày, số lượng mặt hàng, tổng; tính tổng grand total.

- **DetailPrinter**: Triển khai `OrderPrinter`, in chi tiết đơn hàng (đã tạo sẵn).
  - Thuật toán khác: Lặp qua đơn hàng và các mặt hàng bên trong để in đầy đủ chi tiết.

### Cấu Hình Context
- **PrintService**: Lớp context, nhận collection đơn hàng và delegate đến strategy.
  ```java
  public class PrintService {
      private OrderPrinter printer;  // Tham chiếu đến strategy

      public PrintService(OrderPrinter printer) {
          this.printer = printer;  // Cấu hình strategy qua constructor
      }

      public void printOrders(Collection<Order> orders) {
          printer.print(orders);  // Delegate đến strategy
      }
  }
  ```
  - Client quyết định strategy cụ thể khi tạo instance `PrintService`.

### Sử Dụng Trong Client
- Trong `main()`: Tạo strategy cụ thể và cấu hình context.
  ```java
  public static void main(String[] args) {
      createOrders();  // Tạo dữ liệu test
      PrintService service = new PrintService(new SummaryPrinter());  // Hoặc new DetailPrinter()
      service.printOrders(orders);
  }
  ```
  - Chạy với `SummaryPrinter`: Output tóm tắt (ID, ngày, số lượng, tổng, grand total).
  - Chạy với `DetailPrinter`: Output chi tiết (bao gồm mặt hàng trong từng đơn hàng).

### Kết Quả Chạy
- Với SummaryPrinter: Hiển thị báo cáo tóm tắt với tổng hợp.
- Với DetailPrinter: Hiển thị báo cáo chi tiết, lặp qua items.
- Lợi ích: Thay đổi output bằng cách chọn strategy khác nhau mà không sửa `PrintService` hoặc client logic chính.

### Ghi Chú Thêm
- Strategy giúp loại bỏ if-else trong context, tăng tính linh hoạt và tuân thủ nguyên lý mở rộng (Open/Closed Principle).
- Mỗi strategy không lưu state; chỉ nhận dữ liệu từ context khi gọi.