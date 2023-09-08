package helpers;

public class Config {
    String databaseName;
    String tableName;
    int tablesCount;
    int rowsCount;
    String copyFrom;
    boolean addColumnToExistingTable;
    boolean copyFromExistingTable;

    public Config() {
        this.databaseName = "scale";
        this.tableName = "customer_data";
        this.tablesCount = 1000;
        this.rowsCount = 10;
        this.copyFrom = "customer_data1";
        this.addColumnToExistingTable = false;
        this.copyFromExistingTable = false;
    }

    public Config(String databaseName, String tableName, int tablesCount, int rowsCount, String copyFrom, boolean addColumnToExistingTable, boolean copyFromExistingTable) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.tablesCount = tablesCount;
        this.rowsCount = rowsCount;
        this.copyFrom = copyFrom;
        this.addColumnToExistingTable = addColumnToExistingTable;
        this.copyFromExistingTable = copyFromExistingTable;
    }

    public String getCopyFrom() {
        return copyFrom;
    }

    public boolean isAddColumnToExistingTable() {
        return addColumnToExistingTable;
    }

    public void setAddColumnToExistingTable(boolean addColumnToExistingTable) {
        this.addColumnToExistingTable = addColumnToExistingTable;
    }

    public boolean isCopyFromExistingTable() {
        return copyFromExistingTable;
    }

    public void setCopyFromExistingTable(boolean copyFromExistingTable) {
        this.copyFromExistingTable = copyFromExistingTable;
    }

    public void setCopyFrom(String copyFrom) {
        this.copyFrom = copyFrom;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setTablesCount(int tablesCount) {
        this.tablesCount = tablesCount;
    }

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public int getTablesCount() {
        return tablesCount;
    }

    public int getRowsCount() {
        return rowsCount;
    }
}
