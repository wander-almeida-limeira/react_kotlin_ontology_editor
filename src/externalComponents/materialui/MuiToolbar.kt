@file:JsModule("@material-ui/core/Toolbar")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val Toolbar: RClass<ToolbarProps>

external interface ToolbarProps : RProps {
    var className: String
    var color: String
}
