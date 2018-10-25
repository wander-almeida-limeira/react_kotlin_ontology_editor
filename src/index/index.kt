package index

import app.appConnected
import kotlinext.js.require
import kotlinext.js.requireAll
import ktypings.redux.Provider
import ktypings.redux.Redux
import ktypings.redux.ReduxThunk
import react.dom.render
import store.State
import store.mainReducer
import kotlin.browser.document

fun main(args: Array<String>) {

    @JsName("wefjksdjn")
    fun wefjksdjn() {
        console.log("helloWithGreeting")
    }

    requireAll(require.context("src", true, js("/\\.css$/")))

    val store = Redux.createStore(::mainReducer, State(),
            Redux.applyMiddleware(ReduxThunk).asDynamic())

    render(document.getElementById("root")) {
        Provider() {
            attrs.store = store
            appConnected{}
        }
    }
}












