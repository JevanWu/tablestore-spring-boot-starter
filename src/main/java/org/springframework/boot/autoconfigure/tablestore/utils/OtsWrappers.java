package org.springframework.boot.autoconfigure.tablestore.utils;

import com.alicloud.openservices.tablestore.model.ColumnType;
import com.alicloud.openservices.tablestore.model.ColumnValue;
import com.alicloud.openservices.tablestore.model.search.query.*;
import com.alicloud.openservices.tablestore.model.search.sort.Sort;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.autoconfigure.tablestore.enums.OtsColumnType;
import org.springframework.boot.autoconfigure.tablestore.model.IndexSearchQuery;
import org.springframework.boot.autoconfigure.tablestore.model.internal.FieldInfo;

import java.security.InvalidParameterException;
import java.util.*;

public final class OtsWrappers {

    public static <T> QueryWrapper<T> query(Class<T> clazz) {
        return new QueryWrapper<T>(clazz);
    }

    public static class QueryWrapper<T> {
        private final List<Query> mustQueries = new ArrayList<>();
        private final Map<String, FieldInfo> fieldInfoMap;
        private final Class<T> clazz;
        private int pageSize = 20;
        private int pageNum = 0;
        private Sort sort;
        private boolean includeTotalCount = false;

        public QueryWrapper(Class<T> clazz) {
            this.clazz = clazz;
            var declaredFields = FieldUtils.getDeclaredFields(clazz);
            this.fieldInfoMap = declaredFields.getKey();
        }

        public QueryWrapper<T> page(int pageNum, int pageSize) {
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            return this;
        }

        public QueryWrapper<T> sort(Sort sort) {
            this.sort = sort;
            return this;
        }

        public QueryWrapper<T> includeTotalCount(boolean includeTotalCount) {
            this.includeTotalCount = includeTotalCount;
            return this;
        }

        public QueryWrapper<T> eq(String fieldName, String value) {
            return termQuery(fieldName, value);
        }

        public QueryWrapper<T> eq(boolean condition,
                                  String fieldName, String value) {
            if (!condition) return this;
            return eq(fieldName, value);
        }

        public QueryWrapper<T> eq(String fieldName, Long value) {
            return termQuery(fieldName, value);
        }

        public QueryWrapper<T> eq(boolean condition,
                                  String fieldName, Long value) {
            if (!condition) return this;
            return eq(fieldName, value);
        }

        public QueryWrapper<T> eq(String fieldName, Double value) {
            return termQuery(fieldName, value);
        }

        public QueryWrapper<T> eq(boolean condition,
                                  String fieldName, Double value) {
            if (!condition) return this;
            return eq(fieldName, value);
        }

        private QueryWrapper<T> termQuery(String fieldName, Object value) {
            if (ObjectUtils.isNotEmpty(value)) {
                var query = new TermQuery();
                query.setFieldName(fieldName);
                query.setTerm(resolveColumnValue(fieldName, value));
                mustQueries.add(query);
            }
            return this;
        }

        public QueryWrapper<T> in(String fieldName, Collection<?> values) {
            return termsQuery(fieldName, values);
        }

        public QueryWrapper in(boolean condition,
                               String fieldName, Collection<?> values) {
            if (!condition) return this;
            return in(fieldName, values);
        }

        private QueryWrapper<T> termsQuery(String fieldName, Collection<?> values) {
            if (ObjectUtils.isNotEmpty(values)) {
                var query = new TermsQuery();
                query.setFieldName(fieldName);
                values.forEach(value -> {
                    query.addTerm(resolveColumnValue(fieldName, value));
                });
                mustQueries.add(query);
            }
            return this;
        }

        public QueryWrapper<T> ge(String fieldName, String value) {
            return geTermQuery(fieldName, value);
        }

        public QueryWrapper<T> ge(boolean condition,
                                  String fieldName, String value) {
            if (!condition) return this;
            return geTermQuery(fieldName, value);
        }

        public QueryWrapper<T> ge(String fieldName, Double value) {
            return geTermQuery(fieldName, value);
        }

        public QueryWrapper<T> ge(boolean condition,
                                  String fieldName, Double value) {
            if (!condition) return this;
            return geTermQuery(fieldName, value);
        }

