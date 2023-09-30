package com.example.mygames.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygames.R;
import com.example.mygames.Activitys.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragLogin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //MyAddional
    private FirebaseAuth mAuth;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragLogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static fragLogin newInstance(String param1, String param2) {
        fragLogin fragment = new fragLogin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_login, container, false);

        //myCode:
        mAuth = FirebaseAuth.getInstance();
        EditText emailT = v.findViewById(R.id.emailEditTextLoginPage);
        EditText passT = v.findViewById(R.id.passwordEditTextLoginPage);
        MainActivity mainActivity = (MainActivity) getActivity();

        v.findViewById(R.id.registerbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fragLogin_to_fragRegister);
            }
        });


        v.findViewById(R.id.contactButtonLoginpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fragLogin_to_frag_contact);
            }
        });



        v.findViewById(R.id.loginbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailT.getText().toString();
                String password = passT.getText().toString();
                loginFunc(email,password);
                //Navigation.findNavController(v).navigate(R.id.action_fragLogin_to_fragHomepage);
            }
        });

        return v;
    }


    public void loginFunc(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            // Show an error message to the user indicating that fields are empty
            Toast.makeText(getActivity(), "Email and password cannot be empty", Toast.LENGTH_LONG).show();
            return; // Don't proceed with login if fields are empty
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(requireView()).navigate(R.id.action_fragLogin_to_fragHomepage);
                        } else {
                            Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void showCustomToast(String message, int textSizeSP) {
        // Create a custom Toast
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);

        // Get the Toast view
        View toastView = toast.getView();

        // Customize the text size
        TextView toastMessage = toastView.findViewById(android.R.id.message);
        if (toastMessage != null) {
            toastMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP);
        }

        // Show the custom Toast
        toast.show();
    }

}




