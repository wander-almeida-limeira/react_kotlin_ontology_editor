import kotlinx.html.style
import materialui.*
import materialui.Icons.MuiCheckIcon
import materialui.Icons.MuiCloseIcon
import model.ColorRule
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import react.dom.option
import kotlin.js.Json

interface ColorRuleInterface {
    fun openCloseColorRule(colorRuleArray: MutableList<ColorRule>)
}

interface GraphEditorColorRuleProps : RProps {
    var colorRuleInterface: ColorRuleInterface
    var openColorRule: Boolean
}

interface GraphEditorColorRuleState : RState {
    var activeStep: Int
    var classType: String
    var elementValue: String
    var classCustomValue: Boolean
    var classShadeValue: Int
    var selectedClassColorValue: String
    var openColorPalette: Boolean
}

class GraphEditorColorRule(props: GraphEditorColorRuleProps) : RComponent<GraphEditorColorRuleProps, GraphEditorColorRuleState>(props) {

    val colorsArray = arrayOf("red","pink","purple","deepPurple","indigo","blue","lightBlue","cyan","teal","green","lightGreen","lime","yellow","amber","orange","deepOrange")

    override fun GraphEditorColorRuleState.init(props: GraphEditorColorRuleProps) {
        classType = ""
        activeStep = 0
        classShadeValue = 12
        openColorPalette = false
        elementValue = ""
    }

