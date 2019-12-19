package me.abir.dailycricketbd.view.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.abir.dailycricketbd.R;

public class ContactUsFragment extends Fragment implements View.OnClickListener {
    Button btContactSubmit;
    EditText etEmail;
    EditText etMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        btContactSubmit = view.findViewById(R.id.btContactSubmit);
        etEmail = view.findViewById(R.id.etEmail);
        etMessage = view.findViewById(R.id.etMessage);
        btContactSubmit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btContactSubmit:
                String email = etEmail.getText().toString();
                String message = etMessage.getText().toString();
                if(email.isEmpty() || message.isEmpty()) {
                    Toast.makeText(getContext(), getString(R.string.msg_incomplete_data), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getContext(), getString(R.string.msg_feedback), Toast.LENGTH_SHORT).show();
                etEmail.setText("");
                etMessage.setText("");
                break;
        }
    }
}
