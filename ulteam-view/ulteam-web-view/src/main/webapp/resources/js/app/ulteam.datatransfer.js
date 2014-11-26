DataTransfer.prototype.DATA_BY_SEASON_URL = '/rest/ulteam/games/';

/**
 * Created by oleg.sukhov
 */

function DataTransfer() {

}

DataTransfer.prototype.getDataBySeason = function(seasonName, context, responseHandler) {
    $.ajax({
        context: context,
        url: this.prepareSeasonUrl(seasonName),
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        success: responseHandler
    });
};

DataTransfer.prototype.prepareSeasonUrl = function(seasonName) {
    return this.DATA_BY_SEASON_URL + seasonName;
};