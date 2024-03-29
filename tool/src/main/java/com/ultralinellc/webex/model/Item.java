package com.ultralinellc.webex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An example item
 * 
 * @author Mike Jennings (mike_jennings@unc.edu)s
 * 
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	private long id;
	private String name;
}
