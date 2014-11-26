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

GamesHelper.prototype.DTO_METHOD_KEYS = {
    seasonDtoList: 'updateSeasonControl',
    tournamentDtoList: 'updateTournamentControl',
    tourDtoList: 'updateTourControl',
    teamDtoList: 'updateTeamControl'
};

GamesHelper.prototype.updateGamesBySeason = function(seasonName) {
    this.dataTransfer.getDataBySeason(seasonName, this, this.processData);
};

GamesHelper.prototype.getHandler = function(controlName) {
    switch(controlName) {
        case 'season': return this.updateGamesBySeason;
    }
    return null;
};
GamesHelper.prototype.processData = function(data) {
    $.each(data, $.proxy(function(prop, value) {
        var processMethodName = GamesHelper.prototype.DTO_METHOD_KEYS[prop];
        var processMethod = GamesHelper.prototype[processMethodName];
        processMethod.call(this, value);
    }, this));
};

GamesHelper.prototype.updateSeasonControl = function(data) {
    console.log(data);
};

GamesHelper.prototype.updateTournamentControl = function(data) {
    $('#tournament').empty();
    jQuery.each(data, function() {
        $('#tournament').append($('<option>', {text : this.name}));
    });
};

GamesHelper.prototype.updateTourControl = function(data) {
    var controlValues = jQuery.map(data, function(item) {
        return item.name;
    });
    $('#tour').next().find('ul').empty();
    $('#tour').next('.selectpicker').selectpicker('val', ['123','234']);
    $('#tour').next('.selectpicker').selectpicker('refresh');
};

GamesHelper.prototype.updateTeamControl = function(data) {
    console.log(data);
};