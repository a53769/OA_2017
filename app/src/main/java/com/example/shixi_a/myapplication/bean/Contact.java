package com.example.shixi_a.myapplication.bean;

import java.util.List;

/**
 * Created by a5376 on 2017/6/15.
 */

public class Contact {
    public String group_id;
    public String group_name;
    public List<User> users;
    private boolean isChecked;//复选标识位

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }

    public boolean getChecked(){
        return this.isChecked;
    }

    public void toggle(){
        this.isChecked = !this.isChecked;
    }
    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static class User{
        public String user_id;
        public String user_name;
        public String group_id;
        public String group_name;
        public String level;
        private boolean isChecked;


        public void setChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }

        public boolean getChecked() {
            return this.isChecked;
        }

        public void toggle() {
            this.isChecked = !this.isChecked;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }



}
