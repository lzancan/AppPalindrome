package com.br.appaegro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.room.Room
import com.br.appaegro.model.AppDatabase
import com.br.appaegro.model.PalindromeTestItem
import com.br.appaegro.model.PalindromeTestItemDao

class MainActivity : AppCompatActivity() {

    lateinit var inputText: AppCompatEditText
    lateinit var buttonOk: AppCompatButton
    lateinit var textResult: TextView
    lateinit var lvPalindromeList: ListView

    private lateinit var palindromeTestItemDao: PalindromeTestItemDao

    var palindromeListAdapter: PalindromeListAdapter? = null

    var palindromeList: ArrayList<PalindromeTestItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "appaegro-database")
            .allowMainThreadQueries()
            .build()
        palindromeTestItemDao = database.palindromeTestItemDao()

        palindromeList = ArrayList(palindromeTestItemDao.getAllPalindromeItems())

        inputText = findViewById(R.id.etInput)
        buttonOk = findViewById(R.id.buttonOk)
        lvPalindromeList = findViewById(R.id.listItems)

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
                val palindromeTestItem = PalindromeTestItem(inputText.text.toString())
                palindromeTestItemDao.addPalindromeTestItem(palindromeTestItem)
                palindromeList.add(palindromeTestItem)
                palindromeListAdapter?.notifyDataSetChanged()
            }
        }

        palindromeListAdapter = PalindromeListAdapter(this, ArrayList(palindromeList.asReversed()))
        lvPalindromeList.adapter = palindromeListAdapter
    }
}
