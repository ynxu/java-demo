package com.test.hutool;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;
import java.util.Map;

public class HT {
    public static void main(String[] args) {

//        String url = "/Users/yanxu/project-suntech/qcc-cloud-backend/java-demo/hutool/src/main/resources/a.xlsx";
        String url = "/Users/yanxu/a.xlsx";
        ExcelReader reader = ExcelUtil.getReader(url);
        List<Map<String, Object>> maps = reader.readAll();
        for (Map<String, Object> map : maps) {
            for (Map.Entry<String,Object> entry : map.entrySet()) {
                System.out.print(entry.getKey());
                System.out.print(entry.getValue());
                System.out.println();
            }
        }
    }
}
