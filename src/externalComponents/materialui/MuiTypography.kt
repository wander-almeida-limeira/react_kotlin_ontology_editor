@file:JsModule("@material-ui/core/Typography")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val Typography: RClass<TypographyProps>

external interface TypographyProps : RProps {
    var color: String
    var style: String
    var variant: String
    var component: String
    var className: String
    var gutterBottom: Boolean
}
