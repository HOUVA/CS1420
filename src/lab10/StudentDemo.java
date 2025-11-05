package lab10;
import java.util.ArrayList;
import java.util.Collections;

public class StudentDemo {
	public static void main(String[] args) {
		Student s1 = new Student("Matthew", "Suggars", 1067447, 3.9);
		Student s2 = new Student("Angie", "Bennett", 1444444, 4.0);
		int result = s1.compareTo(s2);
		if(result > 0)
		   System.out.println("The first student's GPA is larger.");
		else if(result < 0)
		   System.out.println("The second student's GPA is larger.");
		else
		   System.out.println("Both students have the same GPA.");
		
		ArrayList<Student> graduates = new ArrayList<Student>();
		graduates.add(new Student("Logan", "Marquis", 5555555, 3.8));
		// add more students
		Collections.sort(graduates);
		System.out.println(graduates);
	}
}
