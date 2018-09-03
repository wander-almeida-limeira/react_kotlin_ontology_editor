@file:JsModule("@material-ui/core/styles/MuiThemeProvider")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val MuiThemeProvider: RClass<MuiThemeProviderProps>

external interface MuiThemeProviderProps : RProps {
    var theme : dynamic
    var className: String
}
