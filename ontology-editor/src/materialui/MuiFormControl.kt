@file:JsModule("@material-ui/core/FormControl")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val FormControl: RClass<FormControlProps>

external interface FormControlProps : RProps {
    var className: String
    var color: String
}
