package demo.translateform.bean;

/**
 * Created by Administrator on 2017/6/10.
 */
public class WriteBean {
    private String KeyName;
    private String columnName;
    private String tableName;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTableName() {

        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {

        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getKeyName() {

        return KeyName;
    }

    public void setKeyName(String keyName) {
        KeyName = keyName;
    }
}
