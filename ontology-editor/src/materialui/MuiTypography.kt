@file:JsModule("@material-ui/core/Typography")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val MuiTypography: RClass<TypographyProps>

external interface TypographyProps : RProps {
    var component: String
    var className: String
}
