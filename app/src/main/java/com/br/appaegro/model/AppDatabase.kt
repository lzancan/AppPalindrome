package com.br.appaegro.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PalindromeTestItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun palindromeTestItemDao(): PalindromeTestItemDao
}