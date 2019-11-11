package gui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gui.ships.Ship;
import main.K;
import main.K.PHASE;
import rules.designPatterns.RulesFacade;

@SuppressWarnings("serial")
public class Cell extends JPanel implements MouseListener{
	
	private int x;
	private int y;
	
	private Rectangle2D.Double square;
	
	private Color cellColor;
	private Color borderColor;
	private Color shipColor;
	
	public Cell(int x, int y) {
			
		this.x = x;
		this.y = y;
		
		setBounds(x, y, K.SQUARE_SIZE, K.SQUARE_SIZE);
		setOpaque(false);
		
		cellColor = new Color(150,150,150);
		borderColor = new Color(250,250,250);
		square = new Rectangle2D.Double(0, 0, K.SQUARE_SIZE, K.SQUARE_SIZE);
		
		addMouseListener(this);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setStroke(new BasicStroke(K.STROKE_WIDTH));
		
		g2d.setColor( cellColor );
		g2d.fill(square);
		
		g2d.setColor( borderColor );
		g2d.draw(square);
	}
	
	public Color getOriginalColor() {
		
		if(shipColor != null) {
			return shipColor;
		}
		
		return new Color(150,150,150);
	}
	
	public void setColor(Color color) {
		cellColor = color;
		repaint();
	}
	
	public void setShipColor(Color color) {
		cellColor = color;
		shipColor = color;
		repaint();
	}
	
	private void paintSelectedCells() {
		RulesFacade.getRules().checkPos(x/K.SQUARE_SIZE, y/K.SQUARE_SIZE, JP_PositioningGrid.getGrid().getFinalGrid());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isMiddleMouseButton(e)) {
			return;
		}
		
		if(SwingUtilities.isRightMouseButton(e)) {
			RulesFacade.getRules().shipRotate();
			paintSelectedCells();
			return;
		}
		
		if(RulesFacade.getRules().getPhase() == PHASE.POSITION) {
			RulesFacade.getRules().positionShip(x/K.SQUARE_SIZE, y/K.SQUARE_SIZE, 
				JP_PositioningGrid.getGrid().getFinalGrid());
			return;
		}
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(RulesFacade.getRules().getPhase() == PHASE.POSITION 
				&& RulesFacade.getRules().getSelectedShip() != null) {
			paintSelectedCells();
			return;
		}
		
		setColor(getOriginalColor().darker());
		repaint();
	}
	@Override
	public void mouseExited(MouseEvent e) {	
		if(RulesFacade.getRules().getPhase() == PHASE.POSITION) {
			JP_PositioningGrid.getGrid().unpaintTemporaryCells();
		}
		
		setColor(getOriginalColor());
		repaint();
	}

}
