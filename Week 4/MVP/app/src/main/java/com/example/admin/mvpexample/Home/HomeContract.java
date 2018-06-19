package com.example.admin.mvpexample.Home;

import com.example.admin.mvpexample.base.BasePresenter;
import com.example.admin.mvpexample.entities.Result;

import java.util.List;

public interface HomeContract {
    //Defines the roles and responsibilities of the view and presenter
    //This pattern of interfaces is just one way of doing MVP. It is not an essential part of the pattern
    interface View {
        void showResult(List<Result> results);
        void showError();
        void navigateToDetail(Result result);

    }

    interface Presenter extends BasePresenter {
        void getResult(int numResults);
        void onNavigateToDetail(Result result);
    }
}
