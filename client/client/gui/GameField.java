package client.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import client.Log;
import client.model.GameState;
import client.model.GameUnit;
import client.model.Player;
import client.model.Supervisor;
import client.model.Worker;


public class GameField extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3544845549548981179L;
	private GameState state;

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setBackground(Color.BLACK);
		g2d.clearRect(0, 0, getWidth(), getHeight());
		g2d.drawString("Forest of Life", 50, 50);
		drawState(g2d, state);
	}

	private void drawUnit(Graphics2D g, Supervisor unit) {
		g.setColor(Color.RED);
		g.drawRect(unit.getPosition().x, unit.getPosition().y, 1, 1);
	}

	private void drawUnit(Graphics2D g, Worker unit) {
		g.setColor(Color.GREEN);
		g.drawRect(unit.getPosition().x, unit.getPosition().y, 1, 1);
	}

	private void drawState(Graphics2D g2d, GameState state) {
		if (state != null) {
			List<Player> players = state.getPlayers();
			for (Player p : players) {
				drawPlayer(g2d, p);
			}
		} else {
			Log.d("GameField", "state is null - nothing to draw");
		}
	}

	private void drawPlayer(Graphics2D g2d, Player p) {
		for(GameUnit u : p.getUnits()) {
			if (u instanceof Supervisor) {
				drawUnit(g2d, (Supervisor)u);	
			} else if (u instanceof Worker) {
				drawUnit(g2d, (Worker)u);
			}
			
		}
	}

	public void update(GameState state) {
		this.state = state;
		this.repaint();
	}
}
