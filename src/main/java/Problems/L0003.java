/*
    LeetCode 0003 无重复字符的最长子串
    题目：
        给定一个字符串s，请你找出其中不含有重复字符的最长子串的长度。

    示例 1：
        输入: s = "abcabcbb"
        输出: 3
        解释: 因为无重复字符的最长子串是"abc"，所以其长度为3。
    示例 2：
        输入: s = "bbbbb"
        输出: 1
        解释: 因为无重复字符的最长子串是"b"，所以其长度为1。
    示例 3：
        输入: s = "pwwkew"
        输出: 3
        解释: 因为无重复字符的最长子串是"wke"，所以其长度为3。请注意，你的答案必须是子串的长度，"pwke"是一个子序列，不是子串。

    提示：
        0 <= s.length <= 5 * 104
        s 由英文字母、数字、符号和空格组成
 */

/*方法一：滑动窗口；时间复杂度：O(N); 空间复杂度：O(∣Σ∣)
  其中 Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。
  在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在 [0,128) 内的字符，
  即 ∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，而字符最多有 ∣Σ∣ 个，
  因此空间复杂度为 O(∣Σ∣)
*/

package Problems;

import java.util.HashSet;
import java.util.Set;

public class L0003 {
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
