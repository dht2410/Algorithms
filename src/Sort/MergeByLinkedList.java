package Sort;

//归并将链表排序
public class MergeByLinkedList<Item extends Comparable<Item>> {
    private Node first;
    private int N;
    private class Node{
        Item item;
        Node next;
        Node(Item item){
            this.item = item;
        }
    }
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    public void push(Item it){
        Node oldfirst = first;
        first = new Node(it);
        first.next = oldfirst;
        N++;
    }
    public Item pop(){
        Item i = first.item;
        first = first.next;
        N--;
        return i;
    }
    public Item peek(){
        return first.item;
    }

    private Node getMiddle(Node head){
        if (head==null)
            return null;
        Node fast=head;
        Node slow=head;
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
    private Node Merge(Node list1, Node list2){
        //构造一个头节点，最后返回它的后一个节点，可避免很多讨论
        Node dummy = new Node(null);
        Node curr = dummy;
        while(list1!=null && list2!=null){
            if (less(list1.item,list2.item)){
                curr.next = list1;
                list1 = list1.next;
            }
            else{
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next= (list1==null)?list2:list1;
        return dummy.next;
    }
    private Node MergeSort(Node head){
        //单个元素，则不排序
        if(head==null||head.next==null){
            return head;
        }
        Node middle = getMiddle(head);
        Node middlepast = middle.next;
        middle.next=null;
        return Merge(MergeSort(head),MergeSort(middlepast));
    }
    public void Sort(){
        first = MergeSort(first);
    }

    private static boolean less (Comparable v, Comparable w){
        return v.compareTo(w)<0;
    }
    public static void main(String[] args){
        MergeByLinkedList<Integer> list = new MergeByLinkedList<>();
        list.push(1);
        list.push(4);
        list.push(2);
        list.push(87);
        list.Sort();
        while (!list.isEmpty()){
            System.out.println(list.pop());
        }
    }
}
