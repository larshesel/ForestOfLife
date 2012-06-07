import java.util.logging.Level;
import java.util.logging.Logger;



public class Log {

	private final static String NAME = "CLIENT";
	
	public static void d(String msg) {
		Logger.getLogger(NAME).log(Level.FINEST, msg);
	}
}
