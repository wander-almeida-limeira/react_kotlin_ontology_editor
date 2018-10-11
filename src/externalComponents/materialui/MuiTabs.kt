@file:JsModule("@material-ui/core/Tabs")

package externalComponents.materialui

import org.w3c.dom.events.Event
import react.RClass
import react.RProps

@JsName("default")
external val Tabs: RClass<TabsProps>

external interface TabsProps : RProps {
    var value: Int
    var scrollButtons: String
    var scrollable: Boolean
    var className: String
    var onChange: (Event, Int) -> Unit
}
