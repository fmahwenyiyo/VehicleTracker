package com.fmahwenyiyo.freedam.vehicletracker.account;

/**
 * Created by Hwenyets on 11 Jul 2016.
 */
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.fmahwenyiyo.freedam.vehicletracker.admin.AdminHome;
import com.fmahwenyiyo.freedam.vehicletracker.ceo.CeoHome;
import com.fmahwenyiyo.freedam.vehicletracker.mechanic.MHome;
import com.fmahwenyiyo.freedam.vehicletracker.security.SHome;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String>{
    private ProgressDialog dialog;

    Context context;
    AlertDialog alertDialog;

    public BackgroundWorker(Context ctx){
        context=ctx;
        dialog = new ProgressDialog(ctx);
    }



    @Override
    protected String doInBackground(String... params) {

        String type=params[0];
        String login_url= Constants.LOGIN_URL; //"http://192.168.43.184:3001/mathstutor/mylogin.php";
        String logout_url= Constants.LOGOUT_URL; // "http://192.168.43.184:3001/mathstutor/mylogout.php";
        String reset_url= Constants.RESET_URL; // "http://192.168.43.184:3001/mathstutor/myreset.php";
        String register_url= Constants.REGISTER_URL; // "http://192.168.43.184:3001/mathstutor/doregister.php";
        String finishSignUp_Url= Constants.FINISH_SIGN_UP_URL; // "http://192.168.43.184:3001/mathstutor/commit.php";

        if(type.equals("login")){

            try {
                String username=params[1];
                String password=params[2];
                URL url=new URL(login_url);

                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(username,"UTF-8")+"&"
                        + URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line;
                while((line=bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }  catch(NullPointerException e){
                e.printStackTrace();
            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("register")){

            try {

                String firstname=params[1];
                String lastName=params[2];
                String userName=params[3];
                String Password=params[4];
                String emailaddress=params[5];
                String mGender=params[6];
                String RegNumber=params[6];


                URL url=new URL(register_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("firstname","UTF-8")+"="+ URLEncoder.encode(firstname,"UTF-8")+"&"
                        + URLEncoder.encode("lastName","UTF-8")+"="+ URLEncoder.encode(lastName,"UTF-8")+"&"
                        + URLEncoder.encode("userName","UTF-8")+"="+ URLEncoder.encode(userName,"UTF-8")+"&"
                        +  URLEncoder.encode("Password","UTF-8")+"="+ URLEncoder.encode(Password,"UTF-8")+"&"
                        + URLEncoder.encode("emailaddress","UTF-8")+"="+ URLEncoder.encode(emailaddress,"UTF-8")+"&"
                        + URLEncoder.encode("RegNumber","UTF-8")+"="+ URLEncoder.encode(RegNumber,"UTF-8")+"&"
                        + URLEncoder.encode("mGender","UTF-8")+"="+ URLEncoder.encode(mGender,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line;
                while((line=bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        else if(type.equals("reset")){

            try {
               // userDetails myName= new userDetails();
                String username="last";//myName.getUsername();

                URL url=new URL(reset_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(username,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line;
                while((line=bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("commit")){

            try {
                String Name=params[1];
                String Question=params[2];
                String Answer=params[3];


                URL url=new URL(finishSignUp_Url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("Name","UTF-8")+"="+ URLEncoder.encode(Name,"UTF-8")+"&"
                        + URLEncoder.encode("Question","UTF-8")+"="+ URLEncoder.encode(Question,"UTF-8")+"&"
                        + URLEncoder.encode("Answer","UTF-8")+"="+ URLEncoder.encode(Answer,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line;
                while((line=bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }catch (NullPointerException e){
                Toast.makeText(context, "No Connection to Server", Toast.LENGTH_SHORT).show();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(type.equals("logout")){

            try {
                String Name=params[1];

                URL url=new URL(logout_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("Name","UTF-8")+"="+ URLEncoder.encode(Name,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line;
                while((line=bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }catch (NullPointerException e){
                Toast.makeText(context, "No Connection to Server", Toast.LENGTH_SHORT).show();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Please Wait......");
        dialog.show();

    }

    @Override
    protected void onPostExecute(String result) {

        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        try{
            if(result.equals("UserExists")){
                AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("A user with the same Username already exists.. Change your Username");
                alertDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
            else if(result.equals("Teacher")){

                Intent intent=new Intent(context, AdminHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
            else if(result.equals("Teacher")){

                Intent intent=new Intent(context, SHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
            else if(result.equals("logged_out")){

                Intent intent=new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
            else if(result.equals("Student")){

                Intent intent=new Intent(context, CeoHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
            else if(result.equals("Student")){

                Intent intent=new Intent(context, MHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }

            else if (result.equals("registration successful final")){
                AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Registration Successful Proceed to Login ...");
                alertDialogBuilder.setPositiveButton("Login",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent=new Intent(context, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intent);
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
            else if (result.equals("registration successful")){
                AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("First Step Successful. Complete Signup to Login ...");
                alertDialogBuilder.setPositiveButton("Complete",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent=new Intent(context, ResetAccount.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.putExtra("name","");
                                context.startActivity(intent);
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
            else if (result.equals("reseted")){
                AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Account Reset Successful ...");
                alertDialogBuilder.setPositiveButton("Login",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent=new Intent(context, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intent);
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
            else if (result.equals("logged")){
                AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("This account is already logged into from another location. " +
                        "If you are the owner of this account and you are not logged in please reset your login status ...");
                alertDialogBuilder.setPositiveButton("Reset",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent=new Intent(context, ResetAccount.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intent);
                                // new BackgroundWorker(context).execute("reset");
                            }
                        });
                alertDialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
            else if (result.equals("notActive")){
                AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Your Account is not Active.. Please Contact your system Administrator");

                alertDialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
            else if (result.equals("login failure")){
                //  Toast.makeText(context,"Login Failed... Invalid Credentials",Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Invalid Credentials.. Login Failed..");
                alertDialogBuilder.setPositiveButton("Try again",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

            else{
                AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("No Response from server...Try again Later");
                alertDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }catch (NullPointerException ex){
            AlertDialog.Builder alertDialogBuilder = new   AlertDialog.Builder(context);
            alertDialogBuilder.setMessage("No Connection To server...Try again Later");
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
