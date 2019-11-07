package server;

import server.*;
import java.lang.Thread;

/**
* Server side class
*/

public class ServerMainProcess
{
	public static final int PORT = 10000;

	/**
	* main class
	* open socket
	* @param args String[]
	*/
	public static void main(String[] args) throws ClassNotFoundException {
		new Thread(new ServerPrompt()).start();
		new Receiver().exec();
	}
}