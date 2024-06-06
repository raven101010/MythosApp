package com.example.geeeeek

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class MainActivity2 : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>
    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: AdapterClass
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClass>

    private lateinit var textView: TextView
    private lateinit var sharedPreferences: SharedPreferences




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView = findViewById(R.id.textView)

        sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE)
        val text = sharedPreferences.getString("text", "")
        textView.text = text



        imageList = arrayOf(
            R.drawable.zeusicon,
            R.drawable.poseidonicon,
            R.drawable.hadesicon,
            R.drawable.athenaicon,
            R.drawable.apolloicon,
            R.drawable.aresicon,
            R.drawable.artemisicon,
            R.drawable.aphroditeicon,
            R.drawable.heraicon,
            R.drawable.hermesicon,
            R.drawable.dionysusicon,
            R.drawable.hephaestusicon,
            R.drawable.demetericon,)
        titleList = arrayOf(
            "Zeus",
            "Poseidon",
            "Hades",
            "Athena",
            "Apollo",
            "Ares",
            "Artemis",
            "Aphrodite",
            "Hera",
            "Hermes",
            "Dionysus",
            "Hephaestus",
            "Demeter")
        descList = arrayOf(
            getString(R.string.Zeus),
            getString(R.string.Poseidon),
            getString(R.string.Hades),
            getString(R.string.Athena),
            getString(R.string.Apollo),
            getString(R.string.Ares),
            getString(R.string.Artemis),
            getString(R.string.Aphrodite),
            getString(R.string.Hera),
            getString(R.string.Hermes),
            getString(R.string.Dionysus),
            getString(R.string.Hephaestus),
            getString(R.string.Demeter))

        detailImageList = arrayOf(
            R.drawable.zeusicon,
            R.drawable.poseidonicon,
            R.drawable.hadesicon,
            R.drawable.athenaicon,
            R.drawable.apolloicon,
            R.drawable.aresicon,
            R.drawable.artemisicon,
            R.drawable.aphroditeicon,
            R.drawable.heraicon,
            R.drawable.hermesicon,
            R.drawable.dionysusicon,
            R.drawable.hephaestusicon,
            R.drawable.demetericon,)


        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        dataList = arrayListOf<DataClass>()
        searchList = arrayListOf<DataClass>()
        getData()


        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    dataList.forEach{
                        if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })
        myAdapter = AdapterClass(searchList)
        recyclerView.adapter = myAdapter
        myAdapter.onItemClick = {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }



    }
    private fun getData(){
        for (i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i], descList[i], detailImageList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = AdapterClass(searchList)
    }


}






