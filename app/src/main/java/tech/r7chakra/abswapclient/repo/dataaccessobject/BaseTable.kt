package tech.r7chakra.abswapclient.repo.dataaccessobject

import android.content.Context

open class BaseTable(private val context : Context) {
    protected fun doDatabaseOperation(operation : () -> Unit) {

    }
}