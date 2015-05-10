package pretest;

import java.io.FileReader;
import java.util.List;
import java.util.Map;

public interface Parser {
	public boolean parse(FileReader fr, Map<String, List<String>> contactBook);
}
