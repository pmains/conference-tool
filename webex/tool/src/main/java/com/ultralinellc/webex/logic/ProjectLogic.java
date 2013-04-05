package com.ultralinellc.webex.logic;

import java.util.List;

import com.ultralinellc.webex.model.Item;

/**
 * An example logic interface
 * 
 * @author Mike Jennings (mike_jennings@unc.edu)
 *
 */
public interface ProjectLogic {

	/**
	 * Schedule a new meeting
	 * @return
     */
	public void createMeeting();
	
	/**
     * Update the details of an existing meeting.
     * @return
     */
    public void setMeeting();
}
