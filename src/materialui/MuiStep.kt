@file:JsModule("@material-ui/core/Step")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val Step: RClass<StepProps>

external interface StepProps : RProps {
    var className: String
    var color: String
    var style: Any
    var key: String
}
