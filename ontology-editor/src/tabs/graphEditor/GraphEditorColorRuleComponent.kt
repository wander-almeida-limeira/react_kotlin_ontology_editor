
import materialui.*
import materialui.Icons.MuiCloseIcon
import react.*
import react.dom.div

interface ColorRuleInterface {
    fun openCloseColorRule()
}

interface GraphEditorColorRuleProps : RProps {
    var colorRuleInterface: ColorRuleInterface
    var openColorRule: Boolean
}

interface GraphEditorColorRuleState : RState {
    var activeStep: Int
    var classType: String
}

class GraphEditorColorRule(props: GraphEditorColorRuleProps) : RComponent<GraphEditorColorRuleProps, GraphEditorColorRuleState>(props) {

    override fun GraphEditorColorRuleState.init(props: GraphEditorColorRuleProps) {
        classType = ""
    }

    override fun RBuilder.render() {
        Dialog{
            attrs {
                fullScreen = true
                open = props.openColorRule
                onClose = {
                    props.colorRuleInterface.openCloseColorRule()
                }
            }
            AppBar {
                attrs {
                    color = "secondary"
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
                                +"Graph Settings"
                            }
                            IconButton {
                                attrs {
                                    onClick = {
                                        props.colorRuleInterface.openCloseColorRule()
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
                    className="dialog-graph-editor-color-rule-content"
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
                            FormControl {
                                /*InputLabel {
                                    attrs {
                                        htmlFor = "age-helper"
                                    }
                                    +"Age"
                                }*/
                                Select {
                                    attrs {
                                        displayEmpty = true
                                        value = state.classType
                                        //input = Input { attrs { name = "age"; id = "age-helper"; value = state.classType }}
                                        onChange = { event: dynamic, eventValue: Boolean ->
                                            val eventTargetValue = event.target.value
                                            setState {
                                                classType = eventTargetValue
                                            }
                                        }
                                        //inputProps = JSON.parse("""{name: "age", id: "age-simple"}""")
                                    }
                                    MenuItem {
                                        attrs {
                                            value =  ""
                                        }
                                    }
                                    MenuItem {
                                        attrs {
                                            value =  "Class"
                                        }
                                        +"Class"
                                    }
                                }
                                FormHelperText {
                                    +"Choose the element type"
                                }
                            }
                            Button {
                                attrs {
                                    className = "ml-2"
                                    variant="contained"
                                    color="primary"
                                }
                                +"Next"
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
                            Button {
                                attrs {
                                    color="primary"
                                }
                                +"Back"
                            }
                            Button {
                                attrs {
                                    variant="contained"
                                    color="primary"
                                }
                                +"Next"
                            }
                        }
                    }
                    Step {
                        attrs {
                            key = "Then, apply the color:"
                        }
                        StepLabel {
                            +"Then, apply the color:"
                        }
                        StepContent {
                            Button {
                                attrs {
                                    color="primary"
                                }
                                +"Back"
                            }
                            Button {
                                attrs {
                                    variant="contained"
                                    color="primary"
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

fun RBuilder.graphEditorColorRuleComponent(openColorRule: Boolean, colorRuleInterface: ColorRuleInterface) = child(GraphEditorColorRule::class) {
    attrs.openColorRule = openColorRule
    attrs.colorRuleInterface = colorRuleInterface
}