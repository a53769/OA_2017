package com.example.shixi_a.myapplication.bean;

import java.util.List;

/**
 * Created by a5376 on 2017/6/15.
 */

public class RowsNoPage<T> {
    public boolean rt;
    public List<T> rows;

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
}
