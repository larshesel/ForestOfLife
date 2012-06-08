package client;

import java.io.IOException;

import javax.swing.JFrame;


import client.gui.GameField;
import client.model.GameState;

import com.ericsson.otp.erlang.OtpAuthException;
import com.ericsson.otp.erlang.OtpConnection;
import com.ericsson.otp.erlang.OtpErlangExit;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangRangeException;
import com.ericsson.otp.erlang.OtpPeer;
import com.ericsson.otp.erlang.OtpSelf;

public class Main {

	private static final String SERVER = "tol_server";
	private static final String SERVER_PROCESS = "gui_interface_server";

	// private static final String CLIENT = "gui_interface_client";
	// private static final String CLIENT_MBOX = "gui_interface_client_mbox";

	public static void main(String[] args) {

		GameField gameField = new GameField();

		JFrame frame = new JFrame("Forest of Life");
		frame.add(gameField);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		try {
			OtpSelf self = new OtpSelf("gui_interface_client");
			OtpPeer other = new OtpPeer(SERVER);
			OtpConnection conn;
			try {
				conn = self.connect(other);
				conn.sendRPC(SERVER_PROCESS, "get_state", new OtpErlangList());
				OtpErlangObject state = conn.receiveRPC();
				GameState gameState = new GameState(state);
				gameField.update(gameState);
			} catch (OtpAuthException e) {
				e.printStackTrace();
			} catch (OtpErlangExit e) {
				e.printStackTrace();
			} catch (OtpErlangRangeException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not create an OTP node.");
		}
	}
}