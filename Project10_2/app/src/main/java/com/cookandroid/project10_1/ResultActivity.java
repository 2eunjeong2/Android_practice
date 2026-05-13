package com.cookandroid.project10_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Arrays;
import java.util.Comparator;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.pici_icon);
        setTitle("투표 결과");

        Intent intent = getIntent();

        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");

        Integer imageFileID[] = {
                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
                R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
                R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
        };

        TextView tv[] = new TextView[9];
        RatingBar rbar[] = new RatingBar[9];

        Integer tvID[] = {
                R.id.tv1, R.id.tv2, R.id.tv3,
                R.id.tv4, R.id.tv5, R.id.tv6,
                R.id.tv7, R.id.tv8, R.id.tv9
        };

        Integer rbarID[] = {
                R.id.rbar1, R.id.rbar2, R.id.rbar3,
                R.id.rbar4, R.id.rbar5, R.id.rbar6,
                R.id.rbar7, R.id.rbar8, R.id.rbar9
        };

        // 텍스트 & 레이팅바 출력
        for (int i = 0; i < voteResult.length; i++) {

            tv[i] = findViewById(tvID[i]);
            rbar[i] = findViewById(rbarID[i]);

            tv[i].setText(imageName[i]);
            rbar[i].setRating(voteResult[i]);
        }

        // =========================
        // ViewFlipper 구현
        // =========================

        ViewFlipper viewFlipper = findViewById(R.id.viewFlipper);

        // 인덱스 저장 배열
        Integer rank[] = {0,1,2,3,4,5,6,7,8};

        // 투표수 기준 내림차순 정렬
        Arrays.sort(rank, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return voteResult[o2] - voteResult[o1];
            }
        });

        // 1등부터 이미지 추가
        for(int i=0; i<rank.length; i++) {

            ImageView imageView = new ImageView(this);

            imageView.setImageResource(imageFileID[rank[i]]);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            viewFlipper.addView(imageView);
        }

        // 1초마다 자동 넘김
        Button btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewFlipper.startFlipping();
                viewFlipper.setFlipInterval(1000);
            }
        });

        // =========================

        Button btnReturn = findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}