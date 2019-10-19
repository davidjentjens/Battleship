package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class JP_MouseBoard extends JPanel implements MouseListener{
	
	private JP_Board board = new JP_Board();
	
	public JP_MouseBoard() {
		setLayout(null);
		setBounds(0, 0, 500, 500);
		addMouseListener(this);
		
		add(board);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

		int x = 0, y = 0;
		
		if( e.getX() >= board.getBoardBorder() && e.getY() >= board.getBoardBorder() ) {
			x = (int)( ( e.getX() - board.getBoardBorder() ) / board.getCellSize() );
			y = (int)( ( e.getY() - board.getBoardBorder() ) / board.getCellSize() );
		}
		else {
			return;
		}
		
		if(x < 15 && y < 15) {
			//resto do código entra aqui
			System.out.printf("Click! -> X: %d | Y: %d\n", x, y);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}