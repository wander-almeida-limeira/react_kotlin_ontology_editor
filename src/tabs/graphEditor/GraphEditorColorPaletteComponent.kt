
import externalComponents.materialui.*
import kotlinext.js.js
import kotlinx.html.style
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import kotlin.js.Json

interface ColorPaletteInterface {
    fun openCloseColorPalette()
}

interface GraphEditorColorPaletteProps : RProps {
    var colorPaletteInterface: ColorPaletteInterface
    var openColorPalette: Boolean
}

interface GraphEditorColorPaletteState : RState {}

class GraphEditorColorPalette(props: GraphEditorColorPaletteProps) : RComponent<GraphEditorColorPaletteProps, GraphEditorColorPaletteState>(props) {

    val colorsArray = arrayOf("red","pink","purple","deepPurple","indigo","blue","lightBlue","cyan","teal","green","lightGreen","lime","yellow","amber","orange","deepOrange", "grey", "blueGrey", "brown")
    val shadesArray = arrayOf("50","100","200","300","400","500","600","700","800","900","A100","A200","A400","A700")

    fun getColorPropertyValue(colorBase: String, shade: String): Any? {
        when (colorBase) {
            "red" -> return (red as Json)?.get(shade)
            "pink" -> return (pink as Json)?.get(shade)
            "purple" -> return (purple as Json)?.get(shade)
            "deepPurple" -> return (deepPurple as Json)?.get(shade)
            "indigo" -> return (indigo as Json)?.get(shade)
            "blue" -> return (blue as Json)?.get(shade)
            "lightBlue" -> return (lightBlue as Json)?.get(shade)
            "cyan" -> return (cyan as Json)?.get(shade)
            "teal" -> return (teal as Json)?.get(shade)
            "green" -> return (green as Json)?.get(shade)
            "lightGreen" -> return (lightGreen as Json)?.get(shade)
            "lime" -> return (lime as Json)?.get(shade)
            "yellow" -> return (yellow as Json)?.get(shade)
            "amber" -> return (amber as Json)?.get(shade)
            "orange" -> return (orange as Json)?.get(shade)
            "deepOrange" -> return (deepOrange as Json)?.get(shade)
            "grey" -> return (grey as Json)?.get(shade)
            "blueGrey" -> return (blueGrey as Json)?.get(shade)
            "brown" -> return (brown as Json)?.get(shade)
            else -> {
                return ""
            }
        }
    }

    fun getTypographyColor(shade: String): String {
        when (shade) {
            "50" -> return js { color = "black";}
            "100" -> return js { color = "black";}
            "200" -> return js { color = "black";}
            "300" -> return js { color = "black";}
            "400" -> return js { color = "white";}
            "500" -> return js { color = "white";}
            "600" -> return js { color = "white";}
            "700" -> return js { color = "white";}
            "800" -> return js { color = "white";}
            "900" -> return js { color = "white";}
            "A100" -> return js { color = "black";}
            "A200" -> return js { color = "black";}
            "A400" -> return js { color = "white";}
            "A700" -> return js { color = "white";}
            else -> {
                return ""
            }
        }
    }

    override fun GraphEditorColorPaletteState.init(props: GraphEditorColorPaletteProps) {}

    override fun RBuilder.render() {
        Dialog{
            attrs {
                className="dialog-graph-editor-colorCode-palette"
                scroll = "paper"
                open = props.openColorPalette
                onClose = {
                    props.colorPaletteInterface.openCloseColorPalette()
                }
            }
            DialogTitle {
                +"Color Palette"
            }
            DialogContent {
                div(classes = "container") {
                    div(classes = "row") {
                        for (muiColor in colorsArray) {
                            div(classes = "col mb-5") {
                                div(classes = "colors-first-div-palette") {
                                    attrs { style = kotlinext.js.js { background = getColorPropertyValue(muiColor, "500") } }
                                    div(classes = "container colors-div-palette-height") {
                                        div(classes = "row justify-content-between colors-div-palette-height") {
                                            div(classes = "col palette-colorCode-code-grid") {
                                                div(classes = "row") {
                                                    Typography {
                                                        attrs {
                                                            className = "m-2"
                                                            variant = "body2"
                                                            style = getTypographyColor("500")
                                                        }
                                                        +muiColor
                                                    }
                                                }
                                                div(classes = "row align-items-end") {
                                                    Typography {
                                                        attrs {
                                                            className = "m-2"
                                                            variant = "body2"
                                                            style = getTypographyColor("500")
                                                        }
                                                        +"500"
                                                    }
                                                }
                                            }
                                            div(classes = "col align-self-end palette-colorCode-code-end") {
                                                Typography {
                                                    attrs {
                                                        className = "m-2"
                                                        variant = "body2"
                                                        style = getTypographyColor("500")
                                                    }
                                                    +getColorPropertyValue(muiColor, "500").toString()
                                                }
                                            }
                                        }
                                    }
                                }
                                for (shade in shadesArray) {
                                    div(classes = "colors-div-palette") {
                                        attrs { style = kotlinext.js.js { background = getColorPropertyValue(muiColor, shade) } }
                                        div(classes = "container colors-div-palette-height") {
                                            div(classes = "row justify-content-between colors-div-palette-height") {
                                                div(classes = "col palette-colorCode-code-grid") {
                                                    div(classes = "row align-items-end") {
                                                        Typography {
                                                            attrs {
                                                                className = "m-2"
                                                                variant = "body2"
                                                                style = getTypographyColor(shade)
                                                            }
                                                            +shade
                                                        }
                                                    }
                                                }
                                                div(classes = "col align-self-end palette-colorCode-code-end") {
                                                    Typography {
                                                        attrs {
                                                            className = "m-2"
                                                            variant = "body2"
                                                            style = getTypographyColor(shade)
                                                        }
                                                        +getColorPropertyValue(muiColor, shade).toString()
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
            }
            DialogActions {
                Button {
                    attrs {
                        className = "save-graph-editor-settings-button"
                        variant = "contained"
                        onClick = {
                            props.colorPaletteInterface.openCloseColorPalette()
                        }
                    }
                    +"OK"
                }
            }
        }
    }
}

fun RBuilder.graphEditorColorPaletteComponent(openColorPalette: Boolean, colorPaletteInterface: ColorPaletteInterface) = child(GraphEditorColorPalette::class) {
    attrs.openColorPalette = openColorPalette
    attrs.colorPaletteInterface = colorPaletteInterface
}