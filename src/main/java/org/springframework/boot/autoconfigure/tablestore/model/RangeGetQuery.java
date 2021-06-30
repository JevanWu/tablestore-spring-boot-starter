package org.springframework.boot.autoconfigure.tablestore.model;

import com.alicloud.openservices.tablestore.model.Direction;
import com.alicloud.openservices.tablestore.model.PrimaryKey;
import java.util.List;
import org.springframework.boot.autoconfigure.tablestore.utils.ColumnUtils;

/**
 * Created on 2020/10/09
 *
 * @author Kenn
 */
public class RangeGetQuery {

    private String tableName;

    private PrimaryKey startPrimaryKey;

    private PrimaryKey endPrimaryKey;

    private List<String> columnNames;

    private int limit = 100;

    private Direction direction = Direction.FORWARD;

    public String tableName() {
        return tableName;
    }

    public void tableName(String tableName) {
        this.tableName = tableName;
    }

    public PrimaryKey startPrimaryKey() {
        return startPrimaryKey;
    }

    public <T> void startPrimaryKey(T key) {
        startPrimaryKey = ColumnUtils.primaryKey(key, KeyType.START, direction);
    }

    public PrimaryKey endPrimaryKey() {
        return endPrimaryKey;
    }

    public <T> void endPrimaryKey(T key) {
        endPrimaryKey = ColumnUtils.primaryKey(key, KeyType.END, direction);
    }

    public List<String> columnNames() {
        return columnNames;
    }

    public void columnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public int limit() {
        return limit;
    }

    public void limit(int limit) {
        this.limit = limit;
    }

    public Direction direction() {
        return direction;
    }

    public void direction(Direction direction) {
        this.direction = direction;
    }

    public enum KeyType {
        /**
         * 起始主键
         */
        START,
        /**
         * 终止主键
         */
        END
    }
}
