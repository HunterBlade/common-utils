package com.comn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据校验工具类
 */
public class DataValidateUtil {

    // ==================== Pattern start ===========================
    public static final Pattern CODE_PATTERN = Pattern.compile("^0\\d{2,4}$");

    /**
     * 邮编校验规则
     */
    public static final Pattern POSTCODE_PATTERN = Pattern.compile("^\\d{6}$");

    /**
     * 银行卡号校验股则
     */
    public static final Pattern BANK_CARD_PATTERN = Pattern.compile("^\\d{16,30}$");

    /**
     * 匹配电话
     * <p>
     * 格式为: 0XXX-XXXXXX(10-13位首位必须为0) 或0XXX XXXXXXX(10-13位首位必须为0) 或
     * <p>
     * (0XXX)XXXXXXXX(11-14位首位必须为0) 或 XXXXXXXX(6-8位首位不为0) 或
     * XXXXXXXXXXX(11位首位不为0)
     * <p>
     * 匹配 : 0371-123456 或 (0371)1234567 或 (0371)12345678 或 010-123456 或
     * 010-12345678 或 12345678912
     * <p>
     * 不匹配: 1111-134355 或 0123456789
     */
    public static final String PHONE_REGEXP = "^(?:0[0-9]{2,3}[-//s]{1}|//(0[0-9]{2,4}//))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";

    /**
     * 不包括特殊字符的匹配 (字符串中不包括符号 数学次方号^ 单引号' 双引号" 分号; 逗号, 帽号: 数学减号- 右尖括号> 左尖括号< 反斜杠/
     * 即空格,制表符,回车符等 )
     * <p>
     * 格式为: x 或 一个一上的字符
     * <p>
     * 匹配 : 012345
     * <p>
     * 不匹配: 0123456 // ;,:-<>//s].+$";//
     */
    public static final String NON_SPECIAL_CHAR_REGEXP = "^[^'/";

    /**
     * 匹配邮编代码
     * <p>
     * 格式为: XXXXXX(6位)
     * <p>
     * 匹配 : 012345
     * <p>
     * 不匹配: 0123456
     */
    public static final String ZIP_REGEXP = "^[0-9]{6}$";// 匹配邮编代码

    /**
     * 匹配身份证
     * <p>
     * 格式为: XXXXXXXXXX(10位) 或 XXXXXXXXXXXXX(13位) 或 XXXXXXXXXXXXXXX(15位) 或
     * XXXXXXXXXXXXXXXXXX(18位)
     * <p>
     * 匹配 : 0123456789123
     * <p>
     * 不匹配: 0123456
     */
    public static final String ID_CARD_REGEXP = "^//d{10}|//d{13}|//d{15}|//d{18}$";
    // ==================== Pattern end   ===========================

    // ==================== Method start ============================

    /**
     * 验证邮政编码是否有效.
     *
     * @param postcode 要验证的邮政编码
     * @return true/false
     */
    public static boolean validatePostcode(String postcode) {
        if (StringHelper.isEmpty(postcode)) {
            return false;
        }
        Matcher m = POSTCODE_PATTERN.matcher(postcode);
        return m.matches();
    }

    /**
     * 验证银行卡是否有效.
     *
     * @param bankCardNumber 要验证的银行卡号
     * @return true/false
     */
    public static boolean validateBankCardNumber(String bankCardNumber) {
        if (StringHelper.isEmpty(bankCardNumber)) {
            return false;
        }
        Matcher m = BANK_CARD_PATTERN.matcher(bankCardNumber);
        return m.matches();
    }

    /**
     * 通过身份证获取生日
     *
     * @param idNumber 身份证号
     * @return 返回生日, 格式为 yyyy-MM-dd 的字符串
     */
    public static String getBirthdayByIdNumber(String idNumber) {
        String birthday = "";
        if (idNumber.length() == 15) {
            birthday = "19" + idNumber.substring(6, 8) + "-" + idNumber.substring(8, 10) + "-" + idNumber.substring(10, 12);
        } else if (idNumber.length() == 18) {
            birthday = idNumber.substring(6, 10) + "-" + idNumber.substring(10, 12) + "-" + idNumber.substring(12, 14);
        }
        return birthday;
    }

    /**
     * 通过身份证获取性别
     *
     * @param idNumber 身份证号
     * @return 返回性别, 0 保密 , 1 男 2 女
     */
    public static Integer getGenderByIdNumber(String idNumber) {
        int gender = 0;
        if (idNumber.length() == 15) {
            gender = Integer.parseInt(String.valueOf(idNumber.charAt(14))) % 2 == 0 ? 2 : 1;
        } else if (idNumber.length() == 18) {
            gender = Integer.parseInt(String.valueOf(idNumber.charAt(16))) % 2 == 0 ? 2 : 1;
        }
        return gender;
    }

    /**
     * 通过身份证获取年龄
     *
     * @param idNumber     身份证号
     * @param isNominalAge 是否按元旦算年龄，过了1月1日加一岁 true : 是 false : 否
     * @return 返回年龄
     */
    public static Integer getAgeByIdNumber(String idNumber, boolean isNominalAge) {
        String birthString = getBirthdayByIdNumber(idNumber);
        if (StringHelper.isEmpty(birthString)) {
            return 0;
        }
        return getAgeByBirthString(birthString, isNominalAge);
    }

    /**
     * 通过生日日期获取年龄
     *
     * @param birthDate 生日日期
     * @return 返回年龄
     */
    public static Integer getAgeByBirthDate(Date birthDate) {
        return getAgeByBirthString(new SimpleDateFormat("yyyy-MM-dd").format(birthDate));
    }

    /**
     * 通过生日字符串获取年龄
     *
     * @param birthString 生日字符串
     * @return 返回年龄
     */
    public static Integer getAgeByBirthString(String birthString) {
        return getAgeByBirthString(birthString, "yyyy-MM-dd");
    }

    /**
     * 通过生日字符串获取年龄
     *
     * @param birthString  生日字符串
     * @param isNominalAge 是否按元旦算年龄，过了1月1日加一岁 true : 是 false : 否
     * @return 返回年龄
     */
    public static Integer getAgeByBirthString(String birthString, boolean isNominalAge) {
        return getAgeByBirthString(birthString, "yyyy-MM-dd", isNominalAge);
    }

    /**
     * 通过生日字符串获取年龄
     *
     * @param birthString 生日字符串
     * @param format      日期字符串格式,为空则默认"yyyy-MM-dd"
     * @return 返回年龄
     */
    public static Integer getAgeByBirthString(String birthString, String format) {
        return getAgeByBirthString(birthString, "yyyy-MM-dd", false);
    }

    /**
     * 通过生日字符串获取年龄
     *
     * @param birthString  生日字符串
     * @param format       日期字符串格式,为空则默认"yyyy-MM-dd"
     * @param isNominalAge 是否按元旦算年龄，过了1月1日加一岁 true : 是 false : 否
     * @return 返回年龄
     */
    public static Integer getAgeByBirthString(String birthString, String format, boolean isNominalAge) {
        int age = 0;
        if (StringHelper.isEmpty(birthString)) {
            return age;
        }
        if (StringHelper.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }
        try {
            Calendar birthday = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            birthday.setTime(sdf.parse(birthString));
            age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
            if (!isNominalAge) {
                if (today.get(Calendar.MONTH) < birthday.get(Calendar.MONTH) ||
                        (today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH) &&
                                today.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH))) {
                    age = age - 1;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return age;
    }

    // ==================== Method end   ============================

}
