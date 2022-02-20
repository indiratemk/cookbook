package com.sparkle.cookbook.teacore

class SyncFeature<Msg : Any, State : Any, Eff : Any>(
    initialState: State,
    private val reducer: (Msg, State) -> Pair<State, Set<Eff>>
) : Feature<Msg, State, Eff> {

    override var currentState: State = initialState
        private set

    private var isCanceled = false
    private val stateListeners = mutableListOf<(state: State) -> Unit>()
    private val effListeners = mutableListOf<(eff: Eff) -> Unit>()

    override fun accept(msg: Msg) {
        if (isCanceled) return
        val (newState, commands) = reducer(msg, currentState)
        currentState = newState
        stateListeners.notifyAll(newState)
        commands.forEach { command ->
            effListeners.notifyAll(command)
        }
    }

    // Should immediately invoke for giving initial state
    override fun listenState(listener: (state: State) -> Unit): Cancelable {
        val cancelable = stateListeners.addListenerAndMakeCancelable(listener)
        listener(currentState)
        return cancelable
    }

    override fun listenEffect(listener: (eff: Eff) -> Unit): Cancelable =
        effListeners.addListenerAndMakeCancelable(listener)

    override fun cancel() {
        isCanceled = true
    }
}
