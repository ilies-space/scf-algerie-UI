package com.mbg.scf.adapters;



import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.mbg.scf.R;
import com.mbg.scf.classes.Continent;
import com.mbg.scf.classes.Items;

import static com.mbg.scf.homePage.Lan;


public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Continent> continentList;
    private ArrayList<Continent> originalList;

    public MyListAdapter(Context context, ArrayList<Continent> continentList) {
        this.context = context;
        this.continentList = new ArrayList<Continent>();
        this.continentList.addAll(continentList);
        this.originalList = new ArrayList<Continent>();
        this.originalList.addAll(continentList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Items> itemsList = continentList.get(groupPosition).getItemsList();
        return itemsList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        Items items = (Items) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (Lan.equals("infofr.db"))
            {
                view = layoutInflater.inflate(R.layout.child_row_fr, null);

            }else {
                view = layoutInflater.inflate(R.layout.child_row_ar, null);

            }

        }

        TextView code = (TextView) view.findViewById(R.id.code);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView description = (TextView) view.findViewById(R.id.description);

        code.setText(items.getCode());

        name.setText(items.getName());
        description.setText(items.getDescription());

        switch (groupPosition)
        {
            case 0 :
                description.setTextColor(context.getResources().getColor(R.color.color1));

                break;
            case 1 :
                description.setTextColor(context.getResources().getColor(R.color.color2));
                break;

            case 2 :
                description.setTextColor(context.getResources().getColor(R.color.color3));
                break;

            case 3 :
                description.setTextColor(context.getResources().getColor(R.color.color4));
                break;
            case 4 :
                description.setTextColor(context.getResources().getColor(R.color.color5));
                break;

            case 5 :
                description.setTextColor(context.getResources().getColor(R.color.color6));
                break;

            case 6 :
                description.setTextColor(context.getResources().getColor(R.color.color7));
                break;
        }

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<Items> itemsList = continentList.get(groupPosition).getItemsList();
        return itemsList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return continentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return continentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        Continent continent = (Continent) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (Lan.equals("infofr.db"))
            {
                view = layoutInflater.inflate(R.layout.group_row_fr, null);

            }else {
                view = layoutInflater.inflate(R.layout.group_row_ar, null);

            }        }



        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(continent.getName().trim());

        switch (groupPosition)
        {
            case 0 :
                heading.setBackgroundResource(R.color.color1);
                break;
            case 1 :
                heading.setBackgroundResource(R.color.color2);
                break;

            case 2 :
                heading.setBackgroundResource(R.color.color3);
                break;

            case 3 :
                heading.setBackgroundResource(R.color.color4);
                break;
            case 4 :
                heading.setBackgroundResource(R.color.color5);
                break;

            case 5 :
                heading.setBackgroundResource(R.color.color6);
                break;

            case 6 :
                heading.setBackgroundResource(R.color.color7);
                break;
        }

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        continentList.clear();

        if(query.isEmpty()){
            continentList.addAll(originalList);
        }
        else {

            for(Continent continent: originalList){

                ArrayList<Items> itemsList = continent.getItemsList();
                ArrayList<Items> newList = new ArrayList<Items>();
                for(Items items : itemsList){
                    if(

                            items.getDescription().toLowerCase().contains(query) ||
                            items.getName().toLowerCase().contains(query)){
                        newList.add(items);
                    }
                }
                if(newList.size() > 0){
                    Continent nContinent = new Continent(continent.getName(),newList);
                    continentList.add(nContinent);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        notifyDataSetChanged();

    }

}