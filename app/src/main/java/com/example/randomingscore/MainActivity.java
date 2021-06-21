package com.example.randomingscore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<ScoringButton> allbutton= new ArrayList<>();
    ArrayList<TableRow> buttonrow= new ArrayList<>();
    TextView realscoretext;
    int realscore;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainview());
        setlistener();

        if( realscore<7000){
            realscoretext.setText("Score: "+(realscore));
        }
        else {
            realscoretext.setText("Score: "+(realscore)+" You win");
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LinearLayout mainview(){
        LinearLayout finalview = new LinearLayout(this);
        finalview.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        finalview.setOrientation(LinearLayout.VERTICAL);
        finalview.setGravity(Gravity.TOP);

        realscoretext = new TextView(this);
        realscoretext.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        realscoretext.setTextSize(30);
        realscoretext.setTextColor(Color.BLACK);
        realscoretext.setText(R.string.score);
        realscoretext.setGravity(Gravity.CENTER);

        TableLayout maintable= new TableLayout(this);
        maintable.setBackgroundColor(Color.rgb(0,0,0));
        maintable.setLayoutParams( new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        maintable.setOrientation(TableLayout.VERTICAL);
        maintable.setShrinkAllColumns(true);
        maintable.setStretchAllColumns(true);

        int listval[]= new int[20];
        int valpos;
        int valneg;
        int compteur=0;
        ArrayList<Integer> dejaDedans=new ArrayList<Integer>();
        while (compteur<10){
            valpos=(int)(Math.random()*5000);
                int tmp=getNoReapeateInteger(dejaDedans,20);
                if(listval[tmp]==0){
                    listval[tmp]=valpos;
                    compteur++;
                }
        }

        int compteur2=0;
        ArrayList<Integer> dejaDedans2=new ArrayList<Integer>();
        while (compteur2<10){
            valneg=(int)(Math.random()*-500);
            int tmp=getNoReapeateInteger(dejaDedans2,20);
            if(listval[tmp]==0){
                listval[tmp]=valneg;
                compteur2++;
            }
        }

        for(int i=0; i<5;i++){
           buttonrow.add( new TableRow(this));
           buttonrow.get(i).setLayoutParams( new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            buttonrow.get(i).setOrientation(TableRow.HORIZONTAL);
            for(int j=0;j<4;j++){

                allbutton.add(new ScoringButton(this,(4*i+j),listval[4*i+j]));
                allbutton.get(4*i + j).setId(4*i + j);
                buttonrow.get(i).addView(allbutton.get(4*i + j));
            }
            maintable.addView(buttonrow.get(i));
        }
        finalview.addView(realscoretext);
        finalview.addView(maintable);
        return finalview;
    }

    private int getNoReapeateInteger(ArrayList<Integer> dejaDdans,int v) {
        int variati=0;
        do{
            variati=new Random().nextInt(v);
        }while(dejaDdans.contains(variati));
        dejaDdans.add(variati);
        return variati;
    }

    @Override
    public void onClick(View v) {
        if( realscore<7000){
            ScoringButton buttonclicked = findViewById(v.getId());
            buttonclicked.setText(String.valueOf(buttonclicked.getAddition()));
            realscore+=buttonclicked.getAddition();
            realscoretext.setText("Score: "+(realscore));
            buttonclicked.setEnabled(false);
        }
        else {
            realscoretext.setText("Score: "+(realscore)+" You win");
        }
    }

    public void setlistener(){
        for(int i=0;i<allbutton.size();i++) {
            allbutton.get(i).setOnClickListener(this);
        }
    }
}