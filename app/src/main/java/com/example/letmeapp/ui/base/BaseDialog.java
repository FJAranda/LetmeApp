package com.example.letmeapp.ui.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BaseDialog extends DialogFragment {
    public static final String REQUEST = "request";
    public static final String RESULT = "result";
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(getArguments().getString(TITLE));
            builder.setMessage(getArguments().getString(MESSAGE));
            builder.setNegativeButton("Cancelar", (dialog, which) -> dismiss());
            builder.setPositiveButton("Eliminar", (dialog, which) -> {
                Bundle result = new Bundle();
                result.putBoolean(RESULT, true);
                getActivity().getSupportFragmentManager().setFragmentResult(REQUEST, result);
            });
            // Create the AlertDialog object and return it
            return builder.create();
        }
        return null;
    }
}
