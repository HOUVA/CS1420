package meeting19;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This program implements a Tip Calculator GUI (Graphical User Interface).
 * 
 * Components -- stuff from which GUIs are built (e.g., JTextField, JLabel,
 * JButton)
 * 
 * Containers -- things that contain the components (e.g., JFrame, JPanel)
 * Container are themselves components, which allows them to contain other
 * containers
 * 
 * Events -- a button clicked, a key press, a timer going off
 * 
 * Event listeners -- code to handle events
 * 
 * @author Prof. Parker
 * @version November 3, 2025
 */
public class TipCalculatorFrame extends JFrame implements ActionListener {
	private JTextField checkField;  // where the check amount is typed
	private JTextField tipField;    // where the tip percentage is typed
	private JTextField totalField;  // where the total amount is displayed

	/**
	 * Creates a TipCalculator. Since this class extends JFrame, each
	 * TipCalculator behaves like a JFrame object, subject to any
	 * customizations made below.
	 */
	public TipCalculatorFrame() {
		// Make the application end when X is clicked.
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Give the frame a title.
		this.setTitle("Tip Calculator");

		// Create a label and a text field for the check amount. Note that
		// the text field is stored as a member variable so that it will be
		// accessible to the actionPerformed method.
		JLabel checkLabel = new JLabel("Check");
		this.checkField = new JTextField(10);

		// Create a label and a text field for tip percentage. Note that
		// the text field is stored as a member variable so that it will be
		// accessible to the actionPerformed method.
		JLabel tipLabel = new JLabel("Tip");
		this.tipField = new JTextField(10);

		// Create a label and a text field for total bill. Note that
		// the text field is stored as a member variable so that it will be
		// accessible to the actionPerformed method. We make the text field
		// non-editable because we want to use it for output only.
		JLabel totalLabel = new JLabel("Total");
		this.totalField = new JTextField(10);
		this.totalField.setEditable(false);

		// Create a button to trigger the calculation.
		JButton calculate = new JButton("Calculate");
		
		// Set the fonts of the labels, text fields, and button to be large enough.
		Font calculatorFont = new Font("Dialog", Font.PLAIN, 40);
		checkLabel.setFont(calculatorFont);
		this.checkField.setFont(calculatorFont);
		tipLabel.setFont(calculatorFont);
		this.tipField.setFont(calculatorFont);
		totalLabel.setFont(calculatorFont);
		this.totalField.setFont(calculatorFont);
		calculate.setFont(calculatorFont);
		
		// This line of code connects the button to the calculation that needs
		// to be performed when the button is clicked. The parameter to
		// addActionListener must be an object that implements the
		// ActionListener interface. The TipCalculator that we are constructing
		// (i.e., this) is such an object. It is an ActionListener because the
		// TipCalculator class implements the ActionListener interface.
		calculate.addActionListener(this);

		// Create a sub-panel that includes the check label and text field. It
		// is laid out with a FlowLayout, which is the default.
		JPanel row1 = new JPanel();
		row1.add(checkLabel);
		row1.add(this.checkField);

		// Create a sub-panel that includes the tip label and text field. 
		JPanel row2 = new JPanel();
		row2.add(tipLabel);
		row2.add(this.tipField);

		// Create a sub-panel that includes the Calculate button. 
		JPanel row3 = new JPanel();
		row3.add(calculate);

		// Create a sub-panel that includes the total label and text field. 
		JPanel row4 = new JPanel();
		row4.add(totalLabel);
		row4.add(this.totalField);
		
		// Create a top-level panel to hold the various GUI components.
		JPanel panel = new JPanel();
		
		// Lay out the top-level panel with a GridLayout, which will give equal
		// space to each of the four sub-panels.
		panel.setLayout(new GridLayout(4, 1));
		
		// Add the four sub-panels to the top-level panel.
		panel.add(row1);
		panel.add(row2);
		panel.add(row3);
		panel.add(row4);

		// Make the top-level panel content pane of the JFrame.
		this.setContentPane(panel);
		
		// Set the size of the JFrame.
		this.setPreferredSize(new Dimension(700, 500));

		// Initialize the layout.
		this.pack();
	}

	/**
	 * This is the method that is invoked when the Calculate button is clicked,
	 * assuming that this TipCalculator has been added as an ActionListener for
	 * the button.
	 * 
	 * @param event - event that cause this method to be called
	 */
	public void actionPerformed(ActionEvent event) {
		// Just to show you how it's done, let's display the label of the
		// JButton that launched this event. First, get the object that  
		// generated the event.
		Object o = event.getSource();

		// We know that it was a JButton that generated the event, so this cast
		// is safe.
		JButton b = (JButton)o;

		// Now we can display the label of the button.
		System.out.println(b.getText() + " button was pressed");

		// Check amount and tip percentage.
		double check;
		double pct;

		// Parse the check amount, display an error if there's a problem.
		try {
			check = Double.parseDouble(this.checkField.getText());
		}
		catch(NumberFormatException ex) {
			this.totalField.setText("Bad check amount");
			return;
		}

		// Parse the tip percentage, display an error if there's a problem.
		try {
			pct = Double.parseDouble(this.tipField.getText());
		}
		catch(NumberFormatException ex) {
			this.totalField.setText("Bad tip amount");
			return;
		}

		// Calculate the total and display it in the total field.
		double total = check + check * pct / 100;
		DecimalFormat formatter = new DecimalFormat("###.##");
		this.totalField.setText(formatter.format(total) + "");
	}
	
	// Required by a serializable class like TipCalculator (ignore for now).
	private static final long serialVersionUID = 1L;
}