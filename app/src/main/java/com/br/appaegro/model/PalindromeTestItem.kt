package com.br.appaegro.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class PalindromeTestItem(wordString: String){
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
    val wordString: String = wordString
    val isPalindrome: Boolean = wordString.reversed() == wordString
}