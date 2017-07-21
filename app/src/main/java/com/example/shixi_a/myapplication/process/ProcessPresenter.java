package com.example.shixi_a.myapplication.process;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.bean.Process;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.process.ProcessRepository;

import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by Shixi-A on 2017/6/12.
 */

public class ProcessPresenter implements ProcessContract.Presenter {
    private final ProcessContract.View mProcessView;

    private ProcessRepository mProcessRepository;

    private boolean mFirstLoad = true;

    private Context context;

    private String taskId;

    private String stepId;

    private String time;


    public ProcessPresenter(@NonNull ProcessRepository processRepository, @NonNull ProcessFragment processFragment, Context applicationContext, String taskId, String time) {

        mProcessRepository = checkNotNull(processRepository, "processRepository cannot be null");

        mProcessView = checkNotNull(processFragment,"processFragment cannot be null");

        mProcessView.setPresenter(this);

        this.context = applicationContext;

        this.taskId = taskId;

        this.time = time;
    }

    @Override
    public void start() {
        loadProcesses(false);
    }

    @Override
    public void loadProcesses(boolean forceUpdate) {
        loadProcesses(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadProcesses(boolean forceupdate, final boolean showLoadingUI){
        if(showLoadingUI){
            mProcessView.setLoadingIndicator(true);
        }
        if(forceupdate){
//            mProcessRepository,refreshProcesses();
        }


        mProcessRepository.getProcesses(context,taskId, new GsonResponseHandler<RowsNoPage<Process>>(){

            @Override
            public void onSuccess(int statusCode, RowsNoPage<Process> response) {
                List<Process> processes;
                processes = response.rows;
                if(processes.size() == 0 || processes == null){
                    stepId = "0";
                } else {
                    stepId = processes.get(processes.size()-1).getStep_id();
                    time = processes.get(processes.size()-1).getEstimate_etime();
                }
                mProcessView.setLoadingIndicator(false);
                processProcesses(processes);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mProcessView.showLoadingProcessesError();
                mProcessView.setLoadingIndicator(false);
            }
        });
    }

    private void processProcesses(List<Process> processes) {
        if(processes.isEmpty()){
            mProcessView.showNoProcesses(processes);
        }
        else{
            mProcessView.showProcesses(processes);
        }
    }


    @Override
    public void addNewProcess() {
        mProcessView.showAddProcess(taskId,stepId,time);
    }

    @Override
    public void selectedProcess(@NonNull Process clickedProcess) {//目前没这功能
        checkNotNull(clickedProcess, "requestedProcess cannot be null!");
        mProcessView.showTaskDetail(clickedProcess.getStep_id());
    }

    @Override
    public void EditProcess(Process process) {
        mProcessView.showEditProcess(process);
    }

    @Override
    public void DelProcess(String step_id) {
        mProcessRepository.delProcess(context, step_id, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                ToastUtils.showShort(context,"删除成功");
                loadProcesses(false);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }
}
