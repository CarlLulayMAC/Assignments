package com.example.admin.test6.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.test6.R;
import com.example.admin.test6.di.component.DaggerSchoolComponent;
import com.example.admin.test6.model.response.SchoolResponse;
import com.example.admin.test6.view.school.SchoolActivity;
import com.example.admin.test6.view.school.SchoolPresenter;

import java.util.List;


public class SchoolRecyclerViewAdapter extends RecyclerView.Adapter<SchoolRecyclerViewAdapter.ViewHolder> {

    SchoolPresenter schoolPresenter;
    private final List<SchoolResponse> schoolList;
    private static final String TAG = SchoolRecyclerViewAdapter.class.getSimpleName() + "_TAG";

    public SchoolRecyclerViewAdapter(List<SchoolResponse> schools, SchoolPresenter presenter) {
        schoolList = schools;
        schoolPresenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_school, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final SchoolResponse school = schoolList.get(position);
        holder.school = school;
        holder.tvName.setText(school.getSchoolName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                schoolPresenter.onNavigateToDetail(school);
            }
        });
    }

    @Override
    public int getItemCount() {
        return schoolList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView tvName;
        public SchoolResponse school;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvName = view.findViewById(R.id.tvName);
        }
    }
}
