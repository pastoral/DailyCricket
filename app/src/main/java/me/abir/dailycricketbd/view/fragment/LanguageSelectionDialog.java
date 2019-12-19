package me.abir.dailycricketbd.view.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.appcompat.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.util.LocaleManager;

public class LanguageSelectionDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private static final String TAG = "LanguageSelectionDialog";

    private TextView tvTitle;
    private TextView tvYes;
    private TextView tvCancel;
    private AppCompatRadioButton rbEnglish;
    private AppCompatRadioButton rbBangla;
    private String appLanguage = null;

    private LanguageSelectionListener selectionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_language_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
    }

    private void initViews(View view) {
        tvTitle = view.findViewById(R.id.tvTitle);
        tvYes = view.findViewById(R.id.tvYes);
        tvCancel = view.findViewById(R.id.tvCancel);
        rbEnglish = view.findViewById(R.id.rbEnglish);
        rbBangla = view.findViewById(R.id.rbBangla);

        tvYes.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        rbEnglish.setOnClickListener(this);
        rbBangla.setOnClickListener(this);

        if (getSelectedLanguage().equals("en")) {
            rbEnglish.setChecked(true);
        } else {
            rbBangla.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvYes:
                setSelectedLanguage();
                sendData();
                dismiss();
                break;
            case R.id.tvCancel:
                dismiss();
                break;
            case R.id.rbEnglish:
                if (rbEnglish.isChecked()) {
                    appLanguage = "en";
                }
                break;
            case R.id.rbBangla:
                if (rbBangla.isChecked()) {
                    appLanguage = "bn";
                }
                break;
            default:
                Log.e(TAG, "onClick() unknown");
                break;
        }
    }

    private void sendData() {
        if (selectionListener != null)
            selectionListener.onLanguageSelected(appLanguage);
    }

    public void setSelectionListener(LanguageSelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }

    public interface LanguageSelectionListener {
        void onLanguageSelected(String selectedLanguage);
    }

    private String getSelectedLanguage() {
        if (getContext() != null) {
            appLanguage = LocaleManager.getLanguage(getContext());
        } else {
            appLanguage = "en";
        }
        return appLanguage;
    }

    private void setSelectedLanguage() {
        if (getContext() != null) {
            Log.w(TAG, "setSelectedLanguage()  saving language: " + appLanguage);
            LocaleManager.persistLanguage(getContext(), appLanguage);
        } else {
            Log.e(TAG, "setSelectedLanguage() couldn't save language");
        }
    }
}
