package com.example.assignment3;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AlertMessageFragment extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //create view

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.alert_message, null);

        Button button = v.findViewById(R.id.btnOk);
        button.setOnClickListener(w->{
            Intent intent = new Intent(getActivity(), MainMenu.class);
            startActivity(intent);
        });


        //build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Game Over!")
                .setMessage("You found all the mines")
                .setView(v)
                .create();
    }
}
