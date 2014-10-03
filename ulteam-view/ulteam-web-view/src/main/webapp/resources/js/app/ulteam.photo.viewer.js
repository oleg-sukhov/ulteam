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
    return $('<a/>')
        .append($('<img>').prop('src', photo.url))
        .prop('href', photo.url)
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

