
import externalComponents.materialui.*
import externalComponents.materialui.Icons.*
import org.w3c.dom.events.Event
import react.*

interface IndividualsComponentProps : RProps {

}

interface IndividualsComponentState : RState {
    var open: Boolean
    var src: Any
}

fun getJson() = js("""
        var result = jsonLdInterface.getJson();
        return result; """)

class IndividualsComponent(props: IndividualsComponentProps) : RComponent<IndividualsComponentProps, IndividualsComponentState>(props) {

    override fun IndividualsComponentState.init(props: IndividualsComponentProps) {
        open = false
        src = getJson()
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

        TreeView{
            attrs {
                src =  state.src
                displayDataTypes = false
                name = "teste"
                onSelect = { event: Event ->
                    console.log(event)
                }
                onEdit = { event: Event ->
                    console.log(event)
                }
            }
        }
    }

}

fun RBuilder.individualsComponent() = child(IndividualsComponent::class) {


}