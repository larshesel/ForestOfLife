import com.ericsson.otp.erlang.OtpErlangDecodeException;
import com.ericsson.otp.erlang.OtpErlangExit;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpMbox;
import com.ericsson.otp.erlang.OtpNode;


public class TolClient extends Thread {

	private final OtpNode self;
	private final OtpMbox mbox;

	public TolClient(final OtpNode self, final OtpMbox mbox) {
		this.self = self;
		this.mbox = mbox;
	}
	
	@Override
	public void run() {
		System.out.println("Started receive loop");
		while(true) {
			try {
				OtpErlangObject receive = mbox.receive();
				System.out.println("Received: " + receive);
				
			} catch (OtpErlangExit e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OtpErlangDecodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
