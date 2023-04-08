import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MemberBoard {

    private static final String STUDY_MEMBER_HEADER = "## 스터디 멤버";
    private static final int NEXT_LINE_POINTER = 1;

    public static List<String> extractMember(List<String> lines) {
        String[] boardColumns = getBoardColumns(lines);

        return Arrays
                .stream(boardColumns, 2, boardColumns.length)
                .collect(Collectors.toList());
    }

    private static String[] getBoardColumns(List<String> lines) {
        int boardLine = extractBoardLine(lines);

        String line = lines.get(boardLine + NEXT_LINE_POINTER);
        return line.replace(" ", "").split("\\|");
    }

    private static int extractBoardLine(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).equals(STUDY_MEMBER_HEADER)) return i;
        }
        throw new RuntimeException("스터디 멤버 정보를 가져올 수 없습니다.");
    }
}
