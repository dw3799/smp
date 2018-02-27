package com.alipapa.smp.utils;

import java.security.MessageDigest;

public class MD5 {
    public MD5() {
    }

    public static String digist(String text) {
        String result = "";

        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(text.getBytes("utf-8"));
            byte[] b = md.digest();
            StringBuilder buf = new StringBuilder("");

            for (int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

            result = buf.toString();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return result;
    }
}
