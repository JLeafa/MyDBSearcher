package jp.akatsubakij.util;

import java.time.LocalDateTime;

/**
 * Created by Junya on 2017/06/28.
 */
public class ConnectionLog {
    public static final String LOG_HEADER = " [DEBUG] ";
    
    public static void MessageLog(String message){
        LocalDateTime LOG_TIME = LocalDateTime.now();;
        System.out.println(LOG_TIME + LOG_HEADER + message);
    }
}
