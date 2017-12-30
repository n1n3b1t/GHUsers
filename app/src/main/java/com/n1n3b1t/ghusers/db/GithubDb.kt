package com.n1n3b1t.ghusers.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.n1n3b1t.ghusers.base.SingletonHolder
import com.n1n3b1t.ghusers.dao.UserDao
import com.n1n3b1t.ghusers.entity.User

/**
 * Created by valentintaranenko on 27/12/2017.
 */
@Database(version = 1, entities = [(User::class)])
abstract class GithubDb : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object : SingletonHolder<GithubDb, Context>({ Room.databaseBuilder(it.applicationContext, GithubDb::class.java, "GH.db").build() })
}