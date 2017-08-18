package com.example.shixi_a.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a5376 on 2017/7/7.
 */

public class AttendanceState implements Serializable{
    public String adm_out_c;
    public String oa_off_c;
    public String oa_off_d;
    public List<LeaveInfo> oa_off_s;
    public String check_local_days;
    public String check_other_days;
    public String check_need_days;
    public String check_late_days;
    public String check_late_time;
    public String check_early_days;
    public String check_early_time;

    public String getAdm_out_c() {
        return adm_out_c;
    }

    public void setAdm_out_c(String adm_out_c) {
        this.adm_out_c = adm_out_c;
    }

    public String getOa_off_c() {
        return oa_off_c;
    }

    public void setOa_off_c(String oa_off_c) {
        this.oa_off_c = oa_off_c;
    }

    public String getOa_off_d() {
        return oa_off_d;
    }

    public void setOa_off_d(String oa_off_d) {
        this.oa_off_d = oa_off_d;
    }

    public List<LeaveInfo> getOa_off_s() {
        return oa_off_s;
    }

    public void setOa_off_s(List<LeaveInfo> oa_off_s) {
        this.oa_off_s = oa_off_s;
    }

    public String getCheck_local_days() {
        return check_local_days;
    }

    public void setCheck_local_days(String check_local_days) {
        this.check_local_days = check_local_days;
    }

    public String getCheck_other_days() {
        return check_other_days;
    }

    public void setCheck_other_days(String check_other_days) {
        this.check_other_days = check_other_days;
    }

    public String getCheck_need_days() {
        return check_need_days;
    }

    public void setCheck_need_days(String check_need_days) {
        this.check_need_days = check_need_days;
    }

    public String getCheck_late_days() {
        return check_late_days;
    }

    public void setCheck_late_days(String check_late_days) {
        this.check_late_days = check_late_days;
    }

    public String getCheck_late_time() {
        return check_late_time;
    }

    public void setCheck_late_time(String check_late_time) {
        this.check_late_time = check_late_time;
    }

    public String getCheck_early_days() {
        return check_early_days;
    }

    public void setCheck_early_days(String check_early_days) {
        this.check_early_days = check_early_days;
    }

    public String getCheck_early_time() {
        return check_early_time;
    }

    public void setCheck_early_time(String check_early_time) {
        this.check_early_time = check_early_time;
    }

    public class LeaveInfo implements Serializable{
        String id;
        String admin_id;
        String handover_aid;
        String need_modify;
        String off_start;
        String off_end;
        String sort;
        String reason;
        String offset_start;
        String offset_end;
        String offset_memo;
        String status;
        String is_active;
        String create_time;

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

        public String getHandover_aid() {
            return handover_aid;
        }

        public void setHandover_aid(String handover_aid) {
            this.handover_aid = handover_aid;
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
    }
}
