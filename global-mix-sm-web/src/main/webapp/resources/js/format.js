/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.formatMe = function (value) {
    var numDays = Math.floor(value / 86400);
    var numHours = Math.floor((value % 86400) / 3600);
    var numMinutes = Math.floor(((value % 86400) % 3600) / 60);
    var numSeconds = ((value % 86400) % 3600) % 60;
    return numDays + " Dias, " + pad(numHours, 2) + " Horas, "
        + pad(numMinutes, 2) + " Minutos y " + pad(numSeconds, 2) + " Segundos";
}

function pad(num, size) {
    var s = num+"";
    while (s.length < size) s = "0" + s;
    return s;
}



window.formatMeDias = function (value) {
    var numDays = Math.floor(value / 86400);
    return numDays;
}

window.formatMeHoras = function (value) {
    var numHours = Math.floor((value % 86400) / 3600);
    return pad(numHours, 2);
}

window.formatMeMinutos = function (value) {
    var numMinutes = Math.floor(((value % 86400) % 3600) / 60);
    return pad(numMinutes, 2);
}

window.formatMeSegundos = function (value) {
    var numSeconds = ((value % 86400) % 3600) % 60;
    return pad(numSeconds, 2);
}