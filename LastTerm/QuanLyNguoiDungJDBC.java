import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;

public class QuanLyNguoiDungJDBC {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/QuanLyNguoiDung"; //sửa lại theo người dùng
    private static final String USER = "root";
    private static final String PASS = "1234";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private String maHoaSHA256(String matKhau) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(matKhau.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void themNguoiDung() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma nguoi dung: ");
        String maNguoiDung = scanner.nextLine();
        System.out.print("Nhap ho ten: ");
        String hoTen = scanner.nextLine();
        System.out.print("Nhap so dien thoai: ");
        String soDienThoai = scanner.nextLine();
        System.out.print("Nhap email: ");
        String email = scanner.nextLine();
        System.out.print("Nhap dia chi: ");
        String diaChi = scanner.nextLine();
        System.out.print("Nhap mat khau: ");
        String matKhau = maHoaSHA256(scanner.nextLine());

        String sql = "INSERT INTO NguoiDung (maNguoiDung, hoTen, soDienThoai, email, diaChi, matKhau) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maNguoiDung);
            pstmt.setString(2, hoTen);
            pstmt.setString(3, soDienThoai);
            pstmt.setString(4, email);
            pstmt.setString(5, diaChi);
            pstmt.setString(6, matKhau);
            pstmt.executeUpdate();
            System.out.println("Da them nguoi dung moi.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inDanhSachTheoTen() {
        String sql = "SELECT * FROM NguoiDung ORDER BY hoTen";
        
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Ma nguoi dung: " + rs.getString("maNguoiDung"));
                System.out.println("Ho ten: " + rs.getString("hoTen"));
                System.out.println("So dien thoai: " + rs.getString("soDienThoai"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Dia chi: " + rs.getString("diaChi"));
                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        QuanLyNguoiDungJDBC qlnd = new QuanLyNguoiDungJDBC();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Chon chuc nang:");
            System.out.println("1. Them nguoi dung moi.");
            System.out.println("2. In danh sach nguoi dung theo ten (A-Z).");
            System.out.println("3. Thoat.");
            System.out.print("Lua chon cua ban: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    qlnd.themNguoiDung();
                    break;
                case 2:
                    qlnd.inDanhSachTheoTen();
                    break;
                case 3:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }
}
