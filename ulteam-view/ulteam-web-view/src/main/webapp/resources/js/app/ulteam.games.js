/**
 * @author oleg.sukhov
 */

$(document).ready(function() {
    var gamesHelper = new GamesHelper();

    jQuery.each($('.games-control-bar-item'), function() {
       jQuery(this).change(function() {
           var selectedText = $(this).find('option:selected').text();
           var processMethod = gamesHelper.getHandler($(this).attr('id'));
           processMethod.call(gamesHelper, selectedText);
       });
    });
});

function GamesHelper() {
    this.dataTransfer = new DataTransfer();
}

GamesHelper.prototype.updateGamesBySeason = function(seasonName) {
    this.dataTransfer.getDataBySeason(seasonName, this.processData);
};

GamesHelper.prototype.getHandler = function(controlName) {
    switch(controlName) {
        case 'season': return this.updateGamesBySeason;
    }
    return null;
};
GamesHelper.prototype.processData = function(data) {
    console.log(data);
};

