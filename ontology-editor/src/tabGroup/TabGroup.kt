
import materialui.*
import materialui.Icons.MuiMenuIcon
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import react.dom.img

@JsModule("src/tabGroup/logo-editor.svg")
external val editorLogo: dynamic

interface TabGroupProps : RProps {

}

interface TabGroupState : RState {
    var tab: Int
    var openLeftSideBar: Boolean
}

class TabGroup(props: TabGroupProps) : RComponent<TabGroupProps, TabGroupState>(props) {
    override fun TabGroupState.init(props: TabGroupProps) {
        tab = 0
    }

    override fun componentDidMount() {
        setState {
            tab = 0
        }
    }

    override fun componentWillUnmount() {}

    var tabChange: (value: Int) -> Unit = {
        setState {
            tab = it
        }
    }

    var openLeftSideBar: () -> Unit = {
        setState {
            openLeftSideBar = !openLeftSideBar
        }
    }

    override fun RBuilder.render() {
            AppBar {
                attrs { title = "Knowledge Graph Web Editor" }
                Toolbar {
                    IconButton {
                        attrs {
                            onClick = {
                                openLeftSideBar()
                            }
                        }
                        MuiMenuIcon {}
                    }
                    img(alt = "Ontology editor logo", src = editorLogo, classes = "logo") {}
                    Tabs {
                        attrs {
                            value = state.tab; scrollable = true; scrollButtons = "auto"
                            onChange = { event: Event, eventValue: Int ->
                                tabChange(eventValue)
                            }
                        }
                        Tab {
                            attrs { label = "Graph Editor" }
                        }
                        Tab {
                            attrs { label = "Individuals" }
                        }
                        Tab {
                            attrs { label = "Text Editor" }
                        }
                        Tab {
                            attrs { label = "Class Hierarchy" }
                        }
                        Tab {
                            attrs { label = "Object Property Hierarchy" }
                        }
                        Tab {
                            attrs { label = "Data Property Hierarchy" }
                        }
                        Tab {
                            attrs { label = "General Settings" }
                        }
                    }
                    Drawer{
                        attrs {
                            anchor = "left"
                            open = state.openLeftSideBar
                            onClose = {
                                openLeftSideBar()
                            }
                        }
                        leftSideBar()
                    }
                }
            }
        if (state.tab == 0) graphEditorComponent(1) else
        div(classes="main-div container") {
            if (state.tab == 1) individualsComponent()
            if (state.tab == 6) settingsComponent()
        }
    }
}

fun RBuilder.tabGroup() = child(TabGroup::class) {
}