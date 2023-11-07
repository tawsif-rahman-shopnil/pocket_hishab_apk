package com.techwiz.pockethishab.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techwiz.pockethishab.DatabaseHandler;
import com.techwiz.pockethishab.R;
import com.techwiz.pockethishab.adapter.incomeAdapter2;
import com.techwiz.pockethishab.model.incomeModel;

import java.util.ArrayList;
import java.util.List;
public class Income extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Income() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Income.
     */
    // TODO: Rename and change types and number of parameters
    public static Income newInstance(String param1, String param2) {
        Income fragment = new Income();
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

    private TextView tvIncome;
    private RecyclerView rvIncome;
    private List<incomeModel> incomeModelList = new ArrayList<>();
    private DatabaseHandler databaseHandler;
    private String totalIncome;

    private incomeAdapter2 expenseAdapter;

    private ImageView iv_expensePie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        init(view);

        fillExpense();

        return view;
    }

    public void fillExpense() {
        incomeModelList = databaseHandler.getAllIncome();
        int total = 0;
        for (incomeModel model : incomeModelList) {
            total += Integer.parseInt(model.getAmount());
        }
        totalIncome = String.valueOf(total);
        tvIncome.setText("Tk." + totalIncome);

        expenseAdapter = new incomeAdapter2(getContext(), incomeModelList, databaseHandler);
        rvIncome.setLayoutManager(new LinearLayoutManager(getContext()));
        rvIncome.setHasFixedSize(false);

        rvIncome.setAdapter(expenseAdapter);
    }

    private void init(View view) {
        tvIncome = view.findViewById(R.id.tv_income);
        rvIncome = view.findViewById(R.id.rv_income);
        databaseHandler = new DatabaseHandler(getContext());
    }


}