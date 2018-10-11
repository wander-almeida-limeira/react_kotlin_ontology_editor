@file:JsModule("@material-ui/core/Slide")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val Slide: RClass<SlideProps>

external interface SlideProps : RProps {
    var className: String
    var direction: String
}
