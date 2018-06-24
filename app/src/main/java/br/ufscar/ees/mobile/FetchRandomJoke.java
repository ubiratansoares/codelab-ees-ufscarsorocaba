package br.ufscar.ees.mobile;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by @ubiratanfsoares
 */

public class FetchRandomJoke {

    private static final String API_URL = "https://api.chucknorris.io/jokes/random";

    private final OkHttpClient client = new OkHttpClient();

    interface Listener {

        void onSuccess(Joke randomJoke);

        void onFail();
    }

    public void now(final Listener listener) {

        Request requestRandomJoke = new Request.Builder()
            .url(API_URL)
            .build();

        client
            .newCall(requestRandomJoke)
            .enqueue(
                new Callback() {

                    @Override public void onFailure(Call call, IOException e) {
                        listener.onFail();
                    }

                    @Override public void onResponse(Call call, Response response) {
                        try {
                            String json = response.body().string();
                            Joke parsed = parseJoke(json);
                            listener.onSuccess(parsed);
                        } catch (IOException | JSONException e) {
                            listener.onFail();
                        }
                    }
                }
            );
    }

    @NonNull private Joke parseJoke(String rawJsonString) throws JSONException {

        /* JSON sample
            {
              "icon_url" : "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
              "id" : "-_m_kKLLRD-DIBQNN4K8BQ",
              "url" : "https://api.chucknorris.io/jokes/-_m_kKLLRD-DIBQNN4K8BQ"
              "value" : "Chuck Norris knocked up his babysitter when he was 8"
            }
        */

        JSONObject jsonStructure = new JSONObject(rawJsonString);
        String shareURL = jsonStructure.getString("url");
        String joke = jsonStructure.getString("value");

        return new Joke(joke, shareURL);
    }

}
