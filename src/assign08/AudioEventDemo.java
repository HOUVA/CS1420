package assign08;

public class AudioEventDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NoteEvent shortMiddleC = new NoteEvent(0, 5, 1, 60);
		SimpleSynthesizer synth = new SimpleSynthesizer();

		System.out.println(shortMiddleC.toString());
		shortMiddleC.execute(synth);
		shortMiddleC.complete(synth);
	}

}
