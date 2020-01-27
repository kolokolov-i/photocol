$(document).ready(function(){
    $(".js-album-add").on("click", newAlbum);
    $("#newAlbumDialog .js-cancel").on("click", function(){
        $("#newAlbumDialog").css("visibility", "hidden");
    });
});

function newAlbum(){
    $("#newAlbumDialog").css("visibility", "visible");
}