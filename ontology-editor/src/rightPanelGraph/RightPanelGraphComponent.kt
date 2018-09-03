package leftPanelGraph

import react.*
import react.dom.*
import kotlin.browser.*
import kotlinx.html.*
import kotlinx.html.js.onClickFunction

interface RightPanelGraphProps : RProps {

}

interface RightPanelGraphState : RState {

}

class RightPanelGraph(props: RightPanelGraphProps) : RComponent<RightPanelGraphProps, RightPanelGraphState>(props) {


    override fun RightPanelGraphState.init(props: RightPanelGraphProps) {

    }

    override fun componentDidMount() {

    }

    fun openClose(id: String) {
        var button = document.getElementById("button$id")
        button?.classList?.toggle("active")//.addClass("active")

        var panel = document.getElementById("panel$id")
        if (panel?.getAttribute("style") != null && panel?.getAttribute("style") != ""){
            panel?.setAttribute("style", "")
        } else {
            panel?.setAttribute("style", "max-height: ${panel.scrollHeight}px")
        }
    }

    override fun componentWillUnmount() {}

    override fun RBuilder.render() {
        section("rightArea") {
            h3() {
                +"Knowledge Graph Web Editor"
            }
            h5() {
                +"Ontology Editor : Version 0.1.0"
            }
            button(classes="accordion")
            {
                i(classes="material-icons left_accordion_icon") {+"bubble_chart"}
                + "Nodes"
                i(classes="material-icons expand_less") {+"expand_less"}
                i(classes="material-icons expand_more") {+"expand_more"}
                attrs {
                    id = "button1"
                    onClickFunction = {
                        openClose("1")
                    }
                }
            }
            div(classes="panel") {
                attrs {
                    id = "panel1"
                }
                p() {
                    + "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
                }
            }
            button(classes="accordion")
            {
                i(classes="material-icons left_accordion_icon") {+"details"}
                + "Details"
                i(classes="material-icons expand_less") {+"expand_less"}
                i(classes="material-icons expand_more") {+"expand_more"}
                attrs {
                    id = "button2"
                    onClickFunction = {
                        openClose("2")
                    }
                }
            }
            div(classes="panel") {
                attrs {
                    id = "panel2"
                }
                p() {
                    + "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
                }
            }
            button(classes="accordion")
            {
                i(classes="material-icons left_accordion_icon") {+"edit"}
                + "Edit Node Attributes"
                i(classes="material-icons expand_less") {+"expand_less"}
                i(classes="material-icons expand_more") {+"expand_more"}
                attrs {
                    id = "button3"
                    onClickFunction = {
                        openClose("3")
                    }
                }
            }
            div(classes="panel") {
                attrs {
                    id = "panel3"
                }
                p() {
                    + "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
                }
            }
        }
    }

}

fun RBuilder.rightPanelGraph() = child(RightPanelGraph::class) {
}





