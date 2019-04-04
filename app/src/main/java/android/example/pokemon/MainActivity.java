package android.example.pokemon;

import android.app.Activity;
import android.content.Intent;
import android.example.pokemon.model.Pokemon;
import android.example.pokemon.model.RestPokemonResponse;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar loader;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        loader = findViewById(R.id.loader_main_activity);

        controller = new MainController(this);
        controller.onCreate();
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView

    }

    public void onItemClicked(Pokemon itemClicked) {

    }

    public void showLoader() {

        loader.setVisibility(View.VISIBLE);
    }

    public void hideLoader() {

        loader.setVisibility(View.GONE);
    }

    public void showList(List<Pokemon> list) {

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //define an adapter
        mAdapter = new MyAdapter(list);
        recyclerView.setAdapter(mAdapter);
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}