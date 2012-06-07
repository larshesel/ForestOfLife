import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ericsson.otp.erlang.OtpMbox;
import com.ericsson.otp.erlang.OtpNode;

public class Main extends JPanel {

	private static final String SERVER = "tol_server";

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawString("Java 2D", 50, 50);
	}

	public static void main(String[] args) {

		try {
			OtpNode self = new OtpNode("client");
			OtpMbox mbox = self.createMbox("client_mbox");
			if (self.ping(SERVER, 1000)) {
				// connected!
				TolClient tolClient = new TolClient(self, mbox);
				tolClient.start();
			} else {
				Log.d("INIT: could not connect to " + SERVER);
				throw new RuntimeException("Could not connect to: " + SERVER);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not create an OTP node.");
		}

		JFrame frame = new JFrame("Tree of Life");
		frame.add(new Main());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}