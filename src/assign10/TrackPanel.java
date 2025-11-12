package assign10;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class TrackPanel extends JPanel implements ActionListener{
	private static final String MUTE_ICON_OFF = "src/assign10/speaker.slash.png";
	private static final String MUTE_ICON_ON = "src/assign10/speaker.slash.fill.png";
	private static final String CLEAR_TRACK = "src/assign10/clear.png";
	private static final String NOTE_ICON = "src/assign10/music.quarternote.3.png";
	private static final String VOLUME_ICON = "src/assign10/speaker.wave.3.png";
	private static final String COPY_PASTE_ICON = "src/assign10/document.on.clipboard.png";
	
	private int trackNumber;
	private SimpleSynthesizer synth;
	private TrackEditor trackEditor;
	private JComboBox<String> instruments;
	
	JToggleButton muteToggle;
	ButtonGroup editingModes;
	JToggleButton noteMode;
	JToggleButton volumeMode;
	JToggleButton copyPasteMode;
	JButton clearTrack;
	
	public TrackPanel(int trackNumber, int songLength, ArrayList<AudioEvent> events, SimpleSynthesizer synth) {
		this.trackNumber = trackNumber;
		this.synth = synth;
		trackEditor = new TrackEditor(trackNumber, songLength, events, synth);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel leftPanel = new JPanel(new GridLayout(7, 1));
		leftPanel.setPreferredSize(new Dimension(175, 300));
		
		instruments = new JComboBox<String>(new Vector<String>(synth.getInstrumentNames()));
		muteToggle = new JToggleButton("Mute", new ImageIcon(MUTE_ICON_OFF));
		muteToggle.setSelectedIcon(new ImageIcon(MUTE_ICON_ON));
		editingModes = new ButtonGroup();
		noteMode = new JToggleButton(new ImageIcon(NOTE_ICON));
		volumeMode = new JToggleButton(new ImageIcon(VOLUME_ICON));
		copyPasteMode = new JToggleButton(new ImageIcon(COPY_PASTE_ICON));
		clearTrack = new JButton("Clear Track", new ImageIcon(CLEAR_TRACK));
		editingModes.add(noteMode);
		editingModes.add(volumeMode);
		editingModes.add(copyPasteMode);
		
		leftPanel.add(new JLabel("Instrument Selector"));
		leftPanel.add(instruments);
		leftPanel.add(muteToggle);
		leftPanel.add(noteMode);
		leftPanel.add(volumeMode);
		leftPanel.add(copyPasteMode);
		leftPanel.add(clearTrack);
		
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(trackEditor, BorderLayout.EAST);
		
		this.add(mainPanel);
	}
	
	public void setInstrument(int instrumentValue) {
		synth.setInstrument(trackNumber, instrumentValue);
		instruments.setSelectedIndex(instrumentValue);
	}
	
	public void setSongLength(int duration) {
		trackEditor.setSongLength(duration);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (muteToggle.isSelected())
			synth.setMute(trackNumber, true);
		else
			synth.setMute(trackNumber, false);
		
		if (noteMode.isSelected())
			trackEditor.setMode(TrackEditor.Mode.NOTE);
		if (volumeMode.isSelected())
			trackEditor.setMode(TrackEditor.Mode.VOLUME);
		if (copyPasteMode.isSelected())
			trackEditor.setMode(TrackEditor.Mode.COPY);
		
		if (clearTrack.isSelected())
			trackEditor.clearTrack();
		
		
		
	}

}
