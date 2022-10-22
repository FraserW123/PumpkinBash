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
    TextView linkCitations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_screen);

        linkCreators = findViewById(R.id.tv_creators);
        linkCreators.setMovementMethod(LinkMovementMethod.getInstance());

        linkCitations = findViewById(R.id.tv_citations);
        linkCitations.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, HelpScreen.class);
    }
}