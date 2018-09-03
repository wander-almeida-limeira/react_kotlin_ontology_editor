@file:JsModule("@material-ui/core/IconButton")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val MuiIconButton: RClass<IconButtonProps>

external interface IconButtonProps : RProps {
    var className: String
    var onClick:()-> Unit
}
