package com.hacks.radish.repo.dataaccessobject

import android.content.Context

open class BaseTable(private val context : Context) {
    protected fun doDatabaseOperation(operation : () -> Unit) {

    }
}