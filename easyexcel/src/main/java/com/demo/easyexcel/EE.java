package com.demo.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import org.apache.poi.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class EE {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "d:/projects/java-demo/easyexcel/src/main/resources/dict.xlsx";
        com.alibaba.excel.EasyExcel.read(url, new NoModelDataListener()).sheet().doReadSync();
//        for (Map<Integer, String> map : listMap) {
//            for (Map.Entry<Integer, String> entry : map.entrySet()) {
//                Integer key = entry.getKey();
//                String value = entry.getValue();
//                if (StringUtils.isEmpty(value)) {
//                    break;
//                }else{
//                    System.out.println(key + " " + value);
//                }
//            }
//        }
    }
}
