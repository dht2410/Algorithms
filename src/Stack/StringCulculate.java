package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class StringCulculate {
    public static void main(String[] args){
        String s = "(3+(4*(5+1)))";
        int len = s.length();
        Deque<String> Stack1 = new ArrayDeque<>();
        Deque<Integer> Stack2 = new ArrayDeque<>();
        for (int i =0;i<len;i++){
            String t = String.valueOf(s.charAt(i));
            if (t.equals("(")){}
            else if (t.equals("+")){Stack1.push(t);}
            else if (t.equals("-")){Stack1.push(t);}
            else if (t.equals("*")){Stack1.push(t);}
            else if (t.equals("/")){Stack1.push(t);}
            else if (t.equals(")")){
                int in = Stack2.pop();
                String st = Stack1.pop();
                if (st.equals("+")){ in = Stack2.pop()+in;}
                else if (st.equals("-")){ in = Stack2.pop()-in;}
                else if (st.equals("*")){ in = Stack2.pop()*in;}
                else if (st.equals("/")){ in = Stack2.pop()/in;}
                Stack2.push(in);
            }
            else{
                Stack2.push(Integer.parseInt(t));
            }
        }

        System.out.println(Stack2.pop());
    }
}
