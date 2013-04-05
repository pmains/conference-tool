package com.ultralinellc.webex.logic;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class RequestBuilder {
    private static volatile RequestBuilder requestBuilder = null;

    public static void main(String argv[]) {
        RequestBuilder rb = RequestBuilder.getRequestBuilder();
        Document doc;
        try {
            doc = rb.createCreateMeetingRequest();
        
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            
            // Output to console for testing
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

        System.out.println("");
    }

    public static RequestBuilder getRequestBuilder() {
        if(requestBuilder == null) {
            requestBuilder = new RequestBuilder();
        }
        return requestBuilder;
    }

    public Document createRequestTemplate() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
         
        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("serv:message");
        
        Attr attr = doc.createAttribute("xmlns:xsi");
        attr.setValue("http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttributeNode(attr);
        
        attr = doc.createAttribute("xmlns:serv");
        attr.setValue("http://www.webex.com/schemas/2002/06/service");
        rootElement.setAttributeNode(attr);
        
        attr = doc.createAttribute("xmlns:schemaLocation");
        attr.setValue("http://www.webex.com/schemas/2002/06/service");
        rootElement.setAttributeNode(attr);
        
        doc.appendChild(rootElement);
         
        // header element
        Element header = doc.createElement("header");
        rootElement.appendChild(header);
        
        // body element
        Element body = doc.createElement("body");
        rootElement.appendChild(body);
        
        // Security Context
        Element securityContext = doc.createElement("securityContext");
        header.appendChild(securityContext);
        
        Element webExID = doc.createElement("webExID");
        webExID.appendChild(doc.createTextNode("username"));
        securityContext.appendChild(webExID);
        
        Element password = doc.createElement("password");
        password.appendChild(doc.createTextNode("password"));
        securityContext.appendChild(password);
        
        Element siteID = doc.createElement("siteID");
        siteID.appendChild(doc.createTextNode("siteID"));
        securityContext.appendChild(siteID);
        
        Element partnerID = doc.createElement("partnerID");
        partnerID.appendChild(doc.createTextNode("partnerID"));
        securityContext.appendChild(partnerID);
         
        return doc;
    }
    
    public Document createCreateMeetingRequest() {
        try {
            Document doc = this.createRequestTemplate();
            Element body = (Element)doc.getElementsByTagName("body").item(0);
    
            Element bodyContent = doc.createElement("bodyContent");
            Attr attr = doc.createAttribute("xsi:type");
            attr.setValue("java:com.webex.service.binding.meeting.CreateMeeting");
            body.appendChild(bodyContent);
           
            // Access Control
            Element accessControl = doc.createElement("accessControl");
            bodyContent.appendChild(accessControl);

            accessControl.appendChild(createTextElement(doc, "meetingPassword", "mtg-passwd"));

            // Meta Data
            Element metaData = doc.createElement("metaData");
            bodyContent.appendChild(metaData);

            metaData.appendChild(createTextElement(doc, "confName", "Conference Name"));
            metaData.appendChild(createTextElement(doc, "meetingType", "1"));
            metaData.appendChild(createTextElement(doc, "agenda", "Test Agenda"));

            // Participants
            Element participants = doc.createElement("participants");
            bodyContent.appendChild(participants);

            participants.appendChild(createTextElement(doc, "maxUserNumber", "4"));

            Element attendees = doc.createElement("attendees");
            participants.appendChild(attendees);
            
            Element attendee = doc.createElement("attendee");
            attendees.appendChild(attendee);
            
            Element person = doc.createElement("person");
            attendee.appendChild(person);
            
            person.appendChild(createTextElement(doc, "name", "James Kirk"));
            person.appendChild(createTextElement(doc, "email", "jkirk@gmail.com"));

            // EnableOptions
            Element enableOptions = doc.createElement("enableOptions");
            bodyContent.appendChild(enableOptions);
            
            enableOptions.appendChild(createTextElement(doc, "chat",       "true"));
            enableOptions.appendChild(createTextElement(doc, "poll",       "true"));
            enableOptions.appendChild(createTextElement(doc, "audioVideo", "true"));
            
            // Schedule
            Element schedule = doc.createElement("schedule");
            bodyContent.appendChild(schedule);
            
            schedule.appendChild(createTextElement(doc, "startDate", "05/31/2004 10:10:10"));
            Element openTime = doc.createElement("openTime");
            openTime.appendChild(doc.createTextNode("900"));
            schedule.appendChild(openTime);
            
            Element joinTeleconfBeforeHost = doc.createElement("joinTeleconfBeforeHost");
            joinTeleconfBeforeHost.appendChild(doc.createTextNode("true"));
            schedule.appendChild(joinTeleconfBeforeHost);
            
            Element duration = doc.createElement("duration");
            duration.appendChild(doc.createTextNode("20"));
            schedule.appendChild(duration);
            
            Element timeZoneID = doc.createElement("timeZoneID");
            timeZoneID.appendChild(doc.createTextNode("4"));
            schedule.appendChild(timeZoneID);
            
            // Telephony
            Element telephony = doc.createElement("telephony");
            bodyContent.appendChild(telephony);
            
            telephony.appendChild(createTextElement(doc, "telephonySupport", "CALLIN"));
            telephony.appendChild(createTextElement(doc, "extTelephonyDescription", "Call 1-800-555-1234, Passcode 98765"));
            
            return doc;
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

        return null;
    }
    
    public Document createSetMeetingRequest() {
        try {
            Document doc = this.createRequestTemplate();
            Element body = (Element)doc.getElementsByTagName("body").item(0);
            return doc;
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        return  null;
    }
    
    public Document createDeleteMeetingRequest() {
        try {
            Document doc = this.createRequestTemplate();
            Element body = (Element)doc.getElementsByTagName("body").item(0);
            return doc;
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        return  null;

    }
    
    public Document createGetHostUrlMeetingRequest() {
        try {
            Document doc = this.createRequestTemplate();
            Element body = (Element)doc.getElementsByTagName("body").item(0);
            return doc;
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        return  null;
    }

    private Attr createAttr(Document doc, String name, String value) {
        Attr attr = doc.createAttribute(name);
        attr.setValue(value);
        return attr;
    }

    private Element createTextElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
}
