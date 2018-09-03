@file:JsModule("@material-ui/core/Button")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val MuiButton: RClass<ButtonProps>

external interface ButtonProps : RProps {
    var disabled: Boolean
    var onClick:()-> Unit
    var variant: String
    var className: String
}
