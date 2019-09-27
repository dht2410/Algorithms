package Stack;

public class StackCopy {
    public static void main(String[] args){
        StackbyLinkedList<String> s = new StackbyLinkedList<>();

        for (int i = 0; i<10;i++){
            s.push("a");
        }

        StackbyLinkedList<String> s1 = StackbyLinkedList.copy(s);
        s1.pop();
        System.out.println(s1.size());
        System.out.println(s.size());
    }
}
