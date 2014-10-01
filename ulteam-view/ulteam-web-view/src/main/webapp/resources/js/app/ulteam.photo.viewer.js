/**
 * Created by os
 */
var opts = {
    lines: 17, // The number of lines to draw
    length: 4, // The length of each line
    width: 14, // The line thickness
    radius: 38, // The radius of the inner circle
    corners: 1, // Corner roundness (0..1)
    rotate: 55, // The rotation offset
    direction: 1, // 1: clockwise, -1: counterclockwise
    color: '#000', // #rgb or #rrggbb or array of colors
    speed: 1.3, // Rounds per second
    trail: 42, // Afterglow percentage
    shadow: false, // Whether to render a shadow
    hwaccel: false, // Whether to use hardware acceleration
    className: 'spinner', // The CSS class to assign to the spinner
    zIndex: 2e9, // The z-index (defaults to 2000000000)
    top: '50%', // Top position relative to parent
    left: '50%' // Left position relative to parent
};

var photos = {};

$(document).ready(function () {
    $('.photo-album').find(".avatar .thumbnail").on('click', function (event) {
        var spinner = new Spinner(opts).spin($("#loading"));
        photoAlbumHandler(event.target.parentNode.parentNode);
        spinner.stop();
    });
});

function isAlbumContainsPhotos(photoAlbumId) {
    return photos[photoAlbumId] != undefined;
}

function prepareAlbum(photoAlbumId, allPhotosInAlbum) {
    var processedPhoto = $.map(allPhotosInAlbum, function (photo, index) {
        return convertPhotoToGalleryFormat(index, photo);
    });

    photos[photoAlbumId] = processedPhoto;
}

function photoAlbumHandler(photoAlbum) {
    var photoAlbumId = $(photoAlbum).attr("photoAlbumId");

    if (!isAlbumContainsPhotos(photoAlbumId)) {
        loadAlbumPhotos(photoAlbumId);
    }
    $('#blueimp-gallery').data('useBootstrapModal', false);
    blueimp.Gallery(photos[photoAlbumId], $('#blueimp-gallery').data());
}

function convertPhotoToGalleryFormat(index, photo) {
    var photoSrc = "data:image/jpeg;base64," + photo.base64Data;
    return $('<a/>')
        .append($('<img>').prop('src', photoSrc))
        .prop('href', photoSrc)
        .prop('title', photo.name)
        .attr('data-gallery', '').get(0);
}

function loadAlbumPhotos(photoAlbumId) {
    var url = "/getAllPhotosInAlbum/" + photoAlbumId;
    $.ajax({
        url: url,
        async: false,
        dataType: 'json',
        success: function(data) {
            return prepareAlbum(photoAlbumId, data);
        }
    });
}

