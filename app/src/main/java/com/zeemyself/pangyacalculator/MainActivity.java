package com.zeemyself.pangyacalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView distance,height,wind,angle,terrain;
    private Button calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        distance = (TextView) findViewById(R.id.dist);
        height = (TextView) findViewById(R.id.height);
        wind = (TextView) findViewById(R.id.wind);
        angle = (TextView) findViewById(R.id.angle);
        terrain = (TextView) findViewById(R.id.terrain);
        calc = (Button) findViewById(R.id.calc);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Toast.makeText(getApplicationContext(),distance.getText(), Toast.LENGTH_LONG).show();
                CCTo Toma = new CCTo(distance.getText()+"",height.getText()+"",wind.getText()+"",angle.getText()+"",terrain.getText()+"");
                Toast.makeText(getApplicationContext(), Toma.calcHWIWindMinus() + "", Toast.LENGTH_LONG).show();




                AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Result Tomahawk 280+6")
                        .setMessage("WindFront - Result(" + Toma.getResultFront() +") Force(" + Toma.getForceFront() +")\n" +"WindBack - Result(" + Toma.getResultBack() +") Force(" + Toma.getForceBack() +")")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
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
