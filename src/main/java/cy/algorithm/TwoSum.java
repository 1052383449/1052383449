package cy.algorithm;

import com.cy.algorithm.entity.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoSum {

    public static void main(String[] args) {
        //1. 两数之和 :nums = [2,7,11,15], target = 9
       /* int[] nums = {2,7,11,15};
        int[] res = twoSum(nums,9);
        System.out.println(Arrays.toString(res));*/

        //2. 两数相加 l1 = [2,4,3], l2 = [5,6,4]
        /*ListNode listNode1 = new ListNode(2);
        listNode1.next = new ListNode(4);
        listNode1.next.next =new ListNode(3);
        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(6);
        listNode2.next.next =new ListNode(4);
        ListNode res = addTwoNumbers(listNode1,listNode2);
        while (res!=null){
            System.out.println(res.val);
            res = res.next;
        }*/

        //3. 无重复字符的最长子串 :s = "abcabcbb"
        lengthOfLongestSubstring("abcabcbb");
    }

    /**
     * 1. 两数之和
     * @param nums
     * @param target
     */
    public static int[] twoSum(int[] nums,int target){
        //给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
        //你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
        //你可以按任意顺序返回答案。
        //示例 1：
        //输入：nums = [2,7,11,15], target = 9
        //输出：[0,1]
        //解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if(map.containsKey(num)){
                return new int[]{map.get(num), i};
            }
            map.put(nums[i],i);
        }
        return null;
    }


    /**
     * 两数相加
     * @param listNode1
     * @param listNode2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode listNode1,ListNode listNode2){
        //给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
        //请你将两个数相加，并以相同形式返回一个表示和的链表。
        //你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
        //示例一
        //输入：l1 = [2,4,3], l2 = [5,6,4]
        //输出：[7,0,8]
        //解释：342 + 465 = 807.
        ListNode dummy = new ListNode(0);
        int carry = 0;
        ListNode cur = dummy;
        while (listNode1!=null || listNode2 !=null || carry != 0){
            int sum = listNode1.val+listNode2.val+carry;
            carry = sum / 10;
            cur.next = new ListNode(sum%10);
            cur = cur.next;
            listNode1 = listNode1.next!=null?listNode1.next:null;
            listNode2 = listNode2.next!=null?listNode2.next:null;
        }

        return dummy.next;
    }


    /**
     * 无重复字符的最长子串
     * @param str
     */
    public static void lengthOfLongestSubstring(String str){
        //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
        //输入: s = "abcabcbb"
        //输出: 3
        //解释: 因为无重复字符的最长子串是 "abc"，所以其 长度为 3。

        //i、j 分别表示不重复子串的开始位置和结束位置，ans 表示无重复字符子串的最大长度
        int i = 0,j = 0, ans = 0;
        Set<Character> chars =new HashSet<>();
        for (char c : str.toCharArray()) {
            while(chars.contains(c)){
                chars.remove(str.charAt(i++));
            }
            chars.add(c);
            ans = Math.max(ans,j-i+1);
            ++j;
        }
        System.out.println(ans);
    }

    
}
