package app

import react.*
import materialui.MuiThemeProvider
import ticker.*

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
}

fun RBuilder.app() = child(App::class) {}
