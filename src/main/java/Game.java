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
                default: throw new UnsupportedOperationException("Order not recognised");
        }
        return this.matrix;
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
        int indexResult = 0;
        System.arraycopy(compressedArray,0,result,0,compressedArray.length);
        for(int i=0;i<compressedArray.length-1 ;i++) {
            int currentElement = compressedArray[i];
            int nextElement = compressedArray[i + 1];
            if (currentElement == nextElement) {
                result[i] = 0;
                result[i + 1] = 0;
                result[indexResult] = currentElement + nextElement;
                i++;
            }
            indexResult++;
        }
        int lastElement=result[result.length-1];
        if(lastElement!=0 && indexResult < compressedArray.length-1 ){
            result[indexResult]=lastElement;
            result[result.length-1]=0;
        }
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