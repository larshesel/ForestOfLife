package client.model;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangRangeException;
import com.ericsson.otp.erlang.OtpErlangTuple;

public class Player {
	private final List<GameUnit> units;
	private String playerName;

	public Player(OtpErlangObject o) throws OtpErlangRangeException {
		this.units = new ArrayList<GameUnit>();

		if (o instanceof OtpErlangTuple) {
			OtpErlangTuple playerTuple = ((OtpErlangTuple) o);
			playerName = ((OtpErlangAtom) playerTuple.elementAt(0)).atomValue();
			OtpErlangList erlangUnits = (OtpErlangList) playerTuple
					.elementAt(1);
			for (OtpErlangObject erlangUnit : erlangUnits.elements()) {
				// {type, X, Y}
				GameUnit u = GameUnit.create(erlangUnit);
				units.add(u);
			}
		}
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public final List<GameUnit> getUnits() {
		return units;
	}

}