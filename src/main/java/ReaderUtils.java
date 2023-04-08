import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ReaderUtils {
    private static final String README_PATH = "./README.md";
    private static final String STUDY_MEMBER_HEADER = "## 스터디 멤버";

    public static List<String> getStudyMember() {
        File file = getReadmeFile();
        StudyBoard studyBoard = extractMember(file);
        List<String> memberNames = toListWithFilter(studyBoard.getHeaders());

        memberNames.stream().map(memberName -> "memberName = " + memberName).forEach(System.out::println);
        System.out.println("studyBoard.getLocationIndex() = " + studyBoard.getLocationIndex());

        testWriter(file);

        return Collections.unmodifiableList(memberNames);
    }

    private static void testWriter(File file) {
        try {
            Scanner scanner = new Scanner(file);

            List<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.newLine();
            writer.write("## " + (lines.size() + 1) + "번째 줄에 추가");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> toListWithFilter(String[] headers) {
        return Arrays
                .stream(headers, 2, headers.length)
                .collect(Collectors.toList());
    }

    private static StudyBoard extractMember(File file) {
        int index = 0;
        
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                index++;
                String nextLine = scanner.nextLine();

                if(nextLine.equals(STUDY_MEMBER_HEADER)) {
                    String curLine = scanner.nextLine();
                    String[] headers = curLine.replace(" ", "").split("\\|");
                    return new StudyBoard(headers, index);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("README 파일이 존재하지 않습니다.", e);
        }
        throw new RuntimeException("스터디 구성원이 존재하지 않습니다.");
    }

    private static File getReadmeFile() {
        return new File(README_PATH);
    }
}
