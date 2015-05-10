package pretest.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pretest.Contacts;


import java.util.List;

public class TestContacts {
	
	private Contacts contacts;

	@Before
	public void setUp() throws Exception {
		contacts = new Contacts();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOneRow() {
		contacts.load("row-1.csv");
		assertEquals(1, contacts.count());
	}
	
	@Test
	public void testSameName() {
		contacts.load("row-3-dup.csv");
		assertEquals(2, contacts.count());
		
		List<String> result = contacts.findByName("홍길동");
		assertEquals(2, result.size());
		
		result = contacts.findByName("임꺽정");
		assertEquals(1, result.size());		
	}

	@Test
	public void testNonexistentName() {
		contacts.load("row-3-dup.csv");
		
		List<String> result = contacts.findByName("없는이름");
		assertEquals(0, result.size());
	}

}
