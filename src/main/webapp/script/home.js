function goToCategory(id) {
    const url = `<%= request.getContextPath() %>/Category?id=${id}&action=find&page=Category.jsp`;
    window.location.href = url;
}

function goToDetails() {

}