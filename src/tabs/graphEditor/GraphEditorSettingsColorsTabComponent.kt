
import kotlinx.html.style
import materialui.*
import materialui.Icons.MuiCheckIcon
import model.ColorRule
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import kotlin.js.Json

interface SettingsColorsTabInterface {
    fun openCloseSettings()
}

interface GraphEditorSettingsColorsTabProps : RProps {
    var settingsInterface: SettingsInterface
}

interface GraphEditorSettingsColorsTabState : RState {
    var selectedClassColorValue: String
    var selectedClassLabelColorValue: String
    var selectedRelationColorValue: String
    var classShadeValue: Int
    var classCustomValue: Boolean
    var relationCustomValue: Boolean
    var classLabelCustomValue: Boolean
    var classLabelShadeValue: Int
    var relationShadeValue: Int
    var openColorPalette: Boolean
    var openColorRule: Boolean
    var openBorderColorRule: Boolean
    var colorRuleArray: MutableList<ColorRule>
    var borderColorRuleArray: MutableList<Any>
}
class GraphEditorSettingsColorsTab(props: GraphEditorSettingsColorsTabProps) : RComponent<GraphEditorSettingsColorsTabProps, GraphEditorSettingsColorsTabState>(props) {

    val colorsArray = arrayOf("red","pink","purple","deepPurple","indigo","blue","lightBlue","cyan","teal","green","lightGreen","lime","yellow","amber","orange","deepOrange")

    override fun GraphEditorSettingsColorsTabState.init(props: GraphEditorSettingsColorsTabProps) {
        selectedClassColorValue = ""
        selectedClassLabelColorValue = ""
        selectedRelationColorValue = ""
        classShadeValue = 12
        classLabelShadeValue = 12
        relationShadeValue = 12
        openColorPalette = false
        openColorRule = false
        colorRuleArray = mutableListOf()
        borderColorRuleArray = mutableListOf()
    }

    fun setClassColorCode(code: String) = js("""
        var result = d3interface.setClassColorCode(code);
        d3interface.render();
        return result; """)

    fun setClassLabelColorCode(code: String) = js("""
        var result = d3interface.setClassLabelColorCode(code);
        d3interface.render();
        return result; """)

    fun setRelationColorCode(code: String) = js("""
        var result = d3interface.setRelationColorCode(code);
        d3interface.render();
        return result; """)

    fun d3Render() = js("""
        var result = d3interface.render();
        return result; """)

    fun setClassColorRule(colorRuleArray: MutableList<ColorRule>) = js("""
        var result = d3interface.setClassColorRule(colorRuleArray);
        d3interface.render();
        return result; """)

