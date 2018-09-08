@file:JsModule("@material-ui/core/DialogTitle")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val DialogTitle: RClass<DialogTitleProps>

external interface DialogTitleProps : RProps {
    var className: String
}
