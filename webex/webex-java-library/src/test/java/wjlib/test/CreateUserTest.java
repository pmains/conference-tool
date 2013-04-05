
package wjlib.test;

import java.util.Calendar;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;


import com.webex.schemas.x2002.x06.common.LanguageType;
import com.webex.schemas.x2002.x06.service.user.CreateUser;
import com.webex.schemas.x2002.x06.service.user.CreateUserResponse;
import com.webex.schemas.x2002.x06.service.user.ActiveType;
import com.webex.schemas.x2002.x06.service.user.UserPhonesType;

import wjlib.*;

public class CreateUserTest extends AbstractTest
{
	@Test
	public void testCreateUser() 
	{
		
		CreateUser createUser = CreateUser.Factory.newInstance();
		
		long uniqueNumber = System.currentTimeMillis();
		
		createUser.setFirstName("Barack");
		createUser.setLastName("Obama" + uniqueNumber);
		createUser.setTitle("President");
		createUser.setEmail("barack" + uniqueNumber + "@whitehouse.gov");
		createUser.setWebExId("bobama" + uniqueNumber);
		createUser.setPassword("aIrFORce$1");
		createUser.setActive(ActiveType.ACTIVATED);
		createUser.setCompany("United States Government");
		createUser.setSendWelcome(true);
		createUser.setLanguage(LanguageType.ENGLISH.toString());
		
		UserPhonesType phones = UserPhonesType.Factory.newInstance();
		phones.setPhone("503-555-1234");
		
		createUser.setPhones(phones);
		
		
		CreateUserResponse response = client.createUser(createUser);

		assertNotNull(response);
		
		System.out.println("WebEx user id: " + response.getUserId());
		
		assertNotNull(response.getUserId() >= 0);
		
	}

}
