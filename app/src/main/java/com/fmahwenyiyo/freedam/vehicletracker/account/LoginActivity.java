package com.fmahwenyiyo.freedam.vehicletracker.account;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fmahwenyiyo.freedam.vehicletracker.R;

public class LoginActivity extends Activity {

    EditText edtuserid,edtpass;
    Button btnlogin;
    ProgressBar pbbar;
    TextView signUp, image_Upload;
    TextView forgotPassword;
    Context mContext=this;
    public static final String mySharedData="filename";
    SharedPreferences someData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtuserid = (EditText) findViewById(R.id.edtuserid);
        edtpass = (EditText) findViewById(R.id.edtpass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        signUp = (TextView) findViewById(R.id.signUpTextView);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        forgotPassword = (TextView) findViewById(R.id.txtForgotPassword);
        pbbar.setVisibility(View.GONE);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordAcrivity.class);
                startActivity(intent);
            }
        });
        someData= getSharedPreferences(mySharedData,0);
    }

    public void OnLogin(View view){
        String uname=edtuserid.getText().toString();
        String pword=edtpass.getText().toString();
        String type="login";
        if(uname.isEmpty() || pword.isEmpty()){
            dialogBox();
        }else{
//            userDetailsAdapter un= new userDetailsAdapter(uname);

            BackgroundWorker backgroundWorker=new BackgroundWorker(mContext);
            backgroundWorker.execute(type,uname,pword);
        }


    }


    public void dialogBox() {
        AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Make sure that all fields are Filled ...");
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
        alertDialogBuilder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}