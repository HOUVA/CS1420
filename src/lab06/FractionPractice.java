package lab06;

public class FractionPractice {

	public static void main(String[] args) {
		Fraction five = new Fraction(5, 1);
		Fraction oneHalf = new Fraction(1, 2);
		Fraction ThreeFourths = new Fraction(3, 4);
		Fraction one = new Fraction(1, 1);
		Fraction FourtyTwoOverFiftySix = new Fraction(42,56);
		
		/*System.out.println(five.toString());
		System.out.println(oneHalf.toString());
		System.out.println(ThreeFourths.toString());
		System.out.println(one.toString());
		System.out.println();
		System.out.println(five.toDouble());
		System.out.println(oneHalf.toDouble());
		System.out.println(ThreeFourths.toDouble());
		System.out.println(one.toDouble());*/
		
		FourtyTwoOverFiftySix.reduce();
		System.out.println(FourtyTwoOverFiftySix.toString());
		
		
		

	}

}
