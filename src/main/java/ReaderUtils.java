import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReaderUtils {

    public static void readReadme() {
        File file = new File("./README.md");

        BufferedWriter writer = null;

        try {
            Scanner scanner = new Scanner(file);
            ArrayList<String> lists = new ArrayList<>();

            while (scanner.hasNextLine()) {
                lists.add(scanner.nextLine());
            }

            writer = new BufferedWriter(new FileWriter(file, true));
            writer.newLine();
            writer.write("## " + lists.size() + "번째 줄 입니다.");
            // writer.newLine();
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
