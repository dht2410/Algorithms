package Stack;

public class InfixToPostfix {
    public static void main(String[] Args){
        String s = "(2+(2+3)*(1*3))";
        StackbyLinkedList<String> stk = new StackbyLinkedList<>();
        for (int i = 0; i<s.length() ;i++){
            String c = String.valueOf(s.charAt(i));
            if (c.equals("+")){
                stk.push(c);
            }
            else if (c.equals("*")){
                stk.push(c);
            }
            else if (c.equals("(")){}
            else if (c.equals(")")){
                System.out.print(stk.pop() + " ");
            }
            else {
                System.out.print(c + " ");
            }
        }
    }
}
