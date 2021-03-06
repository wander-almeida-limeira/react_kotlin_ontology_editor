let d3interface = {};

var classColorCode = "#448afe"
var classLabelColorCode = "white"
var relationColourCode = "#6c9d60"
var colorRuleArray = []
var nodes_data =  [];
var links_data = [];

function loader(url, doLoad) {
    $.getJSON("http://localhost:8080/owl-api/ontology/parse", doLoad)
    .error(function() {
        alert("Could not load "+url);
    });
}

function loader2(doLoad) {
    //$.getJSON("http://localhost:10555/classes?node=http%3A%2F%2Fsemantic.icmc.usp.br%2Fsustenagro%23TechnologicalEfficiencyInTheIndustrial", doLoad)
    //.error(function() {
    //    alert("Could not load "+url);
    //});

        $.ajax({
            url: "http://localhost:10555/classes?node=http%3A%2F%2Fsemantic.icmc.usp.br%2Fsustenagro%23TechnologicalEfficiencyInTheIndustrial",
            accepts: {
                json: 'application/ld+json, application/json'
            },
            // ensure Accept header is very specific for JSON-LD/JSON
            headers: {
                'Accept': 'application/ld+json, application/json'
            },
            dataType: 'json',
            crossDomain: true,
            success: function(data, textStatus, jqXHR) {
                console.log(data);
                console.log(textStatus);
                console.log(jqXHR);
            },
            error: function(jqXHR, textStatus, err) {
            }
        });
}


function loadGraph(url, fn) {
    function doLoad(result) {
    console.log(result);
		nodes_data = result.nodes;
		links_data = result.links;
		renderAfterLoading();
    }
    loader(url, doLoad);
}

function loadGraph2() {
    function doLoad(result) {
    console.log(result);
		//nodes_data = result.nodes;
		//links_data = result.links;
		//renderAfterLoading();
    }
    loader2(doLoad);
}

d3interface.setClassColorCode = function (colorCode) {
    classColorCode = colorCode;
}

d3interface.setClassLabelColorCode = function (colorCode) {
    classLabelColorCode = colorCode;
}

d3interface.setRelationColorCode = function (colorCode) {
    relationColourCode = colorCode;
}

d3interface.setClassColorRule = function (colorRules) {
    colorRuleArray = colorRules.toArray()
}

d3interface.render = function (json) {

//d3.select("#d3svg").remove();
d3.selectAll("#d3svg > *").remove();

//loadGraph();
//loadGraph2();

renderAfterLoading();

}

