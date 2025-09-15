## Mẫu Thiết Kế Strategy: Các Ví Dụ Thực Tế

### Ví Dụ 1: Giao Diện Comparator trong Java
- Giao diện `Comparator` (java.util.Comparator) là một ví dụ kinh điển về mẫu thiết kế strategy, cho phép định nghĩa logic so sánh tùy chỉnh bên ngoài lớp cần sắp xếp.
- Lợi ích: Hỗ trợ nhiều chiến lược sắp xếp (sorting strategies) cho cùng một lớp, thường được sử dụng trong các phương thức như `Collections.sort()`.

#### Lớp User
- Định nghĩa lớp `User` với hai thuộc tính đơn giản: `name` (tên) và `age` (tuổi).

#### Triển Khai Concrete Strategy
- **SortByAge**: Triển khai `Comparator<User>`, sử dụng thuộc tính `age` để so sánh hai đối tượng `User`.
- **SortByName**: Triển khai `Comparator<User>`, sử dụng thuộc tính `name` để so sánh hai đối tượng `User`.

#### Mã Nguồn Triển Khai
```java
import java.util.*;

// Lớp User
class User {
    private String name;
    private int age;
    
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

// Concrete Strategy: Sort by Age
class SortByAge implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return Integer.compare(u1.getAge(), u2.getAge());
    }
}

// Concrete Strategy: Sort by Name
class SortByName implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return u1.getName().compareTo(u2.getName());
    }
}

// Sử dụng trong Context
public class StrategyExample {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("Alice", 30));
        users.add(new User("Bob", 25));
        users.add(new User("Charlie", 35));
        
        // Sắp xếp theo tuổi
        Collections.sort(users, new SortByAge());
        System.out.println("Sorted by Age: " + users);
        
        // Sắp xếp theo tên
        Collections.sort(users, new SortByName());
        System.out.println("Sorted by Name: " + users);
    }
}
```
- **Giải Thích**: `Comparator` đóng vai trò strategy interface; `SortByAge` và `SortByName` là concrete strategies. Client (như `Collections.sort()`) "plug" strategy vào để thay đổi hành vi sắp xếp tại runtime.

### Ví Dụ 2: ImplicitNamingStrategy và PhysicalNamingStrategy trong Hibernate
- Hai giao diện này trong Hibernate là ví dụ về mẫu strategy, dùng để chuyển đổi tên logic (logical names) của entity và thuộc tính thành tên vật lý (physical names) trong cơ sở dữ liệu (bảng và cột).
- **ImplicitNamingStrategy**: Xử lý việc suy ra tên logic từ tên lớp Java và thuộc tính khi không chỉ định rõ ràng (ví dụ: từ camelCase sang tên mặc định).
- **PhysicalNamingStrategy**: Ánh xạ tên logic sang tên vật lý, tuân thủ quy ước cơ sở dữ liệu (ví dụ: snake_case).

#### Cách Sử Dụng
- Hibernate cung cấp nhiều triển khai sẵn (built-in implementations) cho các strategy này.
- Cấu hình qua file properties: Chỉ định implementation cụ thể (ví dụ: `hibernate.implicit_naming_strategy` và `hibernate.physical_naming_strategy`).
- Tùy chỉnh: Tạo lớp riêng triển khai giao diện để định nghĩa thuật toán ánh xạ tùy chỉnh (ví dụ: chuyển camelCase thành snake_case).

#### Ví Dụ Triển Khai Tùy Chỉnh (PhysicalNamingStrategy)
```java
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CamelCaseToSnakeCaseStrategy extends PhysicalNamingStrategyStandardImpl {
    private static final String CAMEL_CASE_REGEX = "([a-z]+)([A-Z]+)";
    private static final String SNAKE_CASE_PATTERN = "$1_$2";
    
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return formatIdentifier(super.toPhysicalTableName(name, context));
    }
    
    private Identifier formatIdentifier(Identifier name) {
        if (name == null) {
            return name;
        }
        String regex = CAMEL_CASE_REGEX;
        String newName = name.getText().replaceAll(regex, SNAKE_CASE_PATTERN).toLowerCase();
        return Identifier.toIdentifier(newName);
    }
}
```
- **Cấu Hình**: Trong `hibernate.cfg.xml` hoặc `application.properties`:  
  `hibernate.physical_naming_strategy = com.example.CamelCaseToSnakeCaseStrategy`
- **Lợi Ích**: Cho phép tùy chỉnh linh hoạt quy ước đặt tên, tránh hard-code và dễ mở rộng.

### Ghi Chú Thêm
- Các ví dụ trên minh họa cách strategy pattern tách biệt thuật toán khỏi context, tăng tính linh hoạt và tuân thủ nguyên tắc Open-Closed (mở rộng mà không sửa đổi code hiện có).