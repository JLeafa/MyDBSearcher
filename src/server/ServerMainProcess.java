package server;

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
		new Receiver().exec();
	}
}