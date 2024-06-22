function goToCategory(id) {
    const url = `Category?id=${id}&action=find&page=Category.jsp`;
    window.location.href = url;
}

function goToDetails(id) {
    console.log(id);
    const url = `Details?id=${id}&page=Product.jsp`;
    window.location.href = url;
}