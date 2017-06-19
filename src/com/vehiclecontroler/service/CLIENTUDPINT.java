package com.vehiclecontroler.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.vehiclecontroler.util.APPConstants;
import com.vehiclecontroler.util.IPUTILS;

public class CLIENTUDPINT {

	
	

		private String statusController;
		public String getStatusController() {
			return statusController;
		}
		public void setStatusController(String statusController) {
			this.statusController = statusController;
		}
		
	/*
		private String statusController=null;*/
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

			System.out.println(IPUTILS.ip);
			System.out.println(IPUTILS.portInt);
			clientUDP.startCommanding("FORWARD",IPUTILS.ip,IPUTILS.portInt);
			Thread.sleep(1000);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			

			}
		// clientUDP.startCommanding("LEFT","127.0.0.1",49150);
//		clientUDP.startCommanding("RIGHT","127.0.0.1",49150);
		
		}
		
		
		byte[] intArrToBytesArr(int [] data){
			
			 ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 4);        
		        IntBuffer intBuffer = byteBuffer.asIntBuffer();
		        intBuffer.put(data);

		        byte[] array = byteBuffer.array();
		return array;
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
		String responseDataFromServer =	sendRequest(dataForServer, ip,port);


		System.out.println("receaved data ");
		System.out.println(responseDataFromServer);


		//System.out.println(dataForServer.equals(responseDataFromServer));
		
		Boolean b = validateServerResponse(responseDataFromServer);
	/*	if(b){
			System.out.println("JDBC CODDE GOES HERE TO SAVE DATA");
		}else{
			System.out.println("IVALID RESPONSE");
		}*/

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
		private Boolean validateServerResponse(String responseDataFromServer) {
		// TODO Auto-generated method stub
			
	/*		char startWord =	responseDataFromServer.charAt(0);

			char packetType =	responseDataFromServer.charAt(1);

			char command =	responseDataFromServer.charAt(2);

			char statusInfo =	responseDataFromServer.charAt(3);

			char checkSum =	responseDataFromServer.charAt(4);

			boolean b= false;				
			if(startWord==APPConstants.START_WORD){
				if(packetType==APPConstants.STATUS_PACKET_TYPE){
					
					 b = packetType+command+statusInfo == checkSum;

						System.out.println(b);
		
						if(b){
								
							if(statusInfo==APPConstants.STATUS_INFO_MOVING_FORWARD){
								System.out.println("MOVED FORWARD");
										statusController=APPConstants.INPUT_FORWARD;
								
								
							}else if(statusInfo==APPConstants.STATUS_INFO_MOVING_REVERSE){
								System.out.println("MOVED REVERSE");

								statusController=APPConstants.INPUT_REVERSE;
										
							}else if(statusInfo==APPConstants.STATUS_INFO_MOVING_LEFT){
								System.out.println("MOVED LEFT");

								statusController=APPConstants.INPUT_LEFT;
										
							}else if(statusInfo==APPConstants.STATUS_INFO_MOVING_RIGHT){
								System.out.println("MOVED Right");

								statusController=APPConstants.INPUT_RIGHT;
										
							}else if(statusInfo==APPConstants.STATUS_INFO_OBSTACLE_FORWARD_DIRECTION){
								System.out.println("OBSTACLE FOUNT IN FORWARD");

								statusController=APPConstants.OBSTACLE_FORWARD;
										
							}else if(statusInfo==APPConstants.STATUS_INFO_OBSTACLE_REVERSE_DIRECTION){
								System.out.println("OBSTACLE FOUND IN REVERSE");
								statusController=APPConstants.OBSTACLE_REVERSE;
									
								
							}else{
								System.out.println( "invalid status Info");
								b= false;
							}
							System.out.println();
							
							
						}else{
							System.out.println("INVALID CHECK SUM");
						}
				}else{
					System.out.println("response comes from Devise not from Server");
					
				}
				
				
			}else{
				System.out.println("UNKNOWN SERVER");
			}
			

	*/	
		return null;//b;
		
		}

		public Boolean validateResponseWithCheckSum(char packetType, char command ,char statusInfo , char  checkSum){
			

			if(checkSum == packetType+checkSum+statusInfo){
			return true;
			}else{
				return false;
			}
			
		}
		
		
		/**
		 * it is used to prepare request Command PAckets to send for server
		 * @param command accepts user request type (FORWARD (or)  RIGHT (or)  LEFT (or) REVERSE)
		 * @return prepared Request command Data 
		 */
	public	String generateRequestData(String command){
			
		 int [] request = new int[5];
		 request[0]=APPConstants.START_WORD;
		 request[1]=APPConstants.CONTROL_PACKET_TYPE;
		 request[2]= getControlCommand(command);
		 request[3]=APPConstants.DEFAULT_VALUE;
		 request[4]=(char) (request[1]+request[2]+request[3]);

		byte [] b=  intArrToBytesArr(request);
		 
		 System.out.println("BYTES LENGTH"+b.length);
		return new String(b);
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
				}else{
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
		public String sendRequest( String sentence,String ipAddress , Integer portNo) throws IOException{
			DatagramSocket clientSocket = null;
				try {
					clientSocket = new DatagramSocket();
				
					InetAddress IPAddress;
				
						IPAddress = InetAddress.getByName(ipAddress);
						byte[] sendData = new byte[1024];
				   	      byte[] receiveData = new byte[1024];
				   	      //sendData = sentence.getBytes("UTF-8");

				   	      sendData = sentence.getBytes();
				   	      System.out.println(sendData.length);
				   	      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, portNo);
							clientSocket.send(sendPacket);
					   	   //   DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					   	     // clientSocket.receive(receivePacket);
					   	      //String responseSentence = new String(receivePacket.getData(),"UTF-8");
					   	     // System.out.println(modifiedSentence.charAt(0)==0x5555);
					   	      //System.out.println("FROM SERVER:" + responseSentence);
					   	      return "";// responseSentence;
						} finally{
					if(clientSocket!=null){
						 clientSocket.close();
					}
				}
		   	      
			
		}

	

	
}
