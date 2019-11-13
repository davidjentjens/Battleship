package gui.shipPositioning;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.ships.*;

@SuppressWarnings("serial")
public class JP_ShipOptions extends JPanel{

	private final int OFFSET_X = 70;
	private final int OFFSET_Y = 100;
	private final int LABEL_OFFSET_X = 250;
	
	private final int BATTLESHIP_POSITION = 2;
	private final int CRUISER_POSITION = 3;
	private final int DESTROYER_POSITION = 4;
	private final int SUBMARINE_POSITION = 5;
	private final int SEAPLANE_POSITION = 1;
	
	public int battleship_count = 1;
	public int cruiser_count = 2;
	public int destroyer_count = 3;
	public int submarine_count = 4;
	public int seaplane_count = 5;
	public int ship_count = battleship_count + cruiser_count + destroyer_count + submarine_count + seaplane_count;
	
	JLabel battleshipCount;
	JLabel cruiserCount;
	JLabel destroyerCount;
	JLabel submarineCount;
	JLabel seaplaneCount;
	
	Battleship battleship;
	Cruiser cruiser;
	Destroyer destroyer;
	Submarine submarine;
	Seaplane seaplane;
	
	static JP_ShipOptions shipOptions;
    
    public static JP_ShipOptions getShipOptions() {
        if(shipOptions == null)
            shipOptions = new JP_ShipOptions();
        
        return shipOptions;    
    }

	private JP_ShipOptions() {
		setLayout(null);
		setBounds(0,0,1024/3,618);
		setOpaque(false);

		displayShipOptions();
	}

	public void displayShipOptions() {
		addBattleships();
		addCruisers();
		addDestroyers();
		addSubmarines();
		addSeaplanes();
		
		paintLabels();
	}
	
	private JLabel createLabel(int pos, int count) {
		JLabel label = new JLabel( "x" + Integer.toString(count) );
		label.setFont(new Font("SansSerif", Font.PLAIN, 13));
		label.setBounds(LABEL_OFFSET_X, OFFSET_Y*pos+8, 30, 15);
		
		return label;
	}
	
	public void paintLabels() {		
		
		battleshipCount = createLabel(BATTLESHIP_POSITION, battleship_count);
		cruiserCount = createLabel(CRUISER_POSITION, cruiser_count);
		destroyerCount = createLabel(DESTROYER_POSITION, destroyer_count);
		submarineCount = createLabel(SUBMARINE_POSITION, submarine_count);
		seaplaneCount = createLabel(SEAPLANE_POSITION, seaplane_count);
		
		add(battleshipCount);
		add(cruiserCount);
		add(destroyerCount);
		add(submarineCount);
		add(seaplaneCount);
		
		repaint();
	}
	
	private void repaintLabels() {
		remove(battleshipCount);
		remove(cruiserCount);
		remove(destroyerCount);
		remove(submarineCount);
		remove(seaplaneCount);
				
		battleshipCount = createLabel(BATTLESHIP_POSITION, battleship_count);
		cruiserCount = createLabel(CRUISER_POSITION, cruiser_count);
		destroyerCount = createLabel(DESTROYER_POSITION, destroyer_count);
		submarineCount = createLabel(SUBMARINE_POSITION, submarine_count);
		seaplaneCount = createLabel(SEAPLANE_POSITION, seaplane_count);
		
		add(battleshipCount);
		add(cruiserCount);
		add(destroyerCount);
		add(submarineCount);
		add(seaplaneCount);
		
		repaint();
	}

	public void addBattleships() {
		battleship = new Battleship(OFFSET_X, OFFSET_Y*BATTLESHIP_POSITION);
		add(battleship);
	}
	public void addCruisers() {
		cruiser = new Cruiser(OFFSET_X, OFFSET_Y*CRUISER_POSITION);
		add(cruiser);
	}
	public void addDestroyers() {
		destroyer = new Destroyer(OFFSET_X, OFFSET_Y*DESTROYER_POSITION);
		add(destroyer);
	}
	public void addSubmarines() {
		submarine = new Submarine(OFFSET_X, OFFSET_Y*SUBMARINE_POSITION);
		add(submarine);
	}
	public void addSeaplanes() {
		seaplane= new Seaplane(OFFSET_X, OFFSET_Y*SEAPLANE_POSITION-25);
		add(seaplane);
	}
	
	public void reduceShipCount(Ship ship) {
		String shipName = ship.getClass().getName();
		
		if(!ship.getAvailability()) {
			return;
		}
		
		if(shipName == "gui.ships.Battleship") {
			battleship_count--;
			
			if(battleship_count == 0) {
				ship.setUnavailable();
			}
		}
		else if(shipName == "gui.ships.Cruiser") {
			cruiser_count--;
			
			if(cruiser_count == 0) {
				ship.setUnavailable();
			}
		}
		else if(shipName == "gui.ships.Destroyer") {
			destroyer_count--;
			
			if(destroyer_count == 0) {
				ship.setUnavailable();
			}
		}
		else if(shipName == "gui.ships.Submarine") {
			submarine_count--;
			
			if(submarine_count == 0) {
				ship.setUnavailable();
			}
		}
		else if(shipName == "gui.ships.Seaplane") {
			seaplane_count--;
			
			if(seaplane_count == 0) {
				ship.setUnavailable();
			}
		}
		
		ship_count--;	
		repaintLabels();
		
		if(ship_count == 0) {
			JP_SelectionUtilities.getSelectionUtilites().buttonEnable();
		}
		
	}
	
	public void resetShipCount() {
		battleship_count = 1;
		cruiser_count = 2;
		destroyer_count = 3;
		submarine_count = 4;
		seaplane_count = 5;
		ship_count = battleship_count + cruiser_count + destroyer_count + submarine_count + seaplane_count;

		
		battleship.setAvailable();
		cruiser.setAvailable();
		destroyer.setAvailable();
		submarine.setAvailable();
		seaplane.setAvailable();
		
		repaintLabels();
	}
}

