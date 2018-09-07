
import materialui.*
import materialui.Icons.MuiExpandLessIcon
import materialui.Icons.MuiExpandMoreIcon
import materialui.Icons.MuiSearchIcon
import react.*

interface IndividualsComponentProps : RProps {

}

interface IndividualsComponentState : RState {
    var open: Boolean
}

class IndividualsComponent(props: IndividualsComponentProps) : RComponent<IndividualsComponentProps, IndividualsComponentState>(props) {

    override fun IndividualsComponentState.init(props: IndividualsComponentProps) {
        open = false
    }

    override fun componentDidMount() {}

    override fun componentWillUnmount() {}

    var openTreeNode: () -> Unit = {
        setState {
            open = !open
        }
    }

    override fun RBuilder.render() {
        MuiList {
            attrs { component = "nav" }
            ListItem {
                attrs {
                    button = true;
                    onClick = {
                        openTreeNode()
                    }
                }
                if (state.open) MuiExpandLessIcon{} else MuiExpandMoreIcon{}
                ListItemIcon {
                    MuiSearchIcon{}
                }
                ListItemText {
                    attrs { inset = true; primary = "Sent mail" }
                }
            }
            Collapse {
                attrs { `in` = state.open; timeout = "auto"; unmountOnExit = true}
                MuiList {
                    attrs { component = "nav"; disablePadding = false }
                    ListItem {
                        attrs {
                            button = true;
                            onClick = {
                                openTreeNode()
                            }
                        }
                        if (state.open) MuiExpandLessIcon {} else MuiExpandMoreIcon {}
                        ListItemIcon {
                            MuiSearchIcon {}
                        }
                        ListItemText {
                            attrs { inset = true; primary = "Sent mail" }
                        }
                    }
                }
            }
            ListItem {
                attrs { button = true; }
                ListItemIcon {
                    MuiSearchIcon{}
                }
                ListItemText {
                    attrs { inset = true; primary = "Sent mail" }
                }
            }
        }
    }

}

fun RBuilder.individualsComponent() = child(IndividualsComponent::class) {


}