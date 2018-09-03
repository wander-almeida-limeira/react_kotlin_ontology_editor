package d3Container

import react.*
import materialui.*
import materialui.Icons.*

interface LeftSideBarComponentProps : RProps {

}

interface LeftSideBarComponentState : RState {
    var open: Boolean
}

class LeftSideBarComponent(props: LeftSideBarComponentProps) : RComponent<LeftSideBarComponentProps, LeftSideBarComponentState>(props) {

    override fun LeftSideBarComponentState.init(props: LeftSideBarComponentProps) {

    }

    override fun componentDidMount() {}

    override fun componentWillUnmount() {}

    var openItem: (item: Int) -> Unit = {

    }

    override fun RBuilder.render() {
        MuiList {
            attrs { component = "nav" }
            MuiListItem {
                attrs {
                    button = true
                    onClick = {
                        openItem(1)
                    }
                }
                MuiListItemIcon {
                    MuiArrowBackIcon{}
                }
            }
            MuiListItem {
                attrs {
                    button = true
                    onClick = {
                        openItem(1)
                    }
                }
                MuiListItemIcon {
                    MuiHelpIcon{}
                }
                MuiListItemText {
                    attrs { inset = true; primary = "Help" }
                }
            }
            MuiListItem {
                attrs {
                    button = true
                    onClick = {
                        openItem(1)
                    }
                }
                MuiListItemIcon {
                    MuiFeedbackIcon{}
                }
                MuiListItemText {
                    attrs { inset = true; primary = "Feedback" }
                }
            }
            MuiListItem {
                attrs {
                    button = true
                    onClick = {
                        openItem(1)
                    }
                }
                MuiListItemIcon {
                    MuiContactSupportIcon{}
                }
                MuiListItemText {
                    attrs { inset = true; primary = "Contact" }
                }
            }
            MuiListItem {
                attrs {
                    button = true
                    onClick = {
                        openItem(1)
                    }
                }
                MuiListItemIcon {
                    MuiInfoIcon{}
                }
                MuiListItemText {
                    attrs { inset = true; primary = "About" }
                }
            }
        }
    }

}

fun RBuilder.leftSideBar() = child(LeftSideBarComponent::class) {
}