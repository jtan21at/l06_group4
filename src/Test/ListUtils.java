package Test;

import Test.ListNode;

/**
 * Utilities for singly-linked non-circular lists.
 */
public class ListUtils {
    private ListUtils() {
    }

    public static ListNode copyList(ListNode list) {
        ListNode head = new ListNode(list.val);
        ListNode currentNew = head;
        ListNode currentOld = list.next;
        while (currentOld != null) {
            currentNew.next = new ListNode(currentOld.val);
            currentNew = currentNew.next;
            currentOld = currentOld.next;
        }
        return head;
    }

    public static void displayList(ListNode list) {
        if (list == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (list != null) {
            sb.append(list.val);
            sb.append(',');
            sb.append(' ');
            list = list.next;
        }
        sb.delete(sb.lastIndexOf(","), sb.length());
        sb.append(']');
        System.out.println(sb);
    }

    public static ListNode arrayToLinkedList(int[] arr) {
        if (arr.length == 0)
            return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }
}