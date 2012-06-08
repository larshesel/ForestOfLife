package client;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Log {

	private final static String NAME = "CLIENT";
	
	public static void d(final String msg) {
		Logger.getLogger(NAME).log(Level.FINEST, msg);
	}
	public static void d(final String tag, final String msg) {
		Logger.getLogger(tag).log(Level.FINEST, msg);
	}
}
