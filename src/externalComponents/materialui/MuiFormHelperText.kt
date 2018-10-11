@file:JsModule("@material-ui/core/FormHelperText")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val FormHelperText: RClass<FormHelperTextProps>

external interface FormHelperTextProps : RProps {
    var className: String
    var color: String
    var style: Any
}
