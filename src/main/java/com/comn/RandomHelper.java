package com.comn;

/**
 * 生成随机数，用于生成验证码等
 */
public class RandomHelper {

    /**
     * 生成纯数字验证码
     *
     * @param len 验证码长度
     * @return
     */
    public static String getIdentifyCodeWithoutChar(int len) {
        return getIdentifyCode(len, true, false);
    }

    /**
     * 生成纯字符验证码
     *
     * @param len 验证码长度
     * @return
     */
    public static String getIdentifyCodeWithoutNumber(int len) {
        return getIdentifyCode(len, false, true);
    }

    /**
     * 生成验证码，包含数字、字符
     *
     * @param len 验证码长度
     * @return
     */
    public static String getIdentifyCode(int len) {
        return getIdentifyCode(len, true, true);
    }

    /**
     * 生成验证码
     *
     * @param len           生成验证码的长度
     * @param containNumber 是否包含数字
     * @param containChar   是否字符
     * @return
     */
    public static String getIdentifyCode(int len, boolean containNumber, boolean containChar) {
        char[] identifyStr = null;
        if (containNumber && containChar) {
            identifyStr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                    'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        } else {
            if (containNumber) {
                identifyStr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            } else {
                identifyStr = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            }
        }

        int min = 0;
        int maxnum = identifyStr.length;
        String codeStr = "";
        for (int i = 0; i < len; i++) {
            int num = (int) ((maxnum - min) * Math.random() + min);
            codeStr += identifyStr[num];
        }
        return codeStr;
    }


    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            //String code = getIdentifyCode(4, true);
            System.out.printf("code is %s \n", getIdentifyCodeWithoutNumber(6));
        }
        long time2 = System.currentTimeMillis();

        System.out.printf("Total cost time : " + (time2 - time1) + " ms");


    }

}
