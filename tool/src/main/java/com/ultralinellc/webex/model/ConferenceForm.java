package com.ultralinellc.webex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

/**
 * Webex Conference
 *
 * @author Peter Mains (peter.mains@gmail.com)
 *
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceForm extends Conference {
    private Integer month;
    private Integer date;
    private Integer year;
    private Integer hour;
    private Integer minute;
    private String ampm;
}
