@file:JsModule("@material-ui/lab/Slider")

package materialui

import org.w3c.dom.events.Event
import react.RClass
import react.RProps

@JsName("default")
external val Slider: RClass<SliderProps>

external interface SliderProps : RProps {
    var className: String
    var color: String
    var style: Any
    var value: Int
    var min: Int
    var max: Int
    var step: Int
    var onChange: (Event, Int) -> Unit
}
