/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ArrayList<Integer> a = new ArrayList<>();
        
        for (ListNode n: lists) {
            while (n != null) {
                a.add(n.val);
                n = n.next;
            }
        }

        if (a.isEmpty()) {
            return null;
        }
        
        Collections.sort(a);
        
        ListNode head = new ListNode(a.get(0));
        ListNode cur = head;
        
        for (int i = 1; i < a.size(); i++) {
            cur.next = new ListNode(a.get(i));
            cur = cur.next;
        }
        
        cur.next = null;
        return head;
        
        /*
        TIME LIMIT EXCEEDED
        
        ListNode preHead = new ListNode(0);
        ListNode cur = preHead;
        
        while (true) {
            int min = -1;
            
            for (int i  = 0; i < lists.length; i++) {
                if (lists[i] != null && (min == -1 || lists[i].val < lists[min].val)) {
                    min = i;
                }
            }
            
            if (min == -1) {
                break;
            }
            
            cur.next = lists[min];
            lists[min] = lists[min].next;
            cur = cur.next;
        }
        
        cur.next = null;
        return preHead.next;
        */
    }
}        
