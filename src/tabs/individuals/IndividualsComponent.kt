
import externalComponents.materialui.*
import externalComponents.materialui.Icons.*
import org.w3c.dom.events.Event
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

    }

}

fun RBuilder.individualsComponent() = child(IndividualsComponent::class) {


}