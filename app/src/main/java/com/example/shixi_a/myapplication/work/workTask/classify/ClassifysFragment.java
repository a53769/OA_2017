package com.example.shixi_a.myapplication.work.workTask.classify;

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
import com.example.shixi_a.myapplication.bean.Classify;
import com.example.shixi_a.myapplication.work.workTask.classifyAddEdit.ClassifyAddEditActivity;
import com.example.shixi_a.myapplication.work.workTask.classifyAddEdit.ClassifyAddEditFragment;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static com.example.shixi_a.myapplication.util.StringUtils.SubTime;

/**
 * Created by Shixi-A on 2017/6/9.
 */

public class ClassifysFragment extends Fragment implements ClassifysContract.View {

    private ClassifysContract.Presenter mPresenter;

    private ClassifysAdapter mListAdapter;

    public ClassifysFragment(){}//空的构造函数

    public static ClassifysFragment newInstance() {
        return new ClassifysFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mListAdapter = new ClassifysAdapter(new ArrayList<Classify>(0), mItemListener);//内部接口实现的事件监听

    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.classify_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        //set up projects view
        ListView listView = (ListView) root.findViewById(R.id.classifys_list);
        listView.setAdapter(mListAdapter);

        FloatingActionButton fb = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewClassify();

            }
        });

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.cla_refresh_layout);
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

                mPresenter.loadClassifys(false);
            }
        });


        return root;
    }

    @Override
    public void showClassifys(List<Classify> classifys) {
        mListAdapter.replaceData(classifys);
    }

    @Override
    public void showAddClassify() {
        Intent intent = new Intent(this.getActivity(), ClassifyAddEditActivity.class);
        startActivity(intent);
    }

    @Override
    public void showNoClassifys() {
        showMessage(getString(R.string.loading_null));
    }

    @Override
    public void showLoadingClassifysError() {
        showMessage(getString(R.string.loading_classify_error));
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.cla_refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showTaskAdd(String name,String id) {
        Intent intent = new Intent();
        intent.putExtra("name", name);
        intent.putExtra("id",id);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void showEditClassify(Classify editClassify) {
        Intent intent = new Intent(getActivity(),ClassifyAddEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ClassifyAddEditFragment.ARGUMENT_EDIT_CLA, editClassify);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void setPresenter(ClassifysContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    private void showMessage(String message) {
        ToastUtils.showShort(getContext(),message);
    }

    private class ClassifysAdapter extends BaseAdapter {

        private List<Classify> mClassifys;
        private ClassifyItemListener mItemListener;

        public ClassifysAdapter(List<Classify> classifys, ClassifyItemListener itemListener){
            setList(classifys);
            mItemListener = itemListener;
        }

        private void setList(List<Classify> classifys) {
            mClassifys = checkNotNull(classifys);
        }

        public void replaceData(List<Classify> classifys) {
            setList(classifys);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mClassifys.size();
        }

        @Override
        public Classify getItem(int position) {
            return mClassifys.get(position);
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
                rowView = inflater.inflate(R.layout.list_item_classify, parent, false);
            }

            final Classify classify = getItem(position);

            TextView titleTV = (TextView) rowView.findViewById(R.id.cla_list_name);
            titleTV.setText(classify.getTarget_name());

            TextView descriptionTV = (TextView) rowView.findViewById(R.id.timestamp);
            descriptionTV.setText(SubTime(classify.getIn_time()) + " 创建");

            ImageView editCla = (ImageView) rowView.findViewById(R.id.cla_edit);
            editCla.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onEditClassifyClick(classify);
                }
            });

            ImageView delCla = (ImageView) rowView.findViewById(R.id.cla_delete);
            delCla.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onDelClassifyClick(classify);
                }
            });

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onClassifyClick(classify);
                }
            });

            return rowView;
        }


    }


    /**
     * Listener for clicks on projects in the ListView.
     */
    ClassifysFragment.ClassifyItemListener mItemListener = new ClassifysFragment.ClassifyItemListener() {
        @Override
        public void onClassifyClick(Classify clickedClassify) {
            mPresenter.selectedClassify(clickedClassify);
        }

        @Override
        public void onEditClassifyClick(Classify editClassify) {
            mPresenter.EditClassify(editClassify);
        }

        @Override
        public void onDelClassifyClick(Classify delClassify) {
            mPresenter.DelClassify(delClassify.getId());
        }
    };

    public interface ClassifyItemListener {

        void onClassifyClick(Classify clickedClassify);

        void onEditClassifyClick(Classify editClassify);

        void onDelClassifyClick(Classify delClassify);
    }
}
