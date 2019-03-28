package android.example.pokemon;

import android.example.pokemon.model.Pokemon;
import android.example.pokemon.model.RestPokemonResponse;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {


    private final MainActivity mainActivity;

    private static MainController instance = null;

    public static MainController getInstance(MainActivity mainActivity){
        if (instance == null) {

            instance = new MainController(mainActivity);
        }

        return instance;
    }

    public MainController(MainActivity mainActivity) {

        this.mainActivity = mainActivity;

    }

    public void onCreate() {

        mainActivity.showLoader();


        //On crée un objet Gson
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //On crée un objet retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //On crée notre interface PokemonRestApi
        PokemonRestApi pokemonRestApi = retrofit.create(PokemonRestApi.class);

        //On récupère un objet call
        Call<RestPokemonResponse> call = pokemonRestApi.getListPokemon();

        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                RestPokemonResponse restPokemonResponse = response.body();
                List<Pokemon> listPokemon = restPokemonResponse.getResults();
                mainActivity.showList(listPokemon);
                mainActivity.hideLoader();
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                Log.d("Erreur", "API ERROR");
            }
        });
    }
}
