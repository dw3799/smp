package com.alipapa.smp.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author liuwei
 */
public class StringUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log logger = LogFactory.getLog(StringUtil.class);
    private static final String MOBILE_REG = "^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|17[0-9])\\d{8}$";
    private static final String EMAIL_REG = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final String PASS_REG = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

    /**
     * 生成随机字符串
     *
     * @param random
     * @param len
     * @return
     */
    public static String getRandomString(int random, int len) {
        java.util.Random rd = new java.util.Random();
        StringBuffer sb = new StringBuffer();
        int rdGet; // 取得随机数
        char ch;

        for (int i = 0; i < len; i++) {
            rdGet = Math.abs(rd.nextInt()) % 10 + 48; // 产生48到57的随机数(0-9的键位值)
            //rdGet=Math.abs(rd.nextInt())%26+97; // 产生97到122的随机数(a-z的键位值)
            ch = (char) rdGet;
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String subStringStr(String str, int size) {
        if (StringUtils.isEmpty(str))
            return "";
        if (str.length() < size)
            return str;

        String lastString = str.substring(0, size) + "...";
        return lastString;
    }

    public static String subStringBr(String str) {
        String regex = "\\u002B";
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        String strBr = str.replaceAll(regex, "<br/>");
        return strBr;
    }

    public static String subStringStrNoSuffix(String str, int size) {
        if (StringUtils.isEmpty(str))
            return "";
        if (str.length() < size)
            return str;

        String lastString = str.substring(0, size);
        return lastString;
    }

    /**
     * 判断传入的字符串是否为空串
     *
     * @return
     */
    public static boolean isEmptyString(String str) {
        return str == null ? true : str.trim().equals("") ? true : false;
    }

    /**
     * 判断传入的字符串是否为空串
     *
     * @return
     */
    public static boolean isNotEmptyString(String str) {
        return !StringUtil.isEmptyString(str);
    }


    /**
     * 替换模板中的变量。变量的标识符为${}。
     * 例如，模板中${name}变量将会被Map列表中键名为name的键值替换，如果Map列表中不存在所需要
     * 的键名，则会被替换成空。
     *
     * @param template 模板
     * @param data     参数列表
     * @return
     * @throws Exception
     */
    public static String composeMessage(String template, Map<String, Object> data) {
        String regex = "\\$\\{(.+?)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);

        /*
         * sb用来存储替换过的内容，它会把多次处理过的字符串按源字符串序
         * 存储起来。
         */
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String name = matcher.group(1);//键名
            String value = null == data.get(name) ? "" : data.get(name).toString();//键值
            if (value == null) {
                value = "";
            } else {
                value = value.replaceAll("\\$", "\\\\\\$");
                //value = value.replaceAll("\\", "\\\\");
            }

            matcher.appendReplacement(sb, value);
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String getPercent(int n, Float f) {
        java.text.NumberFormat nf = java.text.NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(n);// 小数点后保留几位
        String str = nf.format(f);// 要转化的数
        return str;
    }

    /**
     * 判断传入的字符串是否为空串,如果是null的就替换为""空字符串.
     *
     * @return
     */
    public static String replaceNullStr(String str) {
        if (str == null) {
            return "";
        }
        if (!isEmptyString(str)) {
            if ("null".equals(str)) {
                str = str.replace("null", "");
                return str;
            } else {
                return str;
            }
        } else {
            return str;
        }
    }

    /**
     * 转移SQL的保留字符，比如讲单引号转义为''
     *
     * @param str 需要转义的字符
     * @return 已经转义的字符
     */
    public static String escapeSQLChar(final String str) {
        if (!StringUtils.isEmpty(str)) {
            return str.replaceAll("'", "''");
        }
        return null;
    }

    /**
     * 验证是否是有效的手机号
     *
     * @param mobile 手机号
     * @return
     */
    public final static boolean validMobileNumber(final String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }

        Pattern pattern = Pattern.compile(MOBILE_REG);

        if (!pattern.matcher(mobile).matches()) {
            return false;
        }
        return true;
    }

    /**
     * 校验密码格式是否正确
     */
    public final static boolean validPassword(final String password) {
        if (StringUtil.isEmptyString(password)) {
            return false;
        }
        Pattern pattern = Pattern.compile(PASS_REG);
        if (!pattern.matcher(password).matches()) {
            return false;
        }
        return true;
    }

    /**
     * 校验是否是有效的邮件地址
     *
     * @param email 邮箱地址
     * @return 有效则返回真，否则返回假
     */
    public static boolean validEmail(final String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REG);
        if (!pattern.matcher(email).matches()) {
            return false;
        }
        return true;

    }

    /**
     * 去除字符串首尾出现的某个字符.
     *
     * @param source  源字符串.
     * @param element 需要去除的字符.
     * @return String.
     */
    public static String trimFirstAndLastChar(String source, char element) {
        boolean beginIndexFlag = true;
        boolean endIndexFlag = true;
        do {
            int beginIndex = source.indexOf(element) == 0 ? 1 : 0;
            int endIndex = source.lastIndexOf(element) + 1 == source.length() ? source.lastIndexOf(element) : source.length();
            source = source.substring(beginIndex, endIndex);
            beginIndexFlag = (source.indexOf(element) == 0);
            endIndexFlag = (source.lastIndexOf(element) + 1 == source.length());
        } while (beginIndexFlag || endIndexFlag);
        return source;
    }

    /**
     * Splits a String on a delimiter into a List of Strings.
     *
     * @param str   the String to split
     * @param delim the delimiter character(s) to join on (null will split on whitespace)
     * @return a list of Strings
     */
    public static List<String> split(String str, String delim) {
        List<String> splitList = null;
        StringTokenizer st = null;

        if (str == null)
            return splitList;

        if (delim != null)
            st = new StringTokenizer(str, delim);
        else
            st = new StringTokenizer(str);

        if (st != null && st.hasMoreTokens()) {
            splitList = new ArrayList<String>();

            while (st.hasMoreTokens())
                splitList.add(st.nextToken());
        }
        return splitList;
    }

    /**
     * 转换成UTF-8编码
     *
     * @param str
     * @return
     */
    public static String toUTF8(String str) {
        if (StringUtils.isNotEmpty(str)) {
            try {
                return new String(str.getBytes("iso8859-1"), "UTF-8");
            } catch (Exception ex) {
                return "";
            }
        }
        return "";
    }

    /**
     * 屏蔽手机的方法
     *
     * @param source 手机号
     * @return
     */
    public static String hiddenMobile(String source) {
        if (StringUtils.isBlank(source)) {
            return "";
        }
        Pattern pattern = Pattern.compile("^1\\d{10}$");

        if (pattern.matcher(source).matches()) {
            return source.substring(0, 3) + "****" + source.substring(7, 11);
        } else {
            return source;
        }

    }


    /**
     * 屏蔽邮箱的方法
     *
     * @param source 邮箱
     * @return
     */
    public static String hiddenEmail(String source) {
        if (StringUtils.isBlank(source)) {
            return "";
        }
        Pattern pattern = Pattern.compile(EMAIL_REG);
        if (pattern.matcher(source).matches()) {
            int splitIndex = source.indexOf("@");
            String emailPrefix = source.substring(0, splitIndex);
            if (emailPrefix.length() > 4) {
                source = source.substring(0, emailPrefix.length() - 4) + "****" + source.substring(splitIndex);
            } else {
                source = source.substring(0, 1) + "****" + source.substring(splitIndex);
            }
            return source;
        } else {
            return source;
        }
    }

    /**
     * 屏蔽身份证号的方法
     *
     * @param source 身份证号
     * @return
     */
    public static String hiddenIDCard(String source) {
        if (StringUtils.isBlank(source)) {
            return "";
        }
        if (source.length() > 8) {
            source = source.substring(0, source.length() - 8) + "********";
        } else {
            source = source.substring(0, 1) + "********";
        }

        return source;

    }

    /**
     * 反射输出实体类所有公共无入参的get和is方法名称和值
     *
     * @param object 实体类对象
     * @return object属性名称名称及属性值
     */
    public static String printParam(Object object) {
        if (object == null) {
            return "";
        }
        StringBuffer out = new StringBuffer();
        try {
            Class<?> clazz = object.getClass();
            out.append(clazz.getSimpleName());
            out.append(" [");
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getName().indexOf("get") >= 0) {
                    Type[] types = method.getGenericParameterTypes();
                    if (types == null || types.length == 0) {
                        Method mtd = clazz.getMethod(method.getName(), new Class[]{});
                        Object value = mtd.invoke(object);
                        if (value != null) {
                            out.append(method.getName() + "=" + value.toString() + ",");
                        }
                    }
                }
            }
            out.append("]");
        } catch (Exception e) {
        }
        return out.toString();
    }

    /**
     * 用变量值替换字符串模板中的变量
     *
     * @param template 模板
     * @param varStart 变量开始字符串
     * @param varEnd   变量结束字符串
     * @param values   变量值
     * @return
     */
    public static String buildStringByTemplate(String template, String varStart, String varEnd, Map<String, String> values) {
        String s = template;
        for (String key : values.keySet()) {
            s = s.replace(varStart + key + varEnd, values.get(key) == null ? "" : values.get(key));
        }
        return s;
    }

    /**
     * 把字节数组保存为一个文件
     */
    public static File getFileFromBytes(byte[] b, String outputFile) throws Exception {
        BufferedOutputStream stream = null;
        FileOutputStream fstream = null;
        File file = null;
        try {
            file = new File(outputFile);
            fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(stream);
            IOUtils.closeQuietly(fstream);
        }
        return file;
    }

    /**
     * 替换或截短用户名
     * 若用户名是手机号、邮箱或用户名为lv+手机号，则部分字符用*替换，其它根据传入的长度截短
     *
     * @param len      (-1表示不截短)
     * @param userName
     * @return
     */
    public static String replaceOrCutUserName(int len, String userName) {
        Pattern userNamePt = Pattern.compile("^lv[0-9]{11}$");
        String str = realplayMobleAndEmail(userName);

        if (!str.equals(userName)) {
            return str;
        } else if (userNamePt.matcher(userName).find() && validMobileNumber(userName.substring(2, userName.length()))) {
            return userName.substring(0, userName.length() - 4) + "****";
        }

        return len == -1 ? str : cutString2(len, str);
    }


    public static String hidenUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return userName;
        }
        return userName.substring(0, 1) + "**";
    }


    public static String realplayMobleAndEmail(final String str) {
        try {
            String s = str;
            //手机
            Pattern p = Pattern.compile(MOBILE_REG);
            //邮件
            Pattern p2 = Pattern.compile(EMAIL_REG);
            Matcher m2 = p2.matcher(str);
            Matcher m = p.matcher(str);
            if (s.length() == 11 && m.find()) {
                String st = s.substring(0, 7);
                s = st + "****";
            } else if (m2.find()) {
                int i = s.indexOf("@");
                String st = s.substring(0, i + 1);
                s = st + "****";
            }
            return s;
        } catch (Exception e) {
            return str;
        }
    }

    public static String cutString2(int len, String source) {
        if (source.length() >= len) {
            return source.substring(0, len) + "… ";
        } else {
            return source;
        }
    }

    public static String cutString(int len, String source) {
        if (source.length() >= len) {
            return source.substring(0, len) + "… ";
        } else {
            return source;
        }
    }

    /**
     * 处理 "null"
     *
     * @param value
     * @return
     */
    public static String coverNullStrValue(String value) {
        if ("null".equals(value) || value == null) {
            return "";
        }
        return value;
    }

    /**
     * 替换null对象或者null字符
     *
     * @param object
     * @return
     * @author dengcheng
     */
    public static String trimNullValue(String object) {
        if (object == null || "null".equals(object)) {
            return "";
        }
        return object;
    }

    /**
     * 变换数组为字符串
     *
     * @param arr
     * @return
     */
    public static String arrToStr(String[] arr) {
        String str = null;
        if (arr != null) {
            str = org.springframework.util.StringUtils.quote(org.springframework.util.StringUtils.arrayToDelimitedString(arr, "','"));
        }
        return str;
    }

    /**
     * 过滤html文档
     *
     * @param inputString
     * @return
     */
    public static String filterOutHTMLTags(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>																						// }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            textStr = htmlStr;
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
        return textStr;// 返回文本字符串
    }

    public static boolean isIdCard(String idNumber) {
        if (idNumber.length() == 18) {
            String pattern = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\\d{3}(x|X))|(\\d{4}))";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(idNumber);
            return m.matches();
        } else {
            String pattern = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((([02468][048])|([13579][26]))0229))|([0-9][0-9])((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))(\\d{3})";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(idNumber);
            return m.matches();
        }

    }

    public static boolean isPinyin(String keyword) {
        Pattern p = Pattern.compile("([a-z|A-Z]*)");
        Matcher m = p.matcher(keyword);
        return m.matches();
    }

    /**
     * 判断是否是数字(>=0 不包括负数)
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return str.matches("^\\d+");
    }

    /**
     * 将文本内容替换成可以在js中正常显示的内容，如将双引转意
     *
     * @param str
     * @return
     */
    public static String outputJSContent(String str) {
        if (StringUtils.isNotEmpty(str)) {
            return str.replaceAll("\"", "\\\\\"");
        } else {
            return "";
        }
    }

    /**
     * 过滤我们产品中的参数，返回正确字符串给coremetrics
     *
     * @param str
     * @return
     */
    public static String outputCoremetricsParam(String str) {
        if (StringUtils.isNotEmpty(str)) {
            str = str.replaceAll("\\(", "");
            str = str.replaceAll("\\)", "");
            return str;
        } else {
            return "";
        }
    }

    /**
     * 判断两个字符串的内容是否相同
     *
     * @param s1
     * @param s2
     */
    public static boolean isSame(String s1, String s2) {
        if (s1 != null) {
            return s1.equals(s2);
        } else if (s2 != null) {
            return s2.equals(s1);
        } else {
            return true;
        }
    }


    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return false;
        }
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getContent(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);//对参数排序，已确保拼接的签名顺序正确
        String prestr = "";
        boolean first = true;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (value == null || value.trim().length() == 0) {
                continue;
            }
            if (first) {
                prestr = prestr + key + "=" + value;
                first = false;
            } else {
                prestr = prestr + "&" + key + "=" + value;
            }
        }
        return prestr;
    }


    public static String formatPriceString(String price, int scale) {
        if (!NumberUtils.isNumber(price)) {
            return null;
        }
        if (scale < 0 && price.indexOf(".") == price.length() - 1 || scale == 0
                && price.indexOf(".") != -1) {
            price = price.substring(0, price.indexOf("."));
        } else if (scale > 0 && price.indexOf(".") == price.length() - 1) {
            for (int i = 0; i < scale; i++) {
                price += "0";
            }
        } else if (scale > 0 && price.indexOf(".") == -1) {
            price += ".";
            for (int i = 0; i < scale; i++) {
                price += "0";
            }
        } else if (scale > 0) {
            String decimal = price.substring(price.indexOf(".") + 1,
                    price.length());
            int length = decimal.length();
            if (length < scale) {
                int need = scale - length;
                for (int i = 0; i < need; i++) {
                    price += "0";
                }
            } else {
                price = price.substring(0, price.indexOf(".") + scale + 1);
            }
        }
        return price;
    }

    public static String lastChars(String str, int len) {
        if (str == null || str.length() < len) {
            return null;
        }
        return str.substring(str.length() - len);
    }


    public static String substringAfterLast(String str, String separator) {
        if (isEmptyString(str)) {
            return str;
        } else if (isEmptyString(separator)) {
            return "";
        } else {
            int pos = str.lastIndexOf(separator);
            return pos != -1 && pos != str.length() - separator.length() ? str.substring(pos + separator.length()) : "";
        }
    }

}
