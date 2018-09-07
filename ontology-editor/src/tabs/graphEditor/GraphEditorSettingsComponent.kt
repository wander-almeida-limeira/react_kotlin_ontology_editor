
import kotlinx.html.style
import materialui.*
import materialui.Icons.MuiCheckIcon
import materialui.Icons.MuiCloseIcon
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import kotlin.js.Json

interface SettingsInterface {
    fun openCloseSettings()
}

interface GraphEditorSettingsProps : RProps {
    var settingsInterface: SettingsInterface
    var openSettings: Boolean
}

interface GraphEditorSettingsState : RState {
    var selectedClassColorValue: String
    var classShadeValue: Int
}
class GraphEditorSettings(props: GraphEditorSettingsProps) : RComponent<GraphEditorSettingsProps, GraphEditorSettingsState>(props) {

    val colorsArray = arrayOf("red","pink","purple","deepPurple","indigo","blue","lightBlue","cyan","teal","green","lightGreen","lime","yellow","amber","orange","deepOrange")

    override fun GraphEditorSettingsState.init(props: GraphEditorSettingsProps) {
        selectedClassColorValue = "0"
        classShadeValue = 12
    }

    fun getShadeValue(): String {
        when (state.classShadeValue) {
            0 -> return "50"
            1 -> return "100"
            2 -> return "200"
            3 -> return "300"
            4 -> return "400"
            5 -> return "500"
            6 -> return "600"
            7 -> return "700"
            8 -> return "800"
            9 -> return "900"
            10 -> return "A100"
            11 -> return "A200"
            12 -> return "A400"
            13 -> return "A700"
        }
        return ""
    }

    fun getColorPropertyValue(colorBase: String): Any? {
        when (colorBase) {
            "red" -> return (red as Json)?.get(getShadeValue())
            "pink" -> return (pink as Json)?.get(getShadeValue())
            "purple" -> return (purple as Json)?.get(getShadeValue())
            "deepPurple" -> return (deepPurple as Json)?.get(getShadeValue())
            "indigo" -> return (indigo as Json)?.get(getShadeValue())
            "blue" -> return (blue as Json)?.get(getShadeValue())
            "lightBlue" -> return (lightBlue as Json)?.get(getShadeValue())
            "cyan" -> return (cyan as Json)?.get(getShadeValue())
            "teal" -> return (teal as Json)?.get(getShadeValue())
            "green" -> return (green as Json)?.get(getShadeValue())
            "lightGreen" -> return (lightGreen as Json)?.get(getShadeValue())
            "lime" -> return (lime as Json)?.get(getShadeValue())
            "yellow" -> return (yellow as Json)?.get(getShadeValue())
            "amber" -> return (amber as Json)?.get(getShadeValue())
            "orange" -> return (orange as Json)?.get(getShadeValue())
            "deepOrange" -> return (deepOrange as Json)?.get(getShadeValue())
        }
        return null
    }

    override fun RBuilder.render() {
        Dialog{
            attrs {
                className="dialog-graph-editor-settings"
                fullScreen = true
                open = props.openSettings
                onClose = {
                    props.settingsInterface.openCloseSettings()
                }
            }
            AppBar {
                attrs {
                    className="app-bar-graph-editor-settings"
                }
                Toolbar {
                    div(classes="container-fluid") {
                        div(classes = "row justify-content-between align-items-center") {
                            Typography {
                                attrs {
                                    variant="title"
                                    color = "inherit"
                                }
                                +"Settings"
                            }

                            IconButton {
                                attrs {
                                    color = "secondary"
                                    onClick = {
                                        props.settingsInterface.openCloseSettings()
                                    }
                                }
                                MuiCloseIcon {}
                            }
                        }
                    }
                }
            }
            div (classes="container-fluid content-graph-editor-settings-div") {
                div(classes = "row ") {
                    div (classes="col m-5") {
                        Grid {
                            Typography {
                                attrs {
                                    className = "py-2"
                                    variant = "title"
                                }
                                +"Class Color"
                            }
                            Typography {+"Shade:"}
                            Slider{
                                attrs {
                                    color="secondary"
                                    value = state.classShadeValue
                                    min = 0
                                    max = 13
                                    step = 1
                                    onChange = { event: Event, eventValue: Int ->
                                        setState {
                                            classShadeValue = eventValue
                                        }
                                    }
                                }
                            }
                            div (classes = "colors-main-div") {
                                for (i in colorsArray) {
                                    Radio {
                                        attrs {
                                            checked = state.selectedClassColorValue == i.toString()
                                            value = i.toString()
                                            color = "default"
                                            icon = div(classes = "colors-div") { attrs { style = kotlinext.js.js { background = getColorPropertyValue(i) } } }
                                            checkedIcon = div(classes = "colors-div-checked") { attrs { style = kotlinext.js.js { background = getColorPropertyValue(i) } } ; MuiCheckIcon {} }
                                            onChange = { event: Event, eventValue: Boolean ->
                                                val eventTargetValue = (event.target as HTMLInputElement)?.value
                                                setState {
                                                    selectedClassColorValue = eventTargetValue
                                                    //js("""d3interface.setClassColorCode('#fdf3f4'); d3interface.render();""")
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    div (classes="col align-self-end p-4 save-div") {
                        Button {
                            attrs {
                                className = "save-graph-editor-settings-button"
                                variant = "outlined"
                                onClick = {
                                    props.settingsInterface.openCloseSettings()
                                }
                            }
                            +"Save Changes"
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.graphEditorSettingsComponent(openSettings: Boolean, settingsInterface: SettingsInterface) = child(GraphEditorSettings::class) {
    attrs.openSettings = openSettings
    attrs.settingsInterface = settingsInterface
}