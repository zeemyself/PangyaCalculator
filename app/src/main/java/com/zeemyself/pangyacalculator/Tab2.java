package com.zeemyself.pangyacalculator;

import android.content.Intent;
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
public class Tab2 extends Fragment {
    private TextView resFront,resBack;
    private TextView percenFront,percenBack;
    private TextView caliperFront,caliperBack;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab2,container,false);
        resFront = (TextView) v.findViewById(R.id.resFront);
        resFront.setText("555");
        return v;
    }


}