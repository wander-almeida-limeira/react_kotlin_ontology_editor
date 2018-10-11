@file:JsModule("@material-ui/core/InputLabel")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val InputLabel: RClass<InputLabelProps>

external interface InputLabelProps : RProps {
    var className: String
    var htmlFor: String
}
