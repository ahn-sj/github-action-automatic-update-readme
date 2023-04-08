import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MemberBoard {

    private static final Pattern pattern = Pattern.compile("(\\d{1,2})월(\\s*\\d{1,2})일");
    private static final String STUDY_MEMBER_HEADER = "## 스터디 멤버";
    private static final int NEXT_LINE_POINTER = 1;
    private static final int THIS_YEAR = LocalDateTime.now().getYear();
    private static final String FORMAT_TODAY_DATE = convertToDateFormat(LocalDate.now());

    private List<String> members = new ArrayList<>();

    public MemberBoard(List<String> members) {
        this.members = members;
    }

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

    public void updateBoard() {
        System.out.println("formatDate = " + FORMAT_TODAY_DATE);

        // TODO: 리팩터링 대상
        for (String member : members) {
            String[] files = ReaderUtils.getFileListInMemberDirectory(member); // 멤버 폴더안 파일 목록

            for (String file : files) { // 특정 멤버 파일 목록 전체 루프
                Matcher matcher = pattern.matcher(file);

                // TODO: 날짜가 일치할 경우 쓰기 작업
                if (isMatchDate(matcher)) {
                    System.out.println("=============> " + file);
                }
            }
        }
    }

    // TODO: 리펙터링 대상
    private boolean isMatchDate(Matcher matcher) {
        if (matcher.find()) {
            String savedDate = convertToDateFormat(
                    LocalDate.of(
                            convertStringToInt(FORMAT_TODAY_DATE.substring(0, 4)),
                            convertStringToInt(matcher.group(1)),
                            convertStringToInt(matcher.group(2))
                    )
            );
            System.out.println("savedDate = " + savedDate);

            if(savedDate.equals(FORMAT_TODAY_DATE)) {
                return true;
            }
        }
        return false;
    }

    private int convertStringToInt(String group) {
        return Integer.parseInt(group.trim());
    }

    private static String convertToDateFormat(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    private static String convertLocalDateTimeToDateFormat(LocalDateTime now) { // 2023/4/8(토)
        return String.format("%s/%s/%s(%s)",
                now.getYear(),
                now.getMonth().getValue(),
                now.getDayOfMonth(),
                now.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREA)
        );
    }

    // TODO: 만약 오늘 날짜의 테이블이 없는 경우 생성
    // TODO: ex) | + (for(member.size) + |)
    public void existBoard() {

    }
}
