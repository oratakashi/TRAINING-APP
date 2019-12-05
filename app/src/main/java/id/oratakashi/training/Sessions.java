package id.oratakashi.training;

import android.content.Context;
import android.content.SharedPreferences;

public class Sessions {
    private static Sessions sessions;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Sessions";

    public static String name = "name";
    public static String email = "email";

    public Sessions(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static Sessions getInstance(Context context){
        if(sessions == null){
            sessions = new Sessions(context);
        }
        return sessions;
    }

    public void putString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key){
        return pref.getString(key, "");
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
}
