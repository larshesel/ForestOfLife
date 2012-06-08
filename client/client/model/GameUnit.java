package client.model;

import java.awt.Point;

import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangLong;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangRangeException;
import com.ericsson.otp.erlang.OtpErlangTuple;

public abstract class GameUnit {

	protected final int x;
	protected final int y;
	private final Type type;

	public Type getType() {
		return type;
	}

	public enum Type {
		SUPERVISOR, 
		WORKER
	}
	
	public GameUnit(final Type type, final int x, final int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public static GameUnit create(OtpErlangObject erlangUnit) throws OtpErlangRangeException {
		OtpErlangTuple unitTuple = (OtpErlangTuple) erlangUnit;
		OtpErlangAtom erlangType = (OtpErlangAtom) unitTuple.elementAt(0);
 		OtpErlangLong x = (OtpErlangLong) unitTuple.elementAt(1);
		OtpErlangLong y = (OtpErlangLong) unitTuple.elementAt(2);

		GameUnit gu = null;
		if ("supervisor".equals(erlangType.atomValue())) {
			gu = new Supervisor(x.intValue(), y.intValue());
		} else {
			gu = new Worker(x.intValue(), y.intValue());
		}
		
		return gu;
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
}
