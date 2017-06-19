package com.vehiclecontroler.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;

import com.vehiclecontroler.util.APPConstants;


public class ClientUDP {


	public String getStatusController() {
		return statusController;
	}
	public void setStatusController(String statusController) {
		this.statusController = statusController;
	}

	private String statusController=null;
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(Integer.toHexString(APPConstants.CONTROL_PACKET_TYPE+APPConstants.FORWARD_COMMAND+APPConstants.DEFAULT_VALUE));
String s = Integer.toHexString(APPConstants.CONTROL_PACKET_TYPE+APPConstants.FORWARD_COMMAND+APPConstants.DEFAULT_VALUE);
/*		int decimal = Integer.parseInt(s, 16);
		char [] c = new char[3];
		c[0]='a';
		c[1]='b';
		c[2]='c';
		
		
		c[0]=APPConstants.START_WORD;
		c[1]=APPConstants.CONTROL_PACKET_TYPE;
		c[2]=APPConstants.DEFAULT_VALUE;
		String s1 = new String(c);
		byte[] b = s1.getBytes("UTF-8");
		String str = new String(b, "UTF-8");
	//	String s2 = new String(b);
		System.out.println(s1.charAt(0)==str.charAt(0));*/
		
		ClientUDP clientUDP= new ClientUDP();
	//clientUDP.startCommanding("FORWARD","127.0.0.1",49150);
int i=0;
		while (i<1000) {
		i++;	
	try {
/*
		System.out.println(IPUTILS.ip);
		System.out.println(IPUTILS.portInt);*/
		clientUDP.startCommanding("LEFT","192.168.100.250",49150);
		Thread.sleep(1000);
		
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		

		}
	// clientUDP.startCommanding("LEFT","127.0.0.1",49150);
//	clientUDP.startCommanding("RIGHT","127.0.0.1",49150);
	
	}
/**
 * 
 * @param cmd Accepts requesting Command to send to server
 * @param ip accepts IP address of server
 * @param port accepts portno of server
 * @return 
 */
boolean startCommanding(String cmd , String ip , int port ){
	String dataForServer = generateRequestData(cmd);
	System.out.println(dataForServer);
	System.out.println("Size"+dataForServer.length());
	System.out.println("sending data ");
	System.out.println(dataForServer);
	try {
	byte[]  responseDataFromServer =	sendRequest(dataForServer, ip,port);


	System.out.println("receaved data ");
	System.out.println(responseDataFromServer);


	//System.out.println(dataForServer.equals(responseDataFromServer));
	
	Boolean b = validateServerResponse(responseDataFromServer);
	if(b){
		System.out.println("JDBC CODDE GOES HERE TO SAVE DATA");
		return true;
	}else{
		System.out.println("IVALID RESPONSE");
	}

	/*if (responseDataFromServer.charAt(0)==APPConstants.START_WORD)
{
	return true;	
	}	else{
		return false;
	}*/
	return false;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

		return false;
	}


	
}	
/**
 * 
 * @param responseDataFromServer
 * @return
 */
	private Boolean validateServerResponse(byte[] responseDataFromServer) {
	// TODO Auto-generated method stub

		   for (int j = 0; j <10; j++) {

			   System.out.printf("%X ",responseDataFromServer[j]);
			   	    	
		}
		short startWord = (short) ((responseDataFromServer[1] << 8) | responseDataFromServer[0]);
		//char startWord =	responseDataFromServer.charAt(0);
System.out.printf("StartWord : %X", startWord);


boolean b= false;
short packetType =	 (short) ((responseDataFromServer[3] << 8) | responseDataFromServer[2]);

System.out.printf("packettype : %X", packetType);


short command =	 (short) ((responseDataFromServer[5] << 8) | responseDataFromServer[4]);

System.out.printf("command : %X", command);


short statusInfo =	 (short) (responseDataFromServer[7] << 8);

if(responseDataFromServer[6] < 0x0)
{
	short temp = 0x0;
	temp = (byte)(responseDataFromServer[6] & (byte)(0x7F));
	System.out.printf("Debug Part 1 : %X\n", temp);
	
	statusInfo =(short)(statusInfo |  temp);
	
	System.out.printf("Debug Part 2 : %X\n", statusInfo);
	
	statusInfo = (short)(statusInfo | 0x80);
	
	System.out.printf("Debug Part 3 : %X\n", statusInfo);
	
	
}
else
{
	statusInfo =(short)(statusInfo |  responseDataFromServer[6]);
}

System.out.printf("statusInfo : %X", statusInfo);


short checkSum = (short)(responseDataFromServer[9] << 8);

System.out.printf("checksum[8] 2 : %X\n", responseDataFromServer[8]);

if(responseDataFromServer[8] < 0x0)
{
	short temp = 0x0;
	temp = (byte)(responseDataFromServer[8] & (byte)(0x7F));
	System.out.printf("Debug Part 1 : %X\n", temp);
	
	checkSum =(short)(checkSum |  temp);
	
	System.out.printf("Debug Part 2 : %X\n", checkSum);
	
	checkSum = (short)(checkSum | 0x80);
	
	System.out.printf("Debug Part 3 : %X\n", checkSum);
	
	
}
else
{
	checkSum =(short)(checkSum |  responseDataFromServer[8]);
}

System.out.printf("Chcksum Part 1 : %X\n", checkSum);
/*int checkSum = 0;

checkSum = checkSum +  (responseDataFromServer[9] << 8);

System.out.printf("Chcksum Part 1 : %X\n", checkSum);

checkSum =(int)( checkSum |responseDataFromServer[8]);

System.out.printf("Chcksum Part 2 : %X\n", checkSum); */

System.out.printf("8  :  %X \n",responseDataFromServer[8]);
System.out.printf("9  :  %X \n",responseDataFromServer[9]);
System.out.printf("ChKSUM : %X", checkSum);
		if((short)(startWord)==APPConstants.START_WORD){
			
			 if((short)(packetType) == (short)APPConstants.HELTH_RESPONSE){
				statusController=APPConstants.TEST;
			b=true;

			return b;
			 }else if((short)(packetType)==APPConstants.STATUS_PACKET_TYPE){
				
				 b = ((short)(packetType+command+statusInfo) == ((short)checkSum));

					System.out.println("Boolean Result"+b);
	
					if(b){
							
						System.out.printf("STATUS InFO ! %X",statusInfo);
						if(statusInfo==APPConstants.STATUS_INFO_MOVING_FORWARD){
							System.out.println("MOVED FORWARD");
									statusController=APPConstants.INPUT_FORWARD_MSG;
							
							
						}else if(statusInfo==APPConstants.STATUS_INFO_MOVING_REVERSE){
							System.out.println("MOVED REVERSE");

							statusController=APPConstants.INPUT_REVERSE_MSG;
									
						}else if(statusInfo==APPConstants.STATUS_INFO_MOVING_LEFT){
							System.out.println("MOVED LEFT");

							statusController=APPConstants.INPUT_LEFT_MSG;
									
						}else if(statusInfo==APPConstants.STATUS_INFO_MOVING_RIGHT){
							System.out.println("MOVED Right");

							statusController=APPConstants.INPUT_RIGHT_MSG;
									
						}else if(statusInfo==APPConstants.STATUS_INFO_OBSTACLE_FORWARD_DIRECTION){
							System.out.println("OBSTACLE FOUNT IN FORWARD");

							statusController=APPConstants.OBSTACLE_FORWARD;
									
						}else if(statusInfo==APPConstants.STATUS_INFO_OBSTACLE_REVERSE_DIRECTION){
							System.out.println("OBSTACLE FOUND IN REVERSE");
						statusController=APPConstants.OBSTACLE_REVERSE;
								
							
						}
						else if((short)statusInfo==(short)APPConstants.STATUS_INFO_IDEALE){
							System.out.println("OBSTACLE FOUND IN REVERSE");
							statusController=APPConstants.IDEALE_POSITION;
								
						}
						else{
							System.out.println( "invalid status Info");
							b= false;
						}
						System.out.println();
						
						
					}
					
					else{

System.out.printf("ck : %X, %X", checkSum, packetType+command+statusInfo);
						System.out.println("INVALID CHECK SUM");
					}
			}else{
				System.out.println("response comes from Devise not from Server");
				
			}
			
			
		}else{
			System.out.println("UNKNOWN SERVER");
		}
		

	
	return b;
	
	}

