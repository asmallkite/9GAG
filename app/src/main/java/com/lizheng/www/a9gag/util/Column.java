package com.lizheng.www.a9gag.util;

/**
 * Created by 10648 on 2016/8/31 0031.
 */

public class Column {
    public static enum Constraint {
        UNIQUE("UNIQUE"), NOT("NOT"), NULL("NULL"), CHECK("CHECK"), FOREIGN_KEY("FOREIGN KEY"), PRIMARY_KEY(
                "PRIMARY KEY");

        private String value;

        private Constraint(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static enum DataType {
        NULL, INTEGER, REAL, TEXT, BLOB
    }

    private String mColumnName;

    private Constraint mConstraint;

    private DataType mDataType;

    public Column(String columnName, Constraint constraint, DataType dataType) {
        mColumnName = columnName;
        mConstraint = constraint;
        mDataType = dataType;
    }

    public String getColumnName() {
        return mColumnName;
    }

    public Constraint getConstraint() {
        return mConstraint;
    }

    public DataType getDataType() {
        return mDataType;
    }
}
