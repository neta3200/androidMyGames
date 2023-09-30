package com.example.mygames.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mygames.Adapters.CustomAdapter;
import com.example.mygames.Models.DataModel;
import com.example.mygames.R;
import com.example.mygames.Services.DataService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragHomepage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragHomepage extends Fragment {

    private EditText searchEditText;
    private Button searchButton;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomAdapter customAdapter;

    private CustomAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragHomepage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragHomepage.
     */
    // TODO: Rename and change types and number of parameters
    public static fragHomepage newInstance(String param1, String param2) {
        fragHomepage fragment = new fragHomepage();
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
        View v =  inflater.inflate(R.layout.fragment_frag_homepage, container, false);
        recyclerView = v.findViewById(R.id.recView);

        searchEditText = v.findViewById(R.id.searchEditText);
        searchButton = v.findViewById(R.id.searchButton);
        // Set an onClickListener for the search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch();
            }
        });

        layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<DataModel> dataSet = DataService.getArrState("https://www.freetogame.com/api/games");


        adapter = new CustomAdapter(dataSet);
        recyclerView.setAdapter(adapter);


        return v;
    }


    private void performSearch() {
        String query = searchEditText.getText().toString().trim();

        // Check if the search query is empty
        if (!query.isEmpty()) {
            // Append the query to your API URL
            String apiUrl = "https://www.freetogame.com/api/games?category=" + query;

                // Make an API call with the updated URL
                ArrayList<DataModel> searchResults = DataService.getArrState(apiUrl);

                if (searchResults != null) {
                    adapter.updateData(searchResults);
                } else {
                    Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT).show();
                }
        } else {
            // Handle the case when the search query is empty
            // For example, show a message to enter a valid search query
            String apiUrl = "https://www.freetogame.com/api/games";
            ArrayList<DataModel> searchResults = DataService.getArrState(apiUrl);
            adapter.updateData(searchResults);
            //Toast.makeText(requireContext(), "Please enter a search query", Toast.LENGTH_SHORT).show();
        }
    }
}