import java.util.List;

public class Application {

	public static void main(String[] args) {
		List<String> lines = ReaderUtils.getReadmeFile();
		List<String> members = MemberBoard.extractMember(lines); // [깨비, 성재]

		MemberBoard board = new MemberBoard(members);

		// TODO: 만약 오늘 날짜의 테이블이 없는 경우 생성
		// TODO: ex) | + (for(member.size) + |)
		board.existBoard();
		board.updateBoard();

		for (String mem : members) {
			System.out.println("mem = " + mem);
		}
	}
}
