package com.example.filterrv

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ExampleAdapter
    private lateinit var exampleList: List<ExampleItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fillExampleList()
        setUpRecyclerView()
    }

    private fun fillExampleList() {
        exampleList = ArrayList()
        val list = exampleList as ArrayList<ExampleItem>
        list.add(ExampleItem(R.drawable.ic_android, "One", "Ten"))
        list.add(ExampleItem(R.drawable.ic_audio, "Two", "Eleven"))
        list.add(ExampleItem(R.drawable.ic_sun, "Three", "Twelve"))
        list.add(ExampleItem(R.drawable.ic_android, "Four", "Thirteen"))
        list.add(ExampleItem(R.drawable.ic_audio, "Five", "Fourteen"))
        list.add(ExampleItem(R.drawable.ic_sun, "Six", "Fifteen"))
        list.add(ExampleItem(R.drawable.ic_android, "Seven", "Sixteen"))
        list.add(ExampleItem(R.drawable.ic_audio, "Eight", "Seventeen"))
        list.add(ExampleItem(R.drawable.ic_sun, "Nine", "Eighteen"))
    }

    private fun setUpRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        adapter = ExampleAdapter(exampleList as ArrayList<ExampleItem>)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.example_menu, menu)

        val searchItem: MenuItem = menu!!.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        return true
    }
}