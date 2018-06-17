package com.example.admin.googlebookshw.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.googlebookshw.R;
import com.example.admin.googlebookshw.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// API KEY: AIzaSyBN6GpgnKJw39rsECTUHtCsvhcq59RFUSE
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private ArrayList<Book> dataList;

    public BookAdapter(ArrayList<Book> dataList) {
        this.dataList = dataList;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        if (dataList.get(position).getTitle() != null)
            holder.tvBookTitle.setText(dataList.get(position).getTitle());
        else
            holder.tvBookTitle.setText("Title Unknown");
        if (dataList.get(position).getAuthors() != null)
            holder.tvBookAuthor.setText(dataList.get(position).getAuthors()[0]);
        else
            holder.tvBookAuthor.setText("Unknown Author");
        if (dataList.get(position).getThumbnailUrl() != null)
            Picasso.get().load(dataList.get(position).getThumbnailUrl()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        TextView tvBookTitle, tvBookAuthor;
        ImageView thumbnail;

        BookViewHolder(View itemView) {
            super(itemView);

            tvBookTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvBookAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            thumbnail = itemView.findViewById(R.id.imageThumbnail);
        }
    }
}
