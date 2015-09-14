package com.zeemyself.pangyacalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {
    private EditText distance,height,wind,angle,terrain,slope;
    private Button calc;
    private CCDunk Dunk;
    private CCTo Toma;
    public boolean pro =false;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("pro", 0);
        pro = settings.getBoolean("pro",false);

        distance = (EditText) findViewById(R.id.dist);
        height = (EditText) findViewById(R.id.height);
        wind = (EditText) findViewById(R.id.wind);
        angle = (EditText) findViewById(R.id.angle);
        terrain = (EditText) findViewById(R.id.terrain);
        slope = (EditText) findViewById(R.id.SlopeBall);
        calc = (Button) findViewById(R.id.calc);

        //Reopen app now show recent input
        distance.setText(settings.getString("distance","250"));
        height.setText(settings.getString("height","3.5"));
        wind.setText(settings.getString("wind","3"));
        angle.setText(settings.getString("angle","40"));
        terrain.setText(settings.getString("terrain","100"));
        slope.setText(settings.getString("slope","0"));

        //Next Button will set cursor to last
        distance.setSelection(distance.getText().length());
        height.setSelection(height.getText().length());
        wind.setSelection(wind.getText().length());
        angle.setSelection(angle.getText().length());
        terrain.setSelection(terrain.getText().length());
        slope.setSelection(slope.getText().length());

        // Advertisement :((((
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        if(!pro)
            mAdView.loadAd(adRequest);
        // Full Picture ads
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2847267145905566/6087620935");


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdRequest adRequest2 = new AdRequest.Builder()
                        .addTestDevice("YOUR_DEVICE_HASH")
                        .build();

                mInterstitialAd.loadAd(adRequest2);
                if (mInterstitialAd.isLoaded() && !pro) {
                    mInterstitialAd.show();
                }
//              Toast.makeText(getApplicationContext(),distance.getText(), Toast.LENGTH_LONG).show();
                Toma = new CCTo(distance.getText()+"",height.getText()+"",wind.getText()+"",angle.getText()+"",terrain.getText()+"",slope.getText()+"");
                Dunk = new CCDunk(distance.getText()+"",height.getText()+"",wind.getText()+"",angle.getText()+"",terrain.getText()+"",slope.getText()+"");
//                Toast.makeText(getApplicationContext(), Dunk.test() + "", Toast.LENGTH_LONG).show();

                AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Result Tomahawk 280+6")
                        .setMessage("WindFront - Result(" + Dunk.getResultFront() +") Force(" + Toma.getForceFront() +")\n" +"WindBack - Result(" + Toma.getResultBack() +") Force(" + Toma.getForceBack() +")")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                               setContentView(R.layout.activitytab);
                               Intent inten =  new Intent(getApplicationContext(),TabActivity.class);
                                String toResF,toResB,toPerF,toPerB,toCalF,toCalB;
                                String dunkResF,dunkResB,dunkPerF,dunkPerB,dunkCalF,dunkCalB,dunkSpinF,dunkSpinB;
                                toResF = Toma.getResultFront()+"";
                                toResB = Toma.getResultBack()+"";
                                toPerF = Toma.getPercentFront()+"%";
                                toPerB = Toma.getPercentBack()+"%";
                                toCalF = Toma.getForceFront()+"";
                                toCalB = Toma.getForceBack()+"";
                                inten.putExtra("toResF",toResF);
                                inten.putExtra("toResB",toResB);
                                inten.putExtra("toPerF",toPerF);
                                inten.putExtra("toPerB",toPerB);
                                inten.putExtra("toCalF",toCalF);
                                inten.putExtra("toCalB", toCalB);

                                dunkResF = Dunk.getResultFront()+"";
                                dunkResB = Dunk.getResultBack()+"";
                                dunkPerF = Dunk.getPercentFront()+"%";
                                dunkPerB = Dunk.getPercentBack()+"%";
                                dunkCalF = Dunk.getForceFront()+"";
                                dunkCalB = Dunk.getForceBack()+"";
                                dunkSpinF = Dunk.getSpinFront()+"";
                                dunkSpinB = Dunk.getSpinBack()+"";
                                inten.putExtra("dunkResF",dunkResF);
                                inten.putExtra("dunkResB",dunkResB);
                                inten.putExtra("dunkPerF",dunkPerF);
                                inten.putExtra("dunkPerB",dunkPerB);
                                inten.putExtra("dunkCalF",dunkCalF);
                                inten.putExtra("dunkCalB",dunkCalB);
                                inten.putExtra("dunkSpinF",dunkSpinF);
                                inten.putExtra("dunkSpinB",dunkSpinB);
                                MainActivity.this.startActivity(inten);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final EditText input = new EditText(this);
       switch(item.getItemId()){
           case R.id.redeem:
               AlertDialog.Builder alert = new AlertDialog.Builder(this);
               alert.setTitle("Redeem Code");
               alert.setMessage("Code");
               alert.setView(input);
               alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       String value = input.getText().toString().trim();
                       if (value.equals("zeemyself")) {
                           pro = true;
                           Toast.makeText(getApplicationContext(), "Pass", Toast.LENGTH_LONG).show();
                       }
                       else{
                           pro = false;
                           Toast.makeText(getApplicationContext(), value, Toast.LENGTH_LONG).show();
                       }
                   }
               });
               alert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });
               alert.show();
               break;

       }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStop(){
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences("pro", MODE_WORLD_READABLE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("pro", pro);
        editor.putString("distance", distance.getText().toString());
        editor.putString("height",height.getText().toString());
        editor.putString("wind",wind.getText().toString());
        editor.putString("angle",angle.getText().toString());
        editor.putString("terrain",terrain.getText().toString());
        editor.putString("slope",slope.getText().toString());
        // Commit the edits!
        editor.commit();
    }
}
