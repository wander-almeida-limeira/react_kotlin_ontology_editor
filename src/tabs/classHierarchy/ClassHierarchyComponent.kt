
import externalComponents.materialui.*
import externalComponents.materialui.Icons.*
import org.w3c.dom.events.Event
import react.*

interface ClassHierarchyComponentProps : RProps {

}

interface ClassHierarchyComponentState : RState {
    var open: Boolean
    var src: Any
}

fun getClassHierarchyJson() = js("""
        var result = jsonLdInterface.getJson();
        return result; """)

class ClassHierarchyComponent(props: ClassHierarchyComponentProps) : RComponent<ClassHierarchyComponentProps, ClassHierarchyComponentState>(props) {

    override fun ClassHierarchyComponentState.init(props: ClassHierarchyComponentProps) {
        open = false
        src = getClassHierarchyJson()
    }

    override fun componentDidMount() {}

    override fun componentWillUnmount() {}

    var openTreeNode: () -> Unit = {
        setState {
            open = !open
        }
    }

    override fun RBuilder.render() {
        Typography {
            attrs {
                className = "py-2"
                variant = "title"
            }
            +"Class Hierarchy"
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

fun RBuilder.classHierarchyComponent() = child(ClassHierarchyComponent::class) {


}