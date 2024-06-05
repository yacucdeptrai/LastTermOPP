import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class filter {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("java filter <input> <output> <limit-num>");
            return;
        }

        String tepDuLieu = args[0];
        String tepLuuKetQua = args[1];
        int limit = Integer.parseInt(args[2]);

        try (Scanner scanner = new Scanner(new File(tepDuLieu));
             PrintWriter writer = new PrintWriter(new FileWriter(tepLuuKetQua))) {

            boolean first = true;  // Để kiểm tra xem có cần in dấu cách trước số hay không
            while (scanner.hasNextInt()) {
                int so = scanner.nextInt();
                if (so < limit) {
                    if (!first) {
                        writer.print(" ");
                    }
                    writer.print(so);
                    first = false;  // Đã in số đầu tiên, không còn là lần đầu nữa
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
