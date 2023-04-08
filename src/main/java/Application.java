import java.util.List;

public class Application {

	public static void main(String[] args) {
		List<String> lines = ReaderUtils.getReadmeFile();
		List<String> list = MemberBoard.extractMember(lines); // [깨비, 성재]

		for (String s : list) {
			System.out.println("s = " + s);
		}
	}
}