	public Boolean validateResponseWithCheckSum(char packetType, char command ,char statusInfo , char  checkSum){
		

		if(checkSum == packetType+checkSum+statusInfo){
		return true;
		}else{
			return false;
		}
		
	}
	
	
	byte [] charArrToByteArr(char []c){
		byte[] b = new byte[c.length*2];
		 for(int i=0; i<c.length; i++) {
		     b[2*i] = (byte) ((c[i]&0xFF00)>>8); 
		     b[2*i+1] = (byte) (c[i]&0x00FF); 
		 }
		 return b;
	}
	
	
	
	
	
	private byte[] charToBytes(final char x) {
		  String temp = new String(new char[] {x});
		  try {
		    return temp.getBytes("ISO-8859-1");
		  } catch (UnsupportedEncodingException e) {
		    // Log a complaint
		    return null;
		  }
		}
	
	
	
	/**
	 * it is used to prepare request Command PAckets to send for server
	 * @param command accepts user request type (FORWARD (or)  RIGHT (or)  LEFT (or) REVERSE)
	 * @return prepared Request command Data 
	 */
public	String generateRequestData(String command){
		
	 char [] request = new char[5];
	 request[0]=APPConstants.START_WORD;
	 
	 
	 
	 
	 request[1]=APPConstants.CONTROL_PACKET_TYPE;

	 
	 
	 request[2]= getControlCommand(command);
	 
	 if(command.equalsIgnoreCase("TEST")){
	request[1]=APPConstants.HELTH_REQUEST;	
		 
	 }
	 request[3]=APPConstants.DEFAULT_VALUE;
	 request[4]=(char) (request[1]+request[2]+request[3]);

	 byte [] b1 = charArrToByteArr(request);
	 

	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+b1.length);
	 //ch
	return new String(b1);
	 
/*	 
	 
	
	 byte [] start=  charToBytes(APPConstants.START_WORD);
	
	 
	 byte [] start1=  charToBytes(APPConstants.CONTROL_PACKET_TYPE);
if(command.equalsIgnoreCase("TEST")){
	start1= charToBytes( APPConstants.HELTH_REQUEST);
		
	
}
	 
	 byte [] start2=  charToBytes(getControlCommand(command));
	byte [] start3=  charToBytes(APPConstants.DEFAULT_VALUE);
	
	char cksum=(char) (request[1]+request[2]+request[3]);
	
	
	String hex = String.format("0x%02X", (int)APPConstants.START_WORD);
start =	hex.getBytes();

hex = String.format("0x%02X", (int)APPConstants.CONTROL_PACKET_TYPE);
start1 =	hex.getBytes();

hex = String.format("0x%02X", (int)getControlCommand(command));
start2 =	hex.getBytes();

hex = String.format("0x%02X", (int)APPConstants.DEFAULT_VALUE);
start3 =	hex.getBytes();

hex = String.format("0x%02X", APPConstants.CONTROL_PACKET_TYPE+getControlCommand(command)+APPConstants.DEFAULT_VALUE);
byte [] start4 =	hex.getBytes();

*/
/*hex = String.format("0x%02X", (int)APPConstants.START_WORD);
start4 =	hex.getBytes();
*/
/*	int len = start1.length+start2.length+start3.length;
	 byte[] allb=	new byte[start.length+len+len];
int ind=0;
for (int i = 0; i < start.length; i++) {
		allb[ind]=start[i];
		ind++;
}

for (int i = 0; i < start1.length; i++) {
	allb[ind]=start1[i];
	ind++;
}
for (int i = 0; i < start2.length; i++) {
	allb[ind]=start2[i];
	ind++;
}
for (int i = 0; i < start3.length; i++) {
	allb[ind]=start3[i];
	ind++;
}

for (int i = 0; i < start4.length; i++) {
	allb[ind]=start4[i];
	ind++;
}
*/
	}
		

