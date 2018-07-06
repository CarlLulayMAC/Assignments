package com.example.admin.test6.di.module;

import com.example.admin.test6.view.school.SchoolActivity;
import com.example.admin.test6.view.school.SchoolPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SchoolModule {

    @Provides
    SchoolPresenter provideSchoolPresenter() {
        return new SchoolPresenter();
    }

}
