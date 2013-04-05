
package wjlib.test;

import java.math.BigInteger;

import org.testng.annotations.*;

import com.webex.schemas.x2002.x06.service.LstControlType;
import com.webex.schemas.x2002.x06.service.user.DataScopeType;
import com.webex.schemas.x2002.x06.service.user.OrderByType;
import com.webex.schemas.x2002.x06.service.user.OrderType;
import com.webex.schemas.x2002.x06.service.user.LstsummaryUser;
import com.webex.schemas.x2002.x06.service.user.UserInstanceType;
import com.webex.schemas.x2002.x06.service.user.UserSummaryInstanceType;

import static org.testng.AssertJUnit.*;

public class ListUsersTest extends AbstractTest
{
	@Test
	public void testListUsers()
	{
		LstsummaryUser list = LstsummaryUser.Factory.newInstance();
		
		LstControlType listControl = list.addNewListControl();
		listControl.setMaximumNum(new BigInteger("100"));
		
		OrderType order = list.addNewOrder();
		
		order.addNewOrderBy().set(OrderByType.LASTNAME);
		order.addNewOrderBy().set(OrderByType.FIRSTNAME);

		UserSummaryInstanceType[] users = client.listUsers(list);
		
		assertNotNull(users);
		
		for (UserSummaryInstanceType user : users)
		{
			assertNotNull(user);
			assertNotNull(user.getWebExId());
			assertNotNull(user.getActive());
		}
	}
}
