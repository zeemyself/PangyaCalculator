package com.zeemyself.pangyacalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText distance,height,wind,angle,terrain;
    private Button calc;
    private CCDunk Dunk;
    private CCTo Toma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        distance = (EditText) findViewById(R.id.dist);
        height = (EditText) findViewById(R.id.height);
        wind = (EditText) findViewById(R.id.wind);
        angle = (EditText) findViewById(R.id.angle);
        terrain = (EditText) findViewById(R.id.terrain);
        calc = (Button) findViewById(R.id.calc);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Toast.makeText(getApplicationContext(),distance.getText(), Toast.LENGTH_LONG).show();
                Toma = new CCTo(distance.getText()+"",height.getText()+"",wind.getText()+"",angle.getText()+"",terrain.getText()+"");
                Toast.makeText(getApplicationContext(), Toma.calcInfBack() + "", Toast.LENGTH_LONG).show();
                Dunk = new CCDunk(distance.getText()+"",height.getText()+"",wind.getText()+"",angle.getText()+"",terrain.getText()+"");


                AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Result Tomahawk 280+6")
                        .setMessage("WindFront - Result(" + Dunk.getResultFront() +") Force(" + Toma.getForceFront() +")\n" +"WindBack - Result(" + Toma.getResultBack() +") Force(" + Toma.getForceBack() +")")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                               setContentView(R.layout.activitytab);
                               Intent inten =  new Intent(getApplicationContext(),TabActivity.class);
                                String toResF,toResB,toPerF,toPerB,toCalF,toCalB;
                                String dunkResF,dunkResB,dunkPerF,dunkPerB,dunkCalF,dunkCalB;
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
                                inten.putExtra("dunkResF",dunkResF);
                                inten.putExtra("dunkResB",dunkResB);
                                inten.putExtra("dunkPerF",dunkPerF);
                                inten.putExtra("dunkPerB",dunkPerB);
                                inten.putExtra("dunkCalF",dunkCalF);
                                inten.putExtra("dunkCalB",dunkCalB);

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
