@file:JsModule("@material-ui/core/DialogContent")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val DialogContent: RClass<DialogContentProps>

external interface DialogContentProps : RProps {
    var className: String
}
