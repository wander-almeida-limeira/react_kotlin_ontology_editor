
import externalComponents.materialui.*
import externalComponents.materialui.Icons.*
import org.w3c.dom.events.Event
import react.*
import react.dom.div

interface SettingsInterface {
    fun openCloseSettings()
}

interface GraphEditorSettingsProps : RProps {
    var settingsInterface: SettingsInterface
    var openSettings: Boolean
}

interface GraphEditorSettingsState : RState {
    var tab: Int
}

class GraphEditorSettings(props: GraphEditorSettingsProps) : RComponent<GraphEditorSettingsProps, GraphEditorSettingsState>(props) {

    override fun GraphEditorSettingsState.init(props: GraphEditorSettingsProps) {
        tab = 0
    }

    var tabChange: (value: Int) -> Unit = {
        setState {
            tab = it
        }
    }

    override fun RBuilder.render() {
        Dialog{
            attrs {
                className="dialog-graph-editor-settings"
                fullScreen = true
                open = props.openSettings
                onClose = {
                    props.settingsInterface.openCloseSettings()
                }
            }
            AppBar {
                attrs {
                    color = "secondary"
                    className="app-bar-graph-editor-settings"
                }
                Toolbar {
                    div(classes="container-fluid") {
                        div(classes = "row justify-content-between align-items-center") {
                            Typography {
                                attrs {
                                    variant="title"
                                    color = "inherit"
                                }
                                +"Graph Settings"
                            }
                            Tabs {
                                attrs {
                                    className = "graph-settings-tab indicator-header"
                                    value = state.tab
                                    scrollable = true
                                    scrollButtons = "auto"
                                    onChange = { event: Event, eventValue: Int ->
                                        tabChange(eventValue)
                                    }
                                }
                                Tab {
                                    attrs { label = "Colors" }
                                }
                                Tab {
                                    attrs { label = "Element Size" }
                                }
                                Tab {
                                    attrs { label = "Shapes" }
                                }
                            }
                            IconButton {
                                attrs {
                                    onClick = {
                                        props.settingsInterface.openCloseSettings()
                                    }
                                }
                                MuiCloseIcon {}
                            }
                        }
                    }
                }
            }
            if (state.tab == 0)
                graphEditorSettingsColorsTabComponent(object : SettingsInterface {
                    override fun openCloseSettings() {
                        setState {
                            props.settingsInterface.openCloseSettings()
                        }
                    }
                })

        }
    }
}

fun RBuilder.graphEditorSettingsComponent(openSettings: Boolean, settingsInterface: SettingsInterface) = child(GraphEditorSettings::class) {
    attrs.openSettings = openSettings
    attrs.settingsInterface = settingsInterface
}