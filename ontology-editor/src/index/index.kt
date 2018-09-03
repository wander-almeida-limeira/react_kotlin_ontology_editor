package index

import app.*
import kotlinext.js.*
import kotlinx.html.A
import kotlinx.html.InputType
import org.w3c.dom.*
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.WheelEvent
import org.w3c.dom.svg.SVGGraphicsElement
import org.w3c.dom.svg.SVGSVGElement
import org.w3c.xhr.XMLHttpRequest
import react.dom.*
import kotlin.browser.*
import kotlin.js.Json
import kotlin.js.Math
import kotlin.js.json

var clickDragAndDrop=false
var clickSvg=false
var clickX=0
var clickY=0
var moveX=0.0
var moveY=0.0
var lastMoveX=0.0
var lastMoveY=0.0
var lastMoveXPan=0.0
var lastMoveYPan=0.0
lateinit var selectedElement: Element
//var selectedElementFlag: Element
var deltaX=0.0
var deltaY=0.0
var scaleSVG=1
var svgWidth=0
var svgHeight=0
var jsonLD = null
var n3=0

var leftContentAreaAddElementsOpen = false
var textEditorOpen = false
var transMatrixA = 1.0
var transMatrixB = 0.0
var transMatrixC = 0.0
var transMatrixD = 1.0
var transMatrixDx = 0.0
var transMatrixDy = 0.0

fun evaluationContent() {

    //val url = "https://cors-anywhere.herokuapp.com/" + "https://script.google.com/macros/s/AKfycbxRb31jpdxRe9WCrLwLXmtJYtfYx5wBQI4cs4Qa-hM_xTLPcuzo/exec?create=true"
    val url = "http://rdf-translator.appspot.com/convert/n3/json-ld/content"
    val n3 = """{ "content": "@prefix owl: <http://www.w3.org/2002/07/owl#> . @prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> . @prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> . @prefix : <http://www.scc2310.ia#> . :InstrumentoMusical rdf:type owl:Class . :Cordas rdf:type owl:Class ; rdfs:subClassOf :InstrumentoMusical . :Sopro rdf:type owl:Class ; rdfs:subClassOf :InstrumentoMusical . :Violino rdf:type :Cordas ; :musico 'A'. :Violao rdf:type :Cordas ; :musico 'B'. :Trombone rdf:type :Sopro ; :musico 'C'. :Violoncelo rdf:type :Cordas ; :musico 'D'."}"""
    val req = XMLHttpRequest()
    req.onloadend = fun(event: Event){
        if (req.status.toInt() != 200) {
            return
        }
        val json = JSON.parse<Json>(req.responseText)
        //divContentChange("evaluation_button_id", json["url"] as String)
        return
    }
    req.onerror = fun (_) {

    }
    req.open("POST", url, true)
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    req.send(JSON.parse(n3))
}

fun main(args: Array<String>) {
    //evaluationContent()

    jsonLD = JSON.parse("""[     {       "@id": "http://www.scc2310.ia#Violoncelo",       "@type": "http://www.scc2310.ia#Cordas",       "http://www.scc2310.ia#musico": "D"     },     {       "@id": "http://www.scc2310.ia#Violao",       "@type": "http://www.scc2310.ia#Cordas",       "http://www.scc2310.ia#musico": "B"     },     {       "@id": "http://www.scc2310.ia#Cordas",       "@type": "owl:Class",       "rdfs:subClassOf": {         "@id": "http://www.scc2310.ia#InstrumentoMusical"       }     },     {       "@id": "http://www.scc2310.ia#Sopro",       "@type": "owl:Class",       "rdfs:subClassOf": {         "@id": "http://www.scc2310.ia#InstrumentoMusical"       }     },     {       "@id": "http://www.scc2310.ia#InstrumentoMusical",       "@type": "owl:Class"     },     {       "@id": "http://www.scc2310.ia#Violino",       "@type": "http://www.scc2310.ia#Cordas",       "http://www.scc2310.ia#musico": "A"     },     {       "@id": "http://www.scc2310.ia#Trombone",       "@type": "http://www.scc2310.ia#Sopro",       "http://www.scc2310.ia#musico": "C"     }   ]""")
    //jsonLD = JSON.parse("""[   {       "@id": "http://www.scc2310.ia#Node2",   "@type": "owl:Class",       "rdfs:subClassOf": {         "@id": "http://www.scc2310.ia#Node1"       }     },     {      "@id": "http://www.scc2310.ia#Node4",       "@type": "owl:Class",       "rdfs:subClassOf": {         "@id": "http://www.scc2310.ia#Node1"       }     },     {    "@id": "http://www.scc2310.ia#Node1",       "@type": "owl:Class"     }   ]""")
    requireAll(require.context("src", true, js("/\\.css$/")))

    render(document.getElementById("root")) {
        app()
    }
/*    window.onload = {
        svgWidth = document.getElementById("svg")!!.clientWidth
        svgHeight = document.getElementById("svg")!!.clientHeight

        createElements()
        connectAll()
        val svg = document.getElementById("svg")
        svg?.addEventListener("mousemove", fun(event: Event)  {
            var evento = event as MouseEvent
           mousemove(evento)
        })
        svg?.addEventListener("mousedown", fun(event: Event)  {
            var evento = event as MouseEvent
            clickSvg=true
            lastMoveXPan = evento.clientX.toDouble()
            lastMoveYPan = evento.clientY.toDouble()
        })

        svg?.addEventListener("mouseup", fun(event: Event)  {
            clickSvg=false
        })

        window?.addEventListener("wheel", fun(event: Event)  {
            var evento = event as WheelEvent
            wheel(evento)
        })
    }*/
}

