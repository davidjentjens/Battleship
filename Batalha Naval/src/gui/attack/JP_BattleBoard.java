package gui.attack;

import gui.board.JP_Board;
import gui.board.JP_Grid;
import gui.victory.JF_Victory;
import main.K;
import rules.designPatterns.IObservable;
import rules.designPatterns.IObserver;
import rules.designPatterns.RulesFacade;

@SuppressWarnings("serial")
public class JP_BattleBoard extends JP_Board implements IObserver{
	
	private int player;
	private JP_Grid battleGrid;
	
	private int[][] shownCells;
	private int[][] hiddenCells;

	public JP_BattleBoard(int player) {
		RulesFacade.getRules().register(this);
		
		this.player = player;
		
		setLayout(null);
		setBounds(0, 0, BOARD_SIZE, BOARD_SIZE);
		setOpaque(false);
				
		battleGrid = new JP_Grid(player);
		
		addLabels();
		
		shownCells = K.createEmptyGrid();
		hiddenCells = K.createEmptyGrid();
		
		if(battleGrid!=null)
			add(battleGrid);
		else {
			System.out.println("Grid Nula");
		}
	}
	
	public void hideHiddenCells() {
		getShownCells();
		battleGrid.repaintCells(shownCells);
	}
	
	public void showHiddenCells(){
		battleGrid.repaintCells(hiddenCells);
	}
	
	private void getShownCells() {
		for(int i = 0; i < K.SQUARE_COUNT; i++)
		{
			for(int j = 0; j < K.SQUARE_COUNT; j++)
			{
				if(hiddenCells[j][i] < 0 || hiddenCells[j][i] == 10) {
					shownCells[j][i] = hiddenCells[j][i];
				}
			}
		}
	}
	
	public void resetBoard() {
		shownCells = K.createEmptyGrid();
		hiddenCells = K.createEmptyGrid();
		repaint();
	}
	
	@Override
	public void notify(IObservable o) {
		Object lob[] = (Object []) o.get();
		
		String player1Name = "";
		String player2Name = "";
		
		boolean result = (boolean) lob[K.objectValues.RESULT.getValue()];
		int currentPlayer = (int) lob[K.objectValues.CURRENT_PLAYER.getValue()];
		
		if(player == 1) {
			hiddenCells = (int[][]) lob[K.objectValues.BOARD_1.getValue()];
			player1Name = (String) lob[K.objectValues.PLAYER_1_NAME.getValue()];
			player2Name = (String) lob[K.objectValues.PLAYER_2_NAME.getValue()];
		}
		else {
			hiddenCells = (int[][]) lob[K.objectValues.BOARD_2.getValue()];
			player1Name = (String) lob[K.objectValues.PLAYER_2_NAME.getValue()];
			player2Name = (String) lob[K.objectValues.PLAYER_1_NAME.getValue()];
		}
			
		if(player != currentPlayer) {
			hideHiddenCells();
		}	
		
		if(result && currentPlayer == player) {
			System.out.println("\n**********\nVITORIA\n**********\n");
			(JF_Victory.getVictoryFrame(player1Name, player2Name)).setVisible(true);
			(JF_Attack.getAttackFrame()).setVisible(false);
			return;
		}
		
	}	
}
