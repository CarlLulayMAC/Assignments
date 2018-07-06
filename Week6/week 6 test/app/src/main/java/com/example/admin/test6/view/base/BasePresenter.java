package com.example.admin.test6.view.base;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}