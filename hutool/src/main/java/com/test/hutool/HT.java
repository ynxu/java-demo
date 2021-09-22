package com.test.hutool;

import java.util.concurrent.atomic.AtomicInteger;

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
//        String s = StrUtil.replace("0010", Pattern.compile("^[0]+"), (matcher)->"");
//        System.out.println(s);
//        String maskBitByMask = Ipv4Util.formatIpBlock("192.168.1.1", "255,255,0,0");
//        System.out.println(maskBitByMask);
//        System.out.println(StrUtil.format("sh {}","getIp"));
//        SecureRandom secureRandom = RandomUtil.getSecureRandom();
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("insert into szc_log (id,app_id,log) values ("+i+",'123456');");
//        }
        AtomicInteger a = new AtomicInteger(0);
        System.out.println(a.get());
        a.set(1);
        System.out.println(a.get());
    }
}
