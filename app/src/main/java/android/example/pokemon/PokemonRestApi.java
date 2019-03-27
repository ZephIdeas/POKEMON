package android.example.pokemon;

import android.example.pokemon.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonRestApi {

    @GET("pokemon")
    Call<RestPokemonResponse> getListPokemon();
}
