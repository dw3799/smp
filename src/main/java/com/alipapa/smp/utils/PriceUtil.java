package com.alipapa.smp.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PriceUtil {
    private static final Log LOG = LogFactory.getLog(PriceUtil.class);

    /**
     * 把钱转换成分
     *
     * @param price
     * @return
     */
    public static long convertToFen(float price) {
        return PriceUtil.convertToFen(Float.toString(price));
    }

    /**
     * 把钱转换成分
     *
     * @param price
     * @return
     */
    public static long convertToFen(String price) {
        return new BigDecimal(price).multiply(new BigDecimal(100f)).longValue();
    }

    /**
     * 格式化订单金额.
     * <pre>
     * 格式：整数位不前补零,小数位补齐2位即：不超过10位整数位+1位小数点+2位小数
     * 无效格式如123，.10，1.1
     * 有效格式如1.00，0.10
     * </pre>
     *
     * @param amount 以分为单位的金额
     * @return
     */
    public static String trans2YuanStr(Long amount) {
        final int MULTIPLIER = 100;
        String amountYuan = new BigDecimal(amount).divide(new BigDecimal(MULTIPLIER)).setScale(2).toString();
        return amountYuan;
    }

    /**
     * 得到整形元
     *
     * @param ammount
     * @return
     */
    public static Long getLongPriceYuan(Long ammount) {
        return Long.valueOf(ammount / 100);
    }

    public static long convertToFen(BigDecimal price) {
        return price.multiply(new BigDecimal(100)).longValue();
    }

    /**
     * 向上取整(保证价格整数元)
     *
     * @return
     */
    public static long getLongPrice(Long price) {
        try {
            if (price == null || price < 0) {
                return 0l;
            }
            BigDecimal p = new BigDecimal(price);
            return 100L * (long) (p.divide(new BigDecimal(100), 0, BigDecimal.ROUND_UP).floatValue());
        } catch (Exception e) {
            LOG.error(e);
        }
        return 0L;
    }

    /**
     * @param price
     * @return
     */
    public static float convertToYuan(final Long price) {
        if (price == null) {
            return 0f;
        }
        BigDecimal p = new BigDecimal(price);
        return p.divide(new BigDecimal(100), 2, BigDecimal.ROUND_UP).floatValue();
    }

    public static String convertToYuanStr(final Long price) {
        if (price == null) {
            return "0";
        }
        return BigDecimal.valueOf(price).divide(new BigDecimal(100)).toString();
    }

    private static DecimalFormat df = new DecimalFormat("0.##");

    /**
     * 数字计算到小数点后2位，去0
     */
    public static String convertToFenRemoveZero(final Long price) {
        if (price == null) {
            return "0";
        }
        BigDecimal p = new BigDecimal(Double.valueOf(price) / 100);
        return df.format(p);
    }


    public static String formatDecimal(Object price) {
        DecimalFormat df = new DecimalFormat("####0.00");
        String strPrice = df.format(price);
        return strPrice;
    }

    public static float convertToYuan(final BigDecimal price) {
        return convertToYuan(price.longValue());
    }

    /**
     * 把字符串钱转换成分
     *
     * @param price
     * @return
     */
    public static long moneyConvertLongPrice(String price) {
        return PriceUtil.convertToFen(new BigDecimal(price));
    }

    public static String moneyConvertStr(long price) {
        BigDecimal bigDecimal = new BigDecimal(price);
        return (bigDecimal.floatValue() / 100) + "";
    }

    public static Long convertToHoursForDistribution(Long minutes) {
        int min = Integer.parseInt(minutes.toString());
        int lesMin = min % 60;
        if (lesMin != 0) {
            return minutes / 60 + 1;
        } else {
            return minutes / 60;
        }
    }

    public static void main(String arg[]) {
        Long price = 232353L;
        BigDecimal p = new BigDecimal(price);
        float pr1 = p.divide(new BigDecimal(100), 0, BigDecimal.ROUND_UP).floatValue();
        float pr2 = p.divide(new BigDecimal(100), 0, BigDecimal.ROUND_DOWN).floatValue();
        float pr3 = p.divide(new BigDecimal(100), 0, BigDecimal.ROUND_HALF_UP).floatValue();
        float pr4 = p.divide(new BigDecimal(100), 0, BigDecimal.ROUND_HALF_DOWN).floatValue();
        System.out.println(pr1);
        System.out.println(pr2);
        System.out.println(pr3);
        System.out.println(pr4);
    }
}
