package com.example.alberto.explorer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Alberto on 15/10/2015.
 */
public class ViewChanger {
    public static View viewtoshow;
    public  static SimpleCursorAdapter adtoshow;

    public static void getview(View view1)
    {
        viewtoshow=view1;
    }

    public static View setview(){

        if(viewtoshow.getParent()!=null)
            ((ViewGroup)viewtoshow.getParent()).removeView(viewtoshow); // <- fix
        return viewtoshow;
    }
    public void func(SimpleCursorAdapter adp){

        adtoshow=adp;
    }

    public SimpleCursorAdapter show()
    {
        return adtoshow;
    }
}
