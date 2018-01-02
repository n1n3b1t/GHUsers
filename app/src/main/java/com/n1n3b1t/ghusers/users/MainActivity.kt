package com.n1n3b1t.ghusers.users

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.n1n3b1t.ghusers.R
import com.n1n3b1t.ghusers.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var userViewMode: UserViewModel

    private var binding: ActivityMainBinding? = null
    private val adapter = UserListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding?.let {
            it.viewPager.adapter = UsersFragmentAdapter(supportFragmentManager)
            it.tabLayout.setupWithViewPager(it.viewPager)
            it.searchRv.layoutManager = LinearLayoutManager(this)
            it.searchRv.addItemDecoration(DividerItemDecoration(this, (it.searchRv.layoutManager as LinearLayoutManager).orientation))
            it.searchRv.adapter = adapter
        }

        userViewMode.results.observe(this, Observer { it?.let { it1 -> adapter.updateUsers(it1) } })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu.findItem(R.id.action_search)
        item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                showSearchList()
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                hideSearchList()
                return true
            }
        })
        (item.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                userViewMode.onQuery(p0)
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                userViewMode.onQuery(p0)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun showSearchList() {
        binding?.let {
            it.searchRv.scaleX = 0f
            it.searchRv.scaleY = 0f
            it.searchRv.visibility = View.VISIBLE
            it.searchRv.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .setDuration(200)
                    .start()
        }
    }

    private fun hideSearchList() {
        binding?.let {
            it.searchRv.animate()
                    .scaleX(0f)
                    .scaleY(0f)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .setDuration(200)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            if (it.searchRv.scaleX == 0f)
                                it.searchRv.visibility = View.GONE
                            super.onAnimationEnd(animation)
                        }
                    })
                    .start()
        }
    }
}
