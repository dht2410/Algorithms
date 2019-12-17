package Test;

import java.util.HashMap;

public class HashmapTest {
    public static void main(String[] args){
        HashMap<std,Integer> hash = new HashMap<>();
        std a = new std(3);
        std b = new std(3);
        hash.put(a,3);
        System.out.println(hash.get(b));
    }
}

class std{
    private int num;
    public std(int num){
        this.num=num;
    }
    public int getNum() {
        return num;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (obj instanceof std){
            std s = (std)obj;
            if (s.getNum()==this.num) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return num;
    }
}
