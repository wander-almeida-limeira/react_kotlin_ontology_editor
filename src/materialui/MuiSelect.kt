@file:JsModule("@material-ui/core/Select")

package materialui

import org.w3c.dom.events.Event
import react.RClass
import react.RProps
import react.ReactElement

@JsName("default")
external val Select: RClass<SelectProps>

external interface SelectProps : RProps {
    var disabled: Boolean
    var displayEmpty: Boolean
    var value: String
    var onChange: (Event, Boolean) -> Unit
    var className: String
    var input: ReactElement
    var inputProps: Any
}
