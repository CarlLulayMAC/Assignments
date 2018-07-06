package com.example.admin.test6.view.school;

import android.util.Log;

import com.example.admin.test6.model.datasource.RemoteDataSource;
import com.example.admin.test6.model.response.SchoolResponse;
import com.example.admin.test6.view.base.BaseView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SchoolPresenter implements SchoolContract.Presenter {
    SchoolContract.SchoolView schoolView;
//    SchoolContract.DetailView detailView;
    private static final String TAG = SchoolPresenter.class.getSimpleName() + "_TAG";

    @Inject
    public SchoolPresenter() {
        Log.d(TAG, "SchoolPresenter: ");
    }


    @Override
    public void attachView(SchoolContract.SchoolView view) {
        this.schoolView = view;
    }

    @Override
    public void attachView(BaseView view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getSchools() {
        RemoteDataSource.getSchoolResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SchoolResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(List<SchoolResponse> schoolResponse) {
                        Log.d(TAG, "onNext: ");
                        ((SchoolContract.SchoolView) schoolView).onSchoolResultReceived(schoolResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    @Override
    public void onNavigateToDetail(SchoolResponse school) {
        schoolView.navigateToDetail(school);
    }
}
