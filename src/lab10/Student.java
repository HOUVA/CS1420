package lab10;

public class Student implements Comparable<Student>{
	private String firstName;
	private String lastName;
	private int id;
	private double gpa;
	
	public Student(String firstName, String lastName, int id, double gpa) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.gpa = gpa;
	}
	
	public String toString() {
		return firstName + " " + lastName + " (" + id + ") GPA = " + gpa;
	}
	
	@Override
	public int compareTo(Student other) {
		if(this.id > other.id)
		   return 1;
		if(this.id < other.id)
		   return -1;
		return 0;
	}
}
