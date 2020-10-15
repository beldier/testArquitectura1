
import java.util.Arrays;
import java.util.Random;

public class Game{
    private int [][] matrix;
    private static int RANDOM_NUMBER = 2;


    public Game(int[][] matrix){
        this.matrix=matrix;
    }

    public Game(){
        this.matrix = new int[4][4];
        setRandomNumber();
    }
    private void setRandomNumber(){
        int i = new Random().nextInt(4);
        int j = new Random().nextInt(4);
        this.matrix[i][j] = RANDOM_NUMBER;
    }
    public int[][] play(char order){
        switch (order){
            case 'W':playUp();break;
            case 'S':playDown();break;
            case 'A':playLeft();break;
            case 'D':playRight();break;
            default: throw new UnsupportedOperationException("Order not recognized");
        }
        return this.matrix;
    }
    private int[][] playLeft() {
        int rowsAmount = matrix.length;
        for (int rowNumber = 0; rowNumber < rowsAmount; rowNumber++) {
            int[] row = matrix[rowNumber];
            int[] compressed = compressArray(row);
            row = transformArray(compressed);
            replaceRow(row, rowNumber);
        }
        return this.matrix;
    }
    private int[][] playRight() {
        int rowsAmount = matrix.length;
        for (int rowNumber = 0; rowNumber < rowsAmount; rowNumber++) {
            int[] row = matrix[rowNumber];
            row = reverseArray(row);
            int[] compressed = compressArray(row);
            row = transformArray(compressed);
            row = reverseArray(row);
            replaceRow(row, rowNumber);
        }
        return this.matrix;
    }
    private int[][] playUp() {
        int columnAmount = matrix[0].length;
        for (int columnNumber = 0; columnNumber < columnAmount; columnNumber++) {
            int[] column = getColumn(columnNumber);
            int[] compressed = compressArray(column);
            column = transformArray(compressed);
            replaceColumn(column, columnNumber);
        }
        return this.matrix;
    }


    private int[][] playDown(){
        int columnAmount = matrix[0].length;
        for(int columnNumber=0; columnNumber<columnAmount;columnNumber++){
            int[] column = getColumn(columnNumber);
            int[] compressed = compressArray(column);
            column = transformArray(compressed);
            column = compressArrayRight(column);
            replaceColumn(column, columnNumber);
        }
        return this.matrix;
    }

    private void replaceRow(int[] row, int rowNumber) {
        this.matrix[rowNumber]=row;
    }

    public int[] compressArrayRight(int[] array){
        int[] result = new int[array.length];
        int indexResult = array.length-1;
        for(int i=array.length-1;i>=0 ;i--){
            int currentElement=array[i];
            if(currentElement !=0)
                result[indexResult--]=currentElement;
        }
        return result;
    }

    private int[][] replaceColumn(int [] column,int columnNumber){
        for(int i=0;i<matrix.length;i++){
            matrix[i][columnNumber]=column[i];
        }
        return matrix;
    }
    public  int[] getColumn(int columnNumber){
        int[] result = new int[matrix.length];
        for(int i=0;i<result.length;i++){
            result[i]=matrix[i][columnNumber];
        }
        return result;
    }
    public int[] transformArray(int[] compressedArray){
        int[] result = new int[compressedArray.length];
        compressedArray = Arrays.copyOf(compressedArray, compressedArray.length + 1);
        compressedArray[compressedArray.length-1] = -2;//
        int indexResult = 0;
        int lastElement = compressedArray[0];
        for(int i=1;i<compressedArray.length;i++) {
            int currentElement = compressedArray[i];
            if (currentElement == lastElement) {
                result[indexResult ++] = currentElement + lastElement;
                lastElement=-1;
            }
            else{
                if(lastElement!=-1)
                    result[indexResult++] = lastElement;
                lastElement = currentElement;
            }
        }
        if(lastElement == -1  )
            result[indexResult]=lastElement;
        return result;
    }
    //shift
    public int[] compressArray(int[] array){
        int[] result = new int[array.length];
        int indexResult = 0;
        for(int i=0;i<array.length ;i++){
            int currentElement=array[i];
            if(currentElement !=0)
                result[indexResult++]=currentElement;
        }
         return result;
    }
    public int[] reverseArray(int[] array){
        int[] result = new int [array.length];
        for(int i=0,j=array.length-1;i<result.length;i++,j--)
            result[i]=array[j];
        return result;
    }

    public boolean isLeft(){
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][0] == RANDOM_NUMBER)
                return true;
        }
        return false;
    }
    public boolean isRight(){
        int lastColumnIndex = matrix[0].length - 1;
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][lastColumnIndex] == RANDOM_NUMBER)
                return true;
        }
        return false;
    }
    public boolean isUp(){
        for(int j=0;j<matrix.length;j++){
            if(matrix[0][j] == RANDOM_NUMBER)
                return true;
        }
        return false;
    }
    public boolean isDown(){
        int lastRowIndex = matrix.length - 1;
        for(int j = 0; j < matrix.length; j++){
            if(matrix[lastRowIndex][j] == RANDOM_NUMBER)
                return true;
        }
        return false;
    }
    public boolean compareMatrix(int[][] mat){
        return Arrays.deepEquals(this.matrix,mat);
    }
}