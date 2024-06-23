function goToDelete(id) {
    const url = `../Product?id=${id}&action=delete`;
    window.location.href = url;
}

function goToModify(id) {
    const url = `../Details?id=${id}&page=ModifyProduct.jsp`;
    window.location.href = url;
}