@file:JsModule("@material-ui/core/TextField")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val TextField: RClass<TextFieldProps>

external interface TextFieldProps : RProps {
    var disabled: Boolean
    var label: String
    var onClick:()-> Unit
    var className: String
}
