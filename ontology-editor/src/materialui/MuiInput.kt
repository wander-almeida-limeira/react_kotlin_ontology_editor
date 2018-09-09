@file:JsModule("@material-ui/core/Input")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val Input: RClass<InputProps>

external interface InputProps : RProps {
    var disabled: Boolean
    var placeholder: String
    var onClick:()-> Unit
    var className: String
    var name: String
    var id: String
}
