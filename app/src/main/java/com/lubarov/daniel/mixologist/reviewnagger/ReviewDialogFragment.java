package com.lubarov.daniel.mixologist.reviewnagger;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import com.lubarov.daniel.mixologist.R;

public class ReviewDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.rate_message)
                .setPositiveButton(R.string.rate, new RateListener())
                .setNeutralButton(R.string.later, new LaterListener())
                .setNegativeButton(R.string.never, new NeverListener())
                .create();
    }

    private class RateListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            getPreferences().edit().putBoolean("dont_nag_for_review", true).commit();
            getActivity().startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=com.lubarov.daniel.mixologist")));
        }
    }

    private class LaterListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            getPreferences().edit().putLong("time_last_review_delay_ms", System.currentTimeMillis()).commit();
        }
    }

    private class NeverListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            getPreferences().edit().putBoolean("dont_nag_for_review", true).commit();
        }
    }

    private SharedPreferences getPreferences() {
        return getActivity().getSharedPreferences("mixologist", 0);
    }
}
