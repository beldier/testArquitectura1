import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Game2048Test {
    private Game2048 game1;
    private Game2048 game2;
    private Game2048 game3;
    private Game2048 game4;
    private Game2048 game5;
    private Game2048 game6;
    private int matrix1[][];
    private int matrix2[][];
    private int matrix3[][];
    private int matrix4[][];
    @Before
    public void setUp(){
        matrix1 = new int[][]{{1,4,0,2},
                            {2,4,4,3},
                            {3,4,8,2},
                            {3,4,0,0}};
        matrix2 = new int[][]{{1,1,0,2},
                             {2,2,0,3},
                             {3,2,0,2},
                             {4,4,0,0}};
        matrix3 = new int[][]{{1,1,0,2},
                             {4,0,0,4},
                             {4,4,0,8},
                             {2,2,2,2}};
        matrix4 = new int[][]{{1,1,0,1},
                             {4,4,2,4},
                             {4,3,2,1},
                             {2,2,2,2}};
        game1 = new Game2048(matrix1);
        game2 = new Game2048(matrix2);
        game3 = new Game2048(matrix3);
        game4 = new Game2048(matrix4);

        game5 = new Game2048();
    }




    @Test
    public void GetColumn_ColumnNumber_MatrixColumn(){
        int columnNumber =0;
        int[] column = game1.getColumn(columnNumber);
        int [] expectedResult = {1,2,3,3};
        Assert.assertArrayEquals(expectedResult,column);
    }

    @Test
    public void GetColumn_ColumnNumber_MatrixZeroColumn(){
        int columnNumber = 2;
        int[] column = game2.getColumn(columnNumber);
        int [] expectedResult = {0,0,0,0};
        Assert.assertArrayEquals(expectedResult,column);
    }

    @Test
    public void TransformArray_Column_TransformedColumn_1(){
        int columnNumber =0;
        int[] column = game1.getColumn(columnNumber);
        int[] compressedArray=game1.compressArray(column);
        int[] transformedColumn = game1.transformArray(compressedArray);
        int [] expectedResult = {1,2,6,0};
        Assert.assertArrayEquals(expectedResult,transformedColumn);
    }
    @Test
    public void TransformArray_Column_TransformedColumn_2(){
        int columnNumber =1;
        int[] column = game1.getColumn(columnNumber);
        int[] compressedArray=game1.compressArray(column);
        int[] transformedColumn = game1.transformArray(compressedArray);
        int [] expectedResult = {8,8,0,0};
        Assert.assertArrayEquals(expectedResult,transformedColumn);
    }
    @Test
    public void TransformArray_Column_TransformedColumn_3(){
        int columnNumber =2;
        int[] column = game1.getColumn(columnNumber);
        int[] compressedArray=game1.compressArray(column);
        int[] transformedColumn = game1.transformArray(compressedArray);
        int [] expectedResult = {4,8,0,0};
        Assert.assertArrayEquals(expectedResult,transformedColumn);
    }
    @Test
    public void TransformArray_Column_TransformedColumn_4(){
        int columnNumber =3;
        int[] column = game1.getColumn(columnNumber);
        int[] compressedArray=game1.compressArray(column);
        int[] transformedColumn = game1.transformArray(compressedArray);
        int [] expectedResult = {2,3,2,0};
        Assert.assertArrayEquals(expectedResult,transformedColumn);
    }
    @Test
    public void TransformArray_Column_TransformedColumn_5(){
        int columnNumber =0;
        int[] column = game2.getColumn(columnNumber);
        int[] compressedArray=game1.compressArray(column);
        int[] transformedColumn =game2.transformArray(compressedArray);
        int [] expectedResult = {1,2,3,4};
        Assert.assertArrayEquals(expectedResult,transformedColumn);
    }
    @Test
    public void TransformArray_Column_TransformedColumn_6(){
        int columnNumber =1;
        int[] column = game2.getColumn(columnNumber);
        int[] compressedArray=game1.compressArray(column);
        int[] transformedColumn =game2.transformArray(compressedArray);
        int [] expectedResult = {1,4,4,0};
        Assert.assertArrayEquals(expectedResult,transformedColumn);
    }
    @Test
    public void ReverseArray_Array_ReversedArray(){
        int [] array = {1,2,3,4,5};
        int [] expectedResult = {5,4,3,2,1};
        int [] reversedArray = game1.reverseArray(array);
        Assert.assertArrayEquals(expectedResult,reversedArray);

    }
    @Test
    public void CompressArrayRight_Array_CompressedArray(){
        int [] array = {2,1,0,0};
        int [] expectedResult = {0,0,2,1};
        int [] compressedArray = game1.compressArrayRight(array);
        Assert.assertArrayEquals(expectedResult,compressedArray);
    }
}
