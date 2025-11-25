package assign12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SongFiles {
	public static class ChronologicalOrder implements Comparator<AudioEvent> {

		@Override
		public int compare(AudioEvent o1, AudioEvent o2) {
			if (o1.getTime() != o2.getTime())
				return o1.getTime() - o2.getTime();
			return o1.compareTo(o2);
		}

	}

	public static void writeFile(File file, Song song) {

	}

	public static void readFile(File file, Song song) {
		Scanner fileInput = null;
		try {
			fileInput = new Scanner(file);
			
			song.setTempo(fileInput.nextInt());
			song.setSongLength(fileInput.nextInt());
			fileInput.nextLine();
			
			
		}catch (FileNotFoundException | InputMismatchException | IllegalStateException e) {
			if (e instanceof FileNotFoundException)
				System.out.println("Unable to locate file");
			else 
				System.out.println("There is data in thhis file that is invalid");
		}
		
		

	}

	public static ArrayList<AudioEvent> filterEvents(ArrayList<AudioEvent> events) {
		ArrayList<AudioEvent> filteredEvents = new ArrayList<AudioEvent>();
		HashMap<Integer, ArrayList<AudioEvent>> mappedEvents = new HashMap<>();

		for (AudioEvent event : events) {
			Integer eventTime = event.getTime();
			if (!(mappedEvents.containsKey(eventTime))) {
				mappedEvents.put(eventTime, new ArrayList<AudioEvent>());
				mappedEvents.get(eventTime).add(event);
				filteredEvents.add(event);
			} else {
				for (AudioEvent timeEvent : mappedEvents.get(eventTime)) {
					if (event instanceof NoteEvent && timeEvent instanceof NoteEvent) {
						NoteEvent inputEvent = (NoteEvent) event;
						NoteEvent eventInMap = (NoteEvent) timeEvent;
						if (inputEvent.getPitch() != eventInMap.getPitch()
								&& inputEvent.getTime() != eventInMap.getTime()) {
							mappedEvents.get(eventTime).add(event);
							filteredEvents.add(event);
						}
					} else if (event instanceof VolumeEvent && timeEvent instanceof VolumeEvent) {
						VolumeEvent inputEvent = (VolumeEvent) event;
						VolumeEvent eventInMap = (VolumeEvent) timeEvent;
						if (inputEvent.getTime() != eventInMap.getTime()) {
							mappedEvents.get(eventTime).add(event);
							filteredEvents.add(event);
						}
					}
				}
			}
		}
		
		Collections.sort(filteredEvents);
		return filteredEvents;
	}

}
