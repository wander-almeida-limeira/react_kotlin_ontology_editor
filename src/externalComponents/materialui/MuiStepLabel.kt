@file:JsModule("@material-ui/core/StepLabel")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val StepLabel: RClass<StepLabelProps>

external interface StepLabelProps : RProps {
    var className: String
    var color: String
    var style: Any
}
