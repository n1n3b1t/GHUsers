package com.n1n3b1t.ghusers.users

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by valentintaranenko on 02/01/2018.
 */
class UsersFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    companion object {
        val CATEGORIES = arrayOf('a', 'h', 'i', 'p', 'q', 'z')
    }

    override fun getItem(position: Int): Fragment {
        return UserListFragment.newInstance(CATEGORIES[position * 2], CATEGORIES[position * 2 + 1])
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "${CATEGORIES[position * 2].toUpperCase()}-${CATEGORIES[position * 2 + 1].toUpperCase()}"
    }

    override fun getCount(): Int = CATEGORIES.size / 2

}