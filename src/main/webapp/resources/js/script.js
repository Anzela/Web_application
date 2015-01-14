function openPopUp(popupId) {
    $( "#" + popupId ).removeClass( "__close" );
}

function closePopUp(popupId, errorTextId) {
    $( "#" + popupId ).addClass( "__close" );
    if (errorTextId != null) {
        $( "#" + errorTextId ).remove();
    }
}