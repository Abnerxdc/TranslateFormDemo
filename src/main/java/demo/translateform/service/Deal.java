package demo.translateform.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/6/10.
 */
public class Deal {
    private static Logger logger = Logger.getLogger(Deal.class);
    static JSONArray processArray = new JSONArray();
    static JSONArray unprocessArray = new JSONArray();

    public static void result(){
        //从数据库中取出要处理的数据
        JSONArray databaseArray = Testdao.test();
        //读取Excel中的内容
        JSONObject readExcelObject = TestReadExcel.result();
        //
        logger.info("转过来的Excel数据："+readExcelObject);
        setArray(databaseArray,readExcelObject);

        //写入已处理xls
        TestWriteExcel testWriteExcel = new TestWriteExcel();
        testWriteExcel.setJsonArray(processArray);
        testWriteExcel.result("conf/processed.xls");

        //写入未处理xls
        TestWriteExcel testWriteExcel2 = new TestWriteExcel();
        testWriteExcel2.setJsonArray(unprocessArray);
        testWriteExcel2.result("conf/unprocessed.xls");

    }

    public static void setArray(JSONArray databaseArray,JSONObject readExcelObject){
        JSONArray dataArray = databaseArray;
        JSONObject readObject = readExcelObject;

        for(Object obj :dataArray){
            JSONObject dataObject = JSON.parseObject(obj.toString());
            String databaseKeyName = dataObject.getString("keyName");
            String databaseColumnName = dataObject.getString("columnName");
            String databaseTableName = dataObject.getString("tableName");
            if(readObject.containsKey(databaseKeyName)){
                JSONObject processedObject = new JSONObject();
                String city = readObject.getString(databaseKeyName);
                processedObject.put("KeyName",databaseKeyName);
                processedObject.put("ColumnName",databaseColumnName);
                processedObject.put("TableName",databaseTableName);
                processedObject.put("city",city);
                processArray.add(processedObject);
            }else{
                JSONObject unprocessedObject = new JSONObject();
                unprocessedObject.put("KeyName",databaseKeyName);
                unprocessedObject.put("ColumnName",databaseColumnName);
                unprocessedObject.put("TableName",databaseTableName);
                unprocessedObject.put("city"," ");
                unprocessArray.add(unprocessedObject);
            }
        }
    }
}
