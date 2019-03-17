package com.hacks.radish.util

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * ReadOnlyProperty delegate that only works on nullable fields
 *
 * This will try to initialize the delegated variable using the provided initializer till
 * it returns a non-null value at which point it uses that value
 */
class InitTillNotNull<T: Any?>(private val initializer: () -> T) : ReadOnlyProperty<Any, T?> {
    var backing: T? = null

    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        if (backing == null) {
            backing = initializer()
        }

        return backing
    }
}