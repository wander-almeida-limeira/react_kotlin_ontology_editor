@file:JsModule("@material-ui/core/Tab")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val MuiTab: RClass<TabProps>

external interface TabProps : RProps {
    var label: String
    var className: String
}