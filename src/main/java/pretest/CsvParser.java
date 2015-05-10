package pretest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvParser implements Parser {

	public CsvParser() {
		super();
	}
	
	@Override
	public boolean parse(FileReader fr, Map<String, List<String>> contactBook) {
		
		if ( contactBook == null ) {
			return false;
		}
		
		contactBook.clear();
		
		try (BufferedReader br = new BufferedReader(fr)) {
			
			int pos = -1;
			String name, phoneNumber;
			
			for(String line = br.readLine(); line != null; line = br.readLine()) {
				
				if ( (pos = line.indexOf(',')) == -1 ) {
					continue;
				}
				
				name = line.substring(0, pos);
				phoneNumber = line.substring(pos + 1).trim();
				
				if (contactBook.containsKey(name)) {
					contactBook.get(name).add(phoneNumber);
				} else {
					List<String> phoneNumbers = new ArrayList<String>();
					phoneNumbers.add(phoneNumber);
					
					contactBook.put(name, phoneNumbers);
				}
			}
			
			return true;
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
}
