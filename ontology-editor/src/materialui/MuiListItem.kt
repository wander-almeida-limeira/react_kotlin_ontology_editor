@file:JsModule("@material-ui/core/ListItem")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val ListItem: RClass<ListItemProps>

external interface ListItemProps : RProps {
    var button: Boolean
    var onClick:()-> Unit
    var className: String
}
