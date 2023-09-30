package com.example.mygames.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mygames.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragContact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragContact extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    Button contactButton1;

    private Context mContext;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragContact() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_contact.
     */
    // TODO: Rename and change types and number of parameters
    public static fragContact newInstance(String param1, String param2) {
        fragContact fragment = new fragContact();
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
        View v = inflater.inflate(R.layout.fragment_frag_contact, container, false);

        contactButton1 = v.findViewById(R.id.contactButton);
        contactButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(v);
            }
        });

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void sendEmail(View view) {
        EditText editTextName = requireView().findViewById(R.id.editTextName);
        EditText editTextEmail = requireView().findViewById(R.id.editTextEmail);
        EditText editTextMessage = requireView().findViewById(R.id.editTextMessage);

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String message = editTextMessage.getText().toString();

        // Validate user input (e.g., check if email is valid)

        // Create an email intent and send the email
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:neta3200@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact from " + name);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Name: " + name + "\nEmail: " + email + "\n\n" + message);

        if (emailIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(emailIntent);
        } else {
            //Toast.makeText(requireContext(), "No email client available.", Toast.LENGTH_SHORT).show();

            // Fallback to open a web browser with a mailto link
            String mailto = "mailto:neta3200@gmail.com" +
                    "?subject=" + Uri.encode("Contact from " + name) +
                    "&body=" + Uri.encode("Name: " + name + "\nEmail: " + email + "\n\n" + message);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mailto));
            startActivity(browserIntent);

        }
    }

}