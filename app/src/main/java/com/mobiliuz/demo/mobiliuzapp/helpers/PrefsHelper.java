package com.mobiliuz.demo.mobiliuzapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.mobiliuz.demo.mobiliuzapp.R;

/**
 * Created by darhan on 3/5/15.
 */
public class PrefsHelper {

    public static final String PREF_FILE_NAME = "prefs";

    public static final String PREF_EMAIL = "remember_me";
    public static final String PREF_PASSWORD = "login_type";
    public static final String PREF_API_TOKEN = "login";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public PrefsHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void delete(String key) {
        if (sharedPreferences.contains(key)) {
            editor.remove(key).commit();
        }
    }

    public void savePref(String key, Object value) {
        delete(key);

        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Enum) {
            editor.putString(key, value.toString());
        } else if (value != null) {
            throw new RuntimeException("Attempting to save non-primitive preference");
        }

        editor.commit();
    }

    @SuppressWarnings("unchecked")
    public <T> T getPref(String key) {
        return (T) sharedPreferences.getAll().get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getPref(String key, T defValue) {
        T returnValue = (T) sharedPreferences.getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }

    public boolean isPrefExists(String key) {
        return sharedPreferences.contains(key);
    }

}
