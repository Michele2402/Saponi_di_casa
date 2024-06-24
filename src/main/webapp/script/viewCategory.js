function goToDelete(id) {
    const url = `../Product?id=${id}&action=delete&page=admin/VIewCatalogue.jsp`;
    window.location.href = url;
}

function goToModify(id) {
    const url = `../Details?id=${id}&page=admin/ModifyProduct.jsp`;
    window.location.href = url;
}