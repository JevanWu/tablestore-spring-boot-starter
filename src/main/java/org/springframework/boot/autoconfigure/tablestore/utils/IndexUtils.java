package org.springframework.boot.autoconfigure.tablestore.utils;

import com.google.common.collect.Lists;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.autoconfigure.tablestore.annotation.OtsIndex;

/**
 * IndexUtils
 *
 * @author 阿古(gujianguo @ xigua.city)
 * @version 1.0
 * @date 2021/02/23 15:40
 */
public class IndexUtils {

    /**
     * 解析 OtsIndex 注解信息
     *
     * @param clazz 对象 Class
     * @return {@link OtsIndex} 集合
     */
    public static List<OtsIndex> getOtsIndex(Class clazz) {
        List<OtsIndex> otsIndices = Lists.newArrayList();
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields).forEach(f -> {
            OtsIndex otsIndex = f.getAnnotation(OtsIndex.class);
            if (otsIndex != null) {
                otsIndices.add(otsIndex);
            }
        });

        return otsIndices;
    }
}
