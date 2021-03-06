@file:JsModule("@material-ui/core/AppBar")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val AppBar: RClass<AppBarProps>

external interface AppBarProps : RProps {
    var position: String
    var title: String
    var className: String
    var color: String
}
