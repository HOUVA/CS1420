package lab11;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonDemoFrame extends JFrame implements ActionListener{
	private static final String YELLOW_ICON = "src/lab11/smileYellow.png";
	private static final String PINK_ICON = "src/lab11/smilePink.png";
	private static final String YELLOW_PRESSED_ICON = "src/lab11/smileYellowPressed.png";
	private static final String PINK_PRESSED_ICON = "src/lab11/smilePinkPressed.png";
	// Required by a serializable class (ignore for now)
	private static final long serialVersionUID = 1L;
	
	private JButton button;
	private int timesClicked;
	private boolean isYellow;
	
	public ButtonDemoFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Button Demo"); 
		JPanel panel = new JPanel();

		// Add components to the panel here ------//
		ImageIcon icon = new ImageIcon(YELLOW_ICON);
		this.button = new JButton("Clicked 0 Times", icon);
		panel.add(button);
		button.setPreferredSize(new Dimension(350,120));
		this.button.addActionListener(this);
		//----------------------------------------//

		this.setContentPane(panel);
		this.pack();	
		this.timesClicked = 0;
		this.isYellow = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.timesClicked++;
		this.button.setText("Clicked " + timesClicked + " Times");
		if (isYellow) {
			this.button.setIcon(new ImageIcon(PINK_ICON));
			this.isYellow = false;
		}else {
			this.button.setIcon(new ImageIcon(YELLOW_ICON));
			this.isYellow = true;
		}
		if (isYellow)
			this.button.setPressedIcon(new ImageIcon(YELLOW_PRESSED_ICON));
		else 
			this.button.setPressedIcon(new ImageIcon(PINK_PRESSED_ICON));
		
	}
	
	 

}
