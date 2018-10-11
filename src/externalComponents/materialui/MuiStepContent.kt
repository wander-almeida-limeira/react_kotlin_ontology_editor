@file:JsModule("@material-ui/core/StepContent")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val StepContent: RClass<StepContentProps>

external interface StepContentProps : RProps {
    var className: String
    var color: String
    var style: Any
    var key: String
}
