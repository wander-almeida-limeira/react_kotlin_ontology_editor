package ticker

import materialui.MuiButton
import materialui.MuiTextField
import react.*
import react.dom.*
import kotlin.browser.*

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
                MuiTextField() {
                    attrs { label = "Placeholder" }
                }
            }
            div(classes="col") {
                MuiButton() {
                    attrs { disabled = false; variant = "outlined" }
                    +"You clicked times"
                }
            }
        }
    }
}

fun RBuilder.settingsComponent() = child(Settings::class) {
}
