
package wjlib.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;

import wjlib.WebexClient;

public class AbstractTest 
{
	protected WebexClient client;
	
	static private Properties testProps = null;
	
	static
	{
		
		InputStream istream = null;
		
		File f = new File("./test.properties");

		if (f.exists())
		{
			try 
			{
				istream = new FileInputStream(f);
			} 
			catch (FileNotFoundException e) 
			{
				throw new RuntimeException(e);
			}
		}
		else
		{
			istream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.properties");
		}
		
		
		try
		{
			testProps = new Properties();
			
			istream = new FileInputStream(f);
			
			if (istream != null)
			{
				testProps.load(istream); 
			}
		}
		catch (Throwable t)
		{
			
		}
		finally
		{
			if (istream != null)
			{
				try
				{
					istream.close();
				}
				catch (Throwable t)
				{
					// ignored
				}
			}
		}
	}

	private String getTestProperty(String propName)
	{
		String value = testProps.getProperty(propName);
		
		if ( (value == null) || (value.trim().length() == 0) )
		{
			return null;
		}
		else
		{
			return value;
		}
	}
	
	private Long getTestPropertyLong(String propName)
	{
		String value = testProps.getProperty(propName);
		
		if ( (value == null) || (value.trim().length() == 0) )
		{
			return null;
		}
		else
		{
			return new Long(value);
		}
	}
	
	@BeforeTest
	public void setUpWebExClient()
	{
 
		String siteName = getTestProperty("siteName");
		Long siteId = getTestPropertyLong("siteId");
		String partnerId = getTestProperty("partnerId");
		String siteUsername = getTestProperty("siteUsername");
		String sitePassword = getTestProperty("sitePassword");
		
		client = new WebexClient(
							siteName,
							siteId,
							partnerId,
							siteUsername, 
							sitePassword);
		
	}
}
