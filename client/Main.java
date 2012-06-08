import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ericsson.otp.erlang.OtpAuthException;
import com.ericsson.otp.erlang.OtpConnection;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpPeer;
import com.ericsson.otp.erlang.OtpSelf;

public class Main extends JPanel {

	private static final String SERVER = "tol_server";
	private static final String SERVER_PROCESS = "gui_interface_server";
	
//	private static final String CLIENT = "gui_interface_client";
//	private static final String CLIENT_MBOX = "gui_interface_client_mbox";

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("Forest of Life", 50, 50);
	}

	public static void main(String[] args) {

		try {
			OtpSelf self = new OtpSelf("gui_interface_client");
			OtpPeer other = new OtpPeer(SERVER);
			OtpConnection conn;
			try {
				conn = self.connect(other);
				conn.sendRPC(SERVER_PROCESS, "get_state", new OtpErlangList());
			} catch (OtpAuthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not create an OTP node.");
		}

		JFrame frame = new JFrame("Forest of Life");
		frame.add(new Main());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}