function AddToCart(id) {
    const url = `Cart?id=${id}&action=add&page=Cart.jsp`;
    window.location.href = url;
}

function RemoveFromCart(id) {
    const url = `Cart?id=${id}&action=remove&page=Cart.jsp`;
    window.location.href = url;
}