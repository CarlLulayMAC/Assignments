package com.example.admin.mvpexample.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.mvpexample.Home.HomePresenter;
import com.example.admin.mvpexample.R;
import com.example.admin.mvpexample.entities.Result;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.UserViewHolder> {
    List<Result> results;
    HomePresenter presenter;

    public HomeAdapter(List<Result> results, HomePresenter presenter) {
        this.presenter = presenter;
        this.results = results;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        String name = results.get(position).getName().getFirst() + " "
                + results.get(position).getName().getLast();
        holder.tvName.setText(name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onNavigateToDetail(results.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