fun mouseDown(evt: MouseEvent){
    clickDragAndDrop=true
    clickX = evt.clientX
    clickY = evt.clientY
    selectedElement = evt.target as Element
    var parentNode = selectedElement.parentNode as Element
    lastMoveX = parentNode.attributes.getNamedItem("transform")?.value?.split("(")?.get(1)?.split(")")?.get(0)?.split(",")?.get(0)!!.toDouble()
    lastMoveY = parentNode.attributes.getNamedItem("transform")?.value?.split("(")?.get(1)?.split(")")?.get(0)?.split(",")?.get(1)!!.toDouble()
    //setNotNull()
}

fun endMove(evt: MouseEvent){
    clickDragAndDrop=false
    var target = evt.target as Element
    target.setAttribute("fill","gray")
}

fun createElements() {
    var indexCircles = 0
    var indexRects = 0
    var indexPaths = 0
    var svgNode = document.getElementById("allElements")
    var defs = document.createElementNS("http://www.w3.org/2000/svg", "defs")
    val jsonArray = jsonLD as Array<dynamic>
    for (index in jsonArray.indices) {
        if (jsonArray[index]["rdfs:subClassOf"] != undefined || (jsonArray[index]["@type"] != undefined && jsonArray[index]["@type"] != "owl:Class")) {
            indexPaths++
            createPaths(indexPaths, jsonArray[index], svgNode, defs, true, false, null)
        }
        val jsonIndex = Object.keys(jsonArray[index]) as Array<dynamic>
        for (subIndex in jsonIndex.indices) {
            if (jsonIndex[subIndex].includes(":") && jsonIndex[subIndex] != "rdfs:subClassOf") {
                indexPaths++;
                createPaths(indexPaths, jsonArray[index], svgNode, defs, false, true, jsonIndex[subIndex]);
            }
    }
    }
    for (index in jsonArray.indices) {
        if (jsonArray[index]["@type"] != undefined && jsonArray[index]["@type"] == "owl:Class") {
            indexCircles++
            createClass(indexCircles, jsonArray[index], svgNode)
        } else if (jsonArray[index]["@type"] != undefined && jsonArray[index]["@type"] != "owl:Class") {
            indexRects++
            createIndividual(indexRects, jsonArray[index], svgNode)
        }
        val jsonIndex = Object.keys(jsonArray[index]) as Array<dynamic>
        for (subIndex in jsonIndex.indices) {
            if (jsonIndex[subIndex].includes(":") && jsonIndex[subIndex] != "rdfs:subClassOf") {
                indexRects++
                createLiteral(indexRects, jsonArray[index], svgNode, jsonIndex[subIndex])
            }
        }
    }
}

