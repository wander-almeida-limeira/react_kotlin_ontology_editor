
import react.*
import externalComponents.materialui.*
import externalComponents.materialui.Icons.*

interface LeftSideBarComponentProps : RProps {

}

interface LeftSideBarComponentState : RState {

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
            ListItem {
                attrs {
                    button = true
                    onClick = {
                        openItem(1)
                    }
                }
                ListItemIcon {
                    MuiArrowBackIcon{}
                }
            }
            ListItem {
                attrs {
                    button = true
                    onClick = {
                        openItem(1)
                    }
                }
                ListItemIcon {
                    MuiHelpIcon{}
                }
                ListItemText {
                    attrs { inset = true; primary = "Help" }
                }
            }
            ListItem {
                attrs {
                    button = true
                    onClick = {
                        openItem(1)
                    }
                }
                ListItemIcon {
                    MuiFeedbackIcon{}
                }
                ListItemText {
                    attrs { inset = true; primary = "Feedback" }
                }
            }
            ListItem {
                attrs {
                    button = true
                    onClick = {
                        openItem(1)
                    }
                }
                ListItemIcon {
                    MuiContactSupportIcon{}
                }
                ListItemText {
                    attrs { inset = true; primary = "Contact" }
                }
            }
            ListItem {
                attrs {
                    button = true
                    onClick = {
                        openItem(6)
                    }
                }
                ListItemIcon {
                    MuiInfoIcon{}
                }
                ListItemText {
                    attrs { inset = true; primary = "About" }
                }
            }
        }
    }

}

fun RBuilder.leftSideBar() = child(LeftSideBarComponent::class) {
}