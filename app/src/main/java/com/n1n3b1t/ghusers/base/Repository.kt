package com.n1n3b1t.ghusers.base

/**
 * Created by valentintaranenko on 26/12/2017.
 */
interface Repository<T> {

    fun add(item: T)
    fun add(item: Iterable<T>)
    fun clear()
    fun get(): List<T>
}