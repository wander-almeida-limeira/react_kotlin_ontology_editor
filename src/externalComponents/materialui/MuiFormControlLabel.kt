@file:JsModule("@material-ui/core/FormControlLabel")

package externalComponents.materialui

import react.RClass
import react.RProps
import react.ReactElement

@JsName("default")
external val FormControlLabel: RClass<FormControlLabelProps>

external interface FormControlLabelProps : RProps {
    var className: String
    var color: String
    var label: String
    var control: ReactElement
}
