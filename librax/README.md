# LibraX Library Management - Modular Monolith

Dự án này là kết quả tái cấu trúc ứng dụng quản lý thư viện LibraX theo kiến trúc **Modular Monolith** (phân chia theo domain nghiệp vụ).

## Các thay đổi và sửa lỗi

### 1. Sửa lỗi logic giới hạn mượn sách
- **Vấn đề ban đầu**: Hàm `canBorrowBook` sử dụng điều kiện `currentBorrowedByMember > 5`. Khi độc giả đã mượn 5 cuốn, biến `currentBorrowedByMember = 5`, lúc này `5 > 5` trả về `false`, hàm `canBorrowBook` sẽ chạy xuống lệnh `return true;`, tức là vẫn cho phép mượn thêm cuốn thứ 6.
- **Cách sửa**: Sửa lại điều kiện trong `BorrowingService` thành `currentBorrowedByMember < 5` để trả về `true` (cho phép mượn nếu nhỏ hơn 5), nếu `>= 5` thì trả về `false` (không cho mượn). 

### 2. Loại bỏ biến dùng chung Shared Mutable State
- **Vấn đề ban đầu**: Biến `totalBorrowedBooks` được khai báo là `static` trong `LibraryController`. Do đó, nó là một biến toàn cục dùng chung cho *tất cả* các phiên và *mọi* độc giả. Khi một độc giả mượn sách, biến này tăng lên 1, dẫn đến việc đếm gộp số sách mượn của tất cả độc giả làm một, hệ thống không thể kiểm soát riêng giới hạn mượn cho từng cá nhân.
- **Cách sửa**: 
  - Loại bỏ hoàn toàn biến `static totalBorrowedBooks`.
  - Thay vào đó, số lượng sách đang mượn được quản lý dưới dạng trạng thái của từng đối tượng độc giả cụ thể (thêm thuộc tính `currentlyBorrowedBooks` vào class `Member`). 
  - Khi độc giả mượn sách, ta lấy số lượng đang mượn của riêng độc giả đó thông qua `MemberService` và kiểm tra. Nếu hợp lệ, tăng số lượng đó lên và cập nhật lại.

## Cấu trúc Package theo Domain
Dự án được chia thành 3 domain chính, mỗi domain bao gồm đủ 3 tầng `Controller`, `Service`, và `Repository`:
- `com.librax.library.book`: Xử lý logic quản lý sách.
- `com.librax.library.member`: Xử lý logic quản lý độc giả.
- `com.librax.library.borrowing`: Xử lý nghiệp vụ mượn trả, giao tiếp với 2 domain `book` và `member` để xác thực và cập nhật trạng thái.

## Tại sao đây vẫn là Monolithic Architecture?
Mặc dù code đã được phân tách rõ ràng theo từng module/domain (Modular Monolith), nhưng đây vẫn là **Monolithic Architecture** (Kiến trúc nguyên khối) vì các lý do sau:
1. **Một khối duy nhất (Single Deployment Unit)**: Toàn bộ các domain (`book`, `member`, `borrowing`) đều được compile, đóng gói chung vào một file thực thi duy nhất (ví dụ: `library-0.0.1-SNAPSHOT.jar`).
2. **Cùng chung một tiến trình (Single Process)**: Khi ứng dụng chạy, cả 3 domain này khởi chạy trong cùng một tiến trình JVM của Spring Boot và sử dụng chung bộ nhớ.
3. **Giao tiếp trực tiếp (In-process Communication)**: Khi `BorrowingService` cần lấy thông tin từ `MemberService` hoặc `BookService`, nó gọi hàm (method call) trực tiếp trên memory thay vì gọi qua mạng (Network / HTTP / gRPC) như trong Microservices.
4. **Dễ triển khai ban đầu**: Không cần setup phức tạp về Service Discovery, API Gateway hay phân tán cơ sở dữ liệu như kiến trúc Microservices.
