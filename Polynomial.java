public class Polynomial{
	public double[] coefficients;
	
	public Polynomial() {
//		coefficients = {0};
		coefficients = new double[]{0};
	}
	
	public Polynomial(double[] coefficients) {
		this.coefficients = coefficients;
	}
	
//	public Polynomial add(Polynomial polynomial) {
////	if (this.polynomial.length >= polynomial.length) {
//		if (this.coefficients.length >= polynomial.coefficients.length) {
//			int length = this.coefficients.length;
//			double [] parameters = new double[length];
//			Polynomial polynomialAdded = new Polynomial(parameters);
////		for (i = 0; i < length; i++) {
//			for (int i = 0; i < length; i++) {
////			if (i <= polynomial.length) {
//				if (i <= coefficients.length) {
//					polynomialAdded.coefficients[i] = this.coefficients[i] + polynomial.coefficients[i];
//				} else {
//					polynomialAdded.coefficients[i] = this.coefficients[i];
//				}
//			}
//		} else {
//			int length = coefficients.length;
//			double [] parameters = new double[length];
//			Polynomial polynomialAdded = new Polynomial(parameters);
//			for (int i = 0; i < length; i++) {
//				if (i <= this.coefficients.length) {
//					polynomialAdded.coefficients[i] = this.coefficients[i] + polynomial.coefficients[i];
//				} else {
//					polynomialAdded.coefficients[i] = polynomial.coefficients[i];
//				}
//			}
//		}
//		return polynomialAdded;
//	}
	
	public Polynomial add(Polynomial polynomial) {
		int length = Math.max(this.coefficients.length, polynomial.coefficients.length);
		double [] parameters = new double[length];
		Polynomial polynomialAdded = new Polynomial(parameters);
//		if (this.polynomial.length >= polynomial.length) {
		if (this.coefficients.length >= polynomial.coefficients.length) {
//			for (i = 0; i < length; i++) {
			for (int i = 0; i < length; i++) {
//				if (i <= polynomial.length) {
				if (i < polynomial.coefficients.length) {
					polynomialAdded.coefficients[i] = this.coefficients[i] + polynomial.coefficients[i];
				} else {
					polynomialAdded.coefficients[i] = this.coefficients[i];
				}
			}
		} else {
			for (int i = 0; i < length; i++) {
				if (i < this.coefficients.length) {
					polynomialAdded.coefficients[i] = this.coefficients[i] + polynomial.coefficients[i];
				} else {
					polynomialAdded.coefficients[i] = polynomial.coefficients[i];
				}
			}
		}
		return polynomialAdded;
	}
	
	public double evaluate(double value) {
		double sum = 0;
		for (int i = 0; i < coefficients.length; i++) {
//			sum += polynomial.coefficients[i] * value^i;
			sum += coefficients[i] * Math.pow(value, i);
		}
		return sum;
	}
	
	public boolean hasRoot(double value) {
//		return polynomial.evaluate(value) == 0;
		return evaluate(value) == 0;
	}
}