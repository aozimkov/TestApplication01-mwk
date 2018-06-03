package com.example.android.testapplication01;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by def on 08.04.18.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int categoryColor;

    public WordAdapter(Activity context, ArrayList<Word> words, int boxColor) {
        super(context, 0, words);

        categoryColor = boxColor;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        RelativeLayout textBox = (RelativeLayout) listItemView.findViewById(R.id.text_box);
        int color = ContextCompat.getColor(getContext(), categoryColor);
        textBox.setBackgroundColor(color);

        ImageView wordImageView = (ImageView) listItemView.findViewById(R.id.word_image_view);
        if(currentWord.hasWordImage() ) {
            wordImageView.setImageResource(currentWord.getmWordImage());
            wordImageView.setVisibility(View.VISIBLE);
        }else {
            wordImageView.setVisibility(View.GONE);
        }

        TextView defaultTextView = (TextView)listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        TextView miwokTextView = (TextView)listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        return listItemView;
    }
}
