package com.example.admin.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.myapplication.ListFragment.OnListFragmentInteractionListener;
import com.example.admin.myapplication.R;
import com.example.admin.myapplication.model.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private final List<Person> personList;
    private final OnListFragmentInteractionListener mListener;
    private static final int ODD_AGE = 1;
    private static final int EVEN_AGE = 2;

    public PersonAdapter(List<Person> items, OnListFragmentInteractionListener listener) {
        personList = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case (ODD_AGE):
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_person_odd_age, parent, false);
                break;
            case (EVEN_AGE):
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_person_even_age, parent, false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_person_odd_age, parent, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.person = personList.get(position);
        holder.tvName.setText(personList.get(position).name);
        holder.tvAge.setText(personList.get(position).age + "");
        holder.tvCity.setText(personList.get(position).city);

//        holder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.person);
//                }
//            }
//        });
    }

    @Override
    public int getItemViewType(int position) {
        if (personList.get(position).age % 2 == 1)
            return ODD_AGE;
        else
            return EVEN_AGE;
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
        public Person person;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvAge = (TextView) view.findViewById(R.id.tvAge);
            tvCity = (TextView) view.findViewById(R.id.tvCity);
        }
    }
}
