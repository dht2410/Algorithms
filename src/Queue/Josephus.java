package Queue;


public class Josephus {
    public static void main(String[] args){
        QueuebyLinkedList<Integer> Q = new QueuebyLinkedList<>();
        int N = 7;
        int M = 3;
        for (int i=0;i<N;i++){
            Q.enqueue(i);
        }
        //好牛逼！！
        while (!Q.isEmpty()){
            for (int i=0;i<M-1;i++)
                Q.enqueue(Q.dequeue());
            System.out.println(Q.dequeue());
        }
    }
}
