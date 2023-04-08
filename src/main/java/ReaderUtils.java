import java.io.*;
import java.util.*;

public class ReaderUtils {
    private static final String README_PATH = "./README.md";

    public static List<String> getReadmeFile() {
        File file = new File(README_PATH);

        List<String> lines = toListByReadme(file);

        // TODO: 스터디원 README 테스트 완료시 삭제
        testWriter(file, lines.size());
        return lines;
    }

    private static List<String> toListByReadme(File file) {
        try {
            List<String> lines = new ArrayList<>();

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            return lines;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("README 파일이 존재하지 않습니다.", e);
        }
    }

    private static void testWriter(File file, int size) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.newLine();
            writer.write("## " + (size + 1) + "번째 줄에 추가");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
