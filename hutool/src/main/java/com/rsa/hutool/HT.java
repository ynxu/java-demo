package com.rsa.hutool;

import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HT {
    public static void main(String[] args) {

//        String url = "/Users/yanxu/project-suntech/qcc-cloud-backend/java-demo/hutool/src/main/resources/a.xlsx";
//        String url = "/Users/yanxu/a.xlsx";
//        ExcelReader reader = ExcelUtil.getReader(url);
//        List<Map<String, Object>> maps = reader.readAll();
//        for (Map<String, Object> map : maps) {
//            for (Map.Entry<String,Object> entry : map.entrySet()) {
//                System.out.print(entry.getKey());
//                System.out.print(entry.getValue());
//                System.out.println();
//            }
//
//        }
        String s = StrUtil.replace("0010", Pattern.compile("^[0]+"), (matcher)->"");
        System.out.println(s);
    }
}
