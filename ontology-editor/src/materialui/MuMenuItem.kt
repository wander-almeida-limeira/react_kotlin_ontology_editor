@file:JsModule("@material-ui/core/MenuItem")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val MenuItem: RClass<MenuItemProps>

external interface MenuItemProps : RProps {
    var disabled: Boolean
    var label: String
    var color: String
    var className: String
    var value: String
}
