
let jsonLdInterface = {};

jsonLdInterface.getJson = function (colorCode) {
    let json = {
                 "@context": {
                   "ical": "http://www.w3.org/2002/12/cal/ical#",
                   "xsd": "http://www.w3.org/2001/XMLSchema#",
                   "ical:dtstart": {
                     "@type": "xsd:dateTime"
                   }
                 },
                 "ical:summary": "Lady Gaga Concert",
                 "ical:location": "New Orleans Arena, New Orleans, Louisiana, USA",
                 "ical:dtstart": "2011-04-09T20:00:00Z"
               }
           return json;
}