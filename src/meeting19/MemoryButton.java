package meeting19;

import javax.swing.JButton;

/**
 * This class represents a button in the Memory Game with GUI.
 * 
 * @author Prof. Parker
 * @version November 3, 2025
 */
public class MemoryButton extends JButton {
	private int secretValue;   // value under the ?
	
	/** 
	 * Create a button for the Memory Game.
	 * 
	 * @param secretValue - the value to hide under a ?
	 */
	public MemoryButton(int secretValue) {
		super("?");
		this.secretValue = secretValue;
	}

	/**
	 * Make the secret value visible to the player.
	 */
	public void showValue() {
		this.setText("" + this.secretValue);
	}
	
	/**
	 * Hide the secret value from the player.
	 */
	public void hideValue() {
		this.setText("?");
	}
	
	/**
	 * Getter for the secret value.
	 * 
	 * @return secret value
	 */
	public int getValue() {
		return this.secretValue;
	}
	
	// Required by a serializable class (ignore for now).
	private static final long serialVersionUID = 1L;
}