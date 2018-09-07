let d3interface = {};

var classColorCode = "#448afe"

d3interface.setClassColorCode = function (colorCode) {
    classColorCode = colorCode;
}

d3interface.render = function (json) {

var svg = d3.select("#d3svg"),
    width = document.getElementById("d3svg").clientWidth,
    height = document.getElementById("d3svg").clientHeight;

    var radius = 40;
    var nodeType = "circle";
    //var nodeType = "rect";
    //let nodeType = "custom";

var nodes_data =  [
    {"name": "Componente", "sex": "F"},
    {"name": "Sistema de Busca", "sex": "M"},
    {"name": "Sistema de Organização", "sex": "M"},
    {"name": "Sistema de Rotulação", "sex": "F"},
    {"name": "Sistema de Navegação", "sex": "F"},
    {"name": "Interface de Busca", "sex": "M"},
    {"name": "Estrutura", "sex": "F"},
    {"name": "Esquemas de Organização", "sex": "M"},
    {"name": "Icone", "sex": "M"},
    {"name": "Link Textual", "sex": "F"},
    {"name": "Embutido", "sex": "M"},
    {"name": "Engenho de Busca", "sex": "F"},
    {"name": "Top Down", "sex": "M"},
    {"name": "Bottom Up", "sex": "M"},
    {"name": "Ambigua", "sex": "M"},
    {"name": "Exata", "sex": "M"},
    {"name": "Global", "sex": "F"},
    {"name": "Local", "sex": "M"},
    {"name": "Contextual", "sex": "F"},
    {"name": "Conteudo", "sex": "F"},
    {"name": "Hibrido", "sex": "F"},
    {"name": "Publico Alvo", "sex": "F"},
    {"name": "Tarefa", "sex": "M"},
    {"name": "Metafora", "sex": "F"},
    {"name": "Assunto", "sex": "M"},
    {"name": "Alfabeto", "sex": "M"},
    {"name": "Localização", "sex": "F"},
	{"name": "Tempo", "sex": "F"},
	{"name": "Resultado", "sex": "F"},
	{"name": "Usuário", "sex": "F"}
    ]

    //Sample links data
    //type: A for Ally, E for Enemy
var links_data = [
	{"source": "Sistema de Busca", "target": "Componente", "type":"É um" },
    {"source": "Sistema de Organização", "target": "Componente", "type":"É um" },
    {"source": "Sistema de Rotulação", "target": "Componente", "type":"É um"},
    {"source": "Sistema de Navegação", "target": "Componente", "type":"É um"},
    {"source": "Sistema de Busca", "target": "Interface de Busca", "type":"Contem"},
    {"source": "Interface de Busca", "target": "Engenho de Busca", "type":"Alimenta"},
    {"source": "Engenho de Busca", "target": "Conteudo", "type":"Pesquisa No"},
	{"source": "Conteudo", "target": "Resultado", "type":"Apresenta Como"},
	{"source": "Resultado", "target": "Usuário", "type":"Analisado"},
	{"source": "Usuário", "target": "Interface de Busca", "type":"Faz Consulta"},
	{"source": "Sistema de Organização", "target": "Estrutura", "type":"Segue"},
	{"source": "Top Down", "target": "Estrutura", "type":"É um"},
	{"source": "Bottom Up", "target": "Estrutura", "type":"É um"},
    {"source": "Ambigua", "target": "Esquemas de Organização", "type":"É um"},
	{"source": "Sistema de Organização", "target": "Esquemas de Organização", "type":"Está Organizado em"},
	{"source": "Exata", "target": "Esquemas de Organização", "type":"É um"},
	{"source": "Hibrido", "target": "Ambigua", "type":"É um"},
	{"source": "Publico Alvo", "target": "Ambigua", "type":"É um"},
	{"source": "Tarefa", "target": "Ambigua", "type":"É um"},
	{"source": "Metafora", "target": "Ambigua", "type":"É um"},
	{"source": "Assunto", "target": "Ambigua", "type":"É um"},
	{"source": "Alfabeto", "target": "Exata", "type":"É um"},
	{"source": "Localização", "target": "Exata", "type":"É um"},
	{"source": "Tempo", "target": "Exata", "type":"É um"},
	{"source": "Icone", "target": "Sistema de Rotulação", "type":"É um"},
	{"source": "Link Textual", "target": "Sistema de Rotulação", "type":"É um"},
	{"source": "Embutido", "target": "Sistema de Navegação", "type":"É um"},
	{"source": "Global", "target": "Embutido", "type":"É um"},
	{"source": "Local", "target": "Embutido", "type":"É um"},
	{"source": "Contextual", "target": "Embutido", "type":"É um"},
]


    //set up the simulation
    var simulation = d3.forceSimulation()
                        //add nodes
                        .nodes(nodes_data);

    var link_force =  d3.forceLink(links_data)
                            .id(function(d) { return d.name; })
                            .distance(function (d) {
							    return 10;
                            })
                            .strength(0.1);

    var charge_force = d3.forceManyBody()
        .strength(-100);

    var center_force = d3.forceCenter(width / 2, height / 2);

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
     ;


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
          .style("stroke", linkColour);

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
			return d.type;
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
            .attr("fill", classColorCode);

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
            .text(function(d) {return d.name})
            .attr("fill", "white");

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
        .attr("d", "M0,-5L10,0L0,5");

    var drag_handler = d3.drag()
        .on("start", drag_start)
        .on("drag", drag_drag)
        .on("end", drag_end);

    drag_handler(node)

    //Function to choose the line colour and thickness
    //If the link type is "A" return green
    //If the link type is "E" return red
    function linkColour(d){
        if(d.type == "A"){
            return "#6c9d60";
        } else {
            return "#6c9d60";
        }
    }

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


    function drag_end(d) {
      if (!d3.event.active) simulation.alphaTarget(0);
      //d.fx = null;
      //d.fy = null;
    }

    function tickActions() {
        //update circle positions each tick of the simulation
        if (nodeType == "circle") {
           node
            .attr("cx", function(d) { return d.x; })
            .attr("cy", function(d) { return d.y; });
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