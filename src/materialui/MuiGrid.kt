@file:JsModule("@material-ui/core/Grid")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val Grid: RClass<GridProps>

external interface GridProps : RProps {
    var item : Boolean
    var xs: Int
    var sm: Int
    var md: Int
    var className: String
}
