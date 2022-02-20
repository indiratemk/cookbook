package com.sparkle.cookbook.teacore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.cache
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.mutableStateOf

interface Feature<Msg : Any, State : Any, Eff : Any> : Cancelable {
    val currentState: State
    fun accept(msg: Msg)
    fun listenState(listener: (state: State) -> Unit): Cancelable
    fun listenEffect(listener: (eff: Eff) -> Unit): Cancelable
}

@Composable
fun <Msg : Any, State : Any> Feature<Msg, State, *>.Application(
    composable: @Composable (state: State, listener: (Msg) -> Unit) -> Unit
) {
    composable(asComposeState().value, ::accept)
}

@Composable
fun <T : Any> Feature<*, T, *>.asComposeState(): State<T> {
    val state = currentComposer.cache(false) { mutableStateOf(currentState) }
    DisposableEffect(this) {
        val cancelable = listenState { state.value = it }
        onDispose { cancelable.cancel() }
    }
    return state
}
