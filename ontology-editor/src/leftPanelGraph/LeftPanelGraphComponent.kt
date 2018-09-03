package leftPanelGraph

import react.*
import react.dom.*
import materialui.MuiIconButton
import materialui.Icons.*

interface LeftPanelGraphProps : RProps {

}

interface LeftPanelGraphState : RState {

}

class LeftPanelGraph(props: LeftPanelGraphProps) : RComponent<LeftPanelGraphProps, LeftPanelGraphState>(props) {


    override fun LeftPanelGraphState.init(props: LeftPanelGraphProps) {}

    override fun componentDidMount() {}

    override fun componentWillUnmount() {}

    override fun RBuilder.render() {
        section("leftArea") {
            MuiIconButton {
                MuiSearchIcon {}
            }
            MuiIconButton {
                MuiUndoIcon {}
            }
            MuiIconButton {
                MuiRedoIcon {}
            }
            MuiIconButton {
                MuiZoomInIcon {}
            }
            MuiIconButton {
                MuiZoomOutIcon {}
            }
            MuiIconButton {
                attrs {
                    className = "config-button"
                }
                MuiSettingsIcon {}
            }
        }
    }

}

fun RBuilder.leftPanelGraph() = child(LeftPanelGraph::class) {
}