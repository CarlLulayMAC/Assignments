package com.example.admin.redditsearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.redditsearch.R;
import com.example.admin.redditsearch.model.PostData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    List<PostData> postsList;

    public PostAdapter(List<PostData> resultList) {
        postsList = resultList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_subreddit, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, final int position) {
        final PostData data = postsList.get(position);
        Picasso.get().load(data.getThumbnail()).into(holder.ivThumbnail);
        if (data.getTitle() != null)
            holder.tvTitle.setText(data.getTitle());
        if (data.getAuthor() != null)
            holder.tvAuthor.setText(data.getAuthor());
        holder.tvNumComments.setText(data.getNumComments().toString() + " Comments");
        holder.tvUpvotes.setText(data.getUps().toString() + "up");
        holder.tvDownvotes.setText(data.getDowns().toString() + "down");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareLink(data.getTitle(), data.getUrl(), holder.itemView.getContext());
            }
        });
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    private void shareLink(String title, String linkUrl, Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, title + ": " + linkUrl);
        intent.setType("text/plain");
        context.startActivity(intent);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivThumbnail;
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvNumComments;
        private TextView tvUpvotes;
        private TextView tvDownvotes;

        public PostViewHolder(View view) {
            super(view);
            ivThumbnail = view.findViewById(R.id.ivThumbnail);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvAuthor = view.findViewById(R.id.tvAuthor);
            tvNumComments = view.findViewById(R.id.tvNumComments);
            tvUpvotes = view.findViewById(R.id.tvUpvotes);
            tvDownvotes = view.findViewById(R.id.tvDownvotes);
        }
    }
}
