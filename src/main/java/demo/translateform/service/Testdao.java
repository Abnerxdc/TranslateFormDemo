package demo.translateform.service;

import com.alibaba.fastjson.JSONArray;
import demo.translateform.Dao.PostgreSqlQuery;
import org.apache.log4j.Logger;


/**
 * Created by Administrator on 2017/6/10.
 */
public class Testdao {
    private static Logger logger = Logger.getLogger(Testdao.class);
    public static JSONArray test(){
        String tableNameInSql = "('tbl_yj_dic_airport_city','tbl_yj_dic_airport_country','tbl_yj_dic_airport_name','tbl_yj_dic_airport_province','tbl_yj_dic_airport_latlng')";
        String sql = "SELECT  * from tbl_yj_expanddic WHERE \"tableName\" IN "+tableNameInSql;
        logger.info("logger sql : "+sql);
       JSONArray array = PostgreSqlQuery.select(sql);
       logger.info("array: "+array);
       return array;
    }
}
