package com.example.sagar.quickdeal;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sagar on 20/4/17.
 */

public class UserActivity extends AppCompatActivity {

    Button message;
    Toolbar toolbar;
    ImageView download;
    private ProgressDialog mProgressDialog;
    TextView uname,days,requirements;
    PrefManager prefManager;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        prefManager=new PrefManager(getApplicationContext());

        message=(Button)findViewById(R.id.message);
        toolbar=(Toolbar)findViewById(R.id.utoolbar);
        download=(ImageView)findViewById(R.id.download);
        uname=(TextView)findViewById(R.id.uname);
        days=(TextView)findViewById(R.id.days);
        requirements=(TextView)findViewById(R.id.require);

        str=toTitleCase(prefManager.getCName());
        uname.setText(str);
        days.setText(prefManager.getDays());
        requirements.setText(prefManager.getRequirements());

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int densityDpi = (int)(metrics.density * 160f);

        //customizing toolbar navigation icon
        DrawableResizeClass dr=new DrawableResizeClass();
        Drawable drawable=dr.resizeImage(getApplicationContext(),R.drawable.back,densityDpi/2,densityDpi/2);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,MainActivity.class));
                finish();
            }
        });


        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UserActivity.this,ChatActivity.class));
            }
        });


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling Async Task
                new Download().execute("");
            }
        });
    }

    public class Download extends AsyncTask<String,Integer,String>
    {
        CountDownTimer cdt;
        int id = 1;
        NotificationManager mNotifyManager;
        NotificationCompat.Builder mBuilder;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //To notify phone for downloading notification
            cdt = new CountDownTimer(100 * 60 * 1000, 500) {
                public void onTick(long millisUntilFinished) {
                    mNotifyManager.notify(id, mBuilder.build());
                }

                public void onFinish() {
                    mNotifyManager.notify(id, mBuilder.build());
                }
            };

            mProgressDialog = new ProgressDialog(UserActivity.this);
            mProgressDialog.setTitle(getResources().getString(R.string.download));
            mProgressDialog.setMessage(getResources().getString(R.string.wait));
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

            // Show progress dialog
            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressDialog.setProgress(values[0]);
            mBuilder.setContentInfo(values[0] + "%")
                    .setProgress(100, (values[0]), false);
        }

        @Override
        protected String doInBackground(String... params) {
            int total=0;

            mNotifyManager =
                    (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder = new NotificationCompat.Builder(getApplicationContext());
            mBuilder.setContentTitle(getResources().getString(R.string.downloading_file))
                    .setContentText(getResources().getString(R.string.file))
                    .setProgress(0, 100, false)
                    .setOngoing(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(Notification.PRIORITY_LOW);

            // Initialize Objects here
            publishProgress(5);
            mNotifyManager.notify(id, mBuilder.build());
            cdt.start();

            // Create connection here
            publishProgress(20);


            //Random loop to show loading
            //Any logic to download can be implemented here
            while (total<=10000){
                total++;
                publishProgress((int)total * 100 / 10000);

            }
            return "Success";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String title;
            if (s.equals("Success")) {
                title = getResources().getString(R.string.downloaded);
            } else {
                title = getResources().getString(R.string.error);
            }
            mBuilder.setContentTitle(title)
                    .setContentInfo("")
                    .setOngoing(false)
                    .setProgress(0, 0, false);
            cdt.onFinish();
            cdt.cancel();
        }
    }

    public static String toTitleCase(String input) {
        String output="";
        input = " "+input.toLowerCase();
                for(int i=0;i<input.length();i++)
                {
                    char ch1=input.charAt(i);
                    if(ch1==' '){
                       output+=" "+Character.toUpperCase(input.charAt(i+1));
                        i++;
                    }
                    else {
                        output=output+ch1;
                    }
                }
                return output.trim();
    }




}


