/**
 * Sets up the text messages for the description of the game, the creators, and citations
 */
package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpScreen extends AppCompatActivity {
    TextView linkCreators;
    TextView linkCiations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_screen);

        linkCreators = findViewById(R.id.tv_creators);
        linkCreators.setMovementMethod(LinkMovementMethod.getInstance());

        linkCiations = findViewById(R.id.tv_citations);
        linkCiations.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, HelpScreen.class);
    }
}