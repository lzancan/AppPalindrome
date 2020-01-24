package com.br.appaegro

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.br.appaegro.model.AppDatabase
import com.br.appaegro.model.PalindromeTestItem
import com.br.appaegro.model.PalindromeTestItemDao
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var palindromeTestItemDao: PalindromeTestItemDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java).build()
        palindromeTestItemDao = db.palindromeTestItemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadPalindromeTestItem() {
        palindromeTestItemDao.addPalindromeTestItem(PalindromeTestItem("agua"))
        palindromeTestItemDao.addPalindromeTestItem(PalindromeTestItem("aba"))
        palindromeTestItemDao.addPalindromeTestItem(PalindromeTestItem("alala"))

        val palindromeTestItemInDatabase = palindromeTestItemDao.getAllItems()
        assert(palindromeTestItemInDatabase.size == 3
                && !palindromeTestItemInDatabase[0].isPalindrome
                && palindromeTestItemInDatabase[1].isPalindrome
                && palindromeTestItemInDatabase[2].isPalindrome)
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.br.appaegro", appContext.packageName)
    }
}
