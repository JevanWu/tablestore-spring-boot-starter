package org.springframework.boot.autoconfigure.tablestore.annotation;

import com.alicloud.openservices.tablestore.model.search.FieldType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Aliyun OTS SearchIndex
 *
 * @author 阿古(gujianguo @ xigua.city)
 * @version 1.0
 * @date 2021/02/23 15:28
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OtsIndex {

    /**
     * Name of index field (default self name)
     *
     * @return field name
     */
    String name() default "";

    /**
     *
     */
    FieldType type() default FieldType.KEYWORD;

    /**
     * 是否开启排序和聚合功能
     */
    boolean enableSortAndAgg();

    /**
     * 附加存储，是否在SearchIndex中附加存储该字段的值。
     * 开启后，可以直接从SearchIndex中读取该字段的值，而不必反查主表，可用于查询性能优化。
     */
    boolean store();

    /**
     * 存的值是否是一个数组
     */
    boolean isArray();

    /**
     * 如果 FiledType 是 NESTED ，则可使用该字段，声明一个嵌套的FieldSchema
     */
    Class subFieldSchemas();

}
