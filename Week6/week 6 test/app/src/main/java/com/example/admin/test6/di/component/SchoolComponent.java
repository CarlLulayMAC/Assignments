package com.example.admin.test6.di.component;

import com.example.admin.test6.adapter.SchoolRecyclerViewAdapter;
import com.example.admin.test6.di.module.SchoolModule;
import com.example.admin.test6.view.DetailActivity;
import com.example.admin.test6.view.school.SchoolActivity;

import dagger.Component;

@Component(modules = SchoolModule.class)
public interface SchoolComponent {

    void inject(SchoolActivity schoolActivity);

    void inject(DetailActivity detailActivity);
}
