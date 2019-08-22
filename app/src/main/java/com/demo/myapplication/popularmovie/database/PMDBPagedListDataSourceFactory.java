package com.demo.myapplication.popularmovie.database;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class PMDBPagedListDataSourceFactory extends DataSource.Factory {
    private PMDBPagedListDataSource dataSource;
    private MutableLiveData<PMDBPagedListDataSource> listDataSourceLiveData;

    public PMDBPagedListDataSourceFactory(PopularMoviesDAO dao) {
        dataSource = new PMDBPagedListDataSource(dao);
        listDataSourceLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<PMDBPagedListDataSource> getListDataSourceLiveData() {
        return listDataSourceLiveData;
    }

    @NonNull
    @Override
    public DataSource create() {
        listDataSourceLiveData.postValue(dataSource);
        return dataSource;
    }
}
