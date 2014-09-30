/**
 * Created by os
 */
var photos = {};

$(document).ready(function () {
    $('.photo-album').find(".avatar .thumbnail").on('click', function (event) {
        photoAlbumHandler(event.target.parentNode.parentNode);
    });
});

function isAlbumContainsPhotos(photoAlbumId) {
    return photos[photoAlbumId] != undefined;
}

function prepareAlbum() {
    var photoAlbums = $(".avatar img");
    var photoContainer = $('#links');
    $.each(photoAlbums, function (index, photo) {
        $('<a/>')
            .append($('<img>').prop('src', photo.src))
            .prop('href', photo.src)
            .prop('title', "Photo " + index)
            .attr('data-gallery', '')
            .appendTo(photoContainer);
    });
}

function photoAlbumHandler(photoAlbum) {
    var photoAlbumId = $(photoAlbum).attr("photoAlbumId");

    if (!isAlbumContainsPhotos(photoAlbumId)) {
        loadAlbumPhotos(photoAlbumId);
    }

    blueimp.Gallery($("#links a"), $('#blueimp-gallery').data());
}

function loadAlbumPhotos(photoAlbumId) {
    var url = "/getAllPhotosInAlbum?id=" + photoAlbumId;
    $.get( url, function( data ) {
        alert( "Load was performed." );
    }, "json");
}

