DataTransfer.prototype.GAMES_DATA_URL = '/rest/ulteam/games/';

/**
 * Created by oleg.sukhov
 */

function DataTransfer() {

}

DataTransfer.prototype.getData = function(url, context, responseHandler) {
    $.ajax({
        context: context,
        url: url,
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        success: responseHandler
    });
};

DataTransfer.prototype.getGamesDataBySeason = function(seasonName, context, responseHandler) {
    var url = this.GAMES_DATA_URL + seasonName;
    this.getData(url, context, responseHandler);
};

DataTransfer.prototype.getGamesDataByTournament = function(seasonName, tournament, context, responseHandler) {
    var url = this.GAMES_DATA_URL + seasonName + '/' + tournament;
    this.getData(url, context, responseHandler);
};

DataTransfer.prototype.getGamesDataByTour = function(seasonName, tournament, tour, context, responseHandler) {
    var url = this.GAMES_DATA_URL + seasonName + '/' + tournament + '/' + tour;
    this.getData(url, context, responseHandler);
};