package ticker

import d3Container.individualsComponent
import d3Container.leftSideBar
import graphEditorComponent
import materialui.*
import materialui.Icons.MuiMenuIcon
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import kotlin.browser.*

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
            MuiAppBar {
                attrs { title = "Placeholder" }
                MuiToolbar {
                    MuiIconButton {
                        attrs {
                            onClick = {
                                openLeftSideBar()
                            }
                        }
                        MuiMenuIcon {}
                    }
                    img(alt = "Ontology editor logo", src = editorLogo, classes = "logo") {}
                    MuiTabs {
                        attrs {
                            value = state.tab; scrollable = true; scrollButtons = "auto"
                            onChange = { event: Event, eventValue: Int ->
                                tabChange(eventValue)
                            }
                        }
                        MuiTab {
                            attrs { label = "Graph Editor" }
                        }
                        MuiTab {
                            attrs { label = "Individuals" }
                        }
                        MuiTab {
                            attrs { label = "Text Editor" }
                        }
                        MuiTab {
                            attrs { label = "Class Hierarchy" }
                        }
                        MuiTab {
                            attrs { label = "Object Property Hierarchy" }
                        }
                        MuiTab {
                            attrs { label = "Data Property Hierarchy" }
                        }
                        MuiTab {
                            attrs { label = "Settings" }
                        }
                    }
                    MuiDrawer{
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