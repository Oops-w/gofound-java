package com.github.w.util;

import com.github.w.mode.IndexModel;

import java.util.List;
import java.util.Objects;

/**
 * @author wsy
 * @date 2022/11/5 4:38 PM
 * @Description
 */
public class AssertUtil {
    public static void isNull(Object obj) {
        if (Objects.nonNull(obj)) {
            throw new IllegalArgumentException("object is not null");
        }
    }

    public static void isNotNull(Object obj) {
        if (Objects.isNull(obj)) {
            throw new IllegalArgumentException("object is null");
        }
    }

    public static void isNotEmpty(List<IndexModel> list) {
        if (Objects.isNull(list) || list.isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }
    }
}
