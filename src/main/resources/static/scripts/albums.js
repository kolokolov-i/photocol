$(document).ready(function(){
    $(".js-album-add").on("click", function (){
        $("#newAlbumDialog").css("visibility", "visible");
    });
    $("#newAlbumDialog .js-cancel").on("click", function(){
        $("#newAlbumDialog").css("visibility", "hidden");
    });

    $(".js-album-edit").on("click", function (){
        $("#editAlbumDialog").css("visibility", "visible");
    });
    $("#editAlbumDialog .js-cancel").on("click", function(){
        $("#editAlbumDialog").css("visibility", "hidden");
    });

});
