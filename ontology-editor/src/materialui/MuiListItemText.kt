@file:JsModule("@material-ui/core/ListItemText")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val ListItemText: RClass<ListItemTextProps>

external interface ListItemTextProps : RProps {
    var primary: String
    var inset: Boolean
    var className: String
}
