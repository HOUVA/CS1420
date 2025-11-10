package assign10;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class TrackPanel extends JPanel {
	private int trackNumber;
	private SimpleSynthesizer synth;
	private TrackEditor trackEditor;
	
	public TrackPanel(int trackNumber, int songLength, ArrayList<AudioEvent> events, SimpleSynthesizer synth) {
		this.trackNumber = trackNumber;
		this.synth = synth;
		trackEditor = new TrackEditor(trackNumber, songLength, events, synth);
		
		JComboBox<String> instruments = new JComboBox<String>(new Vector<String>(synth.getInstrumentNames()));
	}

}
