package store

import async

fun inc(): ((dynamic) -> Unit, () -> State) -> Unit {
    return { dispatch: (dynamic) -> Unit, getState: () -> State ->
        async {
            val state : State = State();
            val count = state.clickCount + 1;//window.fetch("api/count", jsObject{method="POST"; headers= jsObject { "content-type" to "application/json"}}).await().text().await()
            val action = ReduxAction(ActionType.INC_COUNT, IncCount(count.toInt()))()
            dispatch(action)
        }
    }
}