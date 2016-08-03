package com.fmahwenyiyo.freedam.vehicletracker.account;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fmahwenyiyo.freedam.vehicletracker.R;

public class SignUpActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    EditText username, password, confirmPassword, emailAddress,name, surname, RegNumber;
    Button btnRegister;
    TextView gender;
    ProgressBar mProgressBar;
    Context mContext=this;
    RadioGroup mRadioGroup;
    String mGender="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        RegNumber= (EditText) findViewById(R.id.txtReg);
        username= (EditText)findViewById(R.id.etUsername);
        password= (EditText)findViewById(R.id.txtPassword);
        confirmPassword= (EditText)findViewById(R.id.txtConfirmPassword);
        emailAddress= (EditText)findViewById(R.id.txtEmailAddress);
        name= (EditText)findViewById(R.id.txtFirstName);
        surname= (EditText)findViewById(R.id.txtLastName);
        btnRegister= (Button) findViewById(R.id.bregister);
        gender= (TextView) findViewById(R.id.genderId);
        mRadioGroup= (RadioGroup) findViewById(R.id.rgAnswers);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    public void onSignUp(View view) {
        String firstname, lastName, userName, Password, emailaddress, ConfirmPass, myGender, Reg;

        firstname = name.getText().toString().trim();
        lastName = surname.getText().toString().trim();
        userName = username.getText().toString().trim();
        Password = password.getText().toString();
        emailaddress = emailAddress.getText().toString().trim();
        ConfirmPass = confirmPassword.getText().toString();
        myGender =gender.getText().toString();
        Reg=RegNumber.getText().toString();
        String errorMessage="";

        if (firstname.isEmpty() || lastName.isEmpty() || userName.isEmpty() || Password.isEmpty() || emailaddress.isEmpty() || myGender.isEmpty()) {
            dialogBox();
        }
        else {
            if (Password.equals(ConfirmPass) && emailaddress.contains(".com") && emailaddress.contains("@")) {
                if(Password.length()>5){
                    String type = "register";
//                    userDetails userBefore= new userDetails();
//                    userBefore.setUserBeforeCompletion(userName);

                    BackgroundWorker backgroundWorker = new BackgroundWorker(mContext);
                    backgroundWorker.execute(type, firstname, lastName, userName, Password, emailaddress,myGender, Reg);
                }else{
                    AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(this);
                    alertDialogBuilder.setMessage("Password should contain at least six characters");
                    alertDialogBuilder.setPositiveButton("Try again",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

            } else {
                AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Something went wrong.. Did you select your gender ?. Is your E-mail Valid? Also make sure all fields are filled");
                alertDialogBuilder.setPositiveButton("Try again",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

        }
    }

    public void dialogBox() {
        AlertDialog.Builder alertDialogBuilder = new        AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Plase Fill in all fields before submit");
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //setText
//                        Intent intent= new Intent(SignUpActivity.this, SignUpActivity.class);
//                        startActivity(intent);
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the MHome/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.radio1:
                mGender="Male";
                gender.setText(mGender);
                break;
            case R.id.radio2:
                mGender="Female";
                gender.setText(mGender);
                break;
        }
    }
}

