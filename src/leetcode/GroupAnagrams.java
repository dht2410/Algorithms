package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * LeetCode 49 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 解法：HashMap的Key用字符串构造，buildStr()方法完成
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,int[]> map = new HashMap<>();
        for (int i=0;i<strs.length;i++){
            int[] bitArray = build(strs[i]);
            map.put(strs[i],bitArray);
        }
        List<List<String>> ans = new ArrayList<>();
        for (int i=0;i<strs.length;i++){
            String str = strs[i];
            int[] strBit = map.get(str);
            for (int j=0;j<=ans.size();j++){
                if (j<ans.size()){
                    String cmpStr = ans.get(j).get(0);
                    if (compare(strBit,map.get(cmpStr))){
                        ans.get(j).add(str);
                        break;
                    }
                }
                else{
                    List<String> newList = new ArrayList<>();
                    newList.add(str);
                    ans.add(newList);
                    break;
                }
            }
        }
        return ans;
    }
    private int[] build(String str){
        int[] buildArray = new int[26];
        for (char c:str.toCharArray()){
            buildArray[c-'a']++;
        }
        return buildArray;
    }
    private boolean compare(int[] a, int[] b){
        for (int i=0;i<26;i++){
            if (a[i]!=b[i]) return false;
        }
        return true;
    }

    public List<List<String>> groupAnagrams2(String[] strs){
        HashMap<String,Integer> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for (String str : strs) {
            String newStr = buildStr(str);
            int index = map.getOrDefault(newStr,-1);
            if (index>=0){
                ans.get(index).add(str);
            }
            else{
                map.put(newStr,ans.size());
                List<String> list = new ArrayList<>();
                list.add(str);
                ans.add(list);
            }
        }
        return ans;
    }
    private String buildStr(String str) {
        int[] buildArray = build(str);
        StringBuilder sb = new StringBuilder();
        for (int num:buildArray) {
            sb.append('*');
            sb.append(num);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new GroupAnagrams().groupAnagrams2(strs));
    }
}
