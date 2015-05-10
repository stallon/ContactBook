package pretest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contacts {
	
	public Contacts() {
		contactBook = new HashMap<String, List<String>>();
	}
	
	public void load(String filename) {
		
		contactBook.clear();
		
		try ( BufferedReader br = 
				new BufferedReader(new FileReader(filename)) ) {
			
			int pos = 0;
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
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void load(String filename, Parser parser) {
		
		if ( parser == null ) {
			parser = new CsvParser();
		}
		
		try ( FileReader fr = new FileReader(filename)) {
			
			parser.parse(fr, contactBook);
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public int count() {	
		return contactBook.size();
	}
	
	public List<String> findByName(String name) {
		
		if ( contactBook.size() == 0 || !contactBook.containsKey(name)) {
			return new ArrayList<String>();
		}
		
		return contactBook.get(name);
	}
	
	private Map<String, List<String>> contactBook;
}
