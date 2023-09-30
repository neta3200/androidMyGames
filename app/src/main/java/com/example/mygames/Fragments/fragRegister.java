package com.example.mygames.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mygames.R;
import com.example.mygames.Activitys.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragRegister extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // my addional
    private FirebaseAuth mAuth;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragRegister() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragRegister.
     */
    // TODO: Rename and change types and number of parameters
    public static fragRegister newInstance(String param1, String param2) {
        fragRegister fragment = new fragRegister();
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
        View v = inflater.inflate(R.layout.fragment_frag_register, container, false);

        //my code:
        mAuth = FirebaseAuth.getInstance();
        EditText emailT = v.findViewById(R.id.emailText);
        EditText passT = v.findViewById(R.id.passwordText);
        EditText firstT = v.findViewById(R.id.firstNameText);
        EditText lastT = v.findViewById(R.id.lastNameText);
        EditText phoneT = v.findViewById(R.id.phoneText);

        MainActivity mainActivity = (MainActivity) getActivity();



        v.findViewById(R.id.registerButtonRegPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailT.getText().toString();
                String password = passT.getText().toString();
                String first = firstT.getText().toString();
                String last = lastT.getText().toString();
                String phone = phoneT.getText().toString();

                registerFunc( first, last, email, password, phone );

                //Navigation.findNavController(v).navigate(R.id.action_fragRegister_to_fragHomepage);
            }
        });


        return v;
    }


    public void registerFunc(String firstName, String lastName, String email, String password, String phoneNumber) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Reg Successful", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(requireView()).navigate(R.id.action_fragRegister_to_fragHomepage);

                        } else {
                            Toast.makeText(getActivity(), "Reg Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}