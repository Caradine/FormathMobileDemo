package be.formath.formathmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import be.formath.formathmobile.Model.User;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private String user_login;
    //private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.intent = getIntent();
        user_login = this.intent.getStringExtra(LoginActivity.USER_LOGIN);
        //user = new User();
        //user.setUserName(user_login);
        LoginActivity.close_activity();
        setContentView(R.layout.activity_main);
    }

    public void playGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("USER_OBJECT", this.user_login);
        startActivity(intent);
    }

    public void displayTutorial(View view) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }
}
