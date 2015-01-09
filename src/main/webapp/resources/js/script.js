function openPopUp(popupId) {
    $( "#" + popupId ).removeClass( "__close" );
}

function closePopUp(popupId, themeErrorTextId) {
    $( "#" + popupId ).addClass( "__close" );
    if (themeErrorTextId != null) {
        $( "#" + themeErrorTextId ).remove();
    }
}