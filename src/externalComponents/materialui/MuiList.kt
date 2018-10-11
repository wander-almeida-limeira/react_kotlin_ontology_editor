@file:JsModule("@material-ui/core/List")

package externalComponents.materialui

import react.RClass
import react.RProps

@JsName("default")
external val MuiList: RClass<ListProps>

external interface ListProps : RProps {
    var component: String
    var disablePadding: Boolean
    var className: String
}
