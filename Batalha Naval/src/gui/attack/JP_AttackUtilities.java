package gui.attack;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.K;
import rules.designPatterns.IObservable;
import rules.designPatterns.IObserver;
import rules.designPatterns.RulesFacade;

@SuppressWarnings("serial")
public class JP_AttackUtilities extends JPanel implements IObserver{

	public final int UTILITIES_HEIGHT = 150;
	
	JLabel message1 = new JLabel("");
	JLabel message2 = new JLabel("");
	JLabel message3 = new JLabel("");
	
	JButton nextBtn = new JButton();
	JButton exitBtn = new JButton("Exit");
	JButton saveBtn = new JButton("Save");
	
	static JP_AttackUtilities attackUtilities;
    
    public static JP_AttackUtilities getAttackUtilites() {
        if(attackUtilities == null)
        	attackUtilities = new JP_AttackUtilities();
        
        return attackUtilities;    
    }
	
	private JP_AttackUtilities() {
		
		RulesFacade.getRules().register(this);
		
		setLayout(null);
		
		setBounds(0, K.ALT_DEFAULT - UTILITIES_HEIGHT, K.LARG_DEFAULT, UTILITIES_HEIGHT);
		setOpaque(false);
		
		Dimension btnDimension = new Dimension(150, 50);
		
		/* MIDDLE */
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(K.LARG_DEFAULT/3, 0, K.LARG_DEFAULT/3, UTILITIES_HEIGHT);
		leftPanel.setLayout(new GridBagLayout());
		
		JPanel nextContainer = new JPanel();
			
		nextBtn.setPreferredSize(btnDimension);
		nextBtn.setMinimumSize(btnDimension);
		nextBtn.setMaximumSize(btnDimension);
		
		nextContainer.add(nextBtn);
		
		nextBtn.setText("Next");
		nextBtn.setBackground(new Color(0, 218, 60));
		nextBtn.setForeground(new Color(0, 218, 60).darker());
		nextBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		leftPanel.add(nextContainer);
		
		/* RIGHT */
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setBounds(2 * (K.LARG_DEFAULT/3), 0, K.LARG_DEFAULT/3, UTILITIES_HEIGHT);
		
		JPanel buttonsContainer = new JPanel();
		buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.Y_AXIS));
		buttonsContainer.setBounds((K.LARG_DEFAULT/3)-150-10, 10, 150, 305);
		
		exitBtn.setBackground(new Color(223, 21, 26));
		exitBtn.setForeground(new Color(100, 5, 9));
		exitBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
		exitBtn.setPreferredSize(btnDimension);
		exitBtn.setMinimumSize(btnDimension);
		exitBtn.setMaximumSize(btnDimension);
		exitBtn.setAlignmentX( Component.CENTER_ALIGNMENT );
		exitBtn.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  System.exit(0);
			  } 
			} );
		
		saveBtn.setBackground(new Color(0, 203, 231));
		saveBtn.setForeground(new Color(0, 103, 131));
		saveBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
		saveBtn.setPreferredSize(btnDimension);
		saveBtn.setMinimumSize(btnDimension);
		saveBtn.setMaximumSize(btnDimension);
		saveBtn.setAlignmentX( Component.CENTER_ALIGNMENT );
		saveBtn.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    
			  } 
			} );
		
		buttonsContainer.add(exitBtn);
		buttonsContainer.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonsContainer.add(saveBtn);
		
		rightPanel.add(buttonsContainer);
		
		/* LEFT */
		
		JPanel messagesPanel = new JPanel();
		messagesPanel.setLayout(null);
		messagesPanel.setBounds(0, 0, K.LARG_DEFAULT/3, UTILITIES_HEIGHT);
		
		message1.setBounds(0, 60, K.LARG_DEFAULT/3, 40);
		message1.setFont(new Font("SansSerif", Font.BOLD, 20));
		message1.setForeground(Color.BLACK);
		message1.setHorizontalAlignment(SwingConstants.CENTER);
		message1.setVerticalAlignment(SwingConstants.CENTER);
		
		message2.setBounds(0, 40, K.LARG_DEFAULT/3, 30);
		message2.setFont(new Font("SansSerif", Font.BOLD, 15));
		message2.setForeground(Color.GRAY);
		message2.setHorizontalAlignment(SwingConstants.CENTER);
		message2.setVerticalAlignment(SwingConstants.CENTER);
		
		message3.setBounds(0, 20, K.LARG_DEFAULT/3, 30);
		message3.setFont(new Font("SansSerif", Font.BOLD, 10));
		message3.setForeground(Color.LIGHT_GRAY);
		message3.setHorizontalAlignment(SwingConstants.CENTER);
		message3.setVerticalAlignment(SwingConstants.CENTER);
		
		messagesPanel.add(message1);
		messagesPanel.add(message2);
		messagesPanel.add(message3);
		
		add(leftPanel);
		add(messagesPanel);
		add(rightPanel);
		
	}
	
//	public void buttonEnable() {
//		next.setBackground(new Color(0, 218, 60));
//		next.setEnabled(true);
//		this.repaint();
//	}
//	
//	public void buttonDisable() {
//		next.setBackground(Color.LIGHT_GRAY.darker());
//		next.setEnabled(false);
//		this.repaint();
//	}
	
	public void setMessages(List<String> messages, boolean validation) {
		
		try {
			if( this.message1.getText() != messages.get( messages.size() - 1 ) ) {
				if( validation ) {
					message1.setForeground(Color.GREEN.darker().darker());
				}
				else {
					message1.setForeground(Color.RED);
				}
			}
		
		
			message1.setText( messages.get( messages.size() - 1 ) );
			message2.setText( messages.get( messages.size() - 2 ) );
			message3.setText( messages.get( messages.size() - 3 ) );
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		repaint();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void notify(IObservable o) {
		
		Object lob[] = (Object []) o.get();
		
		List<String> newMessages = (List<String>) lob[K.objectValues.MESSAGES.getValue()];
		boolean validation = (boolean) lob[ K.objectValues.IS_VALID.getValue() ];
		
		setMessages(newMessages, validation);
		
	}
	
}
