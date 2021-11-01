package id.my.attendance.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

import id.my.attendance.Model.UserModel;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context _context;

    public static final String IS_LOGGED_IN = "isLogginIn";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String JABATAN = "jabatan";

    public Context get_context(){
        return _context;
    }

    //constructor
    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(UserModel userModel){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(ID, userModel.getId());
        editor.putString(NAME, userModel.getName());
        editor.putString(EMAIL, userModel.getEmail());
        editor.putString(JABATAN, userModel.getJabatan());
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(ID,sharedPreferences.getString(ID, null));
        user.put(NAME,sharedPreferences.getString(NAME, null));
        user.put(EMAIL,sharedPreferences.getString(EMAIL, null));
        user.put(JABATAN,sharedPreferences.getString(JABATAN, null));
        return user;
    }

    public void logoutUser(){
        //clearing all data from shared preferences
        editor.putBoolean(IS_LOGGED_IN, false);
        editor.putString(ID, "");
        editor.putString(NAME, "");
        editor.putString(EMAIL, "");
        editor.putString(JABATAN, "");
//        editor.clear();
//        editor.apply();
        editor.commit();
    }

    public boolean isLogginIN(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}