        private QueryWrapper<T> geTermQuery(String fieldName, Object value) {
            if (ObjectUtils.isNotEmpty(value)) {
                var query = new RangeQuery();
                query.setFieldName(fieldName);
                query.greaterThanOrEqual(resolveColumnValue(fieldName, value));
                mustQueries.add(query);
            }
            return this;
        }

        public QueryWrapper<T> gt(String fieldName, String value) {
            return gtTermQuery(fieldName, value);
        }

        public QueryWrapper<T> gt(boolean condition,
                                  String fieldName, String value) {
            if (!condition) return this;
            return gtTermQuery(fieldName, value);
        }

        public QueryWrapper<T> gt(String fieldName, Double value) {
            return gtTermQuery(fieldName, value);
        }

        public QueryWrapper<T> gt(boolean condition,
                                  String fieldName, Double value) {
            if (!condition) return this;
            return gtTermQuery(fieldName, value);
        }

        private QueryWrapper<T> gtTermQuery(String fieldName, Object value) {
            if (ObjectUtils.isNotEmpty(value)) {
                var query = new RangeQuery();
                query.setFieldName(fieldName);
                query.greaterThan(resolveColumnValue(fieldName, value));
                mustQueries.add(query);
            }
            return this;
        }

        public QueryWrapper<T> le(String fieldName, String value) {
            return leTermQuery(fieldName, value);
        }

        public QueryWrapper<T> le(boolean condition,
                                  String fieldName, String value) {
            if (!condition) return this;
            return leTermQuery(fieldName, value);
        }

        public QueryWrapper<T> le(String fieldName, Double value) {
            return leTermQuery(fieldName, value);
        }

        public QueryWrapper<T> le(boolean condition,
                                  String fieldName, Double value) {
            if (!condition) return this;
            return leTermQuery(fieldName, value);
        }

        private QueryWrapper<T> leTermQuery(String fieldName, Object value) {
            if (ObjectUtils.isNotEmpty(value)) {
                var query = new RangeQuery();
                query.setFieldName(fieldName);
                query.lessThanOrEqual(resolveColumnValue(fieldName, value));
                mustQueries.add(query);
            }
            return this;
        }

        public QueryWrapper<T> lt(String fieldName, String value) {
            return ltTermQuery(fieldName, value);
        }

        public QueryWrapper<T> lt(boolean condition,
                                  String fieldName, String value) {
            if (!condition) return this;
            return ltTermQuery(fieldName, value);
        }

        public QueryWrapper<T> lt(String fieldName, Double value) {
            return ltTermQuery(fieldName, value);
        }

        public QueryWrapper<T> lt(boolean condition,
                                  String fieldName, Double value) {
            if (!condition) return this;
            return ltTermQuery(fieldName, value);
        }

        private QueryWrapper<T> ltTermQuery(String fieldName, Object value) {
            if (ObjectUtils.isNotEmpty(value)) {
                var query = new RangeQuery();
                query.setFieldName(fieldName);
                query.lessThan(resolveColumnValue(fieldName, value));
                mustQueries.add(query);
            }
            return this;
        }

        Query resolveQuery() {
            var boolQuery = new BoolQuery();
            boolQuery.setMustQueries(mustQueries);
            OtsWrappers wrapper = new OtsWrappers();
            return boolQuery;
        }

        public IndexSearchQuery resolveSearchQuery() {
            IndexSearchQuery searchQuery = new IndexSearchQuery();
            searchQuery.query(this.resolveQuery());
            searchQuery.getTotalCount(this.includeTotalCount);
            searchQuery.offset(this.pageNum * this.pageSize);
            searchQuery.size(this.pageSize);
            searchQuery.sort(this.sort);
            return searchQuery;
        }

        public Class<T> getClazz() {
            return this.clazz;
        }

        private ColumnValue resolveColumnValue(String fieldName, Object value) {
            FieldInfo fieldInfo = fieldInfoMap.get(fieldName);
            if (fieldInfo == null) {
                throw new InvalidParameterException();
            }
            var annotation = fieldInfo.otsColumn();
            return new ColumnValue(value, convertType(annotation.type()));
        }

        private ColumnType convertType(OtsColumnType type) {
            return Enum.valueOf(ColumnType.class, type.name());
        }

    }

}
