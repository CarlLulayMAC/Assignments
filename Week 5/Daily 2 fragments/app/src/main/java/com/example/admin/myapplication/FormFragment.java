package com.example.admin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.myapplication.activity.GithubActivity;


public class FormFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText etName;
    private EditText etAge;
    private EditText etCity;
    private Button submitBtn;
    private Button goToGithubBtn;

    public FormFragment() {
        // Required empty public constructor
    }


    public static FormFragment newInstance() {
        FormFragment fragment = new FormFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etName = view.findViewById(R.id.etName);
        etAge = view.findViewById(R.id.etAge);
        etCity = view.findViewById(R.id.etCity);
        submitBtn = view.findViewById(R.id.btnSubmit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        goToGithubBtn = view.findViewById(R.id.btnGoToGithubActivity);
        goToGithubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GithubActivity.class);
                startActivity(intent);
            }
        });
    }

    private void submit() {
        String name = etName.getText().toString();
        String ageString = etAge.getText().toString();
        String city = etCity.getText().toString();
        name = (name.equals("")) ? "No Name" : name;
        int age = (ageString.equals("")) ? 0 : Integer.parseInt(ageString);
        city = (city.equals("")) ? "unknown city" : city;
        mListener.onSubmit(name, age, city);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onSubmit(String name, int age, String city);
    }
}
