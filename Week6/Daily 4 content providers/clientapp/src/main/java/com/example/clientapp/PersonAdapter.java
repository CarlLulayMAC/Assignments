package com.example.clientapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private final List<Person> personList;

    public PersonAdapter(List<Person> items) {
        personList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.tvName.setText(person.name);
        holder.tvAge.setText(person.age + "");
        holder.tvCity.setText(person.city);
        holder.tvEmail.setText(person.email);
        holder.tvPhone.setText(person.phone);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView tvName;
        public final TextView tvAge;
        public final TextView tvCity;
        public final TextView tvEmail;
        public final TextView tvPhone;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            tvName = view.findViewById(R.id.tvName);
            tvAge = view.findViewById(R.id.tvAge);
            tvCity = view.findViewById(R.id.tvCity);
            tvEmail = view.findViewById(R.id.tvEmail);
            tvPhone = view.findViewById(R.id.tvPhone);
        }
    }
}
