package com.comn;

/**
 * 补充字符串工具类，常用字符串操作使用：commons-lang3包中的StringUtils类
 */
public class StringHelper {

    /**
     * 替换为指定的字符串,用于敏感信息脱敏，如：用户姓名、银行卡号 <br>
     * 例：markString("aaabbbcccdddeeefff", "*", 3) <br>
     * "aaabbbcccdddeeefff" --> "aaa************fff"
     *
     * @param val     源字符串
     * @param markStr mark符号
     * @param len     mark符号外左右显示的长度
     * @return
     */
    public static String markString(String val, String markStr, int len) {
        return markString(val, markStr, len, len);
    }


    /**
     * 替换中间字符串，首尾保留指定长度字符 <br>
     * 例：markString("aaabbbcccdddeeefff", "*", 3, 2) <br>
     * "aaabbbcccdddeeefff" --> "aaa************ff"
     *
     * @param val      源字符串
     * @param markStr  替换符号
     * @param leftLen  左侧保留位数
     * @param rightLen 右侧保留位数
     * @return
     */
    public static String markString(String val, String markStr, int leftLen, int rightLen) {
        if (null == val || val.length() < 0) {
            return "";
        }

        int valLen = val.length();
        if (leftLen + rightLen >= valLen) {
            return val;
        }

        String start = val.substring(0, leftLen);
        String end = val.substring(valLen - rightLen, valLen);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < valLen - rightLen - rightLen; i++) {
            sb.append(markStr);
        }
        return start + sb.toString() + end;
    }


    public static void main(String[] args) {
        String str = "aaabbbcccdddeeefff";
        String s = markString(str, "*", 1,4);
        System.out.println("s = " + s);
    }

}
