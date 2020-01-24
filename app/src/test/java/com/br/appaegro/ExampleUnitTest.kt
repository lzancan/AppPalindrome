package com.br.appaegro

import com.br.appaegro.model.PalindromeTestItem
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun nameCreatePalindromeTestItem(){
        val palindromeItem = PalindromeTestItem("aba")
        assert(palindromeItem.wordString == "aba")
    }

    @Test
    fun booleanCreatePalindromeTestItem(){
        val palindromeItem = PalindromeTestItem("aba")
        assert(palindromeItem.isPalindrome)
    }

    @Test
    fun createNonPalindromeTestItem(){
        val palindromeItem = PalindromeTestItem("água")
        assert(!palindromeItem.isPalindrome)
    }
}
