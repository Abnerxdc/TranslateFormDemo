package demo.translateform.Dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;

/**
 * Created by Administrator on 2017/6/10.
 */
public class PostgreSqlQuery {
    public static final String driver = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://139.196.104.13:5432/TOPIBD_DEMO?useUnicode=true&characterEncoding=GBK";
    public static final String username = "toplinker";
    public static final String password = "TopLinker0510";

    /**
     * 数据库执行查询操作
     * @param sql
     * @return resultArray(JsonArray格式)
     */
    public static JSONArray select(String sql){
        JSONArray resultArray = new JSONArray();
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                //获取列的数量
                int columnCount = data.getColumnCount();
                for(int i =1;i<columnCount+1;i++){
                    String columnName = data.getColumnName(i);

                    String columnValue = rs.getString(i);
                    jsonObject.put(columnName, columnValue);
                }
                resultArray.add(jsonObject);
            }
            rs.close();
            stm.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultArray;
    }

    /**
     * 当程序执行插入 、删除、更新等操作
     * @param sql
     * @return boolean
     */
    public static boolean update(String sql){
        boolean result = false;
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stm = con.createStatement();
            int i = stm.executeUpdate(sql);
            if(i>0){
                result = true;
            }else{
                result = false;
            }
            stm.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 只查询一条数据，返回JsonObject
     * @param sql
     * @return
     */
    public static JSONObject selectOne(String sql){
        JSONObject resultObject = new JSONObject();
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            while(rs.next()){
                //获取列的数量
                int columnCount = data.getColumnCount();
                for(int i =1;i<columnCount+1;i++){
                    String columnName = data.getColumnName(i);
                    String columnValue = rs.getString(i);
                    resultObject.put(columnName, columnValue);
                }
            }
            rs.close();
            stm.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultObject;
    }
}
