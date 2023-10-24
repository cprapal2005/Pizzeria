package com.example.pizzeria;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

public class SharedPreferencesUtils {

    private static final String PREFERENCES_NAME = "MyAppPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    public static void saveCredentials(Context context, String username, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public static boolean areCredentialsValid(Context context, String username, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");

        return username.equals(savedUsername) && password.equals(savedPassword);
    }

}
