@file:JsModule("@material-ui/core/Avatar")

package materialui

import react.RClass
import react.RProps

@JsName("default")
external val Avatar: RClass<AvatarProps>

external interface AvatarProps : RProps {
    var label: String
    var color: String
    var className: String
    var style: String
}
