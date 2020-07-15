package Test;


public class FanxingTest<T> {
    private Object[] t;
    public FanxingTest(){
        t = (T[]) new Object[5];
    }
    public T[] getT(){
        return (T[])t;
    }
    public void add(T t1){
        t[0] = t1;
    }
    public T gett1(){
        return (T)t[0];
    }

    public static void main(String[] args){
        FanxingTest<String> fxt = new FanxingTest<String>();
        fxt.add("");
        Object[] obj = new Object[10];
    }
}
