package app

import externalComponents.materialui.MuiThemeProvider
import kotlinext.js.jsObject
import ktypings.redux.connect
import react.*
import store.State
import tabGroup

val appConnect =
        connect<App.AppProps, State>(
                { state: State, _ ->
                    jsObject{clickCount = state.clickCount}
                }, { dispatch, _ ->
            jsObject {
                incAction = {
                    dispatch(store.inc())
                }
            }
        })

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        MuiThemeProvider () {
            attrs { theme = js("""theme""") }
            tabGroup()
        }
/*       div("App-header") {
        p("App-ticker") {
            val num: Int = 0;
            ticker(startFrom=800)
        }*/

    }
    data class AppProps(var clickCount: Int = 0, var incAction: () -> Unit = {}): RProps
}

fun RBuilder.app() = child(App::class) {}
val appConnected: RClass<App.AppProps> = appConnect(App::class.js)
