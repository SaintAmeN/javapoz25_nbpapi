package org.sda.nest.nbpapi.model;

public enum TableType {
    TABLE_MID('a'),
    TABLE_BID_ASK('c');
    private char table;

    TableType(char table) {
        this.table = table;
    }

    public String getTable() {
        return "" + table;
    }
}
