package com.demo.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.util.NumberUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadHeader {


    @Test
    public void test1() {
//        Integer.parseInt("50.00");
    }
    @Test
    public void test(){
        System.out.println("start");
        final Map<Integer, CellData> header = new HashMap<>();
        EasyExcel.read("/home/mnimport.xlsx",  new AnalysisEventListener<Map<Integer, String>>() {
            @Override
            public void onException(Exception exception, AnalysisContext context) throws Exception {
                System.out.println("onException " + exception.getMessage());
            }

            @Override
            public void invokeHead(Map headMap, AnalysisContext context) {
                System.out.println("invokeHead "+headMap.entrySet());
                if (true) {
                    throw new RuntimeException("123");
                }
                header.putAll(headMap);
            }

            @Override
            public void invoke(Map<Integer, String> data, AnalysisContext context) {
//                System.out.println(data);
            }

            @Override
            public void extra(CellExtra extra, AnalysisContext context) {
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
            }

        }).sheet().doRead();
        System.out.println("end");
        for (Map.Entry<Integer, CellData> entry : header.entrySet()) {
            System.out.println(entry.getValue().getStringValue());
        }
    }

}
