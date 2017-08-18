package com.example.shixi_a.myapplication.home.work.workTask.projects;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Project;
import com.example.shixi_a.myapplication.home.work.workTask.projectAddEdit.ProjectAddEditActivity;
import com.example.shixi_a.myapplication.home.work.workTask.projectAddEdit.ProjectAddEditFragment;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static com.example.shixi_a.myapplication.util.StringUtils.SubTime;


/**
 * Created by Shixi-A on 2017/6/7.
 */

public class ProjectsFragment extends Fragment implements ProjectsContract.View {

    private ProjectsContract.Presenter mPresenter;

    private ProjectsAdapter mListAdapter;


    ///////////////////////////////////////////////////////

    public ProjectsFragment(){}//空的构造函数

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mListAdapter = new ProjectsAdapter(new ArrayList<Project>(0), mItemListener);//内部接口实现的事件监听

    }

    @Override
    public void setPresenter(ProjectsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.project_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        //set up projects view
        ListView listView = (ListView) root.findViewById(R.id.projects_list);
        listView.setAdapter(mListAdapter);

        FloatingActionButton fb = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewProject();

            }
        });

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.pro_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(listView);

        swipeRefreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mPresenter.loadProjects(false);
            }
        });

        return root;
    }


    @Override
    public void showProjects(List<Project> projects) {
        mListAdapter.replaceData(projects);
//        mNoTasksView.setVisibility(View.GONE);
    }

    @Override
    public void showAddProject() {
        Intent intent = new Intent(this.getActivity(), ProjectAddEditActivity.class);
        startActivity(intent);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {

        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.pro_refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showTaskAdd(String id, String name) {
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void showEditProject(Project editProject) {
        Intent intent = new Intent(getActivity(),ProjectAddEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ProjectAddEditFragment.ARGUMENT_EDIT_PROJECT, editProject);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showNoProjects() {
        //mNoTasksView.setVisibility(View.VISIBLE);
        showMessage(getString(R.string.loading_null));
    }

    @Override
    public void showLoadingProjectsError() {
        showMessage(getString(R.string.loading_projects_error));
    }

    private void showMessage(String message) {
//        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
        ToastUtils.showShort(getContext(),message);
    }

    private static class ProjectsAdapter extends BaseAdapter {

        private List<Project> mProjects;
        private ProjectItemListener mItemListener;

        public ProjectsAdapter(List<Project> projects, ProjectItemListener itemListener){
            setList(projects);
            mItemListener = itemListener;
        }

        private void setList(List<Project> projects) {
            mProjects = checkNotNull(projects);
        }

        public void replaceData(List<Project> projects) {
            setList(projects);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mProjects.size();
        }

        @Override
        public Project getItem(int position) {
            return mProjects.get(position);
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
                rowView = inflater.inflate(R.layout.list_item_project, parent, false);
            }

            final Project project = getItem(position);

            TextView titleTV = (TextView) rowView.findViewById(R.id.pro_name);
            titleTV.setText(project.getProject_name());

            TextView descriptionTV = (TextView) rowView.findViewById(R.id.timestamp);
            descriptionTV.setText(SubTime(project.getIn_time()) + " 创建");

            ImageView editProject = (ImageView) rowView.findViewById(R.id.pro_edit);
            editProject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onEditProjectClick(project);
                }
            });

            ImageView deletProject = (ImageView) rowView.findViewById(R.id.pro_delete);
            deletProject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onDelProjectClick(project);
                }
            });

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onProjectClick(project);
                }
            });

            return rowView;
        }


    }


    /**
     * Listener for clicks on projects in the ListView.
     */
    ProjectsFragment.ProjectItemListener mItemListener = new ProjectsFragment.ProjectItemListener() {
        @Override
        public void onProjectClick(Project clickedProject) {
            mPresenter.selectedProject(clickedProject);
        }

        @Override
        public void onEditProjectClick(Project editProject) {
            mPresenter.EditProject(editProject);
        }

        @Override
        public void onDelProjectClick(Project delProject) {
            mPresenter.deletProject(delProject.getId());
        }
    };

    public interface ProjectItemListener {

        void onProjectClick(Project clickedProject);

        void onEditProjectClick(Project editProject);

        void onDelProjectClick(Project delProject);
    }
}
