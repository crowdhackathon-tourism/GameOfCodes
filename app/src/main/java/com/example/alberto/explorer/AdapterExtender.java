package com.example.alberto.explorer;

import android.content.Context;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by Alberto on 18/10/2015.
 */
public class AdapterExtender extends SimpleAdapter {


    public AdapterExtender(Context context, List<? extends Map<String, ?>> data,
                           int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setViewImage(ImageView v, String value) {
        UrlImageViewHelper.setUrlDrawable(v, value);
    }
}