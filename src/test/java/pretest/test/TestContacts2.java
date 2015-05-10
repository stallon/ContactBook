package pretest.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pretest.Contacts;
import pretest.CsvParser;
import pretest.JsonParser;

public class TestContacts2 {

	Contacts contacts;
	
	@Before
	public void setUp() throws Exception {
		contacts = new Contacts();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCsvFormat() {
		
		contacts.load("./row-3-dup.csv", new CsvParser());
        List<String> result = contacts.findByName("홍길동");
        assertThat(result.size(), is(2));
	}
	
	@Test
    public void testJsonFormat() throws Exception {
        contacts.load("./row-3-dup.json", new JsonParser());
        List<String> result = contacts.findByName("홍길동");

        assertThat(result.size(), is(2));
    }

}
