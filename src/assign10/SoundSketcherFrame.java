package assign10;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.SpringLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;

public class SoundSketcherFrame extends JFrame {
	private static final int DEFAULT_TEMPO = 300;
	private static final int DEFAULT_SONG_DURATION = 16;
	private static final String PLAY_PAUSE_ICON = "src/assign10/playpause.png";
	private static final String LOOP_ICON = "src/assign10/arrow.trianglehead.clockwise.png";
	
	private Song song;
	private TrackPanel[] trackPanelList;
	
	public SoundSketcherFrame() {
		song = new Song(DEFAULT_TEMPO, DEFAULT_SONG_DURATION);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel northPanel = new JPanel(new BorderLayout()); // North part of main panel
		JPanel northLeftPanel = new JPanel(new GridLayout(2, 1)); // Left side of north panel
		JPanel northCenterPanel = new JPanel(); // Center of north part of main panel
		SpringLayout northCenterLayout = new SpringLayout();
		northCenterPanel.setLayout(northCenterLayout);
		
		this.setTitle("Sound Sketcher");
		this.setPreferredSize(new Dimension(800,800));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//JToggleButton Settings
		JToggleButton playbackButton = new JToggleButton(new ImageIcon(PLAY_PAUSE_ICON));
		JToggleButton playbackLoop = new JToggleButton(new ImageIcon(LOOP_ICON));
		
		//TempoSlider Settings
		JSlider tempoSlider = new JSlider(20, 600);
		tempoSlider.setMajorTickSpacing(10);
		tempoSlider.setMinorTickSpacing(5);
		tempoSlider.setPaintTicks(true);
		tempoSlider.setPaintTrack(true);
		
		//JSpinner settings
		JSpinner durationSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_SONG_DURATION, 4, 1024, 4));
		
		//TrackPanel settings
		/*
		for (int index = 0; index < 10; index++) {
			this.trackPanelList[index] = new TrackPanel(index, song.getSongLength(), song.getTrack(index), song.getSynthesizer());
		}*/
		
		//JTabbedPane settings
		JTabbedPane centerTabs = new JTabbedPane();
		centerTabs.addTab("Track ", new JPanel());
		
		
		northLeftPanel.setPreferredSize(new Dimension(100, 100));	
		northLeftPanel.add(playbackButton);
		northLeftPanel.add(playbackLoop);
		
		JLabel tempoSliderLabel = new JLabel("Tempo Slider");
		JLabel durationLabel = new JLabel("Duration");
		northCenterPanel.add(tempoSliderLabel);
		northCenterPanel.add(tempoSlider);
		//northCenterPanel.add(durationLabel);
		//northCenterPanel.add(durationSpinner);
		
		northCenterLayout.putConstraint(SpringLayout.WEST, tempoSliderLabel, 5, SpringLayout.WEST, northCenterPanel);
		northCenterLayout.putConstraint(SpringLayout.NORTH, tempoSliderLabel, 50, SpringLayout.NORTH, northCenterPanel);

		northCenterLayout.putConstraint(SpringLayout.WEST, tempoSlider, 30, SpringLayout.EAST, durationLabel);
		northCenterLayout.putConstraint(SpringLayout.NORTH, tempoSlider, 30, SpringLayout.NORTH, northCenterPanel);

		//northCenterLayout.putConstraint(SpringLayout.EAST, durationLabel, 5, SpringLayout.EAST, tempoSliderLabel);
		//northCenterLayout.putConstraint(SpringLayout.EAST, durationSpinner, 5 , SpringLayout.SOUTH, durationLabel);
		
		northPanel.add(northLeftPanel, BorderLayout.WEST);
		northPanel.add(northCenterPanel, BorderLayout.CENTER);
		
		mainPanel.add(centerTabs, BorderLayout.CENTER);	
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		this.add(mainPanel);	
		this.pack();
	}
}
