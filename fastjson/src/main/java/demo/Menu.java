package demo;

import com.alibaba.fastjson.annotation.JSONField;

public class Menu extends Base {

    @JSONField(name = "order")
    private String sort;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        System.out.println(super.toString());
        return "Menu{" +
                "sort='" + sort + '\'' +
                '}';
    }
}
