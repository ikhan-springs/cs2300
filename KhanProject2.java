/* Ibrahim Khan
 * CS 2300-02
 * Project 2
 * 10/10/2021
 * This program reads from a text file and first determines whether the following numbers relate to an implicit or
 * parametric equation of a line. Then, the program displays this equation in its original form and converts the equation 
 * to its other form as well as its point-normal form. Both of these equations are displayed as well. Lastly, three points
 * are given for each line and the program determines the distance between each point and the line.
 */

import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.util.Scanner;

public class KhanProject2 {
	
	public static void main(String[] args) throws IOException {
		
		//Setting up to read from file
		File dataFile = new File("CS2300P2Data.txt");
		Scanner readFile = new Scanner(dataFile);
		
		//Loop runs until entire file has been read
		while (readFile.hasNext()) {
			
			String equationType = readFile.next(); //Variable that determines whether line is implicit or parametric
			double a, b, c, p1, p2, v1, v2, distance; //Double variables used for display and calculations
			
			//If statement runs for an implicit line
			if (equationType.equals("i")) {
				
				//Obtain coefficients a, b, and c
				a = readFile.nextDouble();
				b = readFile.nextDouble();
				c = readFile.nextDouble();
				
				displayImplicitForm(a, b, c); //Display equation in implicit form
				convertImplicit(a, b, c); //Display equation in parametric and point-normal form
				
				//Loop iterates through three points
				for (int i = 0; i < 3; i++) {
					
					//Obtain point values
					double r1 = readFile.nextDouble();
					double r2 = readFile.nextDouble();
					
					distance = calculateDistanceFromImplicitLine(a, b, c, r1, r2); //Calculate distance from line
					
					//If point is on the line
					if (distance == 0) {
						System.out.printf("Distance from point [%.2f, %.2f] to the line is %.2f. The point is on the line.\n", r1, r2, distance);
					}
					//If point is not on the line
					else {
						System.out.printf("Distance from point [%.2f, %.2f] to the line is %.2f.\n", r1, r2, distance);
					}
				}
			}
			//If statement runs for a parametric line
			else if (equationType.equals("p")) {
				
				//Obtain values for p and v
				p1 = readFile.nextDouble();
				p2 = readFile.nextDouble();
				v1 = readFile.nextDouble();
				v2 = readFile.nextDouble();
				
				displayParametricForm(p1, p2, v1, v2); //Display equation in parametric form
				convertParametric(p1, p2, v1, v2); //Display equation in implicit and point-normal form
				
				//Loop iterates through three points
				for (int i = 0; i < 3; i++) {
					
					//Obtain point values
					double r1 = readFile.nextDouble();
					double r2 = readFile.nextDouble();
					
					distance = calculateDistanceFromParametricLine(0.0, 1.25, 4.0, -3.0, r1, r2);  //Calculate distance from line
					
					//If point is on the line
					if (distance == 0) {
						System.out.printf("Distance from point [%.2f, %.2f] to the line is %.2f. The point is on the line.\n", r1, r2, distance);
					}
					//If point is not on the line
					else {
						System.out.printf("Distance from point [%.2f, %.2f] to the line is %.2f.\n", r1, r2, distance);
					}
				}
			}
			else {
				System.out.println("This equation is neither implicit nor parametric.");
			}
			
			System.out.println();
			
		} //end of while loop (end of file)
		
		readFile.close();
		
	} //main
	
	//Displays an equation in implicit form
	public static void displayImplicitForm(double a, double b, double c) {
		
		System.out.printf("Implicit Form: %.2fa + %.2fb + %.2f = 0\n", a, b, c);
		
	} //displayImplicitForm
	
	//Displays an equation in parametric form
	public static void displayParametricForm(double p1, double p2, double v1, double v2) {
		
		System.out.printf("Parametric Form: l(t) = [%.2f, %.2f] + t[%.2f, %.2f]\n", p1, p2, v1, v2);
		
	} //displayParametricForm
	
	//Displays an equation in point-normal form
	public static void displayPointNormalForm(double a, double b, double c) {
		
		double scalar = Math.abs(c);
		
		System.out.printf("Point-Normal Form: %.2fx1 + %.2fx + %.2f = 0\n", a/scalar, b/scalar, c/scalar);
		
	} //displayPointNormalForm
	
	//Converts an equation in implicit form to parametric and displays it, also displays point-normal form
	public static void convertImplicit(double a, double b, double c) {
		
		double p1, p2, v1, v2;
		
		//If statements used for numerical stability (the chosen point is closer to the origin)
		if (a > b) {
			p1 = -(c/a);
			p2 = 0;
		}
		else {
			p1 = 0;
			p2 = -(c/b);
		}
		v1 = b;
		v2 = -a;
		
		displayParametricForm(p1, p2, v1, v2); //Display equation in parametric form
		displayPointNormalForm(a, b, c); //Display equation in point-normal form
	
	} //convertImplicit
	
	//Converts an equation in parametric form to implicit and displays it, also displays point-normal form
	public static void convertParametric(double p1, double p2, double v1, double v2) {
		
		double a, b, c;
		
		a = -v2;
		b = v1;
		c = -(a*p1 + b*p2);
		
		displayImplicitForm(a, b, c); //Display equation in implicit form
		displayPointNormalForm(a, b, c); //Display equation in point-normal form
		
	} //convertParametric
	
	//Calculates distance between a point and an implicit line
	public static double calculateDistanceFromImplicitLine(double a, double b, double c, double r1, double r2) {
		
		return (a*r1 + b*r2 + c) / Math.sqrt(a*a + b*b); 
		
	} //calculateDistanceFromImplicitLine
	
	//Calculates distance between a point and a parametric line
	public static double calculateDistanceFromParametricLine(double p1, double p2, double v1, double v2, double r1, double r2) {
		
		double w1, w2, sinalpha, cosalpha;
		
		w1 = r1 - p1;
		w2 = r2 - p2;
		cosalpha = (v1*w1 + v2*w2) / (Math.sqrt(v1*v1 + v2*v2) * Math.sqrt(w1*w1 + w2*w2));
		sinalpha = Math.sqrt(1 - (Math.pow(cosalpha, 2)));
		
		return Math.sqrt(w1*w1 + w2*w2) * sinalpha;
		
	} //calculateDistanceFromParametricLine
}
