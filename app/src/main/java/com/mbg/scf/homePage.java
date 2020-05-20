package com.mbg.scf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.mbg.scf.DataBase.DbHelper;
import com.mbg.scf.adapters.MyListAdapter;
import com.mbg.scf.classes.Continent;
import com.mbg.scf.classes.Items;

import java.util.ArrayList;

public class homePage extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    //content search bar
    private SearchView search;

    //stat of the expandableList
    public int collapsStat ;

    //expand/collapse Button
    public ImageButton colpsBtn ;

    //number of current Class :
    public String[] ClassNumber;

    //ExpandableListView component
    private ExpandableListView myList;

    //ExpandableListView data holder :
    private ArrayList<Continent> continentList = new ArrayList<Continent>();

    //ExpandableListView Titles :
    public String[] title;


    //listAdapter :
    public MyListAdapter listAdapter ;

    //DataBase Helper object :
    public DbHelper db ;
    //content Language
    public static String Lan ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //from StartPage activity :
        Intent intent = getIntent();
        Lan = intent.getStringExtra("lan");

        //initialize search box :
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        //search box hint Language :
        switch (Lan){
            case "infofr.db" :
                search.setQueryHint("Rechercher dans la liste");
                break;
            case "infoar.db" :
                search.setQueryHint("ابحث في القائمة");
                break;
        }

        //initialize expandableList Stat & button :
        collapsStat =1 ;
        colpsBtn = findViewById(R.id.colpsBtn);


        //Create new DBHelper Object :
        db = new DbHelper(this, Lan);

        //retrieve data to the ui from the SQLDB
        displayList();

        //expand all items on Start :
        expandAll();


    }


    //method to expand all groups
    private void displayList() {

        //display the list
        loadData();


        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.expandableList);
        //create the adapter by passing your ArrayList data
        listAdapter = new MyListAdapter(homePage.this, continentList);
        //attach the adapter to the list
        myList.setAdapter(listAdapter);

        // click listener :

        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                TextView code = (TextView) v.findViewById(R.id.code);
                TextView name = (TextView) v.findViewById(R.id.name);
                TextView population = (TextView) v.findViewById(R.id.description);

                String codeValue= code.getText().toString();
                String nameValue= name.getText().toString();
                String populationValue= population.getText().toString();


                Intent myIntent2 = new Intent(homePage.this, detailsPage.class);
                myIntent2.putExtra("codeValue", codeValue);
                myIntent2.putExtra("nameValue", nameValue);
                myIntent2.putExtra("descriptionValue", populationValue);
                homePage.this.startActivity(myIntent2);

                //Toast.makeText(MainActivity.this, ""+codeValue +"\n"+nameValue + "\n" +populationValue, Toast.LENGTH_LONG).show();

                return true;
            }
        });

    }

    // Load Data from the dataBAse :
    private void loadData() {

        if (Lan.equals("infofr.db"))
        {
            title = new String[]{"" +
                    "Classe 1 Comptes de capitaux",
                    "Classe 2 Comptes d’immobilisations",
                    "Classe 3 Comptes de stocks et en-cours",
                    "Classe 4 Comptes de tiers",
                    "Classe 5 Comptes financiers",
                    "Classe 6 Comptes de charges",
                    "Classe 7  Comptes de produits"
            };
        }else
        {
            title = new String[]{
                    "الصنف 1 حسابات رؤوس الأموال",
                    "الصنف 2 حسابات التثبيتات",
                    "الصنف 3 حسابات المخزونات والمنتوجات\n قيد التنفيذ ",
                    "الصنف 4 حسابات الغير",
                    "الصنف 5 الحسابات المالية",
                    "الصنف 6 حسابات الأعباء ",
                    "الصنف 7 حسابات المنتوجات"

            };
        }

        //class 1

        ArrayList<Items> x = db.getClass("class1");
        Continent continent = new Continent(title[0], x);
        continentList.add(continent);



        int i =1;

        ClassNumber = new String[]{"" +
                "class2",
                "class3",
                "class4",
                "class5",
                "class6",
                "class7",
        };

        //class 2 To class 7
        do {
            ArrayList<Items> x2 = db.getClass(ClassNumber[i-1]);
            continent = new Continent(title[i], x2);
            continentList.add(continent);
            i++;

        }while (i<7);


    }

    //When click on back Button :
    @Override
    public void onBackPressed() {
        this.finish();
    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);

        }
    }

    //method to collaps all groups
    private void collapsAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.collapseGroup(i);

        }
    }




    public void expand(View view) {

        if (collapsStat == 0)
        {
            expandAll();
            collapsStat = 1;
            colpsBtn.setImageResource(R.drawable.colapsmin);

        }else
        {
            collapsAll();
            collapsStat =0;
            colpsBtn.setImageResource(R.drawable.collpasplus);

        }


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }
}
