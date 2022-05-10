package com.sparkle.cookbook

class ActionClearableReference<T>(private val init: () -> T) {
    private var ref: T? = null
    fun get(): T = ref ?: init().also { ref = it }
    fun tryGet(): T? = ref // Maybe it is not useful method
    fun clear() {
        ref = null
    }
}

class MappedClearableReference<ARG, T>(private val init: (ARG) -> T) {
    private var refs: MutableMap<ARG, T> = mutableMapOf()
    fun get(arg: ARG): T = refs[arg] ?: init(arg).also { refs[arg] = it }
    fun tryGet(arg: ARG): T? = refs[arg]
    fun clear(arg: ARG) = refs.remove(arg)
}
