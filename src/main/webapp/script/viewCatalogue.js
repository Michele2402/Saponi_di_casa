function goToCategory(id) {
    const url = `../Category?id=${id}&action=find&page=admin/ViewCategory.jsp`;
    window.location.href = url;
}