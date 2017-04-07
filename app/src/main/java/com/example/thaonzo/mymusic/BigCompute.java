package com.example.thaonzo.mymusic;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by lamourer on 28/03/2017.
 */

public class BigCompute extends AsyncTask<Void, Integer, Void> {
    Context mContext;
    ProgressBar mProgressBar;
    MediaPlayer mediaPlayer;
    boolean isPaused;
    BigCompute(Context c, ProgressBar p,MediaPlayer m){
        mContext = c;
        mProgressBar = p;
        mediaPlayer=m;
        isPaused = false;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(mContext, "Launching",
                Toast.LENGTH_SHORT).show();
        super.onPreExecute();
    }
    // Invoquée après la fin de l’exécution doInBackground(Params...).
    // Le résultat de doInBackground(Params...) est passé comme paramètre cette méthode.
    // Ce code est exécuté sur le thread UI.
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgressBar.setProgress(values[0]);

    }
    public void pause()
    {
        this.isPaused = true;
    }

    public void resume()
    {
        this.isPaused = false;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (100*mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration() != 100)
        {
            publishProgress(100*mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration());
        }

        return null;
    }

}
