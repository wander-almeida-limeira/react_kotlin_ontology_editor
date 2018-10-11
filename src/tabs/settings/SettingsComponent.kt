
import externalComponents.materialui.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

interface SettingsProps : RProps {

}

interface SettingsState : RState {

}

class Settings(props: TabGroupProps) : RComponent<TabGroupProps, TabGroupState>(props) {
    override fun TabGroupState.init(props: TabGroupProps) {
    }

    override fun componentDidMount() {}

    override fun componentWillUnmount() {}

    override fun RBuilder.render() {

        div(classes="row align-items-center") {
            div(classes="col") {
                TextField {
                    attrs {
                        label = "Placeholder"
                        color = "secondary"
                    }
                }
            }
            div(classes="col") {
                Button {
                    attrs {
                        disabled = false
                        variant = "outlined"
                        color = "secondary"
                    }
                    +"You clicked times"
                }
            }
        }
    }
}

fun RBuilder.settingsComponent() = child(Settings::class) {
}
