import java.util.Arrays;
import java.util.Random;

class Game2048 implements G2048 {


    private int [][] matrix;
    private static int NUMBER = 2;
    private static int GOAL= 32;
    private static int ROW_SIZE=4;
    private static int COLUMN_SIZE=4;

    public Game2048(){
        this.matrix = new int[4][4];
        setNumberTwoInRandomPosition();
    }
    public Game2048(int[][] matrix){
        this.matrix = matrix;
    }

    private void setNumberTwoInRandomPosition(){
        int i = new Random().nextInt(4);
        int j = new Random().nextInt(4);
        this.matrix[i][j] = NUMBER;
    }

    @Override
    public void moveUp() {
        int columnAmount = matrix[0].length;
        for (int columnNumber = 0; columnNumber < columnAmount; columnNumber++) {
            int[] column = getColumn(columnNumber);
            int[] compressed = compressArray(column);
            column = transformArray(compressed);
            replaceColumn(column, columnNumber);
        }
        setNumberTwoInRandomPosition();

    }

    @Override
    public void moveDown() {
        int columnAmount = matrix[0].length;
        for(int columnNumber=0; columnNumber<columnAmount;columnNumber++){
            int[] column = getColumn(columnNumber);
            int[] compressed = compressArray(column);
            column = transformArray(compressed);
            column = compressArrayRight(column);
            replaceColumn(column, columnNumber);
        }
        setNumberTwoInRandomPosition();

    }

    @Override
    public void moveLeft() {
        int rowsAmount = matrix.length;
        for (int rowNumber = 0; rowNumber < rowsAmount; rowNumber++) {
            int[] row = matrix[rowNumber];
            int[] compressed = compressArray(row);
            row = transformArray(compressed);
            replaceRow(row, rowNumber);
        }
        setNumberTwoInRandomPosition();

    }

    @Override
    public void moveRight() {
        int rowsAmount = matrix.length;
        for (int rowNumber = 0; rowNumber < rowsAmount; rowNumber++) {
            int[] row = matrix[rowNumber];
            row = reverseArray(row);
            int[] compressed = compressArray(row);
            row = transformArray(compressed);
            row = reverseArray(row);
            replaceRow(row, rowNumber);
        }
        setNumberTwoInRandomPosition();

    }

    @Override
    public boolean winGame() {
        for(int i = 0;i<ROW_SIZE;i++){
            for(int j=0;j< COLUMN_SIZE;j++)
                if(this.matrix[i][j]==GOAL)
                    return true;
        }
        return false;

    }

    @Override
    public boolean lostGame() {
        for(int i = 0;i<ROW_SIZE;i++){
            for(int j=0;j< COLUMN_SIZE;j++)
                if(this.matrix[i][j]==0)
                    return false;
        }
        return true;
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
        for(int i=0, j=array.length-1 ; i < result.length ; i++, j--){
            result[i]=array[j];
        }
        return result;
    }

    @Override
    public String toString() {
        String ans = "";
        for(int i = 0;i<ROW_SIZE;i++){
            for(int j=0;j< COLUMN_SIZE;j++) {
                ans+=this.matrix[i][j]+" ";
            }
            ans+="\n";
        }
        return ans;
    }

}