function renderAfterLoading() {
var svg = d3.select("#d3svg"),
    width = document.getElementById("d3svg").clientWidth,
    height = document.getElementById("d3svg").clientHeight;

    var radius = 40;
    var nodeType = "circle";
    //var nodeType = "rect";
    //let nodeType = "custom";

    function convertJsonLD() {

        let jsonArray = jsonLdInterface.getJson();

        //jsonld.expand(jsonArray, function(err, expanded) {
        //    console.log(expanded);
        //});

        // flatten a document
        // see: http://json-ld.org/spec/latest/json-ld/#flattened-document-form
        //jsonld.flatten(jsonArray, (err, flattened) => {
         //   console.log(flattened);
        //});

        // canonize (normalize) a document using the RDF Dataset Normalization Algorithm
        // (URDNA2015), see: http://json-ld.github.io/normalization/spec/
        //jsonld.canonize(jsonArray, {
        //  algorithm: 'URDNA2015',
        //  format: 'application/n-quads'
        //}, (err, canonized) => {
         // console.log(canonized);
        //});

        // serialize a document to N-Quads (RDF)
        //jsonld.toRDF(jsonArray, {format: 'application/n-quads'}, (err, nquads) => {
         // console.log(nquads);
        //});

        var notesKey = Object.keys(jsonArray)

        //criando o array de nós
        for(var i=0; i < notesKey.length; i++){
          let node = notesKey[i];
          var notes = jsonArray[notesKey[i]];
          for (var j=0; j < notes.length ; j++){
              let node = notes[j];
              var nodeObj = new Object();
              nodeObj.nodeName = node["@id"].split("#")[1].toString();
              nodes_data[j] = nodeObj;
          }
        }

        //criando o array de links
        for(var i=0; i < notesKey.length; i++){
          let node = notesKey[i];
          var notes = jsonArray[notesKey[i]];
          var count = 0;
          for (var j=0; j < notes.length ; j++){
           let node = notes[j];
              if (node["subClassOf"] != undefined) {
                  var linkObj = new Object();
                  linkObj.source = node["@id"].split("#")[1].toString();
                  linkObj.target = node["subClassOf"].split("#")[1].toString();
                  linkObj.linkName = "subClassOf";
                  links_data[count] = linkObj;
                  count++;
              }
          }
        }
    }
    convertJsonLD();
    console.log(nodes_data);
    console.log(links_data);

    //set up the simulation
    var simulation = d3.forceSimulation()
                        //add nodes
                        .nodes(nodes_data);

    var link_force =  d3.forceLink(links_data)
                            .id(function(d) { return d.nodeName; })
                            .distance(function (d) {
							    return 200;
                            })
                            .strength(1);

    var charge_force = d3.forceManyBody()
        .strength(-100);

    var center_force = d3.forceCenter(width / 2, height / 2);

    var collide_force = d3.forceCollide(1.2*35);

    //custom force to put stuff in a box
    function box_force(alpha) {
      for (var i = 0, n = nodes_data.length; i < n; ++i) {
        var curr_node = nodes_data[i];
        curr_node.x = Math.max(radius, Math.min(width - radius, curr_node.x));
        curr_node.y = Math.max(radius, Math.min(height - radius, curr_node.y));
      }
    }


    simulation
        .force("charge_force", charge_force)
        .force("center_force", center_force)
        .force("links",link_force)
        .force("box_force", box_force)
        .force("collide", collide_force)
     .alphaDecay(0.03)
              .velocityDecay(0.4);


    //add tick instructions:
    simulation.on("tick", tickActions );

    //draw lines for the links
    var link = svg.append("g")
          .attr("class", "links")
        .selectAll("line")
        .data(links_data)
        .enter().append("line")
          .attr("stroke-width", 2)
          .attr("marker-end", "url(#end)")
          .style("stroke", relationColourCode);

    var linkText = svg.append("g")
        .attr("class", "link-label")
        .selectAll("links")
        .data(links_data)
        .enter()
        .append("text")
		.attr("class", "link-label")
		.attr("font-family", "Arial, Helvetica, sans-serif")
		.attr("fill", "Black")
		.style("font", "normal 12px Arial")
		.attr("dy", ".35em")
		.attr("text-anchor", "middle")
		.text(function(d) {
			return d.linkName;
		});

    //draw circles for the nodes
    var node;
    if (nodeType == "circle") {
        node = svg.append("g")
            .attr("class", "nodes")
            .selectAll("circle")
            .data(nodes_data)
            .enter()
            .append("circle")
            .attr("r", radius)
            .attr("stroke", "#adadad")
            .attr("stroke-width", "2px")
            .attr("fill", getClassColorCode);

    } else if (nodeType == "rect") {
        node = svg.append("g")
          .attr("class", "nodes")
          .selectAll("rect")
          .data(nodes_data)
          .enter().append("rect")
            .attr("width", 40)
            .attr("height", 40)
            .attr("fill", "white");
    } else {
        node = svg.append("g")
          .attr("class", "nodes")
          .selectAll("rect")
          .data(nodes_data)
            .enter().append("path").attr("d",function(d) {return d.shape;})
            .style("stroke","black")
            .style("stroke-width",3)
            .style("fill","grey");
    }

    var text = svg.append("g")
          .attr("class", "labels")
          .selectAll("text")
          .data(nodes_data)
          .enter().append("text")
            .attr("dy", 2)
            .attr("text-anchor", "middle")
            .text(function(d) {return d.nodeName})
            .attr("fill", classLabelColorCode);

    svg.append("svg:defs").selectAll("marker")
        .data(["end"])      // Different link/path types can be defined here
      .enter().append("svg:marker")    // This section adds in the arrows
        .attr("id", String)
        .attr("viewBox", "0 -5 10 10")
        .attr("refX", 61)
        .attr("refY", 0.5)
        .attr("markerWidth", 4)
        .attr("markerHeight", 6)
        .attr("orient", "auto")
      .append("svg:path")
        .attr("d", "M0,-5L10,0L0,5")
        .attr("fill", relationColourCode)
        .style("stroke", relationColourCode);

    var drag_handler = d3.drag()
        .on("start", drag_start)
        .on("drag", drag_drag)
        .on("end", drag_end);

    drag_handler(node)

    //drag handler
    //d is the node
    function drag_start(d) {
     if (!d3.event.active) simulation.alphaTarget(0.3).restart();
        d.fx = d.x;
        d.fy = d.y;
    }

    function drag_drag(d) {
        d.fx = d3.event.x;
        d.fy = d3.event.y;
        //make sure you can't drag the circle outside the box
        //Math.max(radius, Math.min(width - radius, d3.event.x));
        //Math.max(radius, Math.min(height - radius, d3.event.y));
    }

    function getClassColorCode(d) {
      if (colorRuleArray.length > 0) {
        for (var i = 0; i < colorRuleArray.length; i++) {
            if (d.nodeName.toUpperCase() == colorRuleArray[i].elementValue.toUpperCase()) {
                return colorRuleArray[i].colorCode;
            }
        }
        return classColorCode;
      } else {
        return classColorCode;
      }
    }

    function drag_end(d) {
      if (!d3.event.active) simulation.alphaTarget(0);
      //d.fx = null;
      //d.fy = null;
    }

    function tickActions() {
        //update circle positions each tick of the simulation
        if (nodeType == "circle") {
           node
            .attr("cx", function(d) {
            return d.x;
            })
            .attr("cy", function(d) {
            return d.y;
            });
        } else if (nodeType == "rect") {
           node
            .attr("x", function(d) { return d.x; })
            .attr("y", function(d) { return d.y; });
        } else {
           node.attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; })
              //.attr("d", d3.svg.symbol()
                //.size(function(d) { return d.size; })
                //.type(function(d) { return d.type; }))
        }

        //update link positions
        link
            .attr("x1", function(d) { return d.source.x; })
            .attr("y1", function(d) { return d.source.y; })
            .attr("x2", function(d) { return d.target.x; })
            .attr("y2", function(d) { return d.target.y; });

        linkText
            .attr("x", function(d) {
                return ((d.source.x + d.target.x)/2);
            })
            .attr("y", function(d) {
                return ((d.source.y + d.target.y)/2);
            });

        text
          .attr("x", function(d) { return d.x; })
          .attr("y", function(d) { return d.y; });
    }


//zoom init

//create zoom handler
//zoom actions is a function that performs the zooming.
var zoom_handler = d3.zoom()
    .on("zoom", zoom_actions);

	//specify what to do when zoom event listener is triggered
function zoom_actions(){
  node.attr("transform", d3.event.transform);
  link.attr("transform", d3.event.transform);
  linkText.attr("transform", d3.event.transform);
  text.attr("transform", d3.event.transform);
}

//add zoom behaviour to the svg element
//same as svg.call(zoom_handler);
zoom_handler(svg);

//zoom end
}