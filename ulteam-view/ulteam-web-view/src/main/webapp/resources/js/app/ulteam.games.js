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

GamesHelper.prototype.COTROLS_ID = {
    seasonDtoList: 'season',
    tournamentDtoList: 'tournament',
    tourDtoList: 'tour',
    teamDtoList: 'team'
};

GamesHelper.prototype.getHandler = function(controlName) {
    switch(controlName) {
        case 'season': return this.updateGamesBySeason;
        case 'tournament': return this.updateGamesByTournament;
    }
    return null;
};

GamesHelper.prototype.updateGamesBySeason = function(seasonName) {
    this.dataTransfer.getDataBySeason(seasonName, this, this.processData);
};

GamesHelper.prototype.updateGamesByTournament = function(tournamentName) {
    var seasonName = $('#season').val();
    this.dataTransfer.getDataByTournament(seasonName, tournamentName, this, this.processData);
};


GamesHelper.prototype.processData = function(data) {
    var processMethod = GamesHelper.prototype['updateControl'];
    $.each(data, $.proxy(function(prop, value) {
        processMethod.call(this, value, GamesHelper.prototype.COTROLS_ID[prop]);
    }, this));
};

GamesHelper.prototype.updateControl = function(data, controlId) {
    var control = $('#' + controlId);
    control.empty();
    jQuery.each(data, function() {
        control.append(
            $('<option />')
                .text(this.name)
                .val(this.name));
    });

    control.prop('disabled', control.find('option').length == 0);
    control.selectpicker('refresh');
};