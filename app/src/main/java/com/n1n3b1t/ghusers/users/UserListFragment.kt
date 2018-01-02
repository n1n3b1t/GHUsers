package com.n1n3b1t.ghusers.users

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.n1n3b1t.ghusers.R
import com.n1n3b1t.ghusers.databinding.FragmentUserlistBinding
import com.n1n3b1t.ghusers.interactor.UserInteractor
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by valentintaranenko on 02/01/2018.
 */
class UserListFragment : Fragment() {
    @Inject lateinit var userInteractor: UserInteractor
    val adapter = UserListAdapter()

    companion object {
        val START_CHAR = "start_char"
        val END_CHAR = "end_char"
        fun newInstance(startChar: Char, endChar: Char): UserListFragment {
            return UserListFragment().apply {
                arguments = Bundle().apply {
                    putChar(START_CHAR, startChar)
                    putChar(END_CHAR, endChar)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentUserlistBinding>(inflater, R.layout.fragment_userlist, container, false)
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.addItemDecoration(DividerItemDecoration(context, (binding.rv.layoutManager as LinearLayoutManager).orientation))
        binding.rv.adapter = adapter
        userInteractor.getUsers(arguments!!.getChar(START_CHAR), arguments!!.getChar(END_CHAR)).observe(this, Observer { adapter.updateUsers(it!!) })
        return binding.root
    }


}