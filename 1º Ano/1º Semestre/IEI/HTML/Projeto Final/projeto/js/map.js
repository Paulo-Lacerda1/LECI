var map = new L.map("myMap", {center: [40.63299946977405, -8.659079968929293], zoom: 20});
var osmUrl = "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png";
var osmAttrib = "Map data OpenStreetMap contributors";
var osm = new L.TileLayer(osmUrl, {attribution: osmAttrib});
map.addLayer(osm);

map.on("click", showCoordinates);

function showCoordinates(e){
    var s = document.getElementById("coordinates");
    s.innerHTML = "Latitude, Longitude = "+e.latlng.lat+", "+e.latlng.lng;
}

L.marker([40.632777120792966, -8.659199578687549]).bindPopup("LABI@DETI").addTo(map);

let deti = L.polygon(
    [   [40.632806095219365, -8.659114837646486], [40.63281016626841, -8.65928113460541],
        [40.63307478392277, -8.659291863441469], [40.63308292598783, -8.659313321113588],
        [40.63318470171706, -8.659313321113588], [40.63318063069089, -8.65928113460541],
        [40.63322948298879, -8.65928113460541], [40.633225411965334, -8.659120202064516] ],
    {color: "green"} );

deti.bindPopup("DETI").addTo(map);