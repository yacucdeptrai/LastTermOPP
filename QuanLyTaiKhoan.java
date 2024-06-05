import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TaiKhoan {
    private String hoVaTen;
    private String diaChi;
    private String email;
    private String soDienThoai;
    private String matKhau;

    public TaiKhoan(String hoVaTen, String diaChi, String email, String soDienThoai, String matKhau) {
        this.hoVaTen = hoVaTen;
        this.diaChi = diaChi;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.matKhau = maHoaSHA256(matKhau);
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public String getEmail() {
        return email;
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

    public boolean kiemTraMatKhau(String matKhau) {
        return this.matKhau.equals(maHoaSHA256(matKhau));
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "hoVaTen='" + hoVaTen + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", email='" + email + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                '}';
    }
}

public class QuanLyTaiKhoan {
    private Map<String, TaiKhoan> danhSachTaiKhoan = new HashMap<>();

    public void nhapThongTinNguoiDung() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ho va ten: ");
        String hoVaTen = scanner.nextLine();
        System.out.print("Nhap dia chi: ");
        String diaChi = scanner.nextLine();
        System.out.print("Nhap email: ");
        String email = scanner.nextLine();
        System.out.print("Nhap so dien thoai: ");
        String soDienThoai = scanner.nextLine();
        System.out.print("Nhap mat khau: ");
        String matKhau = scanner.nextLine();

        TaiKhoan taiKhoan = new TaiKhoan(hoVaTen, diaChi, email, soDienThoai, matKhau);
        danhSachTaiKhoan.put(email, taiKhoan);
        System.out.println("Da them nguoi dung moi.");
    }

    public boolean kiemTraDangNhap(String email, String matKhau) {
        TaiKhoan taiKhoan = danhSachTaiKhoan.get(email);
        if (taiKhoan != null) {
            return taiKhoan.kiemTraMatKhau(matKhau);
        }
        return false;
    }

    public String timKiemNguoiDungTheoTen(String hoVaTen) {
        for (TaiKhoan taiKhoan : danhSachTaiKhoan.values()) {
            if (taiKhoan.getHoVaTen().equals(hoVaTen)) {
                return taiKhoan.toString();
            }
        }
        return "Khong tim thay nguoi dung.";
    }

    public String timKiemNguoiDungTheoEmail(String email) {
        TaiKhoan taiKhoan = danhSachTaiKhoan.get(email);
        if (taiKhoan != null) {
            return taiKhoan.toString();
        }
        return "Khong tim thay nguoi dung.";
    }

    public static void main(String[] args) {
        QuanLyTaiKhoan qltk = new QuanLyTaiKhoan();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Chon chuc nang:");
            System.out.println("1. Nhap them nguoi dung moi.");
            System.out.println("2. Kiem tra dang nhap.");
            System.out.println("3. Tim kiem nguoi dung theo ten.");
            System.out.println("4. Ra thong tin nguoi dung khi biet dia chi email.");
            System.out.println("5. Thoat.");
            System.out.print("Lua chon cua ban: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    qltk.nhapThongTinNguoiDung();
                    break;
                case 2:
                    System.out.print("Nhap email: ");
                    String email = scanner.nextLine();
                    System.out.print("Nhap mat khau: ");
                    String matKhau = scanner.nextLine();
                    if (qltk.kiemTraDangNhap(email, matKhau)) {
                        System.out.println("Dang nhap thanh cong.");
                    } else {
                        System.out.println("Dang nhap that bai.");
                    }
                    break;
                case 3:
                    System.out.print("Nhap ho va ten de tim kiem: ");
                    String hoVaTen = scanner.nextLine();
                    System.out.println(qltk.timKiemNguoiDungTheoTen(hoVaTen));
                    break;
                case 4:
                    System.out.print("Nhap email de ra thong tin nguoi dung: ");
                    String emailTimKiem = scanner.nextLine();
                    System.out.println(qltk.timKiemNguoiDungTheoEmail(emailTimKiem));
                    break;
                case 5:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }
}
