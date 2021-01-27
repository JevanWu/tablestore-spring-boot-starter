package org.springframework.boot.autoconfigure.tablestore.model;

import com.alicloud.openservices.tablestore.model.search.agg.AggregationResults;
import com.alicloud.openservices.tablestore.model.search.groupby.GroupByResults;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * Created on 2020/10/09
 *
 * @author Kenn
 */
public class IndexSearchReply<T> {

    private long totalCount;

    private boolean allSuccess;

    private List<T> records = Lists.newArrayList();

    private AggregationResults aggregationResults;

    private GroupByResults groupByResults;

    public AggregationResults aggregationResults() {
        return aggregationResults;
    }

    public void aggregationResults(AggregationResults aggregationResults) {
        this.aggregationResults = aggregationResults;
    }

    public GroupByResults groupByResults() {
        return groupByResults;
    }

    public void groupByResults(GroupByResults groupByResults) {
        this.groupByResults = groupByResults;
    }

    public void add(T record) {
        records.add(record);
    }

    public long totalCount() {
        return totalCount;
    }

    public void totalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public boolean allSuccess() {
        return allSuccess;
    }

    public void allSuccess(boolean allSuccess) {
        this.allSuccess = allSuccess;
    }

    public List<T> records() {
        return records;
    }

    public void records(List<T> records) {
        this.records = records;
    }
}
