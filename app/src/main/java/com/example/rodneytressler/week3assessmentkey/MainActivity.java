package com.example.rodneytressler.week3assessmentkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AccountFragment.ActivityCallback {

    @BindView(R.id.welcome_text)
    protected TextView welcomeText;
    @BindView(R.id.fate_textview)
    protected TextView fateTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        welcomeText.setVisibility(View.INVISIBLE);
        fateTextview.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AccountFragment accountFragment = AccountFragment.newInstance();
        accountFragment.attachParent(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, accountFragment).commit();

    }

    @Override
    public void userClassNameInfo(String userName, String UserClass) {
        welcomeText.setText(getString(R.string.welcome_string, userName, UserClass));
        welcomeText.setVisibility(View.VISIBLE);
        fateTextview.setVisibility(View.VISIBLE);
    }
}
