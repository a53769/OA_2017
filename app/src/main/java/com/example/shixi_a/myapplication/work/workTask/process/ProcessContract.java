package com.example.shixi_a.myapplication.work.workTask.process;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Process;

import java.util.List;

/**
 * Created by Shixi-A on 2017/6/12.
 */

public interface ProcessContract {

    interface View extends BaseView<Presenter> {

        void showProcesses(List<Process> processes);

        void showAddProcess(String id, String taskId, String stepId);

        void showNoProcesses(List<Process> processes);

        void showLoadingProcessesError();

        void setLoadingIndicator(final boolean active);

        void showTaskAdd();


        void showEditProcess(Process process);

        void showTaskDetail(String step_id);
    }

    interface Presenter extends BasePresenter {

        void loadProcesses(boolean forceUpdate);

        void addNewProcess();

        void selectedProcess(Process clickedProcess);

        void EditProcess(Process editProcess);

        void DelProcess(String step_id);
    }
}
