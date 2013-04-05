package com.ultralinellc.webex.logic;

import java.net.*;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;

import com.ultralinellc.webex.model.Item;
import com.ultralinellc.webex.logic.RequestBuilder;

/**
 * Implementation of {@link ProjectLogic}
 * 
 * @author Mike Jennings (mike_jennings@unc.edu)
 *
 */
public class ProjectLogicImpl implements ProjectLogic {

	private static final Logger log = Logger.getLogger(ProjectLogicImpl.class);

    @Getter @Setter
    private String siteName = "ultralinellc";           // WebEx site name
    @Getter @Setter
    private String xmlURL = "WBXService/XMLService";    // XML API URL
    @Getter @Setter
    private String siteID = "9839";                     // Site ID
    @Getter @Setter
    private String partnerID = "9839";                  // Partner ID
    @Getter @Setter
    private String webExID = "pmains";                  // Host username
    @Getter @Setter
    private String password = "TC4nn0n$$@";             // Host password
    private RequestBuilder requestBuilder = null;

    public RequestBuilder getRequestBuilder() {
        if(this.requestBuilder == null) {
            this.requestBuilder = RequestBuilder.getRequestBuilder();
        }
        return this.requestBuilder;
    }

    /**
     * {@inheritDoc}
     */
    public void createMeeting() {
        // create XML
        String reqXML = this.getRequestBuilder().createCreateMeetingRequest().toString();
        // send XML to end-point
        String respXML = sendReqToWebexSvc(reqXML);
    }

    /**
     * {@inheritDoc}
     */
    public void setMeeting() {
        // create XML
        String reqXML = this.getRequestBuilder().createSetMeetingRequest().toString();
        // send XML to end-point
        String respXML = sendReqToWebexSvc(reqXML);
    }

    /**
    * Delete an existing meeting.
    **/
    public void deleteMeeting() {
        // create XML
        String reqXML = this.getRequestBuilder().createDeleteMeetingRequest().toString();
        // send XML to end-point
        String respXML = sendReqToWebexSvc(reqXML);
    }

    /**
    * Get the host's URL for starting a meeting.
    **/
    public void getHostUrlMeeting() {
        // create XML
        String reqXML = this.getRequestBuilder().createGetHostUrlMeetingRequest().toString();
        // send xml to endpoint
        String respXML = sendReqToWebexSvc(reqXML);
    }

    // Send and XML request to the Webex XML Service
    public String sendReqToWebexSvc(String reqXML) {
        String xmlServerURL = "https://"+siteName+".webex.com/"+xmlURL;
        
        // connect to XML server
        try {
            URL urlXMLServer = new URL(xmlServerURL);
            
            // URLConnection supports HTTPS protocol only with JDK 1.4+
            URLConnection urlConnectionXMLServer = urlXMLServer.openConnection();
            urlConnectionXMLServer.setDoOutput(true);
            
            PrintWriter out = new PrintWriter(urlConnectionXMLServer.getOutputStream());
            out.println(reqXML);
            out.close();
    	
            // read response
            BufferedReader in = new BufferedReader(new
                InputStreamReader(urlConnectionXMLServer.getInputStream()));
            String line;
            String respXML = "";
            while ((line = in.readLine()) != null) {
                respXML += line;
            }
            in.close();
            
            // output response
            respXML = URLDecoder.decode(respXML,"UTF-8");  
        return respXML;
        } catch(MalformedURLException mue) {
            mue.printStackTrace();
        } catch(UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
	
	/**
	 * init - perform any actions required here for when this bean starts up
	 */
	public void init() {
		log.info("init");
	}
}
