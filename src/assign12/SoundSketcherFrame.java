package assign12;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;

/**
 * This class implements a Sound Sketcher GUI.
 * 
 * Includes a series of buttons to manage the play/pause state as well as to
 * loop the song. Includes a slider to change the tempo of the track, as well as
 * a spinner to change the duration of the song.
 * 
 * Also implements the Track Panel GUI for editing individual tracks.
 * 
 * @author Matthew Suggars
 * @version 2025-11-08
 */
public class SoundSketcherFrame extends JFrame implements ActionListener, ChangeListener {

	private Song song;
	private TrackPanel[] trackPanelList;

	private JToggleButton playbackButton;
	private JToggleButton playbackLoop;
	private JSlider tempoSlider;
	private JSpinner durationSpinner;
	private JTabbedPane centerTabs;
	private JLabel tempoSliderLabel;
	private JLabel durationLabel;
	private JMenuItem save;
	private JMenuItem load;

	/**
	 * Creates a Sound Sketcher GUI
	 */
	public SoundSketcherFrame() {
		final int DEFAULT_TEMPO = 300;
		final int DEFAULT_SONG_DURATION = 16;
		final String PLAY_PAUSE_ICON_OFF = "playpause.png";
		final String PLAY_PAUSE_ICON_ON = "playpause.fill.png";
		final String LOOP_ICON_ON = "point.forward.to.point.capsulepath.png";
		final String LOOP_ICON_OFF = "point.forward.to.point.capsulepath.fill.png";
		final String SAVE_ICON = "square.and.arrow.down.png";
		final String LOAD_ICON = "folder.png";
		//final String ROOT_DIR = "src/assign12/"; // - use for Eclipse, does not work for GradeScope.

		song = new Song(DEFAULT_TEMPO, DEFAULT_SONG_DURATION);

		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel northPanel = new JPanel(new BorderLayout()); // North part of main panel
		JPanel northLeftPanel = new JPanel(new GridLayout(2, 1)); // Left side of north panel
		JPanel northCenterPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 15, 0)); // Center of north part of main
																							// panel

		this.setTitle("Sound Sketcher");
		this.setPreferredSize(new Dimension(820, 740));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		northCenterPanel.setFont(new Font("Monospaced", Font.PLAIN, 16));

		// Playback and Loop button Settings
		playbackButton = new JToggleButton(new ImageIcon(PLAY_PAUSE_ICON_OFF));
		playbackButton.setSelectedIcon(new ImageIcon(PLAY_PAUSE_ICON_ON));
		playbackButton.addActionListener(this);
		playbackLoop = new JToggleButton(new ImageIcon(LOOP_ICON_ON));
		playbackLoop.setSelectedIcon(new ImageIcon(LOOP_ICON_OFF));
		playbackLoop.addActionListener(this);

		// Menu Settings
		JMenu menu = new JMenu("File");
		JMenuBar menuBar = new JMenuBar();
		save = new JMenuItem("Save Song", new ImageIcon(SAVE_ICON));
		save.addActionListener(this);
		load = new JMenuItem("Load Song", new ImageIcon(LOAD_ICON));
		load.addActionListener(this);
		menu.add(save);
		menu.add(load);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);

		// TempoSlider Settings
		tempoSlider = new JSlider(20, 600);
		tempoSlider.setMajorTickSpacing(55);
		tempoSlider.setMinorTickSpacing(20);
		tempoSlider.setPaintTicks(true);
		tempoSlider.setPaintTrack(true);
		tempoSlider.setPaintLabels(true);
		tempoSlider.addChangeListener(this);
		tempoSlider.setPreferredSize(new Dimension(400, 120));

		// Song duration spinner settings
		durationSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_SONG_DURATION, 4, 1024, 4));
		durationSpinner.addChangeListener(this);

		// TrackPanel Tabs settings
		centerTabs = new JTabbedPane();
		trackPanelList = new TrackPanel[10];
		for (int index = 0; index < 10; index++) {
			trackPanelList[index] = new TrackPanel(index, song.getSongLength(), song.getTrack(index),
					song.getSynthesizer());
			centerTabs.addTab("Track " + (index + 1), trackPanelList[index]);
		}

		// North left Panel Settings
		northLeftPanel.setPreferredSize(new Dimension(100, 75));
		northLeftPanel.add(playbackButton);
		northLeftPanel.add(playbackLoop);

		// North Center Panel Settings
		tempoSliderLabel = new JLabel("Tempo Slider");
		durationLabel = new JLabel("Song Length");
		northCenterPanel.add(tempoSliderLabel);
		northCenterPanel.add(tempoSlider);
		northCenterPanel.add(durationLabel);
		northCenterPanel.add(durationSpinner);

		// Main Panel Settings
		northPanel.add(northLeftPanel, BorderLayout.WEST);
		northPanel.add(northCenterPanel, BorderLayout.CENTER);
		mainPanel.add(centerTabs, BorderLayout.CENTER);
		mainPanel.add(northPanel, BorderLayout.NORTH);

		this.add(mainPanel);
		this.pack();

	}

	/**
	 * This method changes the state of the Song's tempo and length using the values
	 * from JSlider and JSpinnder values.
	 * 
	 * @param e - variable for ChangeEvent, used for ChangeListener
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		setTempoSlider(tempoSlider.getValue());
		System.out.println("Tempo slider value is " + song.getTempo());

		int durationValue = (Integer) durationSpinner.getValue();
		System.out.println("duration value is " + durationValue);

		song.setSongLength(durationValue);

		for (int i = 0; i < trackPanelList.length; i++)
			trackPanelList[i].setSongLength(durationValue);

	}

	/**
	 * This method changes whether the Song is playing, or looping using the values
	 * from the JToggleButtons.
	 * 
	 * @param e - variable for an ActionEvent, used for ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		if (playbackButton.isSelected()) {
			song.play();
			System.out.println("Song is playing");
		} else {
			song.stop();
			System.out.println("Song is not playing");
		}

		if (playbackLoop.isSelected()) {
			song.enableLoop(true);
			System.out.println("Playback loop is on");
		} else {
			song.enableLoop(false);
			System.out.println("Playback loop is off");
		}

		Object source = e.getSource();
		chooser.setFileFilter(new FileNameExtensionFilter("Song files", "song"));
		if (source == save) {
			int saveSelection = chooser.showSaveDialog(this);
			if (saveSelection == JFileChooser.APPROVE_OPTION)
				SongFiles.writeFile(chooser.getSelectedFile(), song);
		} else if (source == load) {
			int loadSelection = chooser.showOpenDialog(this);
			if (loadSelection == JFileChooser.APPROVE_OPTION) {
				SongFiles.readFile(chooser.getSelectedFile(), song);
				for (int index = 0; index < trackPanelList.length; index++) {
					trackPanelList[index].setSongLength(song.getSongLength());
					trackPanelList[index].setInstrument(song.getSynthesizer().getInstrument(index));
					setTempoSlider(song.getTempo());
				}
			}
		}
	}
	
	/**
	 * This helper method checks whether the new tempo is within the slider's range and adjusts it accordingly.
	 * 
	 * @param newTempo - a new tempo value.
	 */
	private void setTempoSlider(int newTempo) {
		if (newTempo < tempoSlider.getMinimum())
			tempoSlider.setMinimum(newTempo);
		else if (newTempo > tempoSlider.getMaximum())
			tempoSlider.setMaximum(newTempo);
		tempoSlider.setValue(newTempo);
	}
}
