package com.wzm.poi;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wzm.baidu.translate.BaiduResult;
import com.wzm.baidu.translate.TransApi;
import com.wzm.baidu.translate.TransResult;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

//翻译excel
public class TranslateExcel {
    private static final String APP_ID = "20200315000398577";
    private static final String SECURITY_KEY = "nQEmv3bdY_mlhEO8DJWx";

    public static void main(String[] args) throws Exception {
        File excelFile = new File("D:\\小微需求问题清单.xlsx");
        FileInputStream is = new FileInputStream(excelFile);
        Workbook workbook = WorkbookFactory.create(is);
        int sheetCount = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetCount; i++) {
            //依次获取表格
            Sheet sheet = workbook.getSheetAt(i);
            int rowNum = sheet.getLastRowNum();
            //行
            for (int j = 0; j <= rowNum; j++) {
                Row row = sheet.getRow(j);
                if (ObjectUtil.isNotEmpty(row)) {
                    short lastCellNum = row.getLastCellNum();
                    if (lastCellNum > 0) {
//                    int cellNum = row.getLastCellNum();
                        int cellNum = 2;
                        //单元格
                        for (int k = 1; k < cellNum; k++) {
                            if (ObjectUtil.isNotEmpty(row.getCell(k)) && ObjectUtil.isEmpty(row.getCell(k + 1))) {
                                String query = row.getCell(k).getStringCellValue();
                                if (ObjectUtil.isNotEmpty(query)) {
                                    String result = baiduTranslate(query);
                                    row.createCell(k + 1).setCellValue(result.toUpperCase().replace(" ", "_"));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        FileOutputStream os = new FileOutputStream("D:\\小微需求问题清单.xlsx");
        workbook.write(os);
        os.flush();
        os.close();
    }

    private static String baiduTranslate(String query) {
        System.out.println(query);
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        String transResult = api.getTransResult(query, "auto", "en");
        BaiduResult baiduResult = JSON.parseObject(transResult, new TypeReference<BaiduResult>() {
        });
        List<TransResult> trans_result = baiduResult.getTrans_result();
        String result = trans_result.get(0).getDst();
        System.out.println(result);
        return result;
    }
}
