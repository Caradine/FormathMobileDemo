package be.formath.formathmobile;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import be.formath.formathmobile.Data.DataWriter;
import be.formath.formathmobile.GameActivityFragments.PlayFieldFragment;
import be.formath.formathmobile.GameActivityFragments.ResultFragment;
import be.formath.formathmobile.GameActivityFragments.SelectCategoryFragment;
import be.formath.formathmobile.GameActivityFragments.SelectLevelFragment;
import be.formath.formathmobile.Model.Game;
import be.formath.formathmobile.Model.GameType;
import be.formath.formathmobile.Model.Operation;
import be.formath.formathmobile.Model.User;

public class GameActivity extends AppCompatActivity
                          implements SelectCategoryFragment.OnFragmentSelectCategoryInteractionListener,
                                     SelectLevelFragment.OnFragmentSelectLevelInteractionListener,
                                     PlayFieldFragment.OnFragmentPlayFieldInteractionListener,
                                     ResultFragment.OnFragmentResultInteractionListener{

    private Game dataGame;
    private int currentOperationIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String user_login = getIntent().getExtras().getString("USER_OBJECT");
        dataGame = new Game();
        //TODO: Récupération des infos utilisateur
        User user = new User();
        dataGame.setType(GameType.NORMAL);
        user.setUserName(user_login);
        dataGame.setUser(user);
        setContentView(R.layout.activity_game);
        SelectCategoryFragment newFragment = SelectCategoryFragment.newInstance(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_game_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentSelectCategoryInteraction(int category) throws Exception {
        if (category != 0) {
            SelectLevelFragment newFragment = SelectLevelFragment.newInstance(this, category);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_game_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onFragmentSelectLevelInteraction (int category, int level) {
        if (level != 0) {
            dataGame.setGameStartDateTime(new GregorianCalendar());
            Generator gen = new Generator();
            ArrayList<Operation> listOper = gen.generateOperationList(category, level);
            dataGame.setListOperation(listOper);
            currentOperationIndex = 0;
            Operation oper = dataGame.getListOperation().get(currentOperationIndex);

            PlayFieldFragment newFragment = PlayFieldFragment.newInstance(this, oper.getLabel(), currentOperationIndex + 1);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_game_container, newFragment);
            transaction.commit();
        }
    }

    @Override
    public void onFragmentPlayFieldInteraction(String userResponse) {
        dataGame.getListOperation().get(currentOperationIndex).setGivenResponse(userResponse);
        if (currentOperationIndex < 9) {
            currentOperationIndex++;
            Operation oper = dataGame.getListOperation().get(currentOperationIndex);
            PlayFieldFragment newFragment = PlayFieldFragment.newInstance(this, oper.getLabel(), currentOperationIndex + 1);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_game_container, newFragment);
            transaction.commit();
        }
        else {
            // Enregistrement en DB de la partie
            dataGame.generateResult();
            DataWriter dr = DataWriter.getInstance(getBaseContext());
            dr.saveGame(dataGame);
            // Calcul du gain de médailles
            
            // Lancement du fragment de fin de partie
            ResultFragment newFragment = ResultFragment.newInstance(dataGame.getListOperation());
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_game_container, newFragment);
            transaction.commit();
        }
    }

    @Override
    public void onFragmentResultInteraction() {
        this.finish();
    }
}
