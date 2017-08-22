package com.example.yuseonhan.mystudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YuseonHan on 2017. 8. 21..
 */

public class FragmentItemList extends BaseFragment {

    @BindView(R.id.recyclerView_exam_list)
    RecyclerView recyclerViewList;

    public static Fragment getFragment() {
        return new FragmentItemList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_item_list, container, false);

        unbinder = ButterKnife.bind(this, fragmentView);
        initView();

        ((MainActivity)getActivity()).setSubTitle(null);

        return fragmentView;
    }

    private void initView() {
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewList.setAdapter(new FragmentItemList.ListAdapter());
    }

    private class ListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(View.inflate(parent.getContext(), R.layout.item, null));
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.setPosition(position);
        }

        @Override
        public int getItemCount() {
            return StudyItem.values().length;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.main_item_title)
        TextView title;

        private int position;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setPosition(int position) {
            this.position = position;
            setText(StudyItem.values()[position].getTitleId());
        }

        public void setText(int strId) {
            if (title != null) {
                title.setText(strId);
            }
        }

        @OnClick(R.id.main_item_title)
        public void onItemClicked() {
            ((MainActivity)getActivity()).showFragment(StudyItem.values()[position].getFragment());
            ((MainActivity)getActivity()).setSubTitle(title.getText());
        }
    }
}
