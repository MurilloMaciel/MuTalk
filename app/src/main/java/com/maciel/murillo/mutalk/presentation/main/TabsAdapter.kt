package com.maciel.murillo.mutalk.presentation.main

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.maciel.murillo.mutalk.R
import java.lang.Exception

private val TAB_TITLES = arrayOf(
    R.string.tab_name_chats,
    R.string.tab_name_contacts
)

class TabsAdapter(
    fragmentManager: FragmentManager,
    private val context: Context,
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = when (position) {
        0 -> ChatsFragment.newInstance()
        1 -> ContactsFragment.newInstance()
        else -> throw Exception("Error finding tab fragment")
    }

    override fun getPageTitle(position: Int) = context.resources.getString(TAB_TITLES[position])

    override fun getCount() = TAB_TITLES.size
}