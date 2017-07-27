package com.example.shixi_a.myapplication.model;

/**
 * Created by Shixi-A on 2017/6/2.
 */

public interface IBaseModel {
    // 服务器地址
    String BASEURL			= "http://test.api.xmf.com";
    String BASE             = "https://test2.api.cdnunion.com";

//    String BASEURL			= "http://api.vathome.cn";
//    String BASE             = "https://app.cdnunion.com";

    String LOGIN			    = "/admin/index/login";//登录
    String LOGOUT			    = "/admin/index/logout";//登出

    String ADDPROJECT        = "/user/task/project";//添加项目
    String EDITPROJECT       = "/user/task/project";// 项目信息修改
    String DELETEPROJECT     = "/user/task/project";// 删除项目
    String GETPROJECTLIST    = "/user/task/project";// 获取项目列表

    String ADDTARGETTYPE     = "/user/task/target_type";// 添加目标归类
    String EDITTARGETTYPE    = "/user/task/target_type";// 修改目标归类
    String DELETETARGETTYPE  = "/user/task/target_type";// 删除目标归类
    String GETTARGETTYPELIST = "/user/task/target_type";// 获取目标归类列表

    String addUsre              = "/user/user/index";// 添加用户
    String GETLISTUSER          = "/admin/index/index";// 获取所有的用户列表
    String getDepart            = "/user/user/group";// 获取部门
    String GETUPARTANDUSERLIST = "/user/user/group";// 获取下级部门及其用户列表、获取部门层级关系

    String GETPRIORITY         = "/user/task/index";// 获取优先级

    String GETPROCESSLIST      = "/user/task/flow";// 获取流程列表
    String getProcessStateList = "/user/task/flow";// 获取流程状态列表
    String getProcessDetail     = "/user/task/flow";// 获取流程详情
    String HANDLEPROCESS        = "/user/task/flow";// 执行人流程处理
    String ADDPROCESS           = "/user/task/flow";// 添加流程
    String EDITPROCESS          = "/user/task/flow";// 修改流程
    String DELETEPROCESS        = "/user/task/flow";// 删除流程

    String ADDTASK              = "/user/task/index";// 添加任务
    String UPTASK               = "/user/task/index";// 发布任务
    String EDITTASK             = "/user/task/index";// 修改任务
    String CHECKTASK            = "/user/task/index";// 任务验收
    String DELETETASK           = "/user/task/index";// 取消任务
    String ASSESSTASK           = "/user/task/index";// 任务评分
    String GETASSESSINFO        = "/user/task/index";// 获取评价信息

    String getTaskStateList     = "/user/task/index";// 获取任务状态列表
    String GETTASKLOGLIST       = "/user/task/index";// 获取任务日志列表
    String GETTASKDETAIL        = "/user/task/index";// 获取任务详情
    String GETTASKLIST          = "/user/task/index";// 获取任务列表
    String UPDATETASKSTATE      = "/user/task/flow";// 任务状态更新
    String getTaskLog           = "/user/task/index";// 获取任务日志数据
    String GETMILESTONELIST     = "/user/task/milestone";//获取里程碑列表

    String ATTENDANCELOCAATION  ="/admin/my/now_time";//获取wifi范围
    String ATTENDANCELIST       ="/admin/my/attendance_rows";//获取考勤打卡事件列表
    String ATTENDANCEADD        ="/admin/my/attendance_add";//添加考勤打卡事件
    String ATTENDANCESTATE      ="/admin/me/attendance_stat";//考勤状态

    String USERINFO             ="/admin/index/info";//获取用户信息

    String GETMESSAGELIST       ="/admin/event/pending";//获取消息列表

    String GETEGRESSLIST        ="/admin/my/out";//获取外出列表
    String ADDOUT               ="/admin/my/out";//添加外出申请
    String GETEGRESS            ="/admin/my/out";//获取外出详情
    String DELEGRESS            ="/admin/my/out";//取消外出申请
    String EDITEGRESS           ="/admin/my/out";//修改外出申请
    String CHECKEGRESS          ="/admin/event/add";//添加任务检查

    String GETLEAVELIST         ="/admin/my/off";//获取请假列表
    String GETLEAVEDETAIL       ="/admin/my/off";//获取请假详情
    String GETLEAVETYPE         ="/admin/my/off";//获取请假类型
    String APPLYLEAVE           ="/admin/my/off";//添加请假记录
    String CAMCELLEAVE          ="/admin/my/off";//取消请假申请
    String AUDITLEAVE           ="/admin/my/off";//审批请假
    String EDITLEAVE            ="/admin/my/off";//编辑请假


    String GETREIMBURSE         ="/admin/my/reimburse";//获取报销列表
    String GETREIMBURSEDETAIL   ="/admin/my/reimburse";//获取报销详情
    String GETREIMBURSETYPE     ="/admin/my/reimburse";//获取报销类型列表
    String APPLYREIMBURSE       ="/admin/my/reimburse";//申请报销


    String UPLOADTOKEN           = "/admin/index/upload_token";// 关联token
}
