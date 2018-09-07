@file:JsModule("@material-ui/core/ListItemIcon")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val ListItemIcon: RClass<ListItemIconProps>

external interface ListItemIconProps : RProps {
    var label: String
    var className: String
}
