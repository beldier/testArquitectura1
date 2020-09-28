import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;

public class Game{
    private int [][] matrix;
    public Game(int[][] matrix){
        this.matrix=matrix;
    }
    public int[][] play(char order){
        switch (order){
            case 'W':playUp();break;
            case 'S':playDown();break;
            case 'A':playLeft();break;
            case 'D':playDown();break;
            default: throw new UnsupportedOperationException("Order not recogneised");
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
        compressedArray[compressedArray.length-1] = -2;
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
    public void printMatrix(){
        for (int i=0;i<matrix.length;i++){
            for(int j = 0;j<matrix[0].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public int[] reverseArray(int[] array){
        int[] result = new int [array.length];
        for(int i=0,j=array.length-1;i<result.length;i++,j--)
            result[i]=array[j];
        return result;
    }
}