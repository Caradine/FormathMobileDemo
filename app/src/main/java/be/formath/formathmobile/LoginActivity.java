package be.formath.formathmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import be.formath.formathmobile.Data.DataRetreiver;

public class LoginActivity extends AppCompatActivity {

    public final static String USER_LOGIN = "be.formath.formathmobile.user_login";
    private static LoginActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_login);
    }

    public static void close_activity() {
        if (instance != null)
            instance.finish();
    }

    public void authenticate(View view) {
        String login = ((EditText) findViewById(R.id.login_field_text)).getText().toString();
        if (check_password(login)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(USER_LOGIN, login);
            startActivity(intent);
        }
        else {
            //TODO: Affichage pop up si mauvais login / password
        }
    }

    private boolean check_password(String login) {
        String password = ((EditText) findViewById(R.id.password_field_text)).getText().toString();
        /*if (login.equals("demo")) {
            if (password.equals("demo"))
                return true;
        }*/
        DataRetreiver dr = DataRetreiver.getInstance(getBaseContext());
        String storedPassword = dr.getUserPassword(login);
        if (storedPassword != null) {
            if (storedPassword.startsWith("{CLEAR}")) {
                storedPassword = storedPassword.substring(7);
                if (storedPassword.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
