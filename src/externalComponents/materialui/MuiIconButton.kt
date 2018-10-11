@file:JsModule("@material-ui/core/IconButton")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val IconButton: RClass<IconButtonProps>

external interface IconButtonProps : RProps {
    var color: String
    var className: String
    var onClick:()-> Unit
}
