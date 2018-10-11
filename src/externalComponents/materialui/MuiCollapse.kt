@file:JsModule("@material-ui/core/Collapse")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val Collapse: RClass<CollapseProps>

external interface CollapseProps : RProps {
    var `in`: Boolean
    var timeout: String
    var unmountOnExit: Boolean
    var className: String
}
