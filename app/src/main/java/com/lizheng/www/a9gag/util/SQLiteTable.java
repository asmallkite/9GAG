package com.lizheng.www.a9gag.util;

/**
 * Created by 10648 on 2016/8/31 0031.
 *  用了util中的Column
 * util类中，构造SQLiteTable 添加表名 并自动添加主键
 * 定义关于约束的处理
 * 添加 列
 * 专业处理 建表语句
 * 删除语句
 */

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import java.util.ArrayList;



public class SQLiteTable {
    String mTableName;

    ArrayList<Column> mColumnsDefinitions = new ArrayList<Column>();

    public String getTableName() {
        return mTableName;
    }

    /**
     * 会自动添加主键 BaseColumns._ID
     *
     * @param tableName
     */
    public SQLiteTable(String tableName) {
        mTableName = tableName;
        mColumnsDefinitions.add(new Column(BaseColumns._ID, Column.Constraint.PRIMARY_KEY,
                Column.DataType.INTEGER));
    }

    public SQLiteTable addColumn(Column columnsDefinition) {
        mColumnsDefinitions.add(columnsDefinition);
        return this;
    }

    public SQLiteTable addColumn(String columnName, Column.DataType dataType) {
        mColumnsDefinitions.add(new Column(columnName, null, dataType));
        return this;
    }

    public SQLiteTable addColumn(String columnName, Column.Constraint constraint,
                                 Column.DataType dataType) {
        mColumnsDefinitions.add(new Column(columnName, constraint, dataType));
        return this;
    }

    public void create(SQLiteDatabase db) {
        String formatter = " %s";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(mTableName);
        stringBuilder.append("(");
        int columnCount = mColumnsDefinitions.size();
        int index = 0;
        for (Column columnsDefinition : mColumnsDefinitions) {
            stringBuilder.append(columnsDefinition.getColumnName()).append(
                    String.format(formatter, columnsDefinition.getDataType().name()));
            Column.Constraint constraint = columnsDefinition.getConstraint();

            if (constraint != null) {
                stringBuilder.append(String.format(formatter, constraint.toString()));
            }
            if (index < columnCount - 1) {
                stringBuilder.append(",");
            }
            index++;
        }
        stringBuilder.append(");");
        db.execSQL(stringBuilder.toString());
    }

    public void delete(final SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + mTableName);
    }
}
