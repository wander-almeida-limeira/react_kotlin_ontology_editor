
import materialui.*
import react.*
import react.dom.*
import materialui.Icons.*

interface LeftPanelGraphProps : RProps {

}

interface LeftPanelGraphState : RState {
    var openSettings: Boolean
}

class LeftPanelGraph(props: LeftPanelGraphProps) : RComponent<LeftPanelGraphProps, LeftPanelGraphState>(props) {


    override fun LeftPanelGraphState.init(props: LeftPanelGraphProps) {
        openSettings = false
    }

    override fun componentDidMount() {}

    override fun componentWillUnmount() {}

    var openCloseSettings: (item: Int) -> Unit = {
        if (it == 6)
            setState {
                openSettings = !openSettings
            }
    }

    override fun RBuilder.render() {
        section("leftArea") {
            IconButton {
                MuiSearchIcon {}
            }
            IconButton {
                MuiUndoIcon {}
            }
            IconButton {
                MuiRedoIcon {}
            }
            IconButton {
                MuiZoomInIcon {}
            }
            IconButton {
                MuiZoomOutIcon {}
            }
            IconButton {
                attrs {
                    className = "config-button"
                    onClick = {
                        openCloseSettings(6)
                    }
                }
                MuiSettingsIcon {}
            }
        }
        if (state.openSettings)
        graphEditorSettingsComponent(state.openSettings, object : SettingsInterface {
            override fun openCloseSettings() {
                setState {
                    openSettings = !openSettings
                }
            }
        })
    }

}

fun RBuilder.leftPanelGraph() = child(LeftPanelGraph::class) {
}