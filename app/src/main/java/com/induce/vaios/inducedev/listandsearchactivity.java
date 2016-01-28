package com.induce.vaios.inducedev;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;

/**
 * Created by Vaios on 28/01/2016.
 */
public class listandsearchactivity extends Activity implements SearchView.OnQueryTextListener,
        SearchView.OnCloseListener


{
    private ListView mListView;
    private SearchView searchView;
    private productsdbadapter mDbHelper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listandsearch);

        CardArrayRecyclerViewAdapter mCardArrayAdapter;
        ArrayList<Card> cards = new ArrayList<Card>();





        mCardArrayAdapter = new CardArrayRecyclerViewAdapter(this, cards);
        //Staggered grid view
        CardRecyclerView mRecyclerView = (CardRecyclerView) this.findViewById(R.id.carddemo_recyclerviewup);

        mRecyclerView.setHasFixedSize(false);

       mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
       // mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Set the empty view
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
        }
        cards.add(new customlistscardup(this,R.drawable.ic_account_circle_black_36dp,mCardArrayAdapter));
        mCardArrayAdapter.notifyDataSetChanged();
        cards.add(new customlistscardup(this,R.drawable.background,mCardArrayAdapter));
        mCardArrayAdapter.notifyDataSetChanged();
        cards.add(new customlistscardup(this,R.drawable.background,mCardArrayAdapter));

        mCardArrayAdapter.notifyDataSetChanged();


        //part about search activity
        searchView = (SearchView) findViewById(R.id.search);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

        mListView = (ListView) findViewById(R.id.list);


        mDbHelper = new productsdbadapter(this);
        mDbHelper.open();
        mDbHelper.deleteAllCustomers();
        mDbHelper.createCustomer("PIZZA1", "Pizza Hut", "1107 West Adams Boulevard", "", "Los Angeles", "CA", "90007");
        mDbHelper.createCustomer("PIZZA2", "Pizza Hut", "1562 West Pico Boulevard", "", "Los Angeles", "CA", "90015");
        mDbHelper.createCustomer("PIZZA3", "Pizza Hut", "718 South Los Angeles Street", "", "Los Angeles", "CA", "90014");
        mDbHelper.createCustomer("PIZZA4", "Pizza Hut", "2542 West Temple Street", "", "Los Angeles", "CA", "90026");
        mDbHelper.createCustomer("PIZZA5", "Pizza Hut", "4329 North Figueroa Street", "", "Los Angeles", "CA", "90065");


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDbHelper  != null) {
            mDbHelper.close();
        }
    }

    public boolean onQueryTextChange(String newText) {
        showResults(newText + "*");
        return false;
    }

    public boolean onQueryTextSubmit(String query) {
        showResults(query + "*");
        return false;
    }

    public boolean onClose() {
        showResults("");
        return false;
    }


    private void showResults(String query) {

        Cursor cursor = mDbHelper.searchCustomer((query != null ? query.toString() : "@@@@"));

        if (cursor == null) {
            //
        } else {
            // Specify the columns we want to display in the result
            String[] from = new String[] {
                    productsdbadapter.KEY_CUSTOMER,
                    productsdbadapter.KEY_NAME,
                    productsdbadapter.KEY_ADDRESS,
                    productsdbadapter.KEY_CITY,
                    productsdbadapter.KEY_STATE,
                    productsdbadapter.KEY_ZIP};
            // Specify the Corresponding layout elements where we want the columns to go
            int[] to = new int[] {     R.id.scustomer,
                    R.id.sname,
                    R.id.saddress,
                    R.id.scity,
                    R.id.sstate,
                    R.id.szipCode};


            SimpleCursorAdapter customers = new SimpleCursorAdapter(this,R.layout.customerresult, cursor, from, to);
            mListView.setAdapter(customers);



            // Define the on-click listener for the list items
            mListView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the cursor, positioned to the corresponding row in the result set
                    Cursor cursor = (Cursor) mListView.getItemAtPosition(position);

                    // Get the state's capital from this row in the database.
                    String customer = cursor.getString(cursor.getColumnIndexOrThrow("customer"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                    String city = cursor.getString(cursor.getColumnIndexOrThrow("city"));
                    String state = cursor.getString(cursor.getColumnIndexOrThrow("state"));
                    String zipCode = cursor.getString(cursor.getColumnIndexOrThrow("zipCode"));



                    System.out.println(customer+name+address+city+state+zipCode);

                    searchView.setQuery("",true);
                }
            });
        }
    }


}
