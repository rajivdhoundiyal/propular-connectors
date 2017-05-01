package org.propular;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
//@ContextConfiguration(classes=PropularAutoConfig.class)
public class AppTest {
	
	@Autowired
	PropularAutoConfig propertyAutoConfig;
	
	@Autowired
	PropularProperty propularProperty;
	
	@Test
	public void testApp() throws IOException {
		System.out.println(propularProperty.getProperties());
		//assertTrue(true);
	}
	
	@Configuration
	@Import({ PropularAutoConfig.class })
	@EnableAutoConfiguration()
	public static class TestConfiguration {

	}
}
