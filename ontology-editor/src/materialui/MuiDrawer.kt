@file:JsModule("@material-ui/core/Drawer")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val MuiDrawer: RClass<DrawerProps>

external interface DrawerProps : RProps {
    var open: Boolean
    var onClose: ()-> Unit
    var anchor: String
    var className: String
}
