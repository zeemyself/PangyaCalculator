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
public class Tab2 extends Fragment {
    private TextView resFront,resBack;
    private TextView percenFront,percenBack;
    private TextView caliperFront,caliperBack;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab2,container,false);
        resFront = (TextView) v.findViewById(R.id.resFront);
        resBack = (TextView) v.findViewById(R.id.resBack);
        percenFront = (TextView) v.findViewById(R.id.percenFront);
        percenBack = (TextView) v.findViewById(R.id.percenBack);
        caliperFront = (TextView) v.findViewById(R.id.caliperFront);
        caliperBack = (TextView) v.findViewById(R.id.caliperBack);


        Intent intent = getActivity().getIntent();
        Bundle args = intent.getExtras();
        String toResF = args.getString("toResF");
        String toResB = args.getString("toResB");
        String toPerF = args.getString("toPerF");
        String toPerB = args.getString("toPerB");
        String toCalF = args.getString("toCalF");
        String toCalB = args.getString("toCalB");
        resFront.setText(toResF);
        resBack.setText(toResB);
        percenFront.setText(toPerF);
        percenBack.setText(toPerB);
        caliperFront.setText(toCalF);
        caliperBack.setText(toCalB);
        return v;
    }


}