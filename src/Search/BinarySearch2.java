package Search;

import java.util.Arrays;

public class BinarySearch2 {
    public static void main(String[] args){
        int[] array = getArray(10);
        Arrays.sort(array);
        for (int j=0;j<array.length;j++){
            System.out.print(array[j]+" ");
        }
        System.out.println();
        int key = (int) (Math.random()*30);
        System.out.println("The key is "+key);
        int ret = binarySearch(key,array);
        if (ret == -1){
            System.out.println("Not Found!");
        }else{
            System.out.println("Found! The rank is "+ ret);
        }
    }

    public static int[] getArray(int n){
        int[] array = new int[n];
        for (int i = 0;i<n;i++){
            array[i]=(int) (Math.random()*30);
        }
        return array;
    }
    //非迭代实现
    public static int binarySearch(int key, int[] array){
        int min = 0;
        int max = array.length-1;
        int mid;
        while (min<=max){
            mid = (min+max)/2;
            if (key == array[mid])
                return mid;
            else if (key < array[mid])
                max = mid - 1;
            else
                min = mid + 1;
        }
        return -1;
    }
}
