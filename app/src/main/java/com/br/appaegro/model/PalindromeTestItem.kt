package com.br.appaegro.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class PalindromeTestItem(var wordString: String){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
    var isPalindrome: Boolean = wordString.reversed() == wordString
}