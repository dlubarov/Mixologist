package com.lubarov.daniel.mixologist.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import com.lubarov.daniel.mixologist.R;
import com.lubarov.daniel.mixologist.events.GarnishOptionalEvent;
import com.lubarov.daniel.mixologist.events.PreferredUnitEvent;
import com.lubarov.daniel.mixologist.quantities.Unit;
import com.lubarov.daniel.mixologist.storage.PreferencesStorage;

public class PreferencesActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences_activity_layout);
        Unit preferredUnit = PreferencesStorage.getPreferredUnit(this);
        ((RadioButton) findViewById(unitToId(preferredUnit))).setChecked(true);
        ((CheckBox) findViewById(R.id.checkbox_garnish)).setChecked(true);
    }

    public void onRadioButtonClicked(View view) {
        if (((RadioButton) view).isChecked()) {
            Unit preferredUnit = idToUnit(view.getId());
            PreferencesStorage.setPreferredUnit(this, preferredUnit);
            PreferredUnitEvent.MANAGER.notifyAll(new PreferredUnitEvent(preferredUnit));
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox_garnish:
                PreferencesStorage.setGarnishOptional(this, checked);
                GarnishOptionalEvent.MANAGER.notifyAll(new GarnishOptionalEvent(checked));
                break;
            default:
                throw new IllegalArgumentException("Unrecognized ID: " + view.getId());
        }
    }

    private static int unitToId(Unit unit) {
        switch (unit) {
            case CL: return R.id.radio_cl;
            case ML: return R.id.radio_ml;
            case OZ: return R.id.radio_oz;
            default: throw new IllegalArgumentException("Unrecognized unit: " + unit);
        }
    }

    private static Unit idToUnit(int radioId) {
        switch (radioId) {
            case R.id.radio_cl: return Unit.CL;
            case R.id.radio_ml: return Unit.ML;
            case R.id.radio_oz: return Unit.OZ;
            default: throw new IllegalArgumentException("Unrecognized ID: " + radioId);
        }
    }
}
