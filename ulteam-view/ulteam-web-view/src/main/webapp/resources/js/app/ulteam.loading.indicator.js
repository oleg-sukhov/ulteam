/**
 * Created by os.
 */
var opts = {
    lines: 17, // The number of lines to draw
    length: 4, // The length of each line
    width: 14, // The line thickness
    radius: 38, // The radius of the inner circle
    corners: 1, // Corner roundness (0..1)
    rotate: 55, // The rotation offset
    direction: 1, // 1: clockwise, -1: counterclockwise
    color: '#128', // #rgb or #rrggbb or array of colors
    speed: 1.3, // Rounds per second
    trail: 42, // Afterglow percentage
    shadow: false, // Whether to render a shadow
    hwaccel: false, // Whether to use hardware acceleration
    className: 'loading', // The CSS class to assign to the spinner
    zIndex: 2e9 // The z-index (defaults to 2000000000)
    //top: '50%', // Top position relative to parent
    //left: '50%' // Left position relative to parent};
}

function LoadingIndicator(modalWindow) {
    this.modalWindow = modalWindow;
    this.spinner = new Spinner(opts).spin(this.modalWindow[0]);
}

LoadingIndicator.prototype.show = function() {
    this.modalWindow.show();
};

LoadingIndicator.prototype.hide = function() {
    this.modalWindow.hide();
};