import java.util.ArrayList;
import java.util.Scanner;

public class SoBeNhat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> mang = new ArrayList<>();

        System.out.println("Nhap cac phan tu cua mang :");
        String input = scanner.nextLine();
        String[] parts = input.split(" "); //giua cac phan tu co khoang trang

        for (String part : parts) {
            try {
                double so = Double.parseDouble(part);
                mang.add(so);
            } catch (NumberFormatException e) { //bo qua phan tu khong phai
                System.out.println("Gia tri khong hop le: " + part);
            }
        }

        if (mang.isEmpty()) {
            System.out.println("Mang khong co phan tu nao.");
        } else {
            double soBeNhat = mang.get(0);
            for (double so : mang) {
                if (so < soBeNhat) {
                    soBeNhat = so;
                }
            }
            System.out.println("So be nhat trong mang la: " + soBeNhat);
        }
    }
}
