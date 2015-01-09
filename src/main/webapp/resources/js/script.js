function openPopUp() {
    $( "#popUp" ).removeClass( "__close" );
}

function closePopUp() {
    $( "#popUp" ).addClass( "__close" );
    $( "#popUp" ).remove();
}