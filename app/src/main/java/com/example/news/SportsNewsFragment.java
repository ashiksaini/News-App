package com.example.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SportsNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SportsNewsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ProgressBar mProgressBar;
    /**
     * @param sportsNewsURL    - Url to the Sports category news.
     */
    String sportsNewsURL;

    /**
     * @param sportsNewsList   - List of Sports News.
     */

    List<News> sportsNewsList = new ArrayList<>();

    public SportsNewsFragment(String sportsNewsURL) {
        this.sportsNewsURL = sportsNewsURL;
    }

    public SportsNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SportsNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SportsNewsFragment newInstance(String param1, String param2) {
        SportsNewsFragment fragment = new SportsNewsFragment();
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
        View view = inflater.inflate(R.layout.fragment_general_news, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mProgressBar = view.findViewById(R.id.progress_circular);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(view.getContext());

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET,
                sportsNewsURL,
                null, (JSONObject response) -> {
            try {
                JSONArray item = null;
                item = response.getJSONArray("articles");
                for(int i = 0; i < item.length(); i++) {

                    JSONObject current_news = item.getJSONObject(i);
                    JSONObject src = current_news.getJSONObject("source");

                    String news_name = src.getString("name");
                    String news_title = current_news.getString("title");
                    String urlToImage = current_news.getString("urlToImage");
                    String urlToNews = current_news.getString("url");
                    String news_time = current_news.getString("publishedAt");

                    Log.i("Title", news_title);

                    sportsNewsList.add(new News(urlToImage, urlToNews, news_title, news_name, news_time));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.i("INFO", "Something went wrong " + error.networkResponse.toString()));

        requestQueue.add(objectRequest);

        mProgressBar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(new NewsAdapter(view.getContext() ,sportsNewsList));
        return view;
    }
}