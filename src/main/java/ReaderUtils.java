import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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

        return Collections.unmodifiableList(memberNames);
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
