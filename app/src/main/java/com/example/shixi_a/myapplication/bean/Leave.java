package com.example.shixi_a.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a5376 on 2017/7/18.
 */

public class Leave implements Serializable {
    String id;
    String admin_id;
    String admin_name;
    String handover_aid;
    String handover_name;
    String need_modify;
    String off_start;
    String off_end;
    String sort;
    String sort_show;
    String reason;
    String offset_start;
    String offset_end;
    String offset_memo;
    String status;
    String status_show;
    String is_active;
    String create_time;

    String opt;
    List<Log> logs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getHandover_aid() {
        return handover_aid;
    }

    public void setHandover_aid(String handover_aid) {
        this.handover_aid = handover_aid;
    }

    public String getHandover_name() {
        return handover_name;
    }

    public void setHandover_name(String handover_name) {
        this.handover_name = handover_name;
    }

    public String getNeed_modify() {
        return need_modify;
    }

    public void setNeed_modify(String need_modify) {
        this.need_modify = need_modify;
    }

    public String getOff_start() {
        return off_start;
    }

    public void setOff_start(String off_start) {
        this.off_start = off_start;
    }

    public String getOff_end() {
        return off_end;
    }

    public void setOff_end(String off_end) {
        this.off_end = off_end;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort_show() {
        return sort_show;
    }

    public void setSort_show(String sort_show) {
        this.sort_show = sort_show;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOffset_start() {
        return offset_start;
    }

    public void setOffset_start(String offset_start) {
        this.offset_start = offset_start;
    }

    public String getOffset_end() {
        return offset_end;
    }

    public void setOffset_end(String offset_end) {
        this.offset_end = offset_end;
    }

    public String getOffset_memo() {
        return offset_memo;
    }

    public void setOffset_memo(String offset_memo) {
        this.offset_memo = offset_memo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public class Log implements Serializable{
        String id;
        String admin_id;
        String off_id;
        String action;
        String memo;
        String acmemo;
        String actime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public String getOff_id() {
            return off_id;
        }

        public void setOff_id(String off_id) {
            this.off_id = off_id;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getAcmemo() {
            return acmemo;
        }

        public void setAcmemo(String acmemo) {
            this.acmemo = acmemo;
        }

        public String getActime() {
            return actime;
        }

        public void setActime(String actime) {
            this.actime = actime;
        }
    }
}
