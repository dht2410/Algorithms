package Search;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args){
        int[] array = getArray(10);
        Arrays.sort(array);
        for (int j=0;j<array.length;j++){
            System.out.print(array[j]+" ");
        }
        System.out.println();
        int key = (int) (Math.random()*30);
        System.out.println("key is "+key);
        int ret = binarySearch(key,array);
        if (ret == -1){
            System.out.println("Not Found!");
        }else{
            System.out.println("The key is "+ ret);
        }
    }

    public static int[] getArray(int n){
        int[] array = new int[n];
        for (int i = 0;i<n;i++){
            array[i]=(int) (Math.random()*30);
        }
        return array;
    }

    public static int binarySearch(int key, int[] array){
        int min=0;
        int max=array.length-1;
        int mid=(min+max)/2;
        if ((min==max && key!=array[mid])|(max==-1)){
            return -1;
        }
        else if (key==array[mid]){
            return key;
        } else if (key < array[mid]) {
            int[] array1 =Arrays.copyOfRange(array,min,mid);
            return binarySearch(key, array1);
        }else {
            int[] array1 =Arrays.copyOfRange(array,mid+1,max+1);
            return binarySearch(key, array1);
        }

    }
}
