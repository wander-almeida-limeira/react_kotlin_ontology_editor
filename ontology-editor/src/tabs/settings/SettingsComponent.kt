
import materialui.Button
import materialui.TextField
import react.*
import react.dom.*

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
                TextField() {
                    attrs { label = "Placeholder" }
                }
            }
            div(classes="col") {
                Button() {
                    attrs { disabled = false; variant = "outlined" }
                    +"You clicked times"
                }
            }
        }
    }
}

fun RBuilder.settingsComponent() = child(Settings::class) {
}
