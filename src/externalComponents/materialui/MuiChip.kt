@file:JsModule("@material-ui/core/Chip")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val Chip: RClass<ChipProps>

external interface ChipProps : RProps {
    var color: String
    var onDelete:()-> Unit
    var className: String
    var label: String
    var style: String
    var avatar: Any
}
