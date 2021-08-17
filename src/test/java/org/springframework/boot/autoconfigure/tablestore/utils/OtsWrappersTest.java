package org.springframework.boot.autoconfigure.tablestore.utils;

import com.alibaba.fastjson.JSON;
import com.alicloud.openservices.tablestore.model.ColumnValue;
import com.alicloud.openservices.tablestore.model.search.query.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.tablestore.annotation.OtsColumn;
import org.springframework.boot.autoconfigure.tablestore.enums.OtsColumnType;

import java.util.ArrayList;
import java.util.List;

class OtsWrappersTest {

    @Test
    void testEq() {
        var queryWrapper = OtsWrappers.query(Foo.class)
                .eq("field1", "hello")
                .eq("field2", 2L)
                .eq("field3", 3.0)
                .page(2, 10);

        List<Query> queries = new ArrayList<>();
        var temQuery = new TermQuery();
        temQuery.setFieldName("field1");
        temQuery.setTerm(ColumnValue.fromString("hello"));
        queries.add(temQuery);
        temQuery = new TermQuery();
        temQuery.setFieldName("field2");
        temQuery.setTerm(ColumnValue.fromLong(2));
        queries.add(temQuery);
        temQuery = new TermQuery();
        temQuery.setFieldName("field3");
        temQuery.setTerm(ColumnValue.fromDouble(3));
        queries.add(temQuery);
        var compactQuery = new BoolQuery();
        compactQuery.setMustQueries(queries);

        Assertions.assertEquals(JSON.toJSONString(compactQuery), JSON.toJSONString(queryWrapper.resolveQuery()));
    }

    @Test
    void testIn() {
        var queryWrapper = OtsWrappers.query(Foo.class)
                .in("field1", List.of("hello"));

        List<Query> queries = new ArrayList<>();
        var termsQuery = new TermsQuery();
        termsQuery.setFieldName("field1");
        termsQuery.addTerm(ColumnValue.fromString("hello"));
        queries.add(termsQuery);
        var compactQuery = new BoolQuery();
        compactQuery.setMustQueries(queries);

        Assertions.assertEquals(JSON.toJSONString(compactQuery), JSON.toJSONString(queryWrapper.resolveQuery()));
    }

    @Test
    void testGe() {
        var queryWrapper = OtsWrappers.query(Foo.class)
                .ge("field3", 3.0);

        List<Query> queries = new ArrayList<>();
        var rangeQuery = new RangeQuery();
        rangeQuery.setFieldName("field3");
        rangeQuery.greaterThanOrEqual(ColumnValue.fromDouble(3.0));
        queries.add(rangeQuery);
        var compactQuery = new BoolQuery();
        compactQuery.setMustQueries(queries);

        Assertions.assertEquals(JSON.toJSONString(compactQuery), JSON.toJSONString(queryWrapper.resolveQuery()));
    }

    @Test
    void testGt() {
        var queryWrapper = OtsWrappers.query(Foo.class)
                .gt("field3", 3.0);

        List<Query> queries = new ArrayList<>();
        var rangeQuery = new RangeQuery();
        rangeQuery.setFieldName("field3");
        rangeQuery.greaterThan(ColumnValue.fromDouble(3.0));
        queries.add(rangeQuery);
        var compactQuery = new BoolQuery();
        compactQuery.setMustQueries(queries);

        Assertions.assertEquals(JSON.toJSONString(compactQuery), JSON.toJSONString(queryWrapper.resolveQuery()));
    }

    @Test
    void testLe() {
        var queryWrapper = OtsWrappers.query(Foo.class)
                .le("field3", 3.0);

        List<Query> queries = new ArrayList<>();
        var rangeQuery = new RangeQuery();
        rangeQuery.setFieldName("field3");
        rangeQuery.lessThanOrEqual(ColumnValue.fromDouble(3.0));
        queries.add(rangeQuery);
        var compactQuery = new BoolQuery();
        compactQuery.setMustQueries(queries);

        Assertions.assertEquals(JSON.toJSONString(compactQuery), JSON.toJSONString(queryWrapper.resolveQuery()));
    }

    @Test
    void testLt() {
        var queryWrapper = OtsWrappers.query(Foo.class)
                .lt("field3", 3.0);

        List<Query> queries = new ArrayList<>();
        var rangeQuery = new RangeQuery();
        rangeQuery.setFieldName("field3");
        rangeQuery.lessThan(ColumnValue.fromDouble(3.0));
        queries.add(rangeQuery);
        var compactQuery = new BoolQuery();
        compactQuery.setMustQueries(queries);

        Assertions.assertEquals(JSON.toJSONString(compactQuery), JSON.toJSONString(queryWrapper.resolveQuery()));
    }

    @Test
    void testNe() {
        var queryWrapper = OtsWrappers.query(Foo.class)
                .ne("field3", 3.0);

        List<Query> queries = new ArrayList<>();
        var termQuery = new TermQuery();
        termQuery.setFieldName("field3");
        termQuery.setTerm(ColumnValue.fromDouble(3.0));
        queries.add(termQuery);
        var compactQuery = new BoolQuery();
        compactQuery.setMustNotQueries(queries);
        compactQuery.setMustQueries(List.of());

        Assertions.assertEquals(JSON.toJSONString(compactQuery), JSON.toJSONString(queryWrapper.resolveQuery()));

    }

    class Foo {
        @OtsColumn(type = OtsColumnType.STRING)
        private String field1;
        @OtsColumn(type = OtsColumnType.INTEGER)
        private Long field2;
        @OtsColumn(type = OtsColumnType.DOUBLE)
        private Double field3;
    }
}