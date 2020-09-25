package com.test.hutool;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;
import java.util.Map;

public class HT {
    public static void main(String[] args) {

        String url = "/Users/yanxu/project-suntech/qcc-cloud-backend/java-demo/hutool/src/main/resources/dict.xlsx";
        ExcelReader reader = ExcelUtil.getReader(url);
        List<List<Object>> maps = reader.read();
        for (List<Object> map : maps) {
            for (Object entry : map) {
                System.out.print(entry);
//                System.out.print(entry.getValue());
                System.out.println();
            }
        }
    }
}
