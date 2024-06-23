function AddToCart(id) {
    const url = `Cart?id=${id}&action=add&page=Product.jsp?success=true`;
    window.location.href = url;
}