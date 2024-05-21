package dsa.upc.edu.listapp.github;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    final static String URL = "https://api.github.com/";

    private static Retrofit retrofit;
    private static GitHub github;

    public static Retrofit getRetrofit() {
        if(retrofit==null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static GitHub getGithub() {
        if(github==null) {
            github = getRetrofit().create(GitHub.class);
        }
        return github;
    }

}
