package com.example.gunshot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {
    private SoundPool sounds;
    private int sound_shot;
    private int sound_false;
    private int sound_baraban;
    private ImageView imageBlood;
    private int on_shot=2;
    private int maxn=5;
    private int random;
    private boolean pustonepusto;
    private TextView textView;
    private int lvl=0;
    private String sometext;
    private boolean sdox=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSoundPool();
        loadSound();
        init();
    }
    protected void createSoundPool(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            createNewSoundPool();
        }else{
            createOldSoundPool();
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void createNewSoundPool(){
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        sounds = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }
    @SuppressWarnings("deprecation")
    protected void createOldSoundPool(){sounds = new SoundPool(5, AudioManager.STREAM_MUSIC,0);}
    private void loadSound(){
        sound_shot = sounds.load(this,R.raw.revolver_shot,1);
        sound_false = sounds.load(this,R.raw.gun_false,1);
        sound_baraban = sounds.load(this,R.raw.revolver_baraban,1);

    }

    @SuppressLint("ResourceAsColor")
    public void onShot(View view) {
        Log.d("Main","tipo shot");
        if (random == on_shot && pustonepusto==true){
            sounds.play(sound_shot,1.0f,1.0f,1,0,1);
            imageBlood.setVisibility(View.VISIBLE);
            Log.d("Main","Real shot");
            pustonepusto=false;
            sdox=true;
            lvl=0;
            textView.setText("ТЫ УМЕР");
        }else{
            sounds.play(sound_false,1.0f,1.0f,1,0,1);
            Log.d("Main","wrongshot");
            if(pustonepusto!=false){
                lvl++;
                if(lvl==0 && pustonepusto!=false){
                    sometext="Choooort";
                    pustonepusto=false;
                } else if (lvl==1 && pustonepusto!=false) {
                    sometext="Pffff";
                    pustonepusto=false;
                }else if(lvl==2 && pustonepusto!=false){
                    sometext="Бывало и лучше";
                    pustonepusto=false;
                }else if(lvl==3 && pustonepusto!=false){
                    sometext="Skuuuchno";
                    pustonepusto=false;
                }else if(lvl==4 && pustonepusto!=false){
                    sometext="Xmm";
                    pustonepusto=false;
                }else if(lvl==5 && pustonepusto!=false){
                    sometext="Neploxo";
                    pustonepusto=false;
                }
                speck(6,"Molodec");
                speck(7,"Krasava");
                speck(8,"MUZHIK");
                speck(9,"MONSTR");
                speck(10,"OMG");
                speck(11,"Ұялмайсыңба");
                speck(12,"Даже я до сюда не доходил");
                speck(13,"Или доходил?:O");
                speck(14,"Непомню");
                speck(15,"Ты датого везучи");
                speck(16,"что мне уже лениво");
                speck(17,"писать...");
                speck(18,"...");
                textView.setText(lvl+"."+sometext);
            }
        }
    }
    private void speck(int lvl,String sometext){
        if (lvl==this.lvl&& pustonepusto!=false){
            this.sometext=sometext;
            pustonepusto=false;
        }
    }

    @SuppressLint("ResourceAsColor")
    public void onBaraban(View view) {
        sounds.play(sound_baraban,1.0f,1.0f,1,0,1);
        imageBlood.setVisibility(View.GONE);
        random = new Random().nextInt(maxn);
        Log.d("MainActivity","Random number:" + random);
        pustonepusto=true;
        if(sdox==true){
            textView.setText("Попробуй еще раз пацан");
            sdox=false;
        }
    }

    private void init(){
        imageBlood=findViewById(R.id.imageBlood);
        textView=findViewById(R.id.textView);
    }
}












