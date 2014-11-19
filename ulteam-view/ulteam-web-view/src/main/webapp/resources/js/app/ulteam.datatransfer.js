DataTransfer.prototype.DATA_BY_SEASON_URL = '/rest/ulteam/games/season/';

/**
 * Created by oleg.sukhov
 */

function DataTransfer() {

}

DataTransfer.prototype.getDataBySeason = function(seasonName, responseHandler) {
    $.ajax({
        url: this.prepareSeasonUrl(seasonName),
        type: "GET",
        dataType: "json",
        success: responseHandler
    });
};

DataTransfer.prototype.prepareSeasonUrl = function(seasonName) {
    return this.DATA_BY_SEASON_URL + seasonName;
};