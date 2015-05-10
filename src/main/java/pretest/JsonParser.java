package pretest;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser implements Parser {

	public JsonParser() {
		super();
	}
	
	@Override
	public boolean parse(FileReader fr, Map<String, List<String>> contactBook) {
		
		try {
			if ( fr == null || !fr.ready() || contactBook == null) {
				return false;
			}
			
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject)parser.parse(fr);
			
			JSONArray contactlist = (JSONArray)jsonObject.get("contacts");
			
			String name, phoneNumber;
			
			for(Object obj : contactlist) {
				
				name = (String)((JSONObject)obj).get("name");
				phoneNumber = (String)((JSONObject)obj).get("phoneNumber");
				
				if (contactBook.containsKey(name)) {
					contactBook.get(name).add(phoneNumber);
				} else {
					List<String> phoneNumbers = new ArrayList<String>();
					phoneNumbers.add(phoneNumber);
					
					contactBook.put(name, phoneNumbers);
				}
			}
			
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} 
	}

}
