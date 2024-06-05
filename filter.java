import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class filter {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("java filter <input> <output> <limit-num>"); //print usage
            return;
        }

        String tepDuLieu = args[0];
        String tepLuuKetQua = args[1];
        int limit = Integer.parseInt(args[2]);

        try (Scanner scanner = new Scanner(new File(tepDuLieu));
             PrintWriter writer = new PrintWriter(new FileWriter(tepLuuKetQua))) {

            boolean first = true;  // kiểm tra space
            while (scanner.hasNextInt()) {
                int so = scanner.nextInt();
                if (so < limit) {
                    if (!first) {
                        writer.print(" ");
                    }
                    writer.print(so);
                    first = false; 
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
