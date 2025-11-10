package assign10;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is a temporary placeholder class.
 * You will create this class in the next assignment.
 * DO NOT MODIFY THIS CLASS for assignment 10.
 * 
 * Documentation intentionally omitted.
 * 
 * @author CS 1420 course staff
 * @version 2025-10-30
 */
public class TrackEditor extends JPanel{
	public static enum Mode{NOTE, VOLUME, COPY};
	
	private int trackNumber;
	private int songLength;
	private Mode mode;
	private JLabel label;
	
	public TrackEditor(int trackNumber, int songLength, ArrayList<AudioEvent> events, SimpleSynthesizer synth) {
		this.trackNumber = trackNumber;
		this.songLength = songLength;
		mode = Mode.NOTE;
		label = new JLabel("");
		updateLabel();
		add(label);
		setBackground(Color.WHITE);
	}
	public void clearTrack() {}
	public void setMode(Mode mode) {
		this.mode = mode;
		updateLabel();
	}
	public void setSongLength(int songLength) {
		this.songLength = songLength;
		updateLabel();
	}
	private void updateLabel() {
		label.setText("Track Editor placeholder    Track: " + trackNumber + "    Song length: " + songLength + "    Mode: " + mode);
		repaint();
	}
	
	// Required by a serializable class (ignore for now)
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
}
