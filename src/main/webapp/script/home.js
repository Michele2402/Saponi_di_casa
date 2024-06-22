function goToCategory(id) {
    const url = `Category?id=${id}&action=find&page=Category.jsp`;
    window.location.href = url;
}

function goToDetails(id) {
    const url = `Details?id=${id}&page=Product.jsp`;
    window.location.href = url;
}

function goToAdminPanel() {
    window.location.href = "admin/AdminPanel.jsp";
}