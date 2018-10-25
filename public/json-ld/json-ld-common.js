
let jsonLdInterface = {};

jsonLdInterface.getJson = function (colorCode) {
    let json = {
                 "@graph" : [ {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#AdequacyOfBoilers",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Adequação das caldeiras"
                   }, {
                     "@language" : "en",
                     "@value" : "Adequacy of boilers"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#AtmosphericPollutantsEmission",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Emissão de poluentes atmosféricos"
                   }, {
                     "@language" : "en",
                     "@value" : "Atmospheric pollutants emission"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#BagasseAndStrawDestination",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Destinação mais sustentável do bagaço e palha (Por ex. produção de etanol de 2ª geração, produção de energia, etc)"
                   }, {
                     "@language" : "en",
                     "@value" : "Bagasse and straw destination for 2nd generation ethanol production"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#BagasseDestination",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Destinação do bagaço"
                   }, {
                     "@language" : "en",
                     "@value" : "Bagasse destination"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#DevelopmentOfNewTechniquesForReduingWaterConsumption",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Desenvolvimento de novas técnicas para redução do consumo de água"
                   }, {
                     "@language" : "en",
                     "@value" : "Development of new techniques for reduing water consumption"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#ElectricityProduction",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Produção de energia elétrica"
                   }, {
                     "@language" : "en",
                     "@value" : "Electricity production"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#FermentationEfficiency",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Eficiência da fermentação"
                   }, {
                     "@language" : "en",
                     "@value" : "Fermentation efficiency"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#FiltercakeAndAshesDestination",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Destinação de torta de filtro e cinza"
                   }, {
                     "@language" : "en",
                     "@value" : "Filtercake and ashes destination"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#RawMaterialDiversificationWithImprovedInputs",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Diversificação da matéria prima com insumos melhorados"
                   }, {
                     "@language" : "en",
                     "@value" : "Raw material diversification"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#ReductionInWaterConsumption",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Consumo de água"
                   }, {
                     "@language" : "en",
                     "@value" : "Water consumption"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Eficiência tecnológica na industria"
                   }, {
                     "@language" : "en",
                     "@value" : "Technological efficiency in the industry"
                   } ]
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#UseOfMoreResistantStrainsToTheEthanolConcentration",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Utilização de cepas mais resistentes a concentração do etanol"
                   }, {
                     "@language" : "en",
                     "@value" : "Use of more resistant strains to the ethanol concentration"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#VinasseConcentration",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Concentração da vinhaça"
                   }, {
                     "@language" : "en",
                     "@value" : "Vinasse concentration"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#VinasseTransport",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Transporte da vinhaça"
                   }, {
                     "@language" : "en",
                     "@value" : "Vinasse transport"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 }, {
                   "@id" : "http://semantic.icmc.usp.br/sustenagro#WaterProduction",
                   "http://www.w3.org/2000/01/rdf-schema#label" : [ {
                     "@language" : "pt",
                     "@value" : "Produção de água"
                   }, {
                     "@language" : "en",
                     "@value" : "Water production"
                   } ],
                   "subClassOf" : "http://semantic.icmc.usp.br/sustenagro#TechnologicalEfficiencyInTheIndustrial"
                 } ],
                 "@context" : {
                   "label" : {
                     "@id" : "http://www.w3.org/2000/01/rdf-schema#label",
                     "@type" : "http://www.w3.org/1999/02/22-rdf-syntax-ns#langString"
                   },
                   "subClassOf" : {
                     "@id" : "http://www.w3.org/2000/01/rdf-schema#subClassOf",
                     "@type" : "@id"
                   }
                 }
               }
           return json;
}