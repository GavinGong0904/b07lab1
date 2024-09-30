import java.io.File;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) {
		try {
			Polynomial p = new Polynomial();
			System.out.println(p.evaluate(3));
			File p1File = new File("p1.txt");
			Polynomial p1 = new Polynomial(p1File);
			double [] c2 = {0,-2,0,0,-9};
			Polynomial p2 = new Polynomial(c2);
			System.out.println(p2.coefficients[1]);
			Polynomial s1 = p1.add(p2);
			System.out.println("s1(0.1) = " + s1.evaluate(0.1));
			if(s1.hasRoot(1))
				System.out.println("1 is a root of s1");
			else
				System.out.println("1 is not a root of s1");
			Polynomial s2 = p1.multiply(p2);
			System.out.println("s2(0.1) = -1.2064045, but it equals" + s2.evaluate(0.1));
			s2.saveToFile("s2");
		} catch (IOException e) {
			System.out.println("Error occurs" + e.getMessage());
		}
	}
}