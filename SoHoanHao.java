import java.util.Scanner;

public class SoHoanHao {
    public static boolean laSoHoanHao(int so) {
        int tong = 0;
        for (int i = 1; i <= so / 2; i++) {
            if (so % i == 0) {
                tong += i;
            }
        }
        return tong == so;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap vao mot so: ");
        int so = scanner.nextInt();
        if (laSoHoanHao(so)) {
            System.out.println(so + " la so hoan hao.");
        } else {
            System.out.println(so + " khong phai la so hoan hao.");
        }
    }
}
