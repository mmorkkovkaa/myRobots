package com.example.myrobots;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView name1, name2, opisanieRobotWords;
    private TextView energy1, energy2;
    private TextView lasers1, lasers2;
    private Button btnStart, btnFight, btnGameOver;
    private LottieAnimationView lotty1, lotty2;
    private LinearLayout imagesRobotsLayout;


    TransformerRed robot1 = new TransformerRed(
            "EVA-01", 6000, 200, " I won! It's my planet!");

    TransformerYellow robot2 = new TransformerYellow(
            "EVA-02", 7000, 200, " I won! It's my planet!");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        btnFight.setOnClickListener(new View.OnClickListener() {

            private void stopAnimations() {
                lotty1.cancelAnimation();
                lotty2.cancelAnimation();
            }
            private void showGameOverButton() {
                btnGameOver.setVisibility(View.VISIBLE);
            }

            @Override
            public void onClick(View v) {
                btnStart.setVisibility(View.INVISIBLE);
                imagesRobotsLayout.setVisibility(View.INVISIBLE);

                if ((robot1.energy == 0) || robot1.energy < 0 || (robot2.energy == 0) || robot2.energy < 0) {

                    if(robot1.energy==0 || robot1.energy<0){
                        String wordsRobot2 = robot2.printSelf();
                        opisanieRobotWords.setVisibility(View.VISIBLE);
                        opisanieRobotWords.setText(wordsRobot2);

                        stopAnimations();
                        btnFight.setVisibility(View.INVISIBLE);
                        showGameOverButton();

                    } else  if (robot2.energy==0 || robot2.energy <0) {
                        String wordsRobot1 = robot1.printSelf();
                        opisanieRobotWords.setVisibility(View.VISIBLE);
                        opisanieRobotWords.setText(wordsRobot1);
                    }
                    btnFight.setVisibility(View.INVISIBLE);
                    btnStart.setVisibility(View.INVISIBLE);

                    lotty1.setVisibility(View.INVISIBLE);
                    lotty2.setVisibility(View.INVISIBLE);


                    btnGameOver.setVisibility(View.VISIBLE);

                } else {

                    imagesRobotsLayout.setVisibility(View.INVISIBLE);
                    lotty1.setVisibility(View.VISIBLE);
                    lotty2.setVisibility(View.VISIBLE);


                    String lasers1Value = lasers1.getText().toString();
                    String lasers2Value = lasers2.getText().toString();
                    if (!TextUtils.isEmpty(lasers1Value) && !TextUtils.isEmpty(lasers2Value)) {
                        int m = Integer.parseInt(lasers1Value);
                        robot1.minusEnergy(m);
                        energy1.setText(String.valueOf(robot1.getEnergy()));

                        int n = Integer.parseInt(lasers2Value);
                        robot2.minusEnergy(n);
                        energy2.setText(String.valueOf(robot2.energy));
                    }
                }
            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {

            private void startAnimations() {
                lotty1.playAnimation();
                lotty2.playAnimation();
            }

            @Override
            public void onClick(View v) {
                name1.setText(robot1.getName());
                name2.setText(robot2.name);
                Random rn1 = new Random();

                energy1.setText(String.valueOf(rn1.nextInt(7001)+1));
                Random ls1 = new Random();
                lasers1.setText(String.valueOf(ls1.nextInt(601)+1));

                Random rn2 = new Random();

                energy2.setText(String.valueOf(rn2.nextInt(6001)+1));
                Random ls2 = new Random();
                lasers2.setText(String.valueOf(ls2.nextInt(501)+1));

                btnFight.setVisibility(View.VISIBLE);

                startAnimations();
                btnFight.setVisibility(View.VISIBLE);
            }
        });

    }
    private void init() {
        name1 = findViewById(R.id.name_1);
        name2 = findViewById(R.id.name_2);
        imagesRobotsLayout = findViewById(R.id.images_robots_layout);
        opisanieRobotWords = findViewById(R.id.ho_will_win);

        energy1 = findViewById(R.id.energy1);
        energy2 = findViewById(R.id.energy2);

        lasers1 = findViewById(R.id.laser1);
        lasers2 = findViewById(R.id.lasers2);

        btnStart = findViewById(R.id.btn_start);
        btnFight = findViewById(R.id.btn_fight);
        btnGameOver = findViewById(R.id.btn_game_over);

        lotty1 = findViewById(R.id.lotty_drive1);
        lotty2 = findViewById(R.id.lotty_drive2);


        lotty1.setAnimation(R.raw.red_robot);
        lotty2.setAnimation(R.raw.y_robot);
    }


}
