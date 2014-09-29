/**
 * Created by os
 */
$(document).ready(function() {
    $('#photo-album').find(".avatar .thumbnail").on('click', function (event) {
        prepareAlbum();
        blueimp.Gallery($("#links a"), $('#blueimp-gallery').data());
    });

    function prepareAlbum() {
        var photoAlbums = $(".avatar img");
        var photoContainer = $('#links');
        $.each(photoAlbums, function(index, photo) {
            $('<a/>')
                .append($('<img>').prop('src', photo.src))
                .prop('href', photo.src)
                .prop('title', "Photo " + index)
                .attr('data-gallery', '')
                .appendTo(photoContainer);
        });

    };
});