    fun getShadeValue(elementType: String): String {

        var shadeValue: Int = -1
        when (elementType) {
            "class" -> shadeValue = state.classShadeValue
            "relation" -> shadeValue = state.relationShadeValue
            "classLabel" -> shadeValue = state.classLabelShadeValue
        }
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

    fun getColorPropertyValue(colorBase: String, elementType: String): Any? {
        when (colorBase) {
            "red" -> return (red as Json)?.get(getShadeValue(elementType))
            "pink" -> return (pink as Json)?.get(getShadeValue(elementType))
            "purple" -> return (purple as Json)?.get(getShadeValue(elementType))
            "deepPurple" -> return (deepPurple as Json)?.get(getShadeValue(elementType))
            "indigo" -> return (indigo as Json)?.get(getShadeValue(elementType))
            "blue" -> return (blue as Json)?.get(getShadeValue(elementType))
            "lightBlue" -> return (lightBlue as Json)?.get(getShadeValue(elementType))
            "cyan" -> return (cyan as Json)?.get(getShadeValue(elementType))
            "teal" -> return (teal as Json)?.get(getShadeValue(elementType))
            "green" -> return (green as Json)?.get(getShadeValue(elementType))
            "lightGreen" -> return (lightGreen as Json)?.get(getShadeValue(elementType))
            "lime" -> return (lime as Json)?.get(getShadeValue(elementType))
            "yellow" -> return (yellow as Json)?.get(getShadeValue(elementType))
            "amber" -> return (amber as Json)?.get(getShadeValue(elementType))
            "orange" -> return (orange as Json)?.get(getShadeValue(elementType))
            "deepOrange" -> return (deepOrange as Json)?.get(getShadeValue(elementType))
            else -> {
                return ""
            }
        }
    }

    fun deleteCustomColorRule(): String {
        return ""
    }

    fun deleteCustomBorderColorRule(): String {
        return ""
    }

    override fun RBuilder.render() {
            div (classes="container-fluid") {
                div(classes = "row ") {
                        div (classes="col m-4"){
                            Typography {
                                attrs {
                                    className = "py-2"
                                    variant = "title"
                                }
                                +"Class Color"
                            }
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
                                                icon = div(classes = "colors-div") { attrs { style = kotlinext.js.js { background = getColorPropertyValue(i, "class") } } }
                                                checkedIcon = div(classes = "colors-div-checked") { attrs { style = kotlinext.js.js { background = getColorPropertyValue(i, "class") } }; MuiCheckIcon {} }
                                                onChange = { event: Event, eventValue: Boolean ->
                                                    val eventTargetValue = (event.target as HTMLInputElement)?.value
                                                    setState {
                                                        selectedClassColorValue = eventTargetValue
                                                        val result = setClassColorCode(getColorPropertyValue(selectedClassColorValue, "class").toString())
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
                                        value = getColorPropertyValue(state.selectedClassColorValue, "class").toString()
                                    }
                                }
                            } else {
                                TextField {
                                    attrs {
                                        className = "mt-2 graph-colors-setting-text-field"
                                        label = "Custom colorCode code"
                                        value = getColorPropertyValue(state.selectedClassColorValue, "class").toString()
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
                        div (classes="col m-4") {
                            Typography {
                                attrs {
                                    className = "py-2"
                                    variant = "title"
                                }
                                +"Relation Color"
                            }
                            FormControlLabel {
                                attrs {
                                    control = Switch {
                                        attrs {
                                            color = "primary"
                                            checked = state.relationCustomValue
                                            onChange = { event: Event, eventValue: Boolean ->
                                                setState {
                                                    relationCustomValue = !relationCustomValue
                                                }
                                            }
                                            value = "classCustomColorChecked"
                                        }
                                    }
                                    label = "Custom relation colorCode code"
                                }
                            }
                            if (!state.relationCustomValue) {
                                Typography { +"Shade:" }
                                Slider {
                                    attrs {
                                        className = "slider-colorCode"
                                        value = state.relationShadeValue
                                        min = 0
                                        max = 13
                                        step = 1
                                        onChange = { event: Event, eventValue: Int ->
                                            setState {
                                                relationShadeValue = eventValue
                                            }
                                        }
                                    }
                                }
                                div(classes = "colors-main-div") {
                                    for (i in colorsArray) {
                                        Radio {
                                            attrs {
                                                checked = state.selectedRelationColorValue == i.toString()
                                                value = i.toString()
                                                color = "default"
                                                icon = div(classes = "colors-div") { attrs { style = kotlinext.js.js { background = getColorPropertyValue(i, "relation") } } }
                                                checkedIcon = div(classes = "colors-div-checked") { attrs { style = kotlinext.js.js { background = getColorPropertyValue(i, "relation") } }; MuiCheckIcon {} }
                                                onChange = { event: Event, eventValue: Boolean ->
                                                    val eventTargetValue = (event.target as HTMLInputElement)?.value
                                                    setState {
                                                        selectedRelationColorValue = eventTargetValue
                                                        val result = setRelationColorCode(getColorPropertyValue(selectedRelationColorValue, "relation").toString())
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
                                        value = getColorPropertyValue(state.selectedRelationColorValue, "relation").toString()
                                    }
                                }
                            } else {
                                TextField {
                                    attrs {
                                        className = "mt-2 graph-colors-setting-text-field"
                                        label = "Custom colorCode code"
                                        value = getColorPropertyValue(state.selectedClassColorValue, "class").toString()
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
                        div (classes="col m-4") {
                            Typography {
                                attrs {
                                    className = "py-2"
                                    variant = "title"
                                }
                                +"Class Label Color"
                            }
                            FormControlLabel {
                                attrs {
                                    control = Switch {
                                        attrs {
                                            color = "primary"
                                            checked = state.classLabelCustomValue
                                            onChange = { event: Event, eventValue: Boolean ->
                                                setState {
                                                    classLabelCustomValue = !classLabelCustomValue
                                                }
                                            }
                                            value = "classCustomColorChecked"
                                        }
                                    }
                                    label = "Custom class label colorCode code"
                                }
                            }
                            if (!state.classLabelCustomValue) {
                                Typography { +"Shade:" }
                                Slider {
                                    attrs {
                                        className = "slider-colorCode"
                                        value = state.classLabelShadeValue
                                        min = 0
                                        max = 13
                                        step = 1
                                        onChange = { event: Event, eventValue: Int ->
                                            setState {
                                                classLabelShadeValue = eventValue
                                            }
                                        }
                                    }
                                }
                                div(classes = "colors-main-div") {
                                    for (i in colorsArray) {
                                        Radio {
                                            attrs {
                                                checked = state.selectedClassLabelColorValue == i.toString()
                                                value = i.toString()
                                                color = "default"
                                                icon = div(classes = "colors-div") { attrs { style = kotlinext.js.js { background = getColorPropertyValue(i, "classLabel") } } }
                                                checkedIcon = div(classes = "colors-div-checked") { attrs { style = kotlinext.js.js { background = getColorPropertyValue(i, "classLabel") } }; MuiCheckIcon {} }
                                                onChange = { event: Event, eventValue: Boolean ->
                                                    val eventTargetValue = (event.target as HTMLInputElement)?.value
                                                    setState {
                                                        selectedClassLabelColorValue = eventTargetValue
                                                        val result = setClassLabelColorCode(getColorPropertyValue(selectedClassLabelColorValue, "classLabel").toString())
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
                                        value = getColorPropertyValue(state.selectedClassLabelColorValue, "classLabel").toString()
                                    }
                                }
                            } else {
                                TextField {
                                    attrs {
                                        className = "mt-2 graph-colors-setting-text-field"
                                        label = "Custom colorCode code"
                                        value = getColorPropertyValue(state.selectedClassColorValue, "class").toString()
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
                }
                if (state.openColorPalette)
                    graphEditorColorPaletteComponent(state.openColorPalette, object : ColorPaletteInterface {
                        override fun openCloseColorPalette() {
                            setState {
                                openColorPalette = !openColorPalette
                            }
                        }
                    })
                if (state.openColorRule)
                    graphEditorColorRuleComponent(state.openColorRule, object : ColorRuleInterface {
                        override fun openCloseColorRule(colorRuleArray: MutableList<ColorRule>) {
                            setState {
                                state.colorRuleArray.addAll(colorRuleArray)
                                openColorRule = !openColorRule
                            }
                        }
                    })
                div(classes = "row") {
                    div(classes = "col pt-4 d-flex align-items-center") {
                        Typography {
                            attrs {
                                className = "m-2"
                                variant = "title"
                            }
                            +"Custom Color Rules:"
                        }
                        if (state.colorRuleArray != null) {
                            for (rule in state.colorRuleArray) {
                                Chip {
                                    attrs {
                                        className = "ml-2"
                                        avatar = Avatar { attrs { style = kotlinext.js.js { background = rule.colorCode } } }
                                        if (rule.elementType == 1)
                                        label = "Class: " + rule.elementValue
                                        onDelete = {
                                            deleteCustomColorRule()
                                        }
                                    }
                                }
                            }
                        }
                        Button {
                            attrs {
                                color = "primary"
                                onClick = {
                                    setState {
                                        openColorRule = !openColorRule
                                    }
                                }
                            }
                            +"Add new colorCode rule"
                        }
                    }
                }
                div(classes = "row") {
                    div(classes = "col pt-4 d-flex align-items-center") {
                        Typography {
                            attrs {
                                className = "m-2"
                                variant = "title"
                            }
                            +"Custom Border Color Rules:"
                        }
                        if (state.borderColorRuleArray != null) {
                            for (rule in state.borderColorRuleArray) {
                                Chip {
                                    attrs {
                                        avatar = Avatar { attrs { style = kotlinext.js.js { background = getColorPropertyValue("red", "class") } } }
                                        label = "Class Name"
                                        onDelete = {
                                            deleteCustomBorderColorRule()
                                        }
                                    }
                                }
                            }
                        }
                        Button {
                            attrs {
                                color = "primary"
                                onClick = {
                                    setState {
                                        openBorderColorRule = !openBorderColorRule
                                    }
                                }
                            }
                            +"Add new border colorCode rule"
                        }
                    }
                }
                div(classes = "row content-graph-editor-settings-div") {
                    div (classes="col align-self-end p-4 save-div") {
                        Button {
                            attrs {
                                color = "primary"
                                className = "save-graph-editor-settings-button"
                                variant = "contained"
                                onClick = {
                                    if (state.selectedClassColorValue != "") setClassColorCode(getColorPropertyValue(state.selectedClassColorValue, "class").toString())
                                    if (state.selectedRelationColorValue != "") setRelationColorCode(getColorPropertyValue(state.selectedRelationColorValue, "relation").toString())
                                    if (state.selectedClassLabelColorValue != "") setClassLabelColorCode(getColorPropertyValue(state.selectedClassLabelColorValue, "classLabel").toString())
                                    if (state.colorRuleArray.size > 0) setClassColorRule(state.colorRuleArray)
                                    d3Render()
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

fun RBuilder.graphEditorSettingsColorsTabComponent(settingsInterface: SettingsInterface) = child(GraphEditorSettingsColorsTab::class) {
    attrs.settingsInterface = settingsInterface
}