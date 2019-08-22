package com.demo.myapplication.popularmovie.ui;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.myapplication.R;
import com.demo.myapplication.network.response.Movies;
import com.demo.myapplication.popularmovie.viewmodel.PopularMoviesViewModel;

public class PopularMoviesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private PopularMoviesViewModel viewModel;
    private LiveData<PagedList<Movies>> pagedListLiveData;
    private PopularMoviesAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poular_movies);

        //progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        setUpRecyclerView();

        viewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);
        pagedListLiveData = viewModel.getMovies();
        pagedListLiveData.observe(this, new Observer<PagedList<Movies>>() {
            @Override
            public void onChanged(PagedList<Movies> movies) {
                adapter.submitList(movies);
            }
        });
    }

    private void setUpRecyclerView() {
        adapter = new PopularMoviesAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
    }
}
