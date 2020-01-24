package com.br.appaegro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ListView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import androidx.room.Room
import com.br.appaegro.model.AppDatabase
import com.br.appaegro.model.PalindromeTestItem
import com.br.appaegro.model.PalindromeTestItemDao

class MainActivity : AppCompatActivity() {

    lateinit var inputText: AppCompatEditText
    lateinit var buttonOk: AppCompatButton
    lateinit var lvPalindromeList: ListView

    private lateinit var palindromeTestItemDao: PalindromeTestItemDao

    var palindromeListAdapter: PalindromeListAdapter? = null

    var palindromeList: ArrayList<PalindromeTestItem> = ArrayList()

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "appaegro-database")
            .allowMainThreadQueries()
            .build()

        palindromeTestItemDao = database.palindromeTestItemDao()

        inputText = findViewById(R.id.etInput)
        buttonOk = findViewById(R.id.buttonOk)
        lvPalindromeList = findViewById(R.id.listItems)

        setupTextAndButton()

        setupObserver()

        palindromeListAdapter = PalindromeListAdapter(this, palindromeList.asReversed())
        lvPalindromeList.adapter = palindromeListAdapter
    }

    private fun updateView(palindromeTestItemList: List<PalindromeTestItem>?) {
        palindromeList.clear()
        if(!palindromeTestItemList.isNullOrEmpty()) {
            palindromeList.addAll(palindromeTestItemList)
        }
        palindromeListAdapter?.notifyDataSetChanged()
    }

    private fun setupObserver(){
        palindromeTestItemDao.getAllPalindromeItems().observe(this,
            Observer<List<PalindromeTestItem>> {
                updateView(it)
            })
    }

    private fun setupTextAndButton(){
        inputText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                buttonOk.isEnabled = !s.isNullOrEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        buttonOk.setOnClickListener{
            if(inputText.text.toString().isNotEmpty()){
                palindromeTestItemDao.addPalindromeTestItem(PalindromeTestItem(inputText.text.toString()))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }
}
