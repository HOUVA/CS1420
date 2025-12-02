package assign12;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.SwingConstants;

/**
 * This class implements a Track Panel GUI.
 * 
 * Includes the following: - Toggle button to mute track - Drop down menu to
 * select an instrument, - change the track editor panel, - button to clear any
 * tracks.
 * 
 * Also Includes a track editor panel.
 * 
 * @author Matthew Suggars
 * @version 2025-11-08
 */
public class TrackPanel extends JPanel implements ActionListener {
	private int trackNumber;
	private SimpleSynthesizer synth;
	private TrackEditor trackEditor;
	private JComboBox<String> instruments;

	JToggleButton muteToggle;
	JToggleButton noteMode;
	JToggleButton volumeMode;
	JToggleButton copyPasteMode;
	JButton clearTrack;

	/**
	 * Creates a new TrackPanel GUI
	 * 
	 * @param trackNumber - number for which track is to be edited.
	 * @param songLength  - number for the length of the track.
	 * @param events      - an ArrayList of AudioEvents for the track.
	 * @param synth       - SimpleSynthesizer object for the track
	 */
	public TrackPanel(int trackNumber, int songLength, ArrayList<AudioEvent> events, SimpleSynthesizer synth) {
		final String MUTE_ICON_OFF = "speaker.slash.png";
		final String MUTE_ICON_ON = "speaker.slash.fill.png";
		final String CLEAR_TRACK = "clear.png";
		final String CLEAR_TRACK_PRESSED = "clear.fill.png";
		final String NOTE_ICON_ON = "music.note.png";
		final String NOTE_ICON_OFF = "music.note.slash.png";
		final String VOLUME_ICON_ON = "speaker.wave.3.fill.png";
		final String VOLUME_ICON_OFF = "speaker.png";
		final String COPY_PASTE_ICON_OFF = "document.on.document.png";
		final String COPY_PASTE_ICON_ON = "document.on.document.fill.png";
		// final String ROOT_DIR = "src/assign12/"; - needed for use in Eclipse

		this.trackNumber = trackNumber;
		this.synth = synth;
		trackEditor = new TrackEditor(trackNumber, songLength, events, synth);

		JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 50, 20));
		JPanel leftPanel = new JPanel(new GridLayout(7, 1));
		leftPanel.setPreferredSize(new Dimension(200, 500));
		trackEditor.setPreferredSize(new Dimension(500, 500));

		JPanel instrumentGroup = new JPanel(new GridLayout(2, 1));
		JLabel instrumentSelector = new JLabel("Instrument Selector");
		instrumentSelector.setHorizontalAlignment(SwingConstants.CENTER);
		instruments = new JComboBox<String>(new Vector<String>(synth.getInstrumentNames()));
		instrumentGroup.add(instrumentSelector);
		instrumentGroup.add(instruments);
		instruments.addActionListener(this);

		muteToggle = new JToggleButton("Mute", new ImageIcon(MUTE_ICON_OFF));
		muteToggle.setSelectedIcon(new ImageIcon(MUTE_ICON_ON));
		muteToggle.addActionListener(this);

		// Editing modes JToggle Group
		ButtonGroup editingModes = new ButtonGroup();
		noteMode = new JToggleButton("Note Editor", new ImageIcon(NOTE_ICON_OFF));
		noteMode.setSelectedIcon(new ImageIcon(NOTE_ICON_ON));
		noteMode.addActionListener(this);
		noteMode.setSelected(true);
		volumeMode = new JToggleButton("Volume Editor", new ImageIcon(VOLUME_ICON_OFF));
		volumeMode.setSelectedIcon(new ImageIcon(VOLUME_ICON_ON));
		volumeMode.setIconTextGap(15);
		volumeMode.addActionListener(this);
		copyPasteMode = new JToggleButton("Copy/Pase", new ImageIcon(COPY_PASTE_ICON_OFF));
		copyPasteMode.setSelectedIcon(new ImageIcon(COPY_PASTE_ICON_ON));
		copyPasteMode.addActionListener(this);
		clearTrack = new JButton("Clear Track", new ImageIcon(CLEAR_TRACK));
		clearTrack.addActionListener(this);
		clearTrack.setPressedIcon(new ImageIcon(CLEAR_TRACK_PRESSED));

		editingModes.add(noteMode);
		editingModes.add(volumeMode);
		editingModes.add(copyPasteMode);

		leftPanel.add(instrumentGroup);
		leftPanel.add(muteToggle);
		leftPanel.add(noteMode);
		leftPanel.add(volumeMode);
		leftPanel.add(copyPasteMode);
		leftPanel.add(clearTrack);

		mainPanel.add(leftPanel);
		mainPanel.add(trackEditor);

		this.add(mainPanel);
	}

	/**
	 * Sets the instrument for a SimpleSynthesizer object.
	 * 
	 * @param instrumentValue - number representing the instrument.
	 */
	public void setInstrument(int instrumentValue) {
		synth.setInstrument(trackNumber, instrumentValue);
		instruments.setSelectedIndex(instrumentValue);
	}

	/**
	 * Sets the length of the track for a Track Editor object.
	 * 
	 * @param duration - number representing the desired length of a track.
	 */
	public void setSongLength(int duration) {
		trackEditor.setSongLength(duration);
	}

	/**
	 * This method changes which track editor is viewed, and to mute and clear
	 * tracks using JToggleButtons and JButtons.
	 * 
	 * @param e - variable for an ActionEvent, used for ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		synth.setInstrument(trackNumber, instruments.getSelectedIndex());

		if (muteToggle.isSelected())
			synth.setMute(trackNumber, true);

		else
			synth.setMute(trackNumber, false);

		if (noteMode.isSelected())
			trackEditor.setMode(TrackEditor.Mode.NOTE);

		else if (volumeMode.isSelected())
			trackEditor.setMode(TrackEditor.Mode.VOLUME);

		else if (copyPasteMode.isSelected())
			trackEditor.setMode(TrackEditor.Mode.COPY);

		if (e.getSource() == (clearTrack))
			trackEditor.clearTrack();

	}

}
