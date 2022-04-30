/* Ibrahim Khan
 * CS 2300-02
 * Project 3
 * 11/11/2021
 * This program deals with forward and inverse kinematics of a simple planar robot with two revolute links.
 * First, the program reads from a file to get the lengths of the two links. Then, the file states whether 
 * forward or inverse kinematics needs to be calculated and provides two values after it. If the program must
 * calculate forward kinematics, the file provides two joint angle values and the program calculates an end
 * effector position. If the program must calculate inverse kinematics, the file provides the end effector
 * coordinates and the program must calculate the two joint angle values.
 */

import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.util.Scanner;

public class KhanProject3 {

	public static void main(String[] args) throws IOException {
		
		//Declaring variables
		String lineType, tempRead;
		double lenLink1, lenLink2, givenVar1, givenVar2;
		
		//Setting up to read from file
		File configFile = new File("config.txt");
		Scanner readFile = new Scanner(configFile);
		
		//Read lengths of the two links and print them
		tempRead = readFile.next(); //Temporary string needed due to comma after first value
		lenLink1 = Double.parseDouble(tempRead.substring(0, tempRead.length() - 1));
		lenLink2 = readFile.nextDouble();
		System.out.printf("Link lengths are: %.6f for link1 and %.6f for link2\n", lenLink1, lenLink2);
		
		//Loop iterates until entire file has been read
		while (readFile.hasNext()) {
			
			//Read next three values from file
			lineType = readFile.next();
			tempRead = readFile.next(); //Temporary string needed due to comma after first value
			givenVar1 = Double.parseDouble(tempRead.substring(0, tempRead.length() - 1));
			givenVar2 = readFile.nextDouble();
			
			//Forward kinematics
			if (lineType.charAt(0) == 'f') {
				calculateEndEffector(lenLink1, lenLink2, givenVar1, givenVar2);
			}
			//Inverse kinematics
			else if (lineType.charAt(0) == 'i') {
				calculateJointAngles(lenLink1, lenLink2, givenVar1, givenVar2);
			}
			//If something besides i or f is in the file
			else {
				System.out.println("Invalid line type in file");
			}
		}
		
		readFile.close();
		
	} //main
	
	//Calculates the end effector coordinates when given the lengths of the two links and values for both thetas
	public static void calculateEndEffector(double lenLink1, double lenLink2, double theta1, double theta2) {
		
		//Declaring variables
		double x1Link1, x2Link1, x1Link2, x2Link2, x1, x2;
		
		//Checking if theta1 is 0 and performing appropriate calculations for x1 and x2 related to link1
		if (theta1 == 0) {
			x1Link1 = lenLink1;
			x2Link1 = 0;
		}
		else {
			x1Link1 = lenLink1 * Math.cos(theta1);
			x2Link1 = lenLink1 * Math.sin(theta1);
		}
		
		//Checking if theta2 is 0 and performing appropriate calculations for x1 and x2 related to link2
		if (theta2 == 0) {
			x1Link2 = lenLink2;
			x2Link2 = 0;
		}
		else {
			x1Link2 = lenLink2 * Math.cos(Math.PI - theta2);
			x2Link2 = lenLink2 * Math.sin(Math.PI - theta2);
		}
		
		//Sum the x1 and x2 values found for link1 and link2 to get end effector coordinates
		x1 = x1Link1 + x1Link2;
		x2 = x2Link1 + x2Link2;
		
		//Print the two angles and end effector position
		System.out.printf("FWD: theta1: %.6f theta2: %.6f End effector pos: [%.6f, %.6f]\n", theta1, theta2, x1, x2);
		
	} //calculateEndEffector
	
	//Calculates the joint angles when given the lengths of two links and the end effector coordinates
	public static void calculateJointAngles(double lenLink1, double lenLink2, double x1, double x2) {
		
		//Declaring variables
		double theta1, theta2;
		
		//Calculating joint angles
		theta2 = Math.acos((x1*x1 + x2*x2 - (lenLink1*lenLink1 + lenLink2*lenLink2)) / (2 * lenLink1 * lenLink2));
		theta1 = Math.atan2(x2, x1) - Math.atan2(lenLink2 * Math.sin(theta2), lenLink1 + (lenLink2 * Math.cos(theta2)));
		
		//Check if either angle is NaN (Not a Number)
		if (Double.isNaN(theta1) || Double.isNaN(theta2)) {
			//Print the end effector position and state that it cannot be achieved with the two links provided
			System.out.printf("INV: (x1, x2) = (%.6f, %.6f) POSITION CANNOT BE ACHIEVED\n", x1, x2, theta1, theta2);
		}
		else {
			//Print the end effector position and the joint angles
			System.out.printf("INV: (x1, x2) = (%.6f, %.6f) Joint angles: [%.6f, %.6f]\n", x1, x2, theta1, theta2);
		}
		
	} //calculateJointAngles
}