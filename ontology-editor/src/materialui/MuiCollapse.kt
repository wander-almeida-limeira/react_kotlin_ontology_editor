@file:JsModule("@material-ui/core/Collapse")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val MuiCollapse: RClass<CollapseProps>

external interface CollapseProps : RProps {
    var `in`: Boolean
    var timeout: String
    var unmountOnExit: Boolean
    var className: String
}
