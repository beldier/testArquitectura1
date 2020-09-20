import java.util.Arrays;

public class Practice {
    public static void main(String args[]){
        int[] array =  {1,2,3,4};
         int[] shiftedArray = Arrays.stream(array)
                .skip(4)
                .toArray();
        System.out.println(Arrays.toString(shiftedArray));
    }
    public static int[] transformArray(int[] array){
        int[] result = new int[array.length+1];
        int indexResult = 0;
        int[] compressedArray = compressArray(array,0,1);
        System.arraycopy(compressedArray,0,result,0,compressedArray.length);
        for(int i=0;i<compressedArray.length-1 ;i++){
            int currentElement = compressedArray[i];
            int nextElement = compressedArray[i+1];
            System.out.println(currentElement+" "+nextElement);
            if(currentElement == nextElement){
                System.out.println(currentElement+" iguales "+nextElement);
                result[indexResult++]=currentElement+nextElement;
                i++;
            }
        }

        return result;
    }
    public static int[] compressArray(int[] array,int start,int aditive){
        int[] result = new int[array.length];
        int indexResult = start;
        for(int i=0;i<array.length ;i++){
            int currentElement=array[i];
            if(currentElement !=0) {
                result[indexResult] = currentElement;
                indexResult+=aditive;
            }
        }
        return result;
    }
}
