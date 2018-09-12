
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*

class GraphEditorComponent : RComponent<GraphEditorComponent.Props, RState>() {

    override fun RBuilder.render() {
        div(classes = "page-content") {
            leftPanelGraph()
            d3ContainerComponent()
            rightPanelGraph()
        }
    }

    class Props(var info: Int) : RProps
}

fun RBuilder.graphEditorComponent(info: Int = 0) = child(GraphEditorComponent::class) {
    attrs.info = info
}