package com.hahmetbuyukbesnili.catchthejerry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeView;
    TextView scoreView;
    Button button;
    AlertDialog.Builder alert;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeView = findViewById(R.id.timeView);
        scoreView = findViewById(R.id.scoreView);
        button = findViewById(R.id.button);
        alert = new AlertDialog.Builder(MainActivity.this);
        score = 0;
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageArray = new ImageView[] { imageView, imageView2, imageView3, imageView4, imageView5,
                imageView6, imageView7, imageView8, imageView9 };

        hideImages();
    }

    public void start(View view){

        button.setEnabled(false);
        score = 0;
        scoreView.setText("Score: ");
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                hideImages();

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,500);
            }
        };
        handler.post(runnable);

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeView.setText("Time: "+ millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeView.setText("Time off!");
                handler.removeCallbacks(runnable);
                hideImages();

                alert.setTitle("Restart");
                alert.setMessage("Do you want to retry?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button.setEnabled(true);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over!", Toast.LENGTH_LONG).show();
                        button.setEnabled(true);
                        button.setText("PLAY AGAIN");
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void score(View view) {
        score++;
        scoreView.setText("Score: "+ score);
    }

    public void hideImages() {
        for (ImageView image: imageArray){
            image.setVisibility(View.INVISIBLE);
        }
    }
}