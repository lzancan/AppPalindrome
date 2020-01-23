package com.br.appaegro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.br.appaegro.model.PalindromeTestItem

class PalindromeListAdapter(private val context: Context,
                            private val palindromeTestItemList: ArrayList<PalindromeTestItem>) : BaseAdapter() {

    private class PalindromeItemViewHolder {
        internal var tvPalindromeTest: TextView? = null
        internal var tvPalindromeResult: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val viewHolder: PalindromeItemViewHolder

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.list_item_palindrome, parent, false)

            viewHolder = PalindromeItemViewHolder()
            viewHolder.tvPalindromeTest = view.findViewById(R.id.palindromeTest)
            viewHolder.tvPalindromeResult = view.findViewById(R.id.palindromeResult)
        } else {
            //no need to call findViewById, can use existing ones from saved view holder
            viewHolder = view?.tag as PalindromeItemViewHolder
        }

        viewHolder.tvPalindromeTest?.text = palindromeTestItemList[position].wordString
        viewHolder.tvPalindromeResult?.text = if(palindromeTestItemList[position].isPalindrome) "Sim" else "Não"
        view?.tag = viewHolder

        return view!!
    }

    override fun getItem(position: Int): Any {
        return palindromeTestItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return palindromeTestItemList[position].id
    }

    override fun getCount(): Int {
        return palindromeTestItemList.size
    }

}