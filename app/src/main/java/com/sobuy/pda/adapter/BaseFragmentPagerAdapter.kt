package com.sobuy.pda.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

abstract class BaseFragmentPagerAdapter<T>(
    val context: Context,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(
    fragmentManager
) {

    private var datum: MutableList<T> = mutableListOf()

    override fun getCount(): Int {
        return datum.size
    }


    fun setDatum(datum: MutableList<T>) {
        this.datum.clear()
        this.datum.addAll(datum)
        notifyDataSetChanged()
    }

    fun getData(position: Int): T {
        return datum[position]
    }
}