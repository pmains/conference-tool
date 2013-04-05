
package wjlib;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;


import com.webex.schemas.x2002.x06.service.BodyContentType;
import com.webex.schemas.x2002.x06.service.MessageDocument;
import com.webex.schemas.x2002.x06.service.MessageType;
import com.webex.schemas.x2002.x06.service.MessageType.Body;
import com.webex.schemas.x2002.x06.service.ResponseType;
import com.webex.schemas.x2002.x06.service.ResultTypeType;
import com.webex.schemas.x2002.x06.service.SecurityContextType;
import com.webex.schemas.x2002.x06.service.ep.GetAPIVersion;
import com.webex.schemas.x2002.x06.service.ep.GetAPIVersionResponse;
import com.webex.schemas.x2002.x06.service.meeting.CreateMeeting;
import com.webex.schemas.x2002.x06.service.meeting.CreateMeetingResponse;
import com.webex.schemas.x2002.x06.service.meeting.DelMeeting;
import com.webex.schemas.x2002.x06.service.meeting.DelMeetingResponse;
import com.webex.schemas.x2002.x06.service.meeting.GetMeeting;
import com.webex.schemas.x2002.x06.service.meeting.GetMeetingResponse;
import com.webex.schemas.x2002.x06.service.meeting.GethosturlMeeting;
import com.webex.schemas.x2002.x06.service.meeting.GethosturlMeetingResponse;
import com.webex.schemas.x2002.x06.service.meeting.GetjoinurlMeeting;
import com.webex.schemas.x2002.x06.service.meeting.GetjoinurlMeetingResponse;
import com.webex.schemas.x2002.x06.service.user.ActivateUser;
import com.webex.schemas.x2002.x06.service.user.ActivateUserResponse;
import com.webex.schemas.x2002.x06.service.user.CreateUser;
import com.webex.schemas.x2002.x06.service.user.CreateUserResponse;
import com.webex.schemas.x2002.x06.service.user.DelUser;
import com.webex.schemas.x2002.x06.service.user.DelUserResponse;
import com.webex.schemas.x2002.x06.service.user.GetUser;
import com.webex.schemas.x2002.x06.service.user.GetUserResponse;
import com.webex.schemas.x2002.x06.service.user.GetloginurlUser;
import com.webex.schemas.x2002.x06.service.user.GetloginurlUserResponse;
import com.webex.schemas.x2002.x06.service.user.GetlogouturlUser;
import com.webex.schemas.x2002.x06.service.user.GetlogouturlUserResponse;
import com.webex.schemas.x2002.x06.service.user.InactivateUser;
import com.webex.schemas.x2002.x06.service.user.InactivateUserResponse;
import com.webex.schemas.x2002.x06.service.user.LstUser;
import com.webex.schemas.x2002.x06.service.user.LstsummaryUser;
import com.webex.schemas.x2002.x06.service.user.UserInstanceType;
import com.webex.schemas.x2002.x06.service.user.UserSummaryInstanceType;

/**
 * 
 * Java client for Cisco WebEx XML API
 *
 */
public class WebexClient
{
	public static final String DEFAULT_CHARSET = "UTF-8";
	
	private HttpClient httpClient;
	
	private boolean compressionEnabled = false;
	private String username;
	private String password;
	private String siteName;
	private Long siteId;
	private String partnerId;
	
	public WebexClient(String siteName, Long siteId, String partnerId, String username, String password)
	{
		this(new DefaultHttpClient(), siteName, siteId, partnerId, username, password);
	}
	
	public WebexClient(HttpClient hClient, String siteName, Long siteId, String partnerId, String username, String password)
	{
		this.httpClient = hClient;
		
		setCredentials(username, password);

		if (siteName == null)
		{
			throw new IllegalArgumentException("siteName is null");
		}
		
		this.siteName = siteName;
		this.siteId = siteId;
		this.partnerId = partnerId;
	}
	
	protected HttpClient createHttpClient()
	{
		DefaultHttpClient hclient = new DefaultHttpClient();
		
		setUserAgent("webex-java-library");
		setConnectionTimeout( 10 * 1000);
		setSocketTimeout(25 * 1000);
		
		return hclient;
	}
	
