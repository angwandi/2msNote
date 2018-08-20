package com.example.demad.a2msnote.ActivityFragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demad.a2msnote.NavigationHost;
import com.example.demad.a2msnote.R;

import java.util.Objects;

/**
 * Fragment representing the login screen for Shrine.
 */
public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nt_login_fragment_, container, false);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(getString(R.string.nt_error_password));
                } else {
                    passwordTextInput.setError(null); // Clear the error
                    ((NavigationHost) Objects.requireNonNull(getActivity())).navigateTo(
                            new AllNoteFragment(), false); // Navigate to the next Fragment
                }
            }
        });
        // Clear the error once more than 8 characters are typed.
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });
        passwordEditText.requestFocus(); //remove later please todo
        passwordTextInput.setPasswordVisibilityToggleEnabled(true);
        return view;
    }

    /*
 TODO In reality, this will have more complex logic including, but not limited to, actual
 TODO authentication of the username and password.
*/
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 1;
    }
}
