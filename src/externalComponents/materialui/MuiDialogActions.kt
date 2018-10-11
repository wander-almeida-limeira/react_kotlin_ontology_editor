@file:JsModule("@material-ui/core/DialogActions")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val DialogActions: RClass<DialogActionsProps>

external interface DialogActionsProps : RProps {
    var className: String
}
