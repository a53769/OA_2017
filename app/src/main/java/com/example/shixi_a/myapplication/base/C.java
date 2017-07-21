package com.example.shixi_a.myapplication.base;

/**
 * Created by Shixi-A on 2017/5/25.
 */

public final class C {

    public static final class api {
        // 建议换成你的内网IP的地址
        public static final String baseUrl			= "http://203.156.198.179";//test.app.cdnunion.com
        public static final String baseURL			= "http://203.156.198.118";//test.api.vathome.cn
        public static final String base             = "https://app.cdnunion.com";

        public static final String login			    = "/admin/index/login";//登录
        public static final String logout			    = "/admin/index/logout";//登出

        public static final String addProject        = "user/task/project";//添加项目
        public static final String editProject       = "/user/task/project";// 项目信息修改
        public static final String deleteProject     = "/user/task/project";// 删除项目
        public static final String getProjectList    = "/user/task/project";// 获取项目列表

        public static final String addTargetType     = "/user/task/target_type";// 添加目标归类
        public static final String editTargetType    = "/user/task/target_type";// 修改目标归类
        public static final String deleteTargetType  = "/user/task/target_type";// 删除目标归类
        public static final String getListTargetType = "/user/task/target_type";// 获取目标归类列表

        public static final String addUsre              = "/user/user/index";// 添加用户
        public static final String getListUser          = "/user/user/index";// 获取所有的用户列表
        public static final String getDepart            = "/user/user/group";// 获取部门
        public static final String getUpartAndUserList = "/user/user/group";// 获取下级部门及其用户列表、获取部门层级关系

        public static final String getPriority         = "/user/task/index";// 获取优先级

        public static final String getProcessList      = "/user/task/flow";// 获取流程列表
        public static final String getProcessStateList = "/user/task/flow";// 获取流程状态列表
        public static final String getProcessDetail     = "/user/task/flow";// 获取流程详情
        public static final String handleProcess        = "/user/task/flow";// 执行人流程处理
        public static final String addProcess           = "/user/task/flow";// 添加流程
        public static final String editProcess          = "/user/task/flow";// 修改流程
        public static final String deleteProcess        = "/user/task/flow";// 删除流程

        public static final String addTask              = "/user/task/index";// 添加任务
        public static final String upTask               = "/user/task/index";// 发布任务
        public static final String editTask             = "/user/task/index";// 修改任务
        public static final String checkTask            = "/user/task/index";// 任务验收
        public static final String deleteTask           = "/user/task/index";// 取消任务
        public static final String assessTask           = "/user/task/index";// 任务评分
        public static final String getAssessInfo        = "/user/task/index";// 获取评价信息

        public static final String getTaskStateList     = "/user/task/index";// 获取任务状态列表
        public static final String getTaskLogList       = "/task/index";// 获取任务日志列表
        public static final String getTaskDetail        = "/user/task/index";// 获取任务详情
        public static final String getTaskList          = "/user/task/index";// 获取任务列表
        public static final String updateTaskState      = "/user/task/flow";// 任务状态更新
        public static final String getTaskLog           = "/user/task/index";// 获取任务日志数据



        public static final String refreshApp = "";// 更新app
    }
}
