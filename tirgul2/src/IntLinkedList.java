class Node{
    protected int data;
    protected Node next;

    Node(int data){this.data=data;
        next=null;//default value of new node
    }
}
public class IntLinkedList {
    Node head;//starting point
    public void add(int data){
        //adds the given data to the end of the linked list
        Node newNode=new Node(data);
        if(head==null){
            head=newNode;
            return;
        }
        Node last=head;
        while(last.next!=null){
            last=last.next;
        }
        last.next=newNode;
    }
    public Node find(int data){
        //returns the first occurrence of the given data in the linked list
        Node current=head;
        while(current!=null){
            if(current.data==data){
                return current;
            }
            current=current.next;
        }
        return null;
    }
    public void remove(int data){
        //removes the first occurrence of the given data from the linked list
        Node current=head;
        Node prev=null;
        while(current!=null){
            if(current.data==data){
                if(prev==null){
                    head=current.next;
                }else{
                    prev.next=current.next;
                }
            }
        }
    }
}
