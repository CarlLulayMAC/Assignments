package com.example.admin.test6.view.school;

import android.view.View;

import com.example.admin.test6.model.response.SATResponse;
import com.example.admin.test6.model.response.SchoolResponse;
import com.example.admin.test6.view.base.BasePresenter;
import com.example.admin.test6.view.base.BaseView;

import java.util.List;

public interface SchoolContract {

    interface SchoolView extends BaseView {

        void onSchoolResultReceived(List<SchoolResponse> schools);

        void navigateToDetail(SchoolResponse school);

    }

    interface DetailView extends BaseView {

        void onSATResultReceived(List<SATResponse> satScores);

    }

    interface Presenter extends BasePresenter<BaseView> {

        void attachView(SchoolView view);

        void getSchools();

        void onNavigateToDetail(SchoolResponse school);

    }
}
