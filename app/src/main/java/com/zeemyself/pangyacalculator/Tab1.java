package com.zeemyself.pangyacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab1 extends Fragment {
    private TextView resFront,resBack;
    private TextView percenFront,percenBack;
    private TextView caliperFront,caliperBack;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1,container,false);
        resFront = (TextView) v.findViewById(R.id.resFrontDunk);
        resBack = (TextView) v.findViewById(R.id.resBackDunk);
        percenFront = (TextView) v.findViewById(R.id.percenFrontDunk);
        percenBack = (TextView) v.findViewById(R.id.percenBackDunk);
        caliperFront = (TextView) v.findViewById(R.id.caliperFrontDunk);
        caliperBack = (TextView) v.findViewById(R.id.caliperBackDunk);


        Intent intent = getActivity().getIntent();
        Bundle args = intent.getExtras();
        String dunkResF = args.getString("dunkResF");
        String dunkResB = args.getString("dunkResB");
        String dunkPerF = args.getString("dunkPerF");
        String dunkPerB = args.getString("dunkPerB");
        String dunkCalF = args.getString("dunkCalF");
        String dunkCalB = args.getString("dunkCalB");
        resFront.setText(dunkResF);
        resBack.setText(dunkResB);
        percenFront.setText(dunkPerF);
        percenBack.setText(dunkPerB);
        caliperFront.setText(dunkCalF);
        caliperBack.setText(dunkCalB);
        return v;
    }


}