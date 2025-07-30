
/* C1722325
 *
 * Optionally, if you have any comments regarding your submission, put them here. 
 * For instance, specify here if your program does not generate the proper output or does not do it in the correct manner.
 */

import java.util.*;
import java.io.*;

// A class that represents a dense vector and allows you to read/write its elements
class DenseVector {
	private int[] elements;

	public DenseVector(int n) {
		elements = new int[n];
	}

	public DenseVector(String filename) {
		File file = new File(filename);
		ArrayList<Integer> values = new ArrayList<Integer>();

		try {
			Scanner sc = new Scanner(file);

			while (sc.hasNextInt()) {
				values.add(sc.nextInt());
			}

			sc.close();

			elements = new int[values.size()];
			for (int i = 0; i < values.size(); ++i) {
				elements[i] = values.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Read an element of the vector
	public int getElement(int idx) {
		return elements[idx];
	}

	// Modify an element of the vector
	public void setElement(int idx, int value) {
		elements[idx] = value;
	}

	// Return the number of elements
	public int size() {
		return (elements == null) ? 0 : (elements.length);
	}

	// Print all the elements
	public void print() {
		if (elements == null) {
			return;
		}

		for (int i = 0; i < elements.length; ++i) {
			System.out.println(elements[i]);
		}
	}
}

// A class that represents a sparse matrix
public class SparseMatrix {
	// Auxiliary function that prints out the command syntax
	public static void printCommandError() {
		System.err.println("ERROR: use one of the following commands");
		System.err.println(" - Read a matrix and print information: java SparseMatrix -i <MatrixFile>");
		System.err.println(" - Read a matrix and print elements: java SparseMatrix -r <MatrixFile>");
		System.err.println(" - Transpose a matrix: java SparseMatrix -t <MatrixFile>");
		System.err.println(" - Add two matrices: java SparseMatrix -a <MatrixFile1> <MatrixFile2>");
		System.err.println(" - Matrix-vector multiplication: java SparseMatrix -v <MatrixFile> <VectorFile>");
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			printCommandError();
			System.exit(-1);
		}

		if (args[0].equals("-i")) {
			if (args.length != 2) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			System.out.println("Read matrix from " + args[1]);
			System.out.println("The matrix has " + mat.getNumRows() + " rows and " + mat.getNumColumns() + " columns");
			System.out.println("It has " + mat.numNonZeros() + " non-zeros");
		} else if (args[0].equals("-r")) {
			if (args.length != 2) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			System.out.println("Read matrix from " + args[1] + ":");
			mat.print();
		} else if (args[0].equals("-t")) {
			if (args.length != 2) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			System.out.println("Read matrix from " + args[1]);
			SparseMatrix transpose_mat = mat.transpose();
			System.out.println();
			System.out.println("Matrix elements:");
			mat.print();
			System.out.println();
			System.out.println("Transposed matrix elements:");
			transpose_mat.print();
		} else if (args[0].equals("-a")) {
			if (args.length != 3) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat1 = new SparseMatrix();
			mat1.loadEntries(args[1]);
			System.out.println("Read matrix 1 from " + args[1]);
			System.out.println("Matrix elements:");
			mat1.print();

			System.out.println();
			SparseMatrix mat2 = new SparseMatrix();
			mat2.loadEntries(args[2]);
			System.out.println("Read matrix 2 from " + args[2]);
			System.out.println("Matrix elements:");
			mat2.print();
			SparseMatrix mat_sum1 = mat1.add(mat2);

			System.out.println();
			mat1.multiplyBy(2);
			SparseMatrix mat_sum2 = mat1.add(mat2);

			mat1.multiplyBy(5);
			SparseMatrix mat_sum3 = mat1.add(mat2);

			System.out.println("Matrix1 + Matrix2 =");
			mat_sum1.print();
			System.out.println();

			System.out.println("Matrix1 * 2 + Matrix2 =");
			mat_sum2.print();
			System.out.println();

			System.out.println("Matrix1 * 10 + Matrix2 =");
			mat_sum3.print();
		} else if (args[0].equals("-v")) {
			if (args.length != 3) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			DenseVector vec = new DenseVector(args[2]);
			DenseVector mv = mat.multiply(vec);

			System.out.println("Read matrix from " + args[1] + ":");
			mat.print();
			System.out.println();

			System.out.println("Read vector from " + args[2] + ":");
			vec.print();
			System.out.println();

			System.out.println("Matrix-vector multiplication:");
			mv.print();
		}
	}

	// Loading matrix entries from a text file
	// You need to complete this implementation
	public void loadEntries(String filename) {
		File file = new File(filename);

		try {
			Scanner sc = new Scanner(file);
			numRows = sc.nextInt();
			numCols = sc.nextInt();
			entries = new ArrayList<Entry>();

			while (sc.hasNextInt()) {
				// Read the row index, column index, and value of an element
				int row = sc.nextInt();
				int col = sc.nextInt();
				int val = sc.nextInt();

				// Add your code here to add the element into data member entries

				int position;
				position = row * numCols + col;

				entries.add(new Entry(position,val));
			}

			// Add your code here for sorting non-zero elements
			// Replacing the variable with a sorted merge sort instance by using the method with entries as a parameter
			entries = mergeSort(entries);

		} catch (Exception e) {
			e.printStackTrace();
			numRows = 0;
			numCols = 0;
			entries = null;
		}
	}

	//Existing code that we had created last year and was able to be implemented properly
	// Merge sorting method that sorts the entries array list using their positions
	static ArrayList<Entry> mergeSort(ArrayList<Entry> entriesList) {

		ArrayList<Entry> sortedList;

		if (entriesList.size() == 1) {
			sortedList = entriesList;
		} else {
			int middle = entriesList.size() /2;

			ArrayList<Entry> leftSide = new ArrayList<>();
			ArrayList<Entry> rightSide = new ArrayList<>();

			for ( int x = 0; x < middle; x++) {
				leftSide.add(entriesList.get(x));
			}
			for ( int x = middle; x < entriesList.size(); x++) {
				rightSide.add(entriesList.get(x));
			}

			leftSide = mergeSort(leftSide);
			rightSide = mergeSort(rightSide);
			sortedList = merge(leftSide, rightSide);

		}
		return sortedList;
	}

	static ArrayList<Entry> merge(ArrayList<Entry> leftSide, ArrayList<Entry> rightSide) {

		ArrayList<Entry> merged = new ArrayList<>();
		int left = 0;
		int right = 0;

		while (left < leftSide.size() && right < rightSide.size()) {

			if ((leftSide.get(left).getPosition()) - (rightSide.get(right).getPosition()) < 0) {
				merged.add(leftSide.get(left));
				left++;
			} else {
				merged.add(rightSide.get(right));
				right++;
			}
		}

		while (left < leftSide.size()) {
			merged.add(leftSide.get(left));
			left++;
		}

		while (right < rightSide.size()) {
			merged.add(rightSide.get(right));
			right++;
		}

		return merged;
	}


	// Default constructor
	public SparseMatrix() {
		numRows = 0;
		numCols = 0;
		entries = null;
	}

	// A class representing a pair of column index and elements
	private class Entry {
		private int position; // Position within row-major full array representation
		private int value; // Element value

		// Constructor using the column index and the element value
		public Entry(int pos, int val) {
			this.position = pos;
			this.value = val;
		}

		// Copy constructor
		public Entry(Entry entry) {
			this(entry.position, entry.value);
		}

		// Read column index
		int getPosition() {
			return position;
		}

		// Set column index
		void setPosition(int pos) {
			this.position = pos;
		}

		// Read element value
		int getValue() {
			return value;
		}

		// Set element value
		void setValue(int val) {
			this.value = val;
		}
	}

	// Adding two matrices
	public SparseMatrix add(SparseMatrix M) {
		// Add your code here
		SparseMatrix mat_sum = new SparseMatrix();
		mat_sum.entries = new ArrayList<Entry>();
		mat_sum.numCols = numCols;
		mat_sum.numRows = numRows;
		int indexA = 0;
		int indexB = 0;

		//Reference: https://www.geeksforgeeks.org/operations-sparse-matrices/

		while (indexA < entries.size() && indexB < M.entries.size()) {
			// Find position and values and indexes
			int positionA = entries.get(indexA).getPosition();
			int valueA = entries.get(indexA).getValue();
			int positionB = M.entries.get(indexB).getPosition();
			int valueB = M.entries.get(indexB).getValue();

//          If PositionA is smaller add A
			if (positionA < positionB) {
				mat_sum.entries.add(new Entry(positionA, valueA));
				indexA++;

//          If PositionB is smaller add B
			} else if (positionB < positionA) {
				mat_sum.entries.add(new Entry(positionB, valueB));
				indexB++;
			}

//          If they are have equal position add their values
			else {
				int addResult = valueA + valueB;
				mat_sum.entries.add(new Entry(positionA, addResult));
				indexA++;
				indexB++;
			}
		}

		// Add remaining values after exiting while loop

		while (indexA < entries.size()){
			mat_sum.entries.add(new Entry(entries.get(indexA).getPosition(), entries.get(indexA).getValue()));
			indexA++;
		}

		while (indexB < M.entries.size()){
			mat_sum.entries.add(new Entry(entries.get(indexB).getPosition(), entries.get(indexB).getValue()));
			indexB++;
		}

		return mat_sum;
	}

	// Transposing a matrix
	public SparseMatrix transpose() {
		// Add your code here
		SparseMatrix transpose_mat = new SparseMatrix();
		transpose_mat.entries = new ArrayList<Entry>();
		//Flips the columns to rows, and rows to columns
		int temp = numCols;
		transpose_mat.numCols = numRows;
		transpose_mat.numRows = temp;

		for (int i = 0; i < entries.size(); i++) {
			int pos = entries.get(i).getPosition();
			int val = entries.get(i).getValue();

			//retrieves the non-zeros position for row, column
			int col = pos % transpose_mat.numRows;
			int row = (pos - col) / transpose_mat.numRows;

			// Then creates a new position for the non-zero
			int NewPos = (col * transpose_mat.numCols) + row;

			transpose_mat.entries.add(new Entry(NewPos, val));
		}

		// Uses a built in function that sorts the ArrayList easily according to position
		transpose_mat.entries.sort(Comparator.comparing(Entry::getPosition));

		return transpose_mat;
	}

	// Matrix-vector multiplication
	public DenseVector multiply(DenseVector v) {
		// Add your code here
		DenseVector mv = new DenseVector(numRows);
		ArrayList<Integer> entriesPos = new ArrayList<>();
		int rowSum;
		int entriesVal;
		int VectorVal;

		// Stores in an Arraylist the positions of the non-zeros.
		for (int i = 0; i < entries.size(); i++) {
			entriesPos.add(i, entries.get(i).getPosition());
		}

		// Iterating through all the positions multiplying the corresponding non-zeros with the vector and storing the sum
		for (int row = 0; row < numRows; row++){
			rowSum = 0;

			for (int col = 0; col < numCols; col++){
				if (entriesPos.contains(row * numCols + col)) {

					int index = entriesPos.indexOf(row * numCols + col);

					entriesVal = entries.get(index).getValue();
					VectorVal = v.getElement(col);
					rowSum = rowSum + (entriesVal * VectorVal);

				}
				mv.setElement(row,rowSum);
			}
		}
		return mv;
	}

	// Return the number of non-zeros
	public int numNonZeros() {
		// Add your code here
		// The size of the entries Arraylist shows us the number of non-zeros
		return entries.size();
	}

	// Multiply the matrix by a scalar, and update the matrix elements
	public void multiplyBy(int scalar) {
		// Add your code here
		// For loop that multiplies each non zero value with the scalar and updates the list.
		for (int i =0 ; i < entries.size() ; i++ ) {
			int scalarVal = entries.get(i).getValue() * scalar;
			entries.get(i).setValue(scalarVal);
		}
	}

	// Number of rows of the matrix
	public int getNumRows() {
		return this.numRows;
	}

	// Number of columns of the matrix
	public int getNumColumns() {
		return this.numCols;
	}

	// Output the elements of the matrix, including the zeros
	// Do not modify this method
	public void print() {
		int n_elem = numRows * numCols;
		int pos = 0;

		for (int i = 0; i < entries.size(); ++i) {
			int nonzero_pos = entries.get(i).getPosition();

			while (pos <= nonzero_pos) {
				if (pos < nonzero_pos) {
					System.out.print("0 ");
				} else {
					System.out.print(entries.get(i).getValue());
					System.out.print(" ");
				}

				if ((pos + 1) % this.numCols == 0) {
					System.out.println();
				}

				pos++;
			}
		}

		while (pos < n_elem) {
			System.out.print("0 ");
			if ((pos + 1) % this.numCols == 0) {
				System.out.println();
			}

			pos++;
		}
	}

	private int numRows; // Number of rows
	private int numCols; // Number of columns
	private ArrayList<Entry> entries; // Non-zero elements
}
