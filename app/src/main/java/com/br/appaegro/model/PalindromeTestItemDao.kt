package com.br.appaegro.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PalindromeTestItemDao {

    @Query("SELECT * FROM palindrometestitem")
    fun getAllPalindromeItems(): List<PalindromeTestItem>

    @Insert()
    fun addPalindromeTestItem(vararg palindromeTestItem: PalindromeTestItem)

}