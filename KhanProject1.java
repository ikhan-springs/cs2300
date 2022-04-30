/* Ibrahim Khan
 * CS 2300-02
 * Project 1
 * 09/14/2021
 * This project is divided into four parts. First, the program reads from a file that contains information and values
 * about two matrices. This information is separated into two different files, one for each matrix. Second, the two
 * matrices are displayed individually. Third, a new matrix is calculated using the formula C = 10.5B - 8.0A, where C
 * is the new matrix, B is the second matrix, and A is the first matrix from the prior two parts of the project. This 
 * matrix is written to a new file and is also displayed. Fourth, the second matrix is transposed into a new matrix, 
 * which is also written to a new file and displayed. 
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class KhanProject1 {
	
	public static void main(String[] args) throws IOException {
		
		//Setting up to read from matrixFile
		File matrixFile = new File("matrixFile.txt");
		Scanner readFile = new Scanner(matrixFile);
		
		//-------------------------PART 1 AND 2 FOR FIRST MATRIX-------------------------
		
		//Setting up to write values to firstMatrix file
		File matrix1File = new File("firstMatrix.txt");
		PrintWriter writeMatrix1 = new PrintWriter(matrix1File);
		
		//Gathering matrix info
		String matrix1Name = readFile.next();
		int matrix1Rows = readFile.nextInt();
		int matrix1Columns = readFile.nextInt();
		
		//Initializing first matrix
		int[][] matrix1 = new int[matrix1Columns][matrix1Rows];
		
		//Matrix info written to file
		writeMatrix1.print(matrix1Name + " " + matrix1Rows + " " + matrix1Columns);
		
		//Reading values from file to matrix, then writing those values to another file
		for (int i = 0; i < matrix1Columns; i++) {
			for (int j = 0; j < matrix1Rows; j++) {
				matrix1[i][j] = readFile.nextInt();
				writeMatrix1.print(" " + matrix1[i][j]);
			}
		}
		
		//Displaying first matrix
		System.out.print(matrix1Name + " = ");
		for (int i = 0; i < matrix1Rows; i++) {
			if (i == 0) {
				System.out.print("|");
			}
			else {
				System.out.print("    |");
			}
			for (int j = 0; j < matrix1Columns; j++) {
				System.out.print("\t" + matrix1[j][i] + "\t");
			}
			System.out.print("|\n");
		}
		
		//-------------------------PART 1 AND 2 FOR SECOND MATRIX-------------------------
		
		//Setting up to write values to secondMatrix file
		File matrix2File = new File("secondMatrix.txt");
		PrintWriter writeMatrix2 = new PrintWriter(matrix2File);
		
		//Gathering matrix info
		String matrix2Name = readFile.next();
		int matrix2Rows = readFile.nextInt();
		int matrix2Columns = readFile.nextInt();
		
		//Initializing second matrix
		int[][] matrix2 = new int[matrix2Columns][matrix2Rows];
		
		//Matrix info written to file
		writeMatrix2.print(matrix2Name + " " + matrix2Rows + " " + matrix2Columns);
		
		//Reading values from file to matrix, then writing those values to another file
		for (int i = 0; i < matrix2Columns; i++) {
			for (int j = 0; j < matrix2Rows; j++) {
				matrix2[i][j] = readFile.nextInt();
				writeMatrix2.print(" " + matrix2[i][j]);
			}
		}
		
		//Displaying second matrix
		System.out.print("\n" + matrix2Name + " = ");
		for (int i = 0; i < matrix2Rows; i++) {
			if (i == 0) {
				System.out.print("|");
			}
			else {
				System.out.print("    |");
			}
			for (int j = 0; j < matrix2Columns; j++) {
				System.out.print("\t" + matrix2[j][i] + "\t");
			}
			System.out.print("|\n");
		}
		
		//-----------------------------------PART 3-----------------------------------
		
		//Setting up to write values to calcMatrix file
		File matrix3File = new File("calcMatrix.txt");
		PrintWriter writeMatrix3 = new PrintWriter(matrix3File);
		
		//Initializing matrix name
		String matrix3Name = "10.5" + matrix2Name + " - 8.0" + matrix1Name;
		
		//Initializing calculated matrix
		double[][] matrix3 = new double[matrix1Columns][matrix1Rows];
		
		//Matrix info written to file
		writeMatrix3.print(matrix1Name + " " + matrix1Rows + " " + matrix1Columns);
		
		//Calculating matrix values, then writing those values to file
		for (int i = 0; i < matrix1Columns; i++) {
			for (int j = 0; j < matrix1Rows; j++) {
				matrix3[i][j] = 10.5 * matrix2[i][j] - 8 * matrix1[i][j]; //NEED TO CHANGE VALUES TO 10.5 AND 8
				writeMatrix3.print(" " + matrix3[i][j]);
			}
		}
		
		//Displaying calculated matrix
		System.out.print("\n" + matrix3Name + " = ");
		for (int i = 0; i < matrix1Rows; i++) {
			if (i == 0) {
				System.out.print("|");
			}
			else {
				System.out.print("               |");
			}
			for (int j = 0; j < matrix1Columns; j++) {
				System.out.print("\t" + matrix3[j][i] + "\t");
			}
			System.out.print("|\n");
		}
		
		//-----------------------------------PART 4-----------------------------------
		
		//Setting up to write values to transposedMatrix file
		File matrix4File = new File("transposedMatrix.txt");
		PrintWriter writeMatrix4 = new PrintWriter(matrix4File);
		
		//Initializing matrix size
		int matrix4Rows = matrix2Columns;
		int matrix4Columns = matrix2Rows;
		
		//Initializing transposed matrix
		int[][] matrix4 = new int[matrix4Columns][matrix4Rows];
		
		//Matrix info written to file
		writeMatrix4.print(matrix2Name + " " + matrix4Rows + " " + matrix4Columns);
		
		//Transposing matrix values, then writing those values to file
		for (int i = 0; i < matrix4Columns; i++) {
			for (int j = 0; j < matrix4Rows; j++) {
				matrix4[i][j] = matrix2[j][i];
				writeMatrix4.print(" " + matrix4[i][j]);
			}
		}
		
		//Displaying transposed matrix
		System.out.print("\n" + matrix2Name + " = ");
		for (int i = 0; i < matrix4Rows; i++) {
			if (i == 0) {
				System.out.print("|");
			}
			else {
				System.out.print("    |");
			}
			for (int j = 0; j < matrix4Columns; j++) {
				System.out.print("\t" + matrix4[j][i] + "\t");
			}
			System.out.print("|\n");
		}
		
		//Closing read and write files
		readFile.close();
		writeMatrix1.close();
		writeMatrix2.close();
		writeMatrix3.close();
		writeMatrix4.close();
	}
}