	public void setCredentials(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	
	public void setUserAgent(String ua)
	{
		getHttpClient().getParams().setParameter(AllClientPNames.USER_AGENT, ua);
	}
	
	public void setConnectionTimeout(int milliseconds)
	{
		getHttpClient().getParams().setIntParameter(AllClientPNames.CONNECTION_TIMEOUT, milliseconds);
	}
	
	public void setSocketTimeout(int milliseconds)
	{
		getHttpClient().getParams().setIntParameter(AllClientPNames.SO_TIMEOUT, milliseconds);
	}
	
	
	public GetUserResponse getUser(String webExId)
	{
		GetUser get = GetUser.Factory.newInstance();
		get.setWebExId(webExId);
		
		return sendRequest(get, GetUserResponse.class);
	}
	
	/**
	 * 
	 * @param webExId must be non-null
	 * @param backUrl may be null
	 * 
	 * @return
	 * 
	 */
	public String getLoginUrl(String webExId, String backUrl)
	{
		GetloginurlUser get = GetloginurlUser.Factory.newInstance();
		
		get.setBackURL(backUrl);
		get.setWebExID(webExId);
		
		return sendRequest(get, GetloginurlUserResponse.class).getUserLoginURL();
	}
	
	/**
	 * 
	 * @param webExId must be non-null
	 * @param backUrl may be null
	 * 
	 * @return
	 * 
	 */
	public String getLogoutUrl(String webExId, String backUrl)
	{
		GetlogouturlUser get = GetlogouturlUser.Factory.newInstance();
		
		get.setBackURL(backUrl);
		get.setWebExID(webExId);
		
		return sendRequest(get, GetlogouturlUserResponse.class).getUserLogoutURL();
	}
	
	public void deleteUsers(String... webExIdArray)
	{
		DelUser delete = DelUser.Factory.newInstance();
		delete.setWebExIdArray(webExIdArray);
		sendRequest(delete, DelUserResponse.class);
	}
	
	public CreateUserResponse createUser(CreateUser request)
	{
		return sendRequest(request, CreateUserResponse.class);
	}
	
	protected <T> T sendRequest(com.webex.schemas.x2002.x06.service.BodyContentType request, Class<T> responseClass)
	{
		MessageDocument requestDoc = createRequestDocument(request);
		
		String requestXml = requestDoc.toString();
		
		// System.out.println(requestXml);
		
		HttpClient hc = this.getHttpClient();
		
		HttpPost post = new HttpPost(getEndpointUrl());
		
		
		HttpResponse httpResponse = null;
		
		try
		{
			StringEntity requestEntity = new StringEntity(requestXml, DEFAULT_CHARSET);
			
			post.setEntity(requestEntity);
			
			httpResponse = hc.execute(post);
			
			checkResponse(httpResponse);
			
			String responseXml = EntityUtils.toString(httpResponse.getEntity());
			
			MessageDocument responseDoc = MessageDocument.Factory.parse(responseXml);
			
			checkResponse(responseDoc);
			
			BodyContentType[] bodyContents = responseDoc.getMessage().getBody().getBodyContentArray();
			
			return (T) bodyContents[0];
		}
		catch (Exception ex)
		{
			throw new RuntimeException("request XML=" + requestXml, ex);
		}
		finally
		{
			close(httpResponse);
		}
		
	}
	
	protected String getEndpointUrl()
	{
		return "https://" + getSiteName() + ".webex.com/WBXService/XMLService";
	}

	protected String getSiteName()
	{
		return this.siteName;
	}
	
	protected void close(HttpResponse httpResponse)
	{
		if (httpResponse != null)
		{
			close(httpResponse.getEntity());
		}
	}

	protected void close(HttpEntity entity)
	{
		if (entity != null)
		{
			try
			{
				entity.consumeContent();
			}
			catch (Throwable t)
			{
				// ignore
			}
		}
	}

	protected void checkResponse(HttpResponse response)
	{
		if (response == null)
		{
			throw new RuntimeException("null HttpResponse");
		}
		
		StatusLine status = response.getStatusLine();
		
		if (status == null)
		{
			throw new RuntimeException("null StatusLine");
		}
		
		if (status.getStatusCode() != 200)
		{
			throw new RuntimeException("unexpected HTTP status code: " + status.getStatusCode());
		}
		
		if (response.getEntity() == null)
		{
			throw new RuntimeException("HttpResponse HttpEntity is null");
		}
	}

	protected void checkResponse(MessageDocument responseDocument)
	{
		ResponseType[] responseTypeArray = responseDocument.getMessage().getHeader().getResponseArray();
		
		if ( (responseTypeArray == null) || (responseTypeArray.length == 0) )
		{
			throw new RuntimeException("unexpected ResponseType array");
		}
		
		for (ResponseType rtype : responseTypeArray)
		{
			if (rtype.getResult() != ResultTypeType.SUCCESS)
			{
				throw new RuntimeException("unexpected WebEx response: " 
							+ rtype.getResult()
							+ " --- "
							+ rtype.getReason());
			}
		}
		
	}
	
	protected HttpClient getHttpClient()
	{
		if (this.httpClient == null)
		{
			this.httpClient = createHttpClient();
		}
		
		return this.httpClient;
	}
	

	protected MessageDocument createRequestDocument(BodyContentType request)
	{
		MessageDocument doc = MessageDocument.Factory.newInstance();
		
		MessageType mtype = doc.addNewMessage();
		
		MessageType.Header header = mtype.addNewHeader();
		
		SecurityContextType security = header.addNewSecurityContext();
		
		security.setWebExID(this.username);
		security.setPassword(this.password);
		security.setSiteName(this.siteName);
		
		if (this.partnerId != null)
		{
			security.setPartnerID(this.partnerId);
		}
		
		if (this.siteId != null)
		{
			security.setSiteID(this.siteId);
		}
		
		// TODO (?) security.setSessionTicket(sessionTicket);
		
		Body body = mtype.addNewBody();
		
		BodyContentType[] bodyContentArray = new BodyContentType[1];
		bodyContentArray[0] = request;
		
		body.setBodyContentArray(bodyContentArray);
		
		return doc;
	}
	
	public UserSummaryInstanceType[] listUsers(LstsummaryUser listUsers)
	{
		return sendRequest(listUsers, UserSummaryInstanceType[].class);
	}
	
	/**
	 * 
	 * @param webExId
	 * 
	 * @see #activateUser(String)
	 * 
	 */
	public void deactivateUser(String webExId)
	{
		InactivateUser deactivate = InactivateUser.Factory.newInstance();
		deactivate.setWebexID(webExId);
		
		sendRequest(deactivate, InactivateUserResponse.class);
	}
	
	/**
	 * 
	 * @param webExId
	 * 
	 * @see #deactivateUser(String)
	 * 
	 */
	public void activateUser(String webExId)
	{
		ActivateUser activate = ActivateUser.Factory.newInstance();
		activate.setWebexID(webExId);
		
		sendRequest(activate, ActivateUserResponse.class);
	}
	
	public String getAPIVersion()
	{
		GetAPIVersion get = GetAPIVersion.Factory.newInstance();
		
		GetAPIVersionResponse response = sendRequest(get, GetAPIVersionResponse.class);
		
		return response.getApiVersion();
		
	}
	
	public CreateMeetingResponse createMeeting(CreateMeeting request)
	{
		return sendRequest(request, CreateMeetingResponse.class);
	}
	
	public void deleteMeeting(long meetingKey)
	{
		DelMeeting delete = DelMeeting.Factory.newInstance();
		
		delete.setMeetingKey(meetingKey);
		
		sendRequest(delete, DelMeetingResponse.class);
		
	}

	public String getHostMeetingUrl(long sessionKey)
	{
		
		GethosturlMeeting get = GethosturlMeeting.Factory.newInstance();
		
		get.setSessionKey(sessionKey);
		
		GethosturlMeetingResponse response = sendRequest(get, GethosturlMeetingResponse.class);
		
		return response.getHostMeetingURL();
	}
	
	public String getJoinMeetingUrl(long sessionKey)
	{
		
		GetjoinurlMeeting get = GetjoinurlMeeting.Factory.newInstance();
		
		get.setSessionKey(sessionKey);
		
		GetjoinurlMeetingResponse response = sendRequest(get, GetjoinurlMeetingResponse.class);
		
		return response.getJoinMeetingURL();
	}
	
	public GetMeetingResponse getMeeting(long meetingKey)
	{
		GetMeeting get = GetMeeting.Factory.newInstance();
		
		get.setMeetingKey(meetingKey);
		
		return sendRequest(get, GetMeetingResponse.class);
		
	}
	
}
