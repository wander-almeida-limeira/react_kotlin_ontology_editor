@file:JsModule("react-json-view")

import org.w3c.dom.events.Event
import react.RClass
import react.RProps

@JsName("default")
external val TreeView: RClass<TreeViewProps>

external interface TreeViewProps : RProps {
    var src: Any
    var displayDataTypes: Boolean
    var name: String
    var onSelect: (Event) -> Unit
    var onEdit: (Event) -> Unit
}
