function AddToCart(id) {
    alert("Aggiunto al carrello");
    const url = `Cart?id=${id}&action=add&page=Product.jsp`;
    window.location.href = url;
}