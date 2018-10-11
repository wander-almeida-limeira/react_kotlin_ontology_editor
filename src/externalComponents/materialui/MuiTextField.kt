@file:JsModule("@material-ui/core/TextField")

package externalComponents.materialui

import org.w3c.dom.events.Event
import react.RClass
import react.RProps

@JsName("default")
external val TextField: RClass<TextFieldProps>

external interface TextFieldProps : RProps {
    var disabled: Boolean
    var label: String
    var color: String
    var onClick:()-> Unit
    var onChange: (Event, String) -> Unit
    var className: String
    var value: String
}
