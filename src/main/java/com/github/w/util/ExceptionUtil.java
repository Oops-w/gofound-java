package com.github.w.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author wsy
 * @date 2022/11/5 3:11 PM
 * @Description 获取异常堆栈String
 */
public class ExceptionUtil {

    public static String getStackTrace(Throwable t) {
        try (StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw)) {
            t.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
