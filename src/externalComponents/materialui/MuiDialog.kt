@file:JsModule("@material-ui/core/Dialog")

package externalComponents.materialui

import react.RClass
import react.RProps
import react.ReactElement

@JsName("default")
external val Dialog: RClass<DialogProps>

external interface DialogProps : RProps {
    var className: String
    var scroll: String
    var fullScreen: Boolean
    var open: Boolean
    var onClose:()-> Unit
    var TransitionComponent: ReactElement
}
