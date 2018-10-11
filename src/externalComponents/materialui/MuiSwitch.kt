@file:JsModule("@material-ui/core/Switch")

package externalComponents.materialui

import org.w3c.dom.events.Event
import react.RClass
import react.RProps

@JsName("default")
external val Switch: RClass<SwitchProps>

external interface SwitchProps : RProps {
    var className: String
    var color: String
    var style: Any
    var value: String
    var onChange: (Event, Boolean) -> Unit
    var checked: Boolean
}
