package com.aslan.friendsfinder;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aslan.friendsfinder.model.User;

import java.util.List;


public class FriendsFragment extends Fragment {
    // The fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "friends";

    // Array of friends
    private User[] friends;

    private OnFragmentInteractionListener mListener;

    // RecyclerView for list of friends
    private RecyclerView recyclerView;
    // RecyclerView adapter
    private RecyclerView.Adapter adapter;
    // RecyclerView layout manager
    private RecyclerView.LayoutManager layoutManager;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param friends List of users.
     * @return A new instance of fragment FriendsFragment.
     */
    public static FriendsFragment newInstance(User[] friends) {
        FriendsFragment fragment = new FriendsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, friends);
        fragment.setArguments(args);
        return fragment;
    }

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            friends = (User[]) getArguments().getSerializable(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new FriendsAdapter(friends);
        layoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