    fun getShadeValue(): String {

        var shadeValue = state.classShadeValue

        when (shadeValue) {
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
            else -> {
                return ""
            }
        }
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
            else -> {
                return ""
            }
        }
    }

    override fun RBuilder.render() {
        Dialog {
            attrs {
                fullScreen = true
                open = props.openColorRule
                onClose = {
                    props.colorRuleInterface.openCloseColorRule(mutableListOf())
                }
            }
            AppBar {
                attrs {
                    color = "secondary"
                    className = "app-bar-graph-editor-settings"
                }
                Toolbar {
                    div(classes = "container-fluid") {
                        div(classes = "row justify-content-between align-items-center") {
                            Typography {
                                attrs {
                                    variant = "title"
                                    color = "inherit"
                                }
                                +"Graph Settings"
                            }
                            IconButton {
                                attrs {
                                    onClick = {
                                        props.colorRuleInterface.openCloseColorRule(mutableListOf())
                                    }
                                }
                                MuiCloseIcon {}
                            }
                        }
                    }
                }
            }
            DialogContent {
                attrs {
                    className = "dialog-graph-editor-colorCode-rule-content"
                }
                Stepper {
                    attrs {
                        activeStep = state.activeStep
                        orientation = "vertical"
                    }
                    Step {
                        attrs {
                            key = "Select the element type"
                        }
                        StepLabel {
                            +"Select the element type"
                        }
                        StepContent {
                            div(classes = "container-fluid") {
                                div(classes = "row") {
                                    FormControl {
                                        InputLabel {
                                            attrs {
                                                htmlFor = "element-type"
                                            }
                                            +"Element Type"
                                        }
                                        Select {
                                            attrs {
                                                native = true
                                                value = state.classType
                                                className = "element-type-select"
                                                onChange = { event: dynamic, eventValue: Boolean ->
                                                    val eventTargetValue = event.target.value
                                                    setState {
                                                        classType = eventTargetValue
                                                    }
                                                }
                                                inputProps = js("""return {id: 'element-type'}""")
                                            }
                                            option {
                                                attrs {
                                                    value = ""
                                                }
                                            }
                                            option {
                                                attrs {
                                                    value = "1"
                                                }
                                                +"Class"
                                            }
                                        }
                                    }
                                }
                                div(classes = "row pt-3") {
                                    Button {
                                        attrs {
                                            className = "ml-2"
                                            variant = "contained"
                                            color = "primary"
                                            onClick = {
                                                setState {
                                                    activeStep = activeStep + 1
                                                }
                                            }
                                        }
                                        +"Next"
                                    }
                                }
                            }
                        }
                    }
                    Step {
                        attrs {
                            key = "Rule conditions - When element value equals:"
                        }
                        StepLabel {
                            +"Rule conditions - When element value equals:"
                        }
                        StepContent {
                            div(classes = "container-fluid") {
                                div(classes = "row") {
                                    TextField {
                                        attrs {
                                            value = state.elementValue
                                            onChange = { event: Event, eventValue: String ->
                                                val eventTargetValue = (event.target as HTMLInputElement)?.value
                                                setState {
                                                    elementValue = eventTargetValue
                                                }
                                            }
                                            label = "Element value"
                                            color = "secondary"
                                        }
                                    }
                                }
                                div(classes = "row pt-3") {
                                    Button {
                                        attrs {
                                            className = "ml-2"
                                            color = "primary"
                                            onClick = {
                                                setState {
                                                    activeStep = activeStep - 1
                                                }
                                            }
                                        }
                                        +"Back"
                                    }
                                    Button {
                                        attrs {
                                            className = "ml-2"
                                            variant = "contained"
                                            color = "primary"
                                            onClick = {
                                                setState {
                                                    activeStep = activeStep + 1
                                                }
                                            }
                                        }
                                        +"Next"
                                    }
                                }
                            }
                        }
                    }
                    Step {
                        attrs {
                            key = "Then, apply the colorCode:"
                        }
                        StepLabel {
                            +"Then, apply the colorCode:"
                        }
                        StepContent {
                            div(classes = "container-fluid") {
                                div(classes = "row") {
                                    FormControlLabel {
                                        attrs {
                                            color = "primary"
                                            control = Switch {
                                                attrs {
                                                    color = "primary"
                                                    checked = state.classCustomValue
                                                    onChange = { event: Event, eventValue: Boolean ->
                                                        setState {
                                                            classCustomValue = !classCustomValue
                                                        }
                                                    }
                                                    value = "classCustomColorChecked"
                                                }
                                            }
                                            label = "Custom class colorCode code"
                                        }
                                    }
                                    if (!state.classCustomValue) {
                                        Typography { +"Shade:" }
                                        Slider {
                                            attrs {
                                                className = "slider-colorCode"
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
                                        div(classes = "colors-main-div") {
                                            for (i in colorsArray) {
                                                Radio {
                                                    attrs {
                                                        checked = state.selectedClassColorValue == i.toString()
                                                        value = i.toString()
                                                        color = "default"
                                                        icon = div(classes = "colors-div") { attrs { style = kotlinext.js.js { background = getColorPropertyValue(i) } } }
                                                        checkedIcon = div(classes = "colors-div-checked") { attrs { style = kotlinext.js.js { background = getColorPropertyValue(i) } }; MuiCheckIcon {} }
                                                        onChange = { event: Event, eventValue: Boolean ->
                                                            val eventTargetValue = (event.target as HTMLInputElement)?.value
                                                            setState {
                                                                selectedClassColorValue = eventTargetValue
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        TextField {
                                            attrs {
                                                className = "mt-2 graph-colors-setting-text-field"
                                                label = "Color code"
                                                value = getColorPropertyValue(state.selectedClassColorValue).toString()
                                            }
                                        }
                                    } else {
                                        TextField {
                                            attrs {
                                                className = "mt-2 graph-colors-setting-text-field"
                                                label = "Custom colorCode code"
                                                value = getColorPropertyValue(state.selectedClassColorValue).toString()
                                            }
                                        }
                                        Button {
                                            attrs {
                                                color = "primary"
                                                className = "mt-3"
                                                onClick = {
                                                    setState {
                                                        openColorPalette = true
                                                    }
                                                }
                                            }
                                            +"See colorCode palette"
                                        }
                                    }
                                }
                                div(classes = "row pt-3") {
                                    Button {
                                        attrs {
                                            className = "ml-2"
                                            color = "primary"
                                            onClick = {
                                                setState {
                                                    activeStep = activeStep - 1
                                                }
                                            }
                                        }
                                        +"Back"
                                    }
                                    Button {
                                        attrs {
                                            className = "ml-2"
                                            variant = "contained"
                                            color = "primary"
                                            onClick = {
                                                setState {
                                                    props.colorRuleInterface.openCloseColorRule(
                                                            mutableListOf(
                                                                    ColorRule(
                                                                            colorCode = getColorPropertyValue(state.selectedClassColorValue).toString(),
                                                                            elementType = classType.toInt(),
                                                                            elementValue = state.elementValue)
                                                            )
                                                    )
                                                    activeStep = activeStep + 1
                                                }
                                            }
                                        }
                                        +"Save"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.graphEditorColorRuleComponent(openColorRule: Boolean, colorRuleInterface: ColorRuleInterface) = child(GraphEditorColorRule::class) {
    attrs.openColorRule = openColorRule
    attrs.colorRuleInterface = colorRuleInterface
}