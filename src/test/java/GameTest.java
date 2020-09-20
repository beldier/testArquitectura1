import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
    private Game game1;
    private Game game2;
    private int matrix1[][];
    private int matrix2[][];
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
        game1 = new Game(matrix1);
        game2 = new Game(matrix2);
    }
    @Test
    public void playUp_Matrix1_TransformedMatrix(){
        int[][] expectedResult= new int[][]{{1,8,4,2},
                                            {2,8,8,3},
                                            {6,0,0,2},
                                            {0,0,0,0}};
        int[][] transformedMatrix = game1.play('W');
        Assert.assertArrayEquals(expectedResult,transformedMatrix);
    }
    @Test
    public void playUp_Matrix2_TransformedMatrix(){
        int[][] expectedResult= new int[][]{{1,1,0,2},
                                            {2,4,0,3},
                                            {3,4,0,2},
                                            {4,0,0,0}};
        int[][] transformedMatrix = game2.play('W');
        Assert.assertArrayEquals(expectedResult,transformedMatrix);
    }
    @Test
    public void playDown_Matrix1_TransformedMatrix(){
        int[][] expectedResult= new int[][]{{0,0,0,0},
                                            {1,0,0,2},
                                            {2,8,4,3},
                                            {6,8,8,2}};
        int[][] transformedMatrix = game1.play('S');
        Assert.assertArrayEquals(expectedResult,transformedMatrix);
    }
    @Test
    public void playDown_Matrix2_TransformedMatrix(){
        int[][] expectedResult= new int[][]{{1,0,0,0},
                                            {2,1,0,2},
                                            {3,4,0,3},
                                            {4,4,0,2}};
        int[][] transformedMatrix = game2.play('S');
        Assert.assertArrayEquals(expectedResult,transformedMatrix);
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
