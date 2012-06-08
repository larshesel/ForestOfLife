package client.model;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangRangeException;
import com.ericsson.otp.erlang.OtpErlangTuple;

public class GameState {

	private final List<Player> players;
	public GameState(OtpErlangObject state) throws OtpErlangRangeException {
		players = new ArrayList<Player>();
		
		OtpErlangTuple tuple = ((OtpErlangTuple) state);
		//OtpErlangAtom ok = (OtpErlangAtom) tuple.elementAt(0);
		OtpErlangList playerList = (OtpErlangList) tuple.elementAt(1);
		for (OtpErlangObject o : playerList.elements()) {
			players.add(new Player(o));
		}
	}
	
	public List<Player> getPlayers() {
		return players;
	}
}
