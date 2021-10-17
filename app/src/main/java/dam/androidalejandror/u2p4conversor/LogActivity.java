package  dam.androidalejandror.u2p4conversor;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

// TODO: Ex3 Log Activity Life-Cycle
public class LogActivity extends Activity {
    private static String guardar;
    private String DEBUG_TAG = "LOG-";
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(DEBUG_TAG,"onStart");
        notify("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(DEBUG_TAG,"onStop");
        notify("onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(DEBUG_TAG,"onPause");
        notify("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(DEBUG_TAG,"onResume");
        notify("onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()){
            Log.i(DEBUG_TAG,"onDestroy -> El usuario ha matado a la aplicacion");
        }else{
            Log.i(DEBUG_TAG,"onDestroy -> El sistema ha matado a la aplicacion");
        }
        notify("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(DEBUG_TAG,"onRestart");
        notify("onRestart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDebugTag(this.getClass().getSimpleName());
        Log.i(DEBUG_TAG,"onCreate");
        notify("onCreate");
    }
    // TODO: Ex4 Save textstate
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        TextView tvException = findViewById(R.id.et_Exception);
        super.onSaveInstanceState(outState);
        outState.putString(guardar,tvException.getText().toString());
        Log.i(DEBUG_TAG,"onSaveInstanceState");
    }
    // TODO: Ex4 Save textstate
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        TextView tvException = findViewById(R.id.et_Exception);
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null){
            String cadena = savedInstanceState.getString(guardar);
            tvException.setText(cadena);
        }
        Log.i(DEBUG_TAG,"onRestoreInstanceState");
    }

    private void notify(String eventName){
        String activityName = this.getClass().getSimpleName();

        String CHANNEL_ID = "My_LifeCycle";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,"My Lifecycle",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("lifecycle events");
            notificationChannel.setShowBadge(true);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            if (notificationManager != null){
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(eventName+" "+activityName)
                .setContentText(getPackageName())
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManagerCompat.notify((int)System.currentTimeMillis(),notificationBuilder.build());
    }

    public void setDebugTag(String activity) {
        this.DEBUG_TAG += activity;
    }

    public String getDEBUG_TAG() {
        return DEBUG_TAG;
    }
}
