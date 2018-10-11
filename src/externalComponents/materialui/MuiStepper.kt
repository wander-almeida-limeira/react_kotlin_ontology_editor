@file:JsModule("@material-ui/core/Stepper")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val Stepper: RClass<StepperProps>

external interface StepperProps : RProps {
    var className: String
    var color: String
    var style: Any
    var activeStep: Int
    var orientation: String
}
