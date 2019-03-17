package com.hacks.radish.util

fun <T> lazyAndroid(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)