fun createPaths(index: Int, element: dynamic, svgNode: Element?, defs: Element, arrowStart: Boolean, arrowEnd: Boolean, key: dynamic) {
    var path = document.createElementNS("http://www.w3.org/2000/svg", "path")
    path.setAttribute("id", "path" + index)

    var subject = element["@id"].split("#")[1]
    var predicate = ""
    lateinit var objectElement: Element

    if (element["rdfs:subClassOf"] != undefined) {
        predicate = "rdfs:subClassOf"
        objectElement = element["rdfs:subClassOf"]["@id"].split("#")[1]
    } else if (element["@type"] != undefined && element["@type"].includes("#") && key == undefined) {
        predicate = "rdf:type"
        objectElement = element["@type"].split("#")[1];
    } else {
        if (key.includes("ns1")) {
            predicate = key.split(":")[1]
        } else {
            predicate = key.split("#")[1]
        }
        objectElement = element[key]
    }

    path.setAttribute("name", subject + "-" + objectElement)
    createMarkers(svgNode, defs, arrowStart, arrowEnd, path)
    svgNode?.appendChild(path)

    var g = document.createElementNS("http://www.w3.org/2000/svg", "g")
    g.setAttribute("id", "predicate" + index)

    var rect = document.createElementNS("http://www.w3.org/2000/svg", "rect")
    rect.setAttribute("id", "predicateRect" + index)
    rect.setAttribute("width", "100")
    rect.setAttribute("height", "20")
    rect.setAttribute("fill", "#acf")

    var text = document.createElementNS("http://www.w3.org/2000/svg", "text")
    text.setAttribute("id", "predicateText" + index)
    text.setAttribute("font-family", "Verdana")
    text.setAttribute("font-size", "10")
    text.setAttribute("fill", "black")
    text.textContent  = predicate

    g.appendChild(rect)
    g.appendChild(text)
    svgNode?.appendChild(g)
}

fun createClass(index: Int, element: dynamic, svgNode: Element?) {
    var classXPosition = 0.0
    var classYPosition = 0.0
    var g = document.createElementNS("http://www.w3.org/2000/svg", "g")
    var textContent = element["@id"].split("#")[1]
    if (textContent.length > 12) {
        textContent =  textContent.substring(0, 12) + "..."
    }
    g.setAttribute("id", "circle" + index)
    g.setAttribute("name", element["@id"].split("#")[1])
    g.setAttribute("class", "node")
    //TODO This block requires an algorithm for positioning plotting
    //Fixed elements
    if (element["@id"].split("#")[1] == "InstrumentoMusical" || element["@id"].split("#")[1] == "Instrumentos") {
        classXPosition = ((svgWidth / 6) / 2) * 3.2
        classYPosition = (svgHeight / 6).toDouble()
    }
    if (element["@id"].split("#")[1] == "Sopro") {
        classXPosition = ((svgWidth / 6) / 2).toDouble()
        classYPosition = (svgHeight / 6) * 3.toDouble()
    }
    if (element["@id"].split("#")[1] == "Cordas") {
        classXPosition = ((svgWidth / 6) / 2) * 5.2
        classYPosition = (svgHeight / 6) * 3.toDouble()
    }
    if (element["@id"].split("#")[1] == "New Class") {
        classXPosition = ((svgWidth / 6) / 2) * 7.2
        classYPosition = (svgHeight / 6).toDouble()
        g.setAttribute("id", "circle" + 999)
    }
    //End TODO
    g.setAttribute("transform", "translate("+ classXPosition +","+ classYPosition +")")

    var circle = document.createElementNS("http://www.w3.org/2000/svg", "circle")
    circle.setAttribute("class", "strokeTripleCircle draggable")
    circle.setAttribute("r", "50")

    g.addEventListener("mousedown", fun(event: Event)  {
        mouseDown(event as MouseEvent)
    })

    g.addEventListener("mouseup", fun(event: Event)  {
        endMove(event as MouseEvent)
    })

    g.addEventListener("dblclick", fun(event: Event)  {
        //editElement(event as MouseEvent)
    })

    var title = document.createElementNS("http://www.w3.org/2000/svg", "title")
    title.textContent  = element["@id"].split("#")[1]
    circle.appendChild(title)

    var text = document.createElementNS("http://www.w3.org/2000/svg", "text")
    text.setAttribute("class", "text")
    text.setAttribute("text-anchor", "middle")
    text.setAttribute("style", "fill: rgb(0, 0, 0);")
    text.setAttribute("y", "-6.625px")

    var tspan = document.createElementNS("http://www.w3.org/2000/svg", "tspan")
    tspan.setAttribute("class", "text")
    tspan.setAttribute("x", "0")
    tspan.setAttribute("dy", "12px")
    tspan.textContent  = textContent
    text.appendChild(tspan)

    g.appendChild(circle)
    g.appendChild(text)
    svgNode?.appendChild(g)
}

