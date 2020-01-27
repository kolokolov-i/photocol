$(document).ready(function(){
    $(".js-album-add").on("click", function (){
        $("#newAlbumDialog").css("display", "block");
    });
    $("#newAlbumDialog .js-cancel").on("click", function(){
        $("#newAlbumDialog").css("display", "none");
    });

    $(".js-album-edit").on("click", function (){
        $("#editAlbumDialog").css("display", "block");
    });
    $("#editAlbumDialog .js-cancel").on("click", function(){
        $("#editAlbumDialog").css("display", "none");
    });

    $(".js-album-delete").on("click", function (){
        $("#deleteAlbumDialog").css("display", "block");
    });
    $("#deleteAlbumDialog .js-cancel").on("click", function(){
        $("#deleteAlbumDialog").css("display", "none");
    });

});
