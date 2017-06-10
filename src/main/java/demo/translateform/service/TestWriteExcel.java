package demo.translateform.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/10.
 */
public class TestWriteExcel {
    public static JSONArray array = new JSONArray();
    private static Logger logger = Logger.getLogger(TestWriteExcel.class);

    public void setJsonArray(JSONArray array){
        this.array = array;
    }

    public static void result(String path){
        File file = new File(path);
        try{
            setData(file,1);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(" ");
        }
    }

    public static void setData(File file, int ignoreRows) {

        //创建文件输出流
        OutputStream out = null;
        BufferedInputStream in = null;
        int rowSize = 0;
        try {
            in = new BufferedInputStream(new FileInputStream(
                    file));
            // 打开HSSFWorkbook
            POIFSFileSystem fs = new POIFSFileSystem(in);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFCell cell = null;
            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                //只写sheet的
                HSSFSheet st = wb.getSheetAt(0);
                //预留第一行
                Row row = st.createRow(i + 1);
                //第几列
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(jsonObject.get("KeyName").toString());
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(jsonObject.get("ColumnName").toString());
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(jsonObject.get("TableName").toString());
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(jsonObject.get("city").toString());
            }

            in.close();
            out = new FileOutputStream(file);
            wb.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 去掉字符串右边的空格
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }
}