fun createLiteral(index: Int, element: dynamic, svgNode: Element?, key: dynamic) {
    var literalXPosition = 0.0
    var literalYPosition = 0.0
    var g = document.createElementNS("http://www.w3.org/2000/svg", "g")
    g.setAttribute("id", "literal" + index)
    g.setAttribute("name", element[key])
    g.setAttribute("class", "node")
    //TODO This block requires an algorithm for positioning plotting
    //fixed elements
    if (element[key] == "A") {
        literalXPosition = (((svgWidth / 6) / 2) * 9).toDouble()
        literalYPosition = ((svgHeight / 6) * 3 - 80).toDouble()
    }
    if (element[key] == "B") {
        literalXPosition = (((svgWidth / 6) / 2) * 9).toDouble()
        literalYPosition = ((svgHeight / 6) * 3).toDouble()
    }
    if (element[key] == "D") {
        literalXPosition = (((svgWidth / 6) / 2) * 9).toDouble()
        literalYPosition = ((svgHeight / 6) * 3 + 80).toDouble()
    }
    if (element[key] == "C") {
        literalXPosition = (((svgWidth / 6) / 2) * 5.2).toDouble()
        literalYPosition = ((svgHeight / 6) * 5).toDouble()
    }
    //End TODO
    g.setAttribute("transform", "translate("+ literalXPosition +","+ literalYPosition +")")

    var rect = document.createElementNS("http://www.w3.org/2000/svg", "rect")
    rect.setAttribute("class", "strokeTripleRectLiteral strokeDasharray draggable")
    rect.setAttribute("x", "-26.5")
    rect.setAttribute("y", "-10")
    rect.setAttribute("width", "52")
    rect.setAttribute("height", "20")

    g.addEventListener("mousedown", fun(event: Event)  {
        mouseDown(event as MouseEvent)
    })

    g.addEventListener("mouseup", fun(event: Event)  {
        endMove(event as MouseEvent)
    })

    var title = document.createElementNS("http://www.w3.org/2000/svg", "title")
    title.textContent = element[key]
    rect.appendChild(title)

    var text = document.createElementNS("http://www.w3.org/2000/svg", "text")
    text.setAttribute("class", "text")
    text.setAttribute("text-anchor", "middle")
    text.setAttribute("style", "fill: rgb(0, 0, 0);")
    text.setAttribute("y", "-6.625px")

    var tspan = document.createElementNS("http://www.w3.org/2000/svg", "tspan")
    tspan.setAttribute("class", "text")
    tspan.setAttribute("x", "0")
    tspan.setAttribute("dy", "12px")
    tspan.textContent = element[key]
    text.appendChild(tspan)

    g.appendChild(rect)
    g.appendChild(text)
    svgNode?.appendChild(g)
}

fun createIndividual(index: Int, element: dynamic, svgNode: Element?) {
    var individualXPosition = 0.0
    var individualYPosition = 0.0
    var g = document.createElementNS("http://www.w3.org/2000/svg", "g")
    g.setAttribute("id", "individual" + index)
    g.setAttribute("name", element["@id"].split("#")[1])
    g.setAttribute("class", "node")
    //TODO This block requires an algorithm for positioning plotting
    //fixed elements
    if (element["@id"].split("#")[1] == "Violino") {
        individualXPosition = (((svgWidth / 6) / 2) * 7.2).toDouble()
        individualYPosition = ((svgHeight / 6) * 3 - 80).toDouble()
    }
    if (element["@id"].split("#")[1] == "Violao") {
        individualXPosition = ((svgWidth / 6) / 2) * 7.2
        individualYPosition = ((svgHeight / 6) * 3).toDouble()
    }
    if (element["@id"].split("#")[1] == "Violoncelo") {
        individualXPosition = ((svgWidth / 6) / 2) * 7.2
        individualYPosition = ((svgHeight / 6) * 3 + 80).toDouble()
    }
    if (element["@id"].split("#")[1] == "Trombone") {
        individualXPosition = ((svgWidth / 6) / 2) * 3.5
        individualYPosition = ((svgHeight / 6) * 3).toDouble()
    }
    //End TODO
    g.setAttribute("transform", "translate("+ individualXPosition +","+ individualYPosition +") rotate(45)")

    var rect = document.createElementNS("http://www.w3.org/2000/svg", "rect")
    rect.setAttribute("class", "strokeTripleRectObject draggable")
    rect.setAttribute("x", "-26.5")
    rect.setAttribute("y", "-10")
    rect.setAttribute("width", "50")
    rect.setAttribute("height", "50")

    g.addEventListener("mousedown", fun(event: Event)  {
        mouseDown(event as MouseEvent)
    })

    g.addEventListener("mouseup", fun(event: Event)  {
        endMove(event as MouseEvent)
    })

    var title = document.createElementNS("http://www.w3.org/2000/svg", "title")
    title.textContent  = element["@id"].split("#")[1]
    rect.appendChild(title)

    var text = document.createElementNS("http://www.w3.org/2000/svg", "text")
    text.setAttribute("class", "text")
    text.setAttribute("text-anchor", "middle")
    text.setAttribute("style", "fill: rgb(0, 0, 0);")
    text.setAttribute("transform", "rotate(-45)")

    var tspan = document.createElementNS("http://www.w3.org/2000/svg", "tspan")
    tspan.setAttribute("class", "text")
    tspan.setAttribute("x", "-10")
    tspan.setAttribute("dy", "12px")
    tspan.textContent = element["@id"].split("#")[1]
    text.appendChild(tspan)

    g.appendChild(rect)
    g.appendChild(text)
    svgNode?.appendChild(g)
}

