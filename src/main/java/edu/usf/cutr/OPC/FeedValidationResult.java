/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usf.cutr.OPC;

import java.io.Serializable;

public class FeedValidationResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Were we able to load the GTFS at all (note that this should only indicate corrupted files,
	 * not missing ones; that should raise an exception instead.)
	 */
	public LoadStatus loadStatus;
	
	/**
	 * Additional description of why the feed failed to load.
	 */
	public String loadFailureReason;
	
	/**
	 * The name of the feed on the file system
	 */
	public String feedFileName;		
}