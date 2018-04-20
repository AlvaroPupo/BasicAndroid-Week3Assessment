package com.example.rodneytressler.week3assessmentkey;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rodneytressler on 12/14/17.
 */

public class AccountFragment extends Fragment {

    @BindView(R.id.name_input)
    protected EditText nameInput;

    @BindView(R.id.class_input)
    protected EditText classInput;

    private ActivityCallback activityCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_creation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static AccountFragment newInstance() {

        Bundle args = new Bundle();

        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @OnClick(R.id.button_finish)
    protected void onFinishButtonClicked() {

        if(TextUtils.isEmpty(nameInput.getText().toString()) || TextUtils.isEmpty(classInput.getText().toString())) {
            emptyFields();
        }else if (!classInput.getText().toString().equalsIgnoreCase("warrior") && !classInput.getText().toString().equalsIgnoreCase("mage") && !classInput.getText().toString().equalsIgnoreCase("archer")){
            classMismatch();
        } else {
            activityCallback.userClassNameInfo(nameInput.getText().toString(), classInput.getText().toString());
            nameInput.setText("");
            classInput.setText("");
        }
    }

    protected void emptyFields() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Information required")
                .setMessage("Please complete the information on all fields")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.alert_light_frame).show();
    }
    protected void classMismatch(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Enter a valid class")
                .setMessage("You can only enter: warrior, mage or archer")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.alert_light_frame).show();
                classInput.setText("");
    }

    public void attachParent(ActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }
    public interface ActivityCallback{
        void userClassNameInfo (String userName, String UserClass);
    }
}
