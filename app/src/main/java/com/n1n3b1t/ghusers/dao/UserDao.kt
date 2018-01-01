package com.n1n3b1t.ghusers.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.n1n3b1t.ghusers.entity.User
import io.reactivex.Single

/**
 * Created by valentintaranenko on 27/12/2017.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE login GLOB '[a-f]*'")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE login GLOB :filterString ORDER BY login ASC")
    fun getUsers(filterString: String): LiveData<List<User>>

    @Query("SELECT * FROM User ORDER BY id DESC LIMIT 1")
    fun getLastUser(): Single<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)
}