fun createMarkers(svgNode: Element?,  defs: Element, arrowStart: Boolean, arrowEnd: Boolean, path: Element) {
    if (arrowStart) {
        var markerStart = document.createElementNS("http://www.w3.org/2000/svg", "marker")
        markerStart.setAttribute("id", "arrow-start-" + path.getAttribute("id"))
        markerStart.setAttribute("viewBox", "-14 -10 28 20")
        markerStart.setAttribute("refY", "0")
        markerStart.setAttribute("markerWidth", "10")
        markerStart.setAttribute("markerHeight", "10")
        markerStart.setAttribute("orient", "auto")
        var pathStart = document.createElementNS("http://www.w3.org/2000/svg", "path")
        markerStart.appendChild(pathStart)
        pathStart.setAttribute("d", "M0,0L 12,-8L12,8L0,0")
        svgNode?.appendChild(defs)
        defs.appendChild(markerStart)
        path.setAttribute("marker-start", "url(#arrow-start-" + path.getAttribute("id") + ")")
    }
    if (arrowEnd) {
        var markerEnd = document.createElementNS("http://www.w3.org/2000/svg", "marker")
        markerEnd.setAttribute("id", "arrow-end-" + path.getAttribute("id"))
        markerEnd.setAttribute("viewBox", "-14 -10 28 20")
        markerEnd.setAttribute("refY", "0")
        markerEnd.setAttribute("markerWidth", "10")
        markerEnd.setAttribute("markerHeight", "10")
        markerEnd.setAttribute("orient", "auto")
        var pathEnd = document.createElementNS("http://www.w3.org/2000/svg", "path")
        markerEnd.appendChild(pathEnd)
        pathEnd.setAttribute("d", "M0,0L -12,8L-12,-8L0,0")
        svgNode?.appendChild(defs)
        defs.appendChild(markerEnd)
        path.setAttribute("marker-end", "url(#arrow-end-" + path.getAttribute("id") + ")")
    }
}

private fun mousemove(evt: MouseEvent) {
    if(clickDragAndDrop){
        var selectedElementParentNode = selectedElement?.parentNode as Element
        connectAll()
        moveX = lastMoveX + ( evt.clientX - clickX )
        moveY = lastMoveY + ( evt.clientY - clickY )
        if (selectedElementParentNode.getAttribute("id")?.substring(0, 10) == "individual") {
            selectedElementParentNode.setAttribute("transform", "translate(" + moveX + "," + moveY + ") rotate(45)")
        } else {
            selectedElementParentNode.setAttribute("transform", "translate(" + moveX + "," + moveY + ")")
        }
    } else {
        if(clickSvg){
            if (lastMoveXPan != 0.0) {
                pan( evt.clientX - lastMoveXPan, 0.0)
            }
            lastMoveXPan = evt.clientX.toDouble()
            if (lastMoveYPan != 0.0) {
                pan( 0.0, evt.clientY - lastMoveYPan)
            }
            lastMoveYPan = evt.clientY.toDouble()
        }
    }
}


var width = null
var height = null

