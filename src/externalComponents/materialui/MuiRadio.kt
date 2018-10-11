@file:JsModule("@material-ui/core/Radio")

package externalComponents.materialui

import org.w3c.dom.events.Event
import react.RClass
import react.RProps
import react.ReactElement

@JsName("default")
external val Radio: RClass<RadioProps>

external interface RadioProps : RProps {
    var className: String
    var onChange: (Event, Boolean) -> Unit
    var checked: Boolean
    var color: String
    var value: String
    var name: String
    var checkedIcon: ReactElement
    var icon: ReactElement
}
