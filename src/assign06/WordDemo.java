package assign06;

public class WordDemo {

	public static void main(String[] args) {
		Word hello = new Word("hello");
		System.out.println(hello.toString());
		Word olleh = hello.reverse();
		System.out.println(olleh.toString());
	}

}