fun pan(dx: Double, dy: Double)
{
    deltaX += dx
    deltaY += dy
    var mapMatrix = document.getElementById("allElements")
    transMatrixDx += dx.toInt()
    transMatrixDy += dy.toInt()
    var newMatrix = "matrix(" +  "${transMatrixA} ${transMatrixB} ${transMatrixC} ${transMatrixD} ${transMatrixDx} ${transMatrixDy}" + ")"
    mapMatrix?.setAttributeNS(null, "transform", newMatrix)

}

fun wheel(event: WheelEvent){
    if (event.deltaY.toInt() == -100) {
        transMatrixA *= 1.25
        transMatrixD *= 1.25
    } else if (event.deltaY.toInt() == 100) {
        transMatrixA *= 0.8
        transMatrixD *= 0.8
    }

    var mapMatrix = document.getElementById("allElements")
    var newMatrix = "matrix(" +  "${transMatrixA} ${transMatrixB} ${transMatrixC} ${transMatrixD} ${transMatrixDx} ${transMatrixDy}" + ")"
    mapMatrix?.setAttributeNS(null, "transform", newMatrix)
}

public fun connectAll() {

    val jsonArray = jsonLD as Array<dynamic>
    for (index in jsonArray.indices) {
        if (jsonArray[index]["rdfs:subClassOf"] != undefined) {
            connectAllElements(
                    document.getElementsByName(jsonArray[index]["rdfs:subClassOf"]["@id"].split("#")[1]),
                    document.getElementsByName(jsonArray[index]["@id"].split("#")[1] + "-" + jsonArray[index]["rdfs:subClassOf"]["@id"].split("#")[1]),
                    document.getElementsByName(jsonArray[index]["@id"].split("#")[1]))
        }
        if (jsonArray[index]["@type"] != undefined && jsonArray[index]["@type"] != "owl:Class") {
            connectAllElements(
            document.getElementsByName(jsonArray[index]["@type"].split("#")[1]),
            document.getElementsByName(jsonArray[index]["@id"].split("#")[1]  + "-" + jsonArray[index]["@type"].split("#")[1]),
            document.getElementsByName(jsonArray[index]["@id"].split("#")[1]))
        }

        val jsonIndex = Object.keys(jsonArray[index]) as Array<dynamic>
        for (subIndex in jsonIndex.indices) {
            if (jsonIndex[subIndex].includes(":") && jsonIndex[subIndex] != "rdfs:subClassOf") {
                connectAllElements(
                document.getElementsByName(jsonArray[index]["@id"].split("#")[1]),
                document.getElementsByName(jsonArray[index]["@id"].split("#")[1] + "-" + jsonArray[index][jsonIndex[subIndex]]),
                document.getElementsByName(jsonArray[index][jsonIndex[subIndex]]))
            }
        }
    }
}

