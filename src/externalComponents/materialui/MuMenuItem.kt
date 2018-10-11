@file:JsModule("@material-ui/core/MenuItem")

package externalComponents.materialui

import react.RClass
import react.RProps
external val MenuItem: RClass<MenuItemProps>

@JsName("default")

external interface MenuItemProps : RProps {
    var disabled: Boolean
    var label: String
    var color: String
    var className: String
    var value: String
}
