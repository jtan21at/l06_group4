package L06;

import Test.ListUtils;
import Test.ListNode;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static Test.ListUtils.copyList;
import static Test.ListUtils.displayList;


public class LinkedListPractice {

    private LinkedListPractice() {
    }

    public static void main(String[] args) {

        ListNode list1 = new ListNode(0);
        list1.next = new ListNode(3);
        list1.next.next = new ListNode(5);
        list1.next.next.next = new ListNode(3);
        list1.next.next.next.next = new ListNode(6);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(2);
        list2.next.next = new ListNode(7);

        displayList(removeElements(list1, 3));
    }

    /**
     * Given the heads of two sorted linked lists, merges the two lists in a one sorted list.
     * The list is made by splicing together the nodes of the original two lists, without
     * creating any new nodes.
     * <p>
     * Returns the head of the merged linked list.
     */

    public static ListNode mergeLists(ListNode head1, ListNode head2) {
        ListNode previous1=null;
        ListNode previous2=null;
        ListNode current1,current2,current_m;
        ListNode next1=head1.next;
        ListNode next2=head2.next;
        current1=head1;
        current2=head2;
        ListNode head;
        if (head1.val<head2.val){
          head=head1;
          current1=head1.next;
        }else {
            head = head2;
            current2 = head2.next;
        }
        current_m=head;
        previous1=head1;
        previous2=head2;
        while (current1.next!=null && current2.next!=null){
            if (current1.val>current2.val ){
                current_m.next=current1;
                current_m=current_m.next;
                current1=current1.next;


            }else {
                current_m.next=current2;
                current_m=current_m.next;
                current2=current2.next;
        }}
        if (current1!=null)
        {current_m.next=current1;
        current_m=current_m.next;}
        if (current2!=null)
        {
          current_m.next=current2;
        current_m=current_m.next;

        }
        return head;
    }

    /**
     * Given the head of a sorted linked list, deletes all duplicates such that each element appears only once.
     * <p>
     * Returns the head of the resulting linked list, which is still sorted.
     */
    public static ListNode deleteDuplicates(ListNode head) {
        HashSet<Integer> pool = new HashSet<>();
 
        ListNode current = head;
        ListNode previous;
          while (current != null) 
        {
            int a = current.val; 
            pool.add(a);

            if (pool.contains(a)) 
            {
                previous = current.next;
                previous=current;
                current = current.next;

            }else{
                previous = current;
                current=current.next;
            }
            
        }   return head;
    }

    /**
     * Given the head of a linked list and an integer val, removes all the nodes of the linked list that has
     * Node.val == val.
     * <p>
     * Returns the head of the resulting list.
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode pointer;
        ListNode previous, next, current;
        current=head;
        next=head.next;
        previous=null;

        while (current != null) {
          if (current.val==val){
            previous.next=next;
          //  next = head.next;
            current = next;
            next = next.next;
            continue;

          }
            previous=current;
            current=next;

          }
          return head;
        }


        /**
         * Given the head of a zero-indexed linked list and two indices i and j, swaps the elements at these indices.
         *
         * Returns the head of the resulting list.
         */

        ArrayList<ListNode> list = new ArrayList<ListNode>();
        public static ListNode swapElements (ListNode head,int i, int j){
            ListNode var1,var2;
            var1=head;
            var2=head;
            for(int a=0;a<i;a++){
                var1=var1.next;
            }
            for(int a=0;a<j;a++){
                var2=var2.next;
            }
            int temp;
            temp=var2.val;
            var2.val=var1.val;
            var1.val=temp;

            return head;


            //return
        }


        /**
         * Given the head of a singly linked list, reverse the list, and return the reversed list.
         */
        public static ListNode reverseList (ListNode head){

            ListNode next = null;
            ListNode prev = null;

            while (head != null) {
                next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }

            return prev;
        }

        /**
         * Given the head of a singly linked list, returns the middle node of the linked list.
         *
         * If there are an even number of elements -- and thus two middle nodes -- returns the second middle node.
         */
        public static ListNode middleNode (ListNode head){
          int count = 0;
          ListNode current2 = head;
          ListNode current = head;


          //while loop to count how long the list is
          while(current != null){
              count += 1;
              current = current.next;
          }

          //if loop to see if the length is even or odd
          if(count % 2 == 1){
              for(int i = 0; i < count / 2; i++)
                  current2 = current2.next;
              return current2;
          }
          else
              for(int i = 0; i < count / 2; i++)
                  current2 = current2.next;
              return current2;
        }


    }

