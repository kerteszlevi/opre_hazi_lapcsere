import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.equals("")|| line.equals("\n")) {
                continue;
            }

            String[] references = line.split(",");
        }
    }
}