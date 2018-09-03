package d3Container

import react.*
import react.dom.*
import kotlin.browser.*
import kotlinx.html.*
import org.w3c.dom.get

interface TickerProps : RProps {

}

interface TickerState : RState {
    var svgWidth: Int
    var svgHeight: Int
}

class D3ContainerComponent(props: TickerProps) : RComponent<TickerProps, TickerState>(props) {

    override fun TickerState.init(props: TickerProps) {}

    override fun componentDidMount() {
        js("""d3interface.render();""")
    }

    override fun componentWillUnmount() {}

    override fun RBuilder.render() {
        //+ " ${state.secondsElapsed} svgWidth =  ${state.svgWidth} svgHeight = ${state.svgHeight} ."
        section (classes = "svgArea"){
            attrs {
                id = "svgArea"
            }
            div() {
                attrs {
                    id = "svgContainer"
                }
                svg(classes = "drawArea") {
                    attrs {
                        id = "d3svg"
                    }
                }
            }
        }
    }
}

fun RBuilder.d3ContainerComponent() = child(D3ContainerComponent::class) {
}