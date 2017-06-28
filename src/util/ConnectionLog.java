package util;

/**
 * Created by Junya on 2017/06/28.
 */
public class ConnectionLog {
    public static final String LOG_HEADER = "DEBUG : ";

    public static void MessageLog(String message){
        System.out.print(LOG_HEADER + message);
    }
}
