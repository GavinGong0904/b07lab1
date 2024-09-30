import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Polynomial{
	public double[] coefficients;
	public int[] exponents;
	
	public Polynomial() {
		coefficients = new double[]{0};
		exponents = new int[]{0};
	}
	
	public Polynomial(double[] coefficients) {
		int length = 0;
		for (int i = 0; i < coefficients.length; i++) {
			if (coefficients[i] != 0) {
				length++;
			}
		}
		this.coefficients = new double[length];
		exponents = new int[length];
		int j = 0;
		for (int i = 0; i < coefficients.length; i++) {
			if (coefficients[i] != 0) {
				this.coefficients[j] = coefficients[i];
				exponents[j] = i;
				j++;
			}
		}
	}
	
	public Polynomial(File file) throws IOException {
		ArrayList<Double> coefficientsAL = new ArrayList<>();
		ArrayList<Integer> exponentsAL = new ArrayList<>();
		Scanner scanner = new Scanner(file);
//		String polyFile = scanner.nextline();
		String polyFile = scanner.nextLine();
		scanner.close();
		
		if (polyFile.length() == 0) {
			coefficients = new double[]{0};
			exponents = new int[]{0};
			return;
		}
		
		// keep + and - in items using regex language.
		String[] items = polyFile.split("(?=[+-])");
		if (items[0].contains("x")) {
			String[] coeffExpo = items[0].split("x");
//			coefficientsAL.add((double) coeffExpo[0]);
			coefficientsAL.add(Double.parseDouble(coeffExpo[0]));
			exponentsAL.add(Integer.parseInt(coeffExpo[1]));
		} else {
//			coefficientsAL.add(Double.parseDouble(coeffExpo[0]));
			coefficientsAL.add(Double.parseDouble(items[0]));
			exponentsAL.add(0);
		}
		if (items.length >= 2) {
//			for (int i = 1; i < items.length(); i++) {
			for (int i = 1; i < items.length; i++) {
				String[] coeffExpo = items[i].split("x");
				coefficientsAL.add(Double.parseDouble(coeffExpo[0]));
				exponentsAL.add(Integer.parseInt(coeffExpo[1]));
			}
		}
		
		coefficients = new double[coefficientsAL.size()];
		exponents = new int[coefficientsAL.size()];
		for (int i = 0; i < coefficientsAL.size(); i++) {
			coefficients[i] = coefficientsAL.get(i);
			exponents[i] = exponentsAL.get(i);
		}
	}
	
	public Polynomial add(Polynomial polynomial) {
		if (this.coefficients.length == 0) {
			return polynomial;
		}
		if (polynomial.coefficients.length == 0) {
			return this;
		}
		
		int same = 0;
		int i = 0;
		int j = 0;
		while (i < this.exponents.length && j < polynomial.exponents.length) {
			if (this.exponents[i] == polynomial.exponents[j]) {
				same++;
				i++;
				j++;
			} else if (this.exponents[i] < polynomial.exponents[j]) {
				i++;
			} else {
				j++;
			}
		}
		// when i out of range, we should also consider j.
		while (i < this.exponents.length) {
			i++;
		}
		while (j < polynomial.exponents.length) {
			j++;
		}
		
		double[] parameters = new double[i + j - same];
		for (int m = 0; m < i + j - same; m++) {
			parameters[m] = 1;
		}
		Polynomial polynomialAdded = new Polynomial(parameters);
		i = 0;
		j = 0;
//		k = 0;
		int k = 0;
		while (i < this.exponents.length && j < polynomial.exponents.length) {
//			if (this.exponents[i] == polynomial.exponents[j]) {
			if (this.exponents[i] == polynomial.exponents[j]) {
				polynomialAdded.coefficients[k] = this.coefficients[i] + polynomial.coefficients[j];
				polynomialAdded.exponents[k] = this.exponents[i];
				i++;
				j++;
				k++;
			} else if (this.exponents[i] < polynomial.exponents[j]) {
				polynomialAdded.coefficients[k] = this.coefficients[i];
				polynomialAdded.exponents[k] = this.exponents[i];
				i++;
				k++;
			} else if (this.exponents[i] > polynomial.exponents[j]) {
				polynomialAdded.coefficients[k] = polynomial.coefficients[j];
				polynomialAdded.exponents[k] = polynomial.exponents[j];
				j++;
				k++;
			}
		}
		while (i < this.exponents.length) {
		    polynomialAdded.coefficients[k] = this.coefficients[i];
		    polynomialAdded.exponents[k] = this.exponents[i];
		    i++;
		    k++;
		}
		while (j < polynomial.exponents.length) {
		    polynomialAdded.coefficients[k] = polynomial.coefficients[j];
		    polynomialAdded.exponents[k] = polynomial.exponents[j];
		    j++;
		    k++;
		}
		return polynomialAdded;
	}
	
	public double evaluate(double value) {
		double sum = 0;
		for (int i = 0; i < coefficients.length; i++) {
			sum += coefficients[i] * Math.pow(value, exponents[i]);
		}
		return sum;
	}
	
	public boolean hasRoot(double value) {
		return evaluate(value) == 0;
	}
	
	public Polynomial multiply(Polynomial polynomial) {
		if (this.coefficients.length == 0 || polynomial.coefficients.length == 0) {
			return new Polynomial();
		}

//		int exponentialLarge = polynomial.exponents[polynomial.exponents.length - 1];
//		double[] parameters = new double[exponentialLarge + 1];
//		int i = 0;
//		int j = 0;
//		while (j < polynomial.exponents.length) {
//			if (i == polynomial.exponents[j]) {
//				parameters[i] = polynomial.coefficients[j];
//				i++;
//				j++;
//			} else {
//				parameters[i] = 0;
//				i++;
//			}
//		}
		int maxExponentThis = this.exponents[this.exponents.length - 1];
		int maxExponentPolynomial = polynomial.exponents[polynomial.exponents.length - 1];
		int maxExponent = maxExponentThis + maxExponentPolynomial + 1;
		double[] parameters = new double[maxExponent];
		
		int k = 0;
		for (int i = 0; i < this.exponents.length; i++) {
			for (int j = 0; j < polynomial.exponents.length; j++) {
				k = this.exponents[i] + polynomial.exponents[j];
				parameters[k] += this.coefficients[i] * polynomial.coefficients[j];
			}
		}
//		return Polynomial(parameters);
		return new Polynomial(parameters);
	}
		
	public void saveToFile(String name) throws IOException {
		PrintStream output = new PrintStream(name + ".txt");
		for (int i = 0; i < coefficients.length; i++) {
			if (exponents[i] == 0) {
				output.print(coefficients[i]);
			} else {
				output.print(coefficients[i] + "x" + exponents[i]);
			}
		}
		// close Printstream after use.
		output.close();
	}
}