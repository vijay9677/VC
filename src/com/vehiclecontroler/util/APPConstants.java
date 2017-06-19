/**
 * 
 */
package com.vehiclecontroler.util;

/**
 * @author Vijay Kumar Reddy K
 *
 */
public class APPConstants {
	public static final char START_WORD=0x5555;
	public static final char CONTROL_PACKET_TYPE=0x1234;
	public static final char STATUS_PACKET_TYPE=0x4321;

	public static final char DEFAULT_VALUE=0x0000;
	public static final char FORWARD_COMMAND=0x1111;
	public static final char REVERSE_COMMAND=0x2222;
	public static final char LEFT_COMMAND=0x3333;
	public static final char RIGHT_COMMAND=0x4444;
	public static final char STATUS_INFO_STOP=0X55FF;
	

	public static final char STATUS_INFO_MOVING_FORWARD=0x1155;
	public static final char STATUS_INFO_MOVING_REVERSE=0x1166;
	public static final char STATUS_INFO_MOVING_LEFT=0x1177;
	public static final char STATUS_INFO_MOVING_RIGHT=0x1188;	
	public static final char STATUS_INFO_OBSTACLE_FORWARD_DIRECTION=0x1199;
	public static final char STATUS_INFO_OBSTACLE_REVERSE_DIRECTION=0x11AA;
	public static final char STATUS_INFO_IDEALE=0x11BB;
	public static final char STATUS_INFO_ERROR=0x11CC;
	
	public static final String INPUT_FORWARD="FORWARD";
	public static final String INPUT_REVERSE="REVERSE";
	public static final String INPUT_LEFT="LEFT";
	public static final String INPUT_RIGHT="RIGHT";
	
	public static final String OBSTACLE_FORWARD = "OBSTACLE FOUND AT FRONT";
	public static final String OBSTACLE_REVERSE = "OBSTACLE FOUND AT BEHIND";
	public static final String INPUT_FORWARD_MSG = "MOVED FORWARD";
	public static final String INPUT_REVERSE_MSG = "MOVED REVERSE";
	public static final String INPUT_RIGHT_MSG = "MOVED RIGHT";
	public static final String INPUT_LEFT_MSG = "MOVED LEFT";
	public static final String IDEALE_POSITION = "NO MOVEMENT IDEAL POSITION";

	public static final String TEST = "SUCCESS";

	public static final String  ERROR_CONTROL_MESSAGE = "ERROR OCCURED";
	public static final char HELTH_REQUEST = 0x5678;
	public static final char HELTH_RESPONSE = 0x8765;
	
}
