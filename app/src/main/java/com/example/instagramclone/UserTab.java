package com.example.instagramclone;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserTab extends Fragment {

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;
    private TextView textView;

    public UserTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users_tab, container, false);
        listView = view.findViewById(R.id.listView);
        textView = view.findViewById(R.id.textView);
        arrayList = new ArrayList();
        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrayList);
        final ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
//        FancyToast.makeText(getContext(), getContext().toString()+"", Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();


        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null) {
                    if (users.size() > 0) {
//                FancyToast.makeText(getContext(), "User is read success ", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                        for (ParseUser user : users) {
                            arrayList.add(user.getUsername());
                        }

                        listView.setAdapter(arrayAdapter);
                        textView.animate().alpha(0).setDuration(2000);
                        listView.setVisibility(View.VISIBLE);
//
                    }
                }

            }
        });


        return view;
    }

}