public fun connectAllElements(startElemNode: NodeList, path: NodeList, endElemNode: NodeList) {

    var svg = document.getElementById("svg")
    var element = path[0] as Element
    var startElem = startElemNode[0] as Element
    var endElem = endElemNode[0] as Element
    var startCoordLeft = getLeftOffset(startElem) - deltaX
    var startCoordTop = getTopOffset(startElem) - deltaY
    var endCoordLeft = getLeftOffset(endElem) - deltaX
    var endCoordTop = getTopOffset(endElem) - deltaY

    startCoordLeft = startCoordLeft + (startCoordLeft - (startCoordLeft * transMatrixA))
    startCoordTop = startCoordTop + (startCoordTop - (startCoordTop * transMatrixA))
    endCoordLeft = endCoordLeft + (endCoordLeft - (endCoordLeft * transMatrixA))
    endCoordTop = endCoordTop + (endCoordTop - (endCoordTop * transMatrixA))



    var startX: Double
    var startY: Double
    var endX: Double
    var endY: Double
    if (startElem.children[0]?.nodeName == "circle") {
        startX = startCoordLeft    + adjustValue(50.0)
        startY = startCoordTop //ajustar conforme header
        var marker = document.getElementById("arrow-start-" + element.getAttribute("id"))
        if (marker != undefined) {
            marker.setAttribute("refX", "-72")
        }
    } else {
        startX = startCoordLeft + adjustValue(36.0)
        startY = startCoordTop  - adjustValue(15.0) //ajustar conforme header
    }
    if (endElem.children[0]?.nodeName == "circle") {
        endX = endCoordLeft  + adjustValue(50.0)
        endY = endCoordTop //ajustar conforme header
        var marker = document.getElementById("arrow-end-" + element.getAttribute("id"))
        if (marker != undefined) {
            marker.setAttribute("refX", "72")
        }
    } else {
        if (endElem.children[0]?.nodeName == "rect" && endElem.getAttribute("id")!!.substring(0, 10) == "individual") {
            endX = endCoordLeft  + adjustValue(36.0)
            endY = endCoordTop   - adjustValue(15.0) //ajustar conforme header
        } else if (endElem.children[0]?.nodeName == "rect" && endElem.getAttribute("id")!!.substring(0, 7) == "literal") {
            endX = endCoordLeft  + adjustValue(25.0)
            endY = endCoordTop   - adjustValue(40.0) //ajustar conforme header
        } else {
            endX = endCoordLeft   + adjustValue(50.0)
            endY = endCoordTop
        }
        if (startElem.children[0]?.nodeName == "circle") {
            startCoordTop += adjustValue(40.0)
        }

        var marker = document.getElementById("arrow-end-" + element.getAttribute("id"))
        if (marker != undefined) {
            var tempLeft = (endCoordLeft - startCoordLeft) / 20
            var tempTop = (endCoordTop - startCoordTop) / 20
            if (29 + (kotlin.math.abs(tempLeft) - kotlin.math.abs(tempTop)) < 40) {
                marker.setAttribute("refX", (29 + (kotlin.math.abs(tempLeft) - kotlin.math.abs(tempTop))).toString())
            } else {
                marker.setAttribute("refX", "40")
            }
        }

    }
    if (transMatrixA > 1.0) {

    }
    if (transMatrixA < 1.0) {
        //startX = startX + (startX - (startX * transMatrixA))
        //endX = endX + (endX - (endX * transMatrixA))
        //startY = startY + (startY - (startY * transMatrixA))
        //endY = endY + (endY - (endY * transMatrixA))
    }
    drawPath(svg!!, path, startX, startY, endX, endY)
    drawPredicate(path)
}

private fun adjustValue(value: Double): Double {
    return value + (value - (value * transMatrixA))
}

public fun getLeftOffset(el: Element): Double {
    var el: DOMRect = el.getBoundingClientRect()
    return el.left + window.scrollX
}

public fun getTopOffset(el: Element): Double {
    var el: DOMRect = el.getBoundingClientRect()
    return el.top + window.scrollY
}

public fun drawPath(svg: Element, path: NodeList, startX: Double, startY: Double, endX: Double, endY: Double) {

    //var stroke = "3".toDouble()
    if (svg.clientHeight!!.toDouble() <  endY) {
        svg.setAttribute("height", endY.toString())
    }
    if (svg.clientHeight!!.toDouble() < (startX + 3)) {
        svg.setAttribute("width", (startX + 3).toString())
    }
    if (svg.clientWidth!!.toDouble() < (endX   + 3))    {
        svg.setAttribute("width", (endX   + 3).toString())
    }
    var element = path[0] as Element
    element.setAttribute("d",  "M"  + startX + " " + startY +  " L"  + endX + " " + endY);

}

public fun drawPredicate(path: NodeList) {

    var posicao = getCenter(path)
    var element = path[0] as Element
    var predicateRect = document.getElementById("predicateRect"+ element.getAttribute("id")!!.substring(4, element.id.length))
    var predicateText = document.getElementById("predicateText" + element.getAttribute("id")!!.substring(4, element.id.length))

    predicateRect!!.setAttribute("x", (posicao.x - 50).toString())
    predicateRect!!.setAttribute("y", (posicao.y - 10).toString())
    predicateText!!.setAttribute("x", (posicao.x - ((predicateText!!.textContent!!.length * 1.6) + 20)).toString())
    predicateText!!.setAttribute("y", (posicao.y + 5).toString())

}

public fun getCenter(path: NodeList): DOMPoint {

    var element = path[0] as Element
    var svgSubElement = document.getElementById(element.getAttribute("id")!!) as SVGGraphicsElement
    var bbox = svgSubElement.getBBox()
    var ctm = svgSubElement.getCTM()
    var cx = bbox.x + bbox.width/2
    var cy = bbox.y + bbox.height/2
    var svgElement = document.getElementById("svg") as SVGSVGElement
    var pt = svgElement.createSVGPoint()
    pt.x = cx - deltaX
    pt.y = cy - deltaY
    return pt.matrixTransform(ctm!!)
}