package com.example.shixi_a.myapplication.work.workTask.process;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Process;
import com.example.shixi_a.myapplication.work.workTask.processAddEdit.ProcessAddEditActivity;
import com.example.shixi_a.myapplication.work.workTask.processAddEdit.ProcessAddEditFragment;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static com.example.shixi_a.myapplication.R.id.process_list;

/**
 * Created by Shixi-A on 2017/6/12.
 */

public class ProcessFragment extends Fragment implements ProcessContract.View {

    private ProcessContract.Presenter mPresenter;

    private ProcessAdapter mListAdapter;

    private LinearLayout noProcess;

    public ProcessFragment(){}

    public static ProcessFragment newInstance(){
        return new ProcessFragment();
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mListAdapter = new ProcessAdapter(new ArrayList<Process>(0), mItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.process_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ListView listView = (ListView) root.findViewById(process_list);
        listView.setAdapter(mListAdapter);


        noProcess = (LinearLayout) root.findViewById(R.id.noProcess);
        TextView link = (TextView) noProcess.findViewById(R.id.noItemAdd);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewProcess();
            }
        });

        FloatingActionButton fb = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewProcess();
            }
        });
        if(getActivity().getIntent().hasExtra("detail")||getActivity().getIntent().hasExtra("refuse")){
            fb.setVisibility(View.GONE);
        }

        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.process_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        swipeRefreshLayout.setScrollUpChild(listView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadProcesses(false);
            }
        });

        return root;
    }

    @Override
    public void showProcesses(List<Process> processes) {
        mListAdapter.replaceData(processes);
        noProcess.setVisibility(View.GONE);
    }

    @Override
    public void showAddProcess(String taskId, String stepId,String time) {
        Intent intent = new Intent(this.getActivity(), ProcessAddEditActivity.class);
        intent.putExtra("taskId",taskId);
        intent.putExtra("step_id",stepId);
        intent.putExtra("time",time);
        startActivity(intent);
    }

    @Override
    public void showEditProcess(Process process) {
        Intent intent = new Intent(getActivity(),ProcessAddEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ProcessAddEditFragment.ARGUMENT_EDIT_PROCESS, process);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showTaskDetail(String step_id) {
        Intent intent = new Intent();
        intent.putExtra("step_id",step_id);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void showNoProcesses(List<Process> processes) {
        mListAdapter.replaceData(processes);
        noProcess.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingProcessesError() {
        showMessage(getString(R.string.loding_faile));
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.process_refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showTaskAdd() {
//        Intent intent = new Intent();
//        intent.putExtra("id", id);
//        intent.putExtra("name", name);
//        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();

    }

    @Override
    public void setPresenter(ProcessContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void showMessage(String message) {
//        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
        ToastUtils.showShort(getContext(),message);
    }

    private class ProcessAdapter extends BaseAdapter {

        private List<Process> mProcess;
        private ProcessItemListener mItemListener;

        public ProcessAdapter(List<Process> process, ProcessItemListener itemListener){
            setList(process);
            mItemListener = itemListener;
        }

        private void setList(List<Process> process) {
            mProcess = checkNotNull(process);
        }

        public void replaceData(List<Process> process) {
            setList(process);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mProcess.size();
        }

        @Override
        public Process getItem(int position) {
            return mProcess.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if(rowView == null){
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.list_item_process, parent, false);
            }

            final Process process = getItem(position);

            ImageView fiber = (ImageView) rowView.findViewById(R.id.fiber);

            TextView titleTV = (TextView) rowView.findViewById(R.id.process_goal);
            titleTV.setText(process.getTitle());
            if(process.getIs_done() != 1){
                titleTV.setTextColor(getResources().getColor(R.color.dark_grey));
                fiber.setImageResource(R.drawable.ic_fiber_d_24dp);
            }

            TextView methodTV = (TextView) rowView.findViewById(R.id.process_method);
            methodTV.setText(process.getDescription());

            TextView executorTV = (TextView) rowView.findViewById(R.id.executor_content);
            executorTV.setText(process.getUser_name());

            TextView descriptionTV = (TextView) rowView.findViewById(R.id.deadline_content);
            descriptionTV.setText(process.getEstimate_etime());
            if(getActivity().getIntent().hasExtra("refuse")) {
                rowView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mItemListener.onProcessClick(process);
                    }
                });
            }
            ImageView edit = (ImageView) rowView.findViewById(R.id.process_edit);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(hasOpt("edit",process.getOpts()) ){
                        mItemListener.onEditProcessClick(process);
                    }else{
                        showMessage("没有权限");
                    }
                }
            });
            ImageView delet = (ImageView) rowView.findViewById(R.id.process_delete);
            delet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(hasOpt("delete",process.getOpts())) {
                        mItemListener.onDelProcessClick(process);
                    }else{
                        showMessage("没有权限");
                    }
                }
            });
            return rowView;
        }



    }

    private boolean hasOpt(String opt, List<String>opts) {
        if(opts.contains(opt))
            return true;
        return false;
    }

    ProcessFragment.ProcessItemListener mItemListener = new ProcessFragment.ProcessItemListener() {
        @Override
        public void onProcessClick(Process clickedProcess) {
            mPresenter.selectedProcess(clickedProcess);
        }

        @Override
        public void onEditProcessClick(Process editProcess) {
            mPresenter.EditProcess(editProcess);
        }

        @Override
        public void onDelProcessClick(Process delProcess) {
            mPresenter.DelProcess(delProcess.getStep_id());
        }
    };

    public interface ProcessItemListener {

        void onProcessClick(Process clickedProcess);

        void onEditProcessClick(Process editProcess);

        void onDelProcessClick(Process delProcess);
    }

}
