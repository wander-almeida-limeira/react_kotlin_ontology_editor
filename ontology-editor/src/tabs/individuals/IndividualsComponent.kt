package d3Container

import react.*
import materialui.*
import materialui.Icons.MuiExpandLessIcon
import materialui.Icons.MuiExpandMoreIcon
import materialui.Icons.MuiSearchIcon

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
            MuiListItem {
                attrs {
                    button = true;
                    onClick = {
                        openTreeNode()
                    }
                }
                if (state.open) MuiExpandLessIcon{} else MuiExpandMoreIcon{}
                MuiListItemIcon {
                    MuiSearchIcon{}
                }
                MuiListItemText {
                    attrs { inset = true; primary = "Sent mail" }
                }
            }
            MuiCollapse {
                attrs { `in` = state.open; timeout = "auto"; unmountOnExit = true}
                MuiList {
                    attrs { component = "nav"; disablePadding = false }
                    MuiListItem {
                        attrs {
                            button = true;
                            onClick = {
                                openTreeNode()
                            }
                        }
                        if (state.open) MuiExpandLessIcon {} else MuiExpandMoreIcon {}
                        MuiListItemIcon {
                            MuiSearchIcon {}
                        }
                        MuiListItemText {
                            attrs { inset = true; primary = "Sent mail" }
                        }
                    }
                }
            }
            MuiListItem {
                attrs { button = true; }
                MuiListItemIcon {
                    MuiSearchIcon{}
                }
                MuiListItemText {
                    attrs { inset = true; primary = "Sent mail" }
                }
            }
        }
    }

}

fun RBuilder.individualsComponent() = child(IndividualsComponent::class) {


}