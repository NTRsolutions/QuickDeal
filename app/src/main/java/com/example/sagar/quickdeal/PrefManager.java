package com.example.sagar.quickdeal;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sagar on 20/4/17.
 */

public class PrefManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;


    // Shared pref file name
    private static final String PREF_NAME = "QuickDeal1";

    // Constructor
    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }


    public void setRequirements(String requirements) {

        editor.putString("requirements", requirements);
        editor.commit();
    }

    public void setCName(String name) {

        editor.putString("name", name);
        editor.commit();
    }

    public void setDays(String days) {

        editor.putString("days", days);
        editor.commit();
    }







    public String getCName() {
        return pref.getString("name", null);
    }
    public String getDays() {
        return pref.getString("days", null);
    }
    public String getRequirements() {
        return pref.getString("requirements", null);
    }

}

