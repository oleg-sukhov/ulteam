/**
 * @author oleg.sukhov
 */

$(document).ready(function() {
    var gamesHelper = new GamesHelper();

    jQuery.each($('.games-control-bar-item'), function() {
       jQuery(this).change(function() {
           var selectedText = $(this).find('option:selected').val();
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
    tourDtoList: 'tour'
};

GamesHelper.prototype.getHandler = function(controlName) {
    switch(controlName) {
        case 'season': return this.updateGamesBySeason;
        case 'tournament': return this.updateGamesByTournament;
        case 'tour': return this.updateGamesByTour;
    }
    return null;
};

GamesHelper.prototype.updateGamesBySeason = function(seasonName) {
    this.dataTransfer.getGamesDataBySeason(seasonName, this, this.processData);
};

GamesHelper.prototype.updateGamesByTournament = function(tournamentName) {
    var seasonName = $('#season').val();
    this.dataTransfer.getGamesDataByTournament(seasonName, tournamentName, this, this.processData);
};

GamesHelper.prototype.updateGamesByTour = function(tourName) {
    var seasonName = $('#season').val();
    var tournamentName = $('#tournament').val();
    this.dataTransfer.getGamesDataByTour(seasonName, tournamentName, tourName, this, this.processData);
};

GamesHelper.prototype.processData = function(data) {
    var updateControlMethod = GamesHelper.prototype['updateControl'];
    $.each(data, $.proxy(function(prop, value) {
        if(prop == 'gameDtoList') {
            this.updateTable(value);
        } else {
            updateControlMethod.call(this, value, GamesHelper.prototype.COTROLS_ID[prop]);
        }
    }, this));
};

GamesHelper.prototype.updateControl = function(data, controlId) {
    var control = $('#' + controlId);
    control.empty();

    if(controlId == 'tour') {
        control.append($('<option />').text('...').val('ALL-TOURS'));
    }

    jQuery.each(data, function() {
        control.append(
            $('<option />')
                .text(this.name)
                .val(this.name));
    });

    control.prop('disabled', control.find('option').length == 0);
    control.selectpicker('refresh');
};

GamesHelper.prototype.updateTable = function(data) {
    var gameTable = jQuery('.game-table tbody');
    gameTable.empty();

    $.each(data, function() {
        gameTable.append(
            $('<tr/>').addClass('game-row').append(
                $('<td/>').addClass('team-name').append(
                    $('<img/>').attr('src', this.ownerTeamLogoUrl),
                    $('<span/>').html(this.ownerTeam + ' (' + this.ownerTeamTown + ')')
                ),

                $('<td/>').addClass('team-name').append(
                    $('<img/>').attr('src', this.guestTeamLogoUrl),
                    $('<span/>').html(this.guestTeam + ' (' + this.guestTeamTown + ')')
                ),

                $('<td/>').addClass('goals success').html(this.ownerTeamGoals + ' : ' + this.guestTeamGoals),
                $('<td/>').addClass('date').html(this.date),
                $('<td/>').addClass('tournament').html(this.tournament),
                $('<td/>').addClass('tour').html(this.tour)
            )
        );
    });
};