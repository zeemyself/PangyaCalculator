package com.zeemyself.pangyacalculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab3 extends Fragment {
    private TextView resFront,resBack;
    private TextView percenFront,percenBack;
    private TextView caliperFront,caliperBack;
    private TextView spinFront,spinBack;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab3,container,false);
        resFront = (TextView) v.findViewById(R.id.resFrontDunk);
        resBack = (TextView) v.findViewById(R.id.resBackDunk);
        percenFront = (TextView) v.findViewById(R.id.percenFrontDunk);
        percenBack = (TextView) v.findViewById(R.id.percenBackDunk);
        caliperFront = (TextView) v.findViewById(R.id.caliperFrontDunk);
        caliperBack = (TextView) v.findViewById(R.id.caliperBackDunk);
        spinFront = (TextView) v.findViewById(R.id.spinFrontDunk);
        spinBack = (TextView) v.findViewById(R.id.spinBackDunk);

        Intent intent = getActivity().getIntent();
        Bundle args = intent.getExtras();
        String bsResF = args.getString("bsResF");
        String bsResB = args.getString("bsResB");
        String bsPerF = args.getString("bsPerF");
        String bsPerB = args.getString("bsPerB");
        String bsCalF = args.getString("bsCalF");
        String bsCalB = args.getString("bsCalB");
        String bsSpinF = args.getString("bsSpinF");
        String bsSpinB = args.getString("bsSpinB");
        resFront.setText(bsResF);
        resBack.setText(bsResB);
        percenFront.setText(bsPerF);
        percenBack.setText(bsPerB);
        caliperFront.setText(overloadResult(bsPerF,bsCalF));
        caliperBack.setText(overloadResult(bsPerB,bsCalB));
        spinFront.setText(bsSpinF);
        spinBack.setText(bsSpinB);

        if(bsCalF.equals("0.0"))
            caliperFront.setTextColor(Color.RED);
        if(bsCalB.equals("0.0"))
            caliperBack.setTextColor(Color.RED);




        return v;
    }
    public String overloadResult(String percen,String calip){
        if(calip.equals("0.0")) {
            percen = percen.substring(0, percen.length() - 1);
            double tmp = Double.parseDouble(percen);
            tmp = tmp * 280 / 100;
            tmp = Math.floor(tmp * 100)/100;
            return tmp+"";
        }
        else
            return calip;
    }

}