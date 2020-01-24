package com.br.appaegro.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PalindromeTestItemDao {

    @Query("SELECT * FROM palindrometestitem")
    fun getAllPalindromeItems(): LiveData<List<PalindromeTestItem>>

    @Query("SELECT * FROM palindrometestitem")
    fun getAllItems(): List<PalindromeTestItem>

    @Insert
    fun addPalindromeTestItem(vararg palindromeTestItem: PalindromeTestItem)

}