/**
 * 
 * @param command accepts request action command(FORWARD (or)  RIGHT (or)  LEFT (or) REVERSE)
 * @return
 */
		public char	getControlCommand(String command){
		 if(command.equalsIgnoreCase(APPConstants.INPUT_FORWARD)){
			 return APPConstants.FORWARD_COMMAND;
			 }else if (command.equalsIgnoreCase(APPConstants.INPUT_LEFT)) {
				return APPConstants.LEFT_COMMAND;
			}else if (command.equalsIgnoreCase(APPConstants.INPUT_REVERSE)) {
				return APPConstants.REVERSE_COMMAND;
			}else if (command.equalsIgnoreCase(APPConstants.INPUT_RIGHT)) {
				return APPConstants.RIGHT_COMMAND;
			}
			else if(command.equalsIgnoreCase("STOP")){
				return APPConstants.STATUS_INFO_STOP;
					
			}
			else{
				return APPConstants.DEFAULT_VALUE;
			}
		}
	
		
		
		
		
	/**
	 * to convert String to byte array but not used
	 * @param s
	 * @return
	 */
		public static byte[] hexStringToByteArray(String s) {
		    int len = s.length();
		    byte[] data = new byte[len / 2];
		    for (int i = 0; i < len; i += 2) {
		        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
		                             + Character.digit(s.charAt(i+1), 16));
		    }
		    return data;
		}
		
		/**
		 * 
		 * @param sentence accepts request command to send data for server 
		 * @param ipAddress accepts ip address of server
		 * @param portNo accepts portno of server
		 * @return returns Response Data from server
		 * @throws IOException
		 */
	public byte[] sendRequest( String sentence,String ipAddress , Integer portNo) throws IOException{
		DatagramSocket clientSocket = null;
			try {
				

				InetSocketAddress is=	new InetSocketAddress(portNo);
				clientSocket = new DatagramSocket(is);
				clientSocket.setReuseAddress(true);
				clientSocket.setSoTimeout(200);
				/*	clientSocket.bind(is);
			*/
				InetAddress IPAddress;
			
				
				
					IPAddress = InetAddress.getByName(ipAddress);
					byte[] sendData = new byte[1024];
			   	      byte[] receiveData = new byte[1024];
			   	      //sendData = sentence.getBytes("UTF-8");
			   	   sendData = sentence.getBytes();
			   	      
			   	      System.out.println(sendData.length);
			   	      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, portNo);
						clientSocket.send(sendPacket);
						
						 
			//	clientSocket.bind(is);
					//InetAddress i = InetAddress.getByName("0.0.0.0");
						
						DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
						try{
				   	    clientSocket.receive(receivePacket);
				   	    
						}catch(SocketTimeoutException se){
							
							sendRequest(sentence,ipAddress,portNo);
						}
				   	    
						byte[] bytes=	 receivePacket.getData();
				   	   
				   for (int j = 0; j <10; j++) {

					   System.out.printf("%X ",bytes[j]);
					   	    	
				}
				   	    //  String responseSentence = new String(receivePacket.getData());
				   	     // System.out.println(modifiedSentence.charAt(0)==0x5555);
				   	   //   System.out.println("FROM SERVER:" + responseSentence);
				   	    return bytes;
					} finally{
				if(clientSocket!=null){
					 clientSocket.close();
				}
			}
	   	      
		
	}





}
