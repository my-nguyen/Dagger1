package com.nguyen.dagger1.storage

interface Storage {
    fun setString(key: String, value: String)
    fun getString(key: String): String
}
