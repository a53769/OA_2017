package com.example.shixi_a.myapplication.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Shixi-A on 2017/6/5.
 */

public class Rows<T>{
    public boolean rt;
    public List<T> rows;
    public Page page;

    public boolean isRt() {
        return rt;
    }

    public void setRt(boolean rt) {
        this.rt = rt;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public static class Page{
        String total;
        String pn;
        String pages;
        String np;
    }
}
