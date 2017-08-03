package com.example.shixi_a.myapplication.bean;

import java.util.List;

/**
 * Created by a5376 on 2017/8/1.
 */

public class FinanceType {
    boolean rt;
    List<Sort> sort_arr;

    public boolean isRt() {
        return rt;
    }

    public void setRt(boolean rt) {
        this.rt = rt;
    }

    public List<Sort> getSort_arr() {
        return sort_arr;
    }

    public void setSort_arr(List<Sort> sort_arr) {
        this.sort_arr = sort_arr;
    }

    public class Sort{
        Info info;
        List<Child> children;

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public List<Child> getChildren() {
            return children;
        }

        public void setChildren(List<Child> children) {
            this.children = children;
        }

        public class Info{
            String name;
            String is_public;
            String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIs_public() {
                return is_public;
            }

            public void setIs_public(String is_public) {
                this.is_public = is_public;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
        public class Child{
            String subid;
            String subname;

            public String getSubid() {
                return subid;
            }

            public void setSubid(String subid) {
                this.subid = subid;
            }

            public String getSubname() {
                return subname;
            }

            public void setSubname(String subname) {
                this.subname = subname;
            }
        }
    }
}
