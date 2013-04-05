
package wjlib.test;

import java.math.BigInteger;
import java.util.Calendar;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;


import com.webex.schemas.x2002.x06.common.LanguageType;
import com.webex.schemas.x2002.x06.common.TimeZoneType;
import com.webex.schemas.x2002.x06.service.CallInNumType;
import com.webex.schemas.x2002.x06.service.meeting.AccessControlType;
import com.webex.schemas.x2002.x06.service.meeting.AttendeeOptionsType;
import com.webex.schemas.x2002.x06.service.meeting.CreateMeetingResponse;
import com.webex.schemas.x2002.x06.service.meeting.EnableOptionsType;
import com.webex.schemas.x2002.x06.service.meeting.MetaDataType;
import com.webex.schemas.x2002.x06.service.meeting.RemindType;
import com.webex.schemas.x2002.x06.service.meeting.CreateMeeting;
import com.webex.schemas.x2002.x06.service.meeting.RepeatType;
import com.webex.schemas.x2002.x06.service.meeting.ScheduleType;
import com.webex.schemas.x2002.x06.service.meeting.TelephonyType;
import com.webex.schemas.x2002.x06.service.user.CreateUser;
import com.webex.schemas.x2002.x06.service.user.CreateUserResponse;
import com.webex.schemas.x2002.x06.service.user.ActiveType;
import com.webex.schemas.x2002.x06.service.user.UserPhonesType;

/**
 * 
 * create a WebEx meeting using the WebEx XML API
 *
 */
public class CreateMeetingTest extends AbstractTest
{
	@Test
	public void testCreateMeeting() 
	{
		
		CreateUser createUser = CreateUser.Factory.newInstance();
		
		long uniqueNumber = System.currentTimeMillis();
		
		createUser.setFirstName("Meeting Host");
		createUser.setLastName("Lastname" + uniqueNumber);
		createUser.setTitle("VP of Engineering");
		createUser.setEmail("meetinghost" + uniqueNumber + "@meetinghost.org");
		createUser.setWebExId("testhost" + uniqueNumber);
		createUser.setPassword("aIrFORce$1");
		createUser.setActive(ActiveType.ACTIVATED);
		createUser.setCompany("Meeting Host LLC");
		createUser.setSendWelcome(true);
		createUser.setLanguage(LanguageType.ENGLISH.toString());
		
		UserPhonesType phones = UserPhonesType.Factory.newInstance();
		phones.setPhone("503-555-1111");
		
		createUser.setPhones(phones);
		
		
		CreateUserResponse createUserResponse = client.createUser(createUser);

		long userId = createUserResponse.getUserId();
		
		CreateMeeting meeting = CreateMeeting.Factory.newInstance();
		
		MetaDataType metadata = meeting.addNewMetaData();
		metadata.setConfName("Engineering meeting");
		metadata.setLocation("Portland, Oregon, USA");

		EnableOptionsType enableOptions = meeting.addNewEnableOptions();
		enableOptions.setApplicationShare(true);
		enableOptions.setChat(true);
		enableOptions.setAttendeeList(true);
		enableOptions.setChatAllAttendees(true);
		enableOptions.setChatHost(true);
		enableOptions.setChatPresenter(true);
		enableOptions.setDesktopShare(true);
		enableOptions.setAttendeeList(true);
		enableOptions.setMeetingRecord(true);
		
		AttendeeOptionsType attendeeOptions = meeting.addNewAttendeeOptions();
		attendeeOptions.setEmailInvitations(true);
		attendeeOptions.setExcludePassword(false);
		attendeeOptions.setJoinRequiresAccount(false);
		attendeeOptions.setParticipantLimit(new BigInteger("500"));
		attendeeOptions.setRequest(false);
		attendeeOptions.setRegistration(false);
		
		meeting.addNewRemind().setMinutesAhead(new BigInteger("15"));
		
		TelephonyType telephony = meeting.addNewTelephony();
		
		CallInNumType callInNumber = telephony.addNewCallInNum();
		callInNumber.setTollNum("503-555-1234");
		
		telephony.setTollFree(false);
		
		ScheduleType schedule = meeting.addNewSchedule();
		schedule.setHostWebExID("" + userId);
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		String startDate = "12/31/" + year + " " + "23:59:59";   // MM/DD/YYYY HH:MM:SS
		schedule.setStartDate(startDate);
		
		schedule.setTimeZone(TimeZoneType.GMT_08_00_PACIFIC_SAN_JOSE);
		
		schedule.setDuration(60 /* minutes */);
		
		AccessControlType access = meeting.addNewAccessControl();
		access.setEnforcePassword(true);
		access.setMeetingPassword("meetingPa$$word");
		access.setIsPublic(false);
		access.setListToPublic(false);
		
		CreateMeetingResponse response = client.createMeeting(meeting);
		
		assertNotNull(response);
		
		assertTrue(response.getMeetingkey() > 0);
		
	}

}
