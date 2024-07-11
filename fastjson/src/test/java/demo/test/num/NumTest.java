package demo.test.num;

import com.alibaba.fastjson.JSON;
import demo.Menu;
import demo.MenuWrap;
import org.junit.Test;

import java.util.List;

public class NumTest {

    @Test
    public void test02(){
        int a = 0;
        int b = a++;
        System.out.println(a);
        System.out.println(b);
    }
    @Test
    public void test01(){
//        MenuA fakeA = new MenuA();
//        fakeA.setOrder("fake");
//        String s = JSON.toJSONString(fakeA);
//        Object temp = JSONObject.parse(s);
//        System.out.println(temp);
        String str = "{\n    \"menuFlatList\": [\n        {\n            \"icon\": \"QrcodeOutlined\",\n            \"id\": 1,\n            \"name\": \"码值管理\",\n            \"order\": \"1\",\n            \"page\": {\n                \"current\": 1,\n                \"pages\": 0,\n                \"records\": [],\n                \"size\": 10,\n                \"total\": 0\n            },\n            \"permission\": \"code\",\n            \"product\": 2,\n            \"show\": 1\n        },\n        {\n            \"href\": \"/cloud/codeValueManage/codeTypeSetting\",\n            \"id\": 2,\n            \"name\": \"码类设置\",\n            \"order\": \"2\",\n            \"page\": {\n                \"current\": 1,\n                \"pages\": 0,\n                \"records\": [],\n                \"size\": 10,\n                \"total\": 0\n            },\n            \"parent\": 1,\n            \"permission\": \"code:setting\",\n            \"product\": 2,\n            \"show\": 1\n        },\n        {\n            \"id\": 4,\n            \"name\": \"搜索\",\n            \"order\": \"1\",\n            \"page\": {\n                \"current\": 1,\n                \"pages\": 0,\n                \"records\": [],\n                \"size\": 10,\n                \"total\": 0\n            },\n            \"parent\": 2,\n            \"permission\": \"code:setting:search\",\n            \"product\": 2,\n            \"show\": 0\n        }]}";
        MenuWrap a = JSON.parseObject(str, MenuWrap.class);
//        System.out.println(a);
        List<Menu> menuFlatList = a.getMenuFlatList();
        for (Menu menu : menuFlatList) {
            System.out.println(menu);
        }
    }

}
