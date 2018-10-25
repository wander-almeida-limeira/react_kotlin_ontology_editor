package util

import org.w3c.dom.events.Event
import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Json

fun post() {

    val url = "http://localhost:8080/owl-api/ontology/parse"
    val req = XMLHttpRequest()
    req.onloadend = fun(event: Event){
        if (req.status.toInt() != 200) {
            return
        }
        val json = JSON.parse<Json>(req.responseText)
        return
    }
    req.onerror = fun (_) {

    }
    req.open("POST", url, true)
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    req.send(null)
}

fun get() {
    val req = XMLHttpRequest()
    req.open("GET", "http://www.example.com/", true)
    req.onreadystatechange = fun(event: Event): Json {
        val json = JSON.parse<Json>(req.responseText)
        return json
    }
    req.send()
}