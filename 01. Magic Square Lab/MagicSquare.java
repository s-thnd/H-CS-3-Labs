import java.util.*;

/** 
 * The MagicSquare class implements several methods that
 * check if a 2d array of numbers is a magic square or not
 * as well as creates its own magic square *
 * @author Shyam Thandullu
 * Collaborators: None
 * Teacher Name: Ms. Bailey
 * Period: 5
 * Due Date: 08-18-22
 */

public class MagicSquare
{
	public static final String MAGIC_SUCCESS = "successful";
	public static final String FAILED_NOT_ALL_VALUES = "not all values used";
	public static final String FAILED_ROW_SUM = "failed row sum";
	public static final String FAILED_COL_SUM = "failed column sum";
	public static final String FAILED_DIAG_SUM = "failed diagonal sum";

	private int[][] numberArray;
	private ArrayList<Integer> numbersEntered  = new ArrayList<Integer>();
	
	private int numAdded = 0;
	private int currentCol = 0;
	
	/**
	 * Creates a 2D Array with the dimensions of the inputed side lengths
	 * @param sideLength the length of the rows and columns of the array
	 */
	public MagicSquare(int sideLength)
	{
		numberArray = new int[sideLength][sideLength];
	}
	
	/**
	 * Adds a user inputed number into the next available spot in the array (row major)
	 * @param number the number the user inputed
	 */
	public void add(int number)
	{
		numbersEntered.add(number);
		//need to add to row until row is full and then go to next column
		if (numAdded < numberArray[0].length) {
			numberArray[currentCol][numAdded] = number;
			numAdded++;
		}
		else {
			numAdded = 0;
			numberArray[currentCol+1][numAdded] = number;
			numAdded++;
			currentCol++;
		}
	}
	
	/**
	 * Checks if the 2D array qualifies as a Magic Square
	 * @return The corresponding success string or error message
	 */
	public String isMagic()
	{
		//check diagonal sums
		int diaSum1 = 0, diaSum2 = 0;
	    for (int i = 0; i < numberArray.length; i++)
        {
            diaSum1 += numberArray[i][i];
            diaSum2 += numberArray[i][numberArray.length-1-i];
        }	
		
	    //list to keep track of row and col sums
        ArrayList<Integer> rowSums = new ArrayList<Integer>();
        ArrayList<Integer> colSums = new ArrayList<Integer>();
        
        //populate list of row and col sums
        for (int row = 0; row < numberArray.length; row++) {	 
            int rowSum = 0, colSum = 0;
            for (int col = 0; col < numberArray.length; col++)
            {
                rowSum += numberArray[row][col];
                colSum += numberArray[col][row];
            }
            rowSums.add(rowSum);
            colSums.add(colSum);
        }
      
        //check equal row sum
        boolean equalRow = true;
        for (Integer n : rowSums) {
            if (n != rowSums.get(0))
            	equalRow = false;
        }
        
        //check equal col sum
        boolean equalCol = true;
        for (Integer n : colSums) {
            if (n != colSums.get(0))
            	equalCol = false;
        }
        
        //check consecutive numbers
        Collections.sort(numbersEntered);
        boolean isOrdered = true;
        if (numbersEntered.get(0) != 1) {
        	isOrdered = false;
        }
        for (int i = 0; i < numbersEntered.size() - 1; i++) {
            if (numbersEntered.get(i) != (numbersEntered.get(i + 1) - 1)) {
                isOrdered = false;
            } 
        }
        
        if (!isOrdered) {
        	return FAILED_NOT_ALL_VALUES;
        }
        if (!equalRow) {
        	return FAILED_ROW_SUM;
        }
        if (!equalCol) {
        	return FAILED_COL_SUM;
        }
        if (diaSum1 != diaSum2) {
            return FAILED_DIAG_SUM;		
        }          
		return MAGIC_SUCCESS;
	}
	
	@Override
	/**
	 * Formats and outputs the string to the console
	 * @return outputString the string being displayed in console
	 */
	public String toString()
	{
		String outputString = "";
		for (int row = 0; row < numberArray.length; row++) {
			for (int col = 0; col < numberArray[0].length; col++) {
				outputString += String.format("%7d", numberArray[row][col]);
			}
			outputString += "\n";
		}
		return outputString;
	}
	
	/**
	 * Creates a magic square of specified dimensions
	 * @param sideLength the dimensions of the magic square
	 * @return magicSquare a 2D array which is a magic square
	 */
	public static int[][] makeMagic(int sideLength)
	{
		int[][] magicSquare = new int[sideLength][sideLength];
		int row = sideLength-1, col = sideLength/2;
		magicSquare[row][col] = 1;
		
		for (int i = 2; i <= sideLength * sideLength; i++) {
			if (magicSquare[(row + 1) % sideLength][(col + 1) % sideLength] == 0) {
				row = (row + 1) % sideLength;
				col = (col + 1) % sideLength;
			}
			else {
				row = (row - 1 + sideLength) % sideLength;
			}
			magicSquare[row][col] = i;
		}
		
		return magicSquare;
	}
	
}
