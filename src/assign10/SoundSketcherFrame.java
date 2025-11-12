package assign10;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;

/**
 * This class implements a Sound Sketcher GUI.
 * 
 * Includes a series of buttons to manage the play/pause state as well as to loop the song.
 * Includes a slider to change the tempo of the track, as well as a spinner to change the duration 
 * of the song.
 * 
 * Also implements the Track Panel GUI for editing tracks.
 * 
 * @author Matthew Suggars
 * @version 2025-11-08
 */
public class SoundSketcherFrame extends JFrame implements ActionListener, ChangeListener{
	private static final int DEFAULT_TEMPO = 300;
	private static final int DEFAULT_SONG_DURATION = 16;
	private static final String PLAY_PAUSE_ICON_OFF = "src/assign10/playpause.png";
	private static final String PLAY_PAUSE_ICON_ON = "src/assign10/playpause.fill.png";
	private static final String LOOP_ICON = "src/assign10/arrow.trianglehead.rectanglepath.png";
	
	private Song song;
	private TrackPanel[] trackPanelList;
	
	private JPanel mainPanel;
	private JPanel northPanel;
	private JPanel northLeftPanel;
	private JPanel northCenterPanel;
	
	private JToggleButton playbackButton;
	private JToggleButton playbackLoop;
	private JSlider tempoSlider;
	private JSpinner durationSpinner;
	private JTabbedPane centerTabs;
	private JLabel tempoSliderLabel;
	private JLabel durationLabel;
	
	public SoundSketcherFrame() {
		song = new Song(DEFAULT_TEMPO, DEFAULT_SONG_DURATION);
		
		mainPanel = new JPanel(new BorderLayout());
		northPanel = new JPanel(new BorderLayout()); // North part of main panel
		northLeftPanel = new JPanel(new GridLayout(2, 1)); // Left side of north panel
		northCenterPanel = new JPanel(); // Center of north part of main panel
		SpringLayout northCenterLayout = new SpringLayout();
		northCenterPanel.setLayout(northCenterLayout);
		
		this.setTitle("Sound Sketcher");
		this.setPreferredSize(new Dimension(800,800));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// JToggleButton Settings
		playbackButton = new JToggleButton("Play/ Pause", new ImageIcon(PLAY_PAUSE_ICON_OFF));
		playbackButton.setSelectedIcon(new ImageIcon(PLAY_PAUSE_ICON_ON));
		playbackLoop = new JToggleButton("Loop", new ImageIcon(LOOP_ICON));
		
		// TempoSlider Settings
		tempoSlider = new JSlider(20, 600);
		tempoSlider.setMajorTickSpacing(50);
		tempoSlider.setMinorTickSpacing(5);
		tempoSlider.setPaintTicks(true);
		tempoSlider.setPaintTrack(true);
		tempoSlider.setPaintLabels(true);
		
		// JSpinner settings
		durationSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_SONG_DURATION, 4, 1024, 4));
		
		// TrackPanel settings
		trackPanelList = new TrackPanel[10];
		for (int index = 0; index < 10; index++) {
			trackPanelList[index] = new TrackPanel(index, song.getSongLength(), song.getTrack(index), song.getSynthesizer());
		}
		
		// JTabbedPane settings
		centerTabs = new JTabbedPane();
		centerTabs.addTab("Track ", trackPanelList[0]);
		
		// North left Panel Settings
		northLeftPanel.setPreferredSize(new Dimension(150, 100));	
		northLeftPanel.add(playbackButton);
		northLeftPanel.add(playbackLoop);
		
		// North Center Panel Settings
		tempoSliderLabel = new JLabel("Tempo Slider");
		durationLabel = new JLabel("Duration");
		northCenterPanel.add(tempoSliderLabel);
		northCenterPanel.add(tempoSlider);
		northCenterPanel.add(durationLabel);
		northCenterPanel.add(durationSpinner);	
		
		northCenterLayout.putConstraint(SpringLayout.WEST, tempoSliderLabel, 10, SpringLayout.WEST, northCenterPanel);
		northCenterLayout.putConstraint(SpringLayout.NORTH, tempoSliderLabel, 20, SpringLayout.NORTH, northCenterPanel);

		northCenterLayout.putConstraint(SpringLayout.WEST, tempoSlider, 0, SpringLayout.WEST, tempoSliderLabel);
		northCenterLayout.putConstraint(SpringLayout.NORTH, tempoSlider, 5, SpringLayout.SOUTH, tempoSliderLabel);
		northCenterLayout.putConstraint(SpringLayout.EAST, tempoSlider, -200, SpringLayout.EAST, northCenterPanel);

		northCenterLayout.putConstraint(SpringLayout.EAST, durationLabel, -50, SpringLayout.EAST, northCenterPanel);
		northCenterLayout.putConstraint(SpringLayout.NORTH, durationLabel, 0 , SpringLayout.NORTH, tempoSliderLabel);
		
		northCenterLayout.putConstraint(SpringLayout.EAST, durationSpinner, -30, SpringLayout.EAST, northCenterPanel);
		northCenterLayout.putConstraint(SpringLayout.NORTH, durationSpinner, 10, SpringLayout.SOUTH, durationLabel);
		
		// Main Panel Settings
		northPanel.add(northLeftPanel, BorderLayout.WEST);
		northPanel.add(northCenterPanel, BorderLayout.CENTER);	
		mainPanel.add(centerTabs, BorderLayout.CENTER);	
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		this.add(mainPanel);	
		this.pack();
	}

	/**
	 * 
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		song.setTempo(tempoSlider.getValue());
		int sliderValue = Integer.parseInt(durationSpinner.getValue().toString());
		song.setSongLength(sliderValue);
		for (int i = 0; i < trackPanelList.length; i++)
			trackPanelList[i].setSongLength(sliderValue);
		
	}
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (playbackButton.isSelected())
			song.play();
		else 
			song.stop();
		
		if (playbackLoop.isSelected())
			song.enableLoop(true);
		else 
			song.enableLoop(false);
	}
}
