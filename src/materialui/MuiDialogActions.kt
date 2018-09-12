@file:JsModule("@material-ui/core/DialogActions")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val DialogActions: RClass<DialogActionsProps>

external interface DialogActionsProps : RProps {
    var className: String
}
