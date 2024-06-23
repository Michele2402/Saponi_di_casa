function all() {
    const url = `../AllOrders?action=all`;
    window.location.href = url;
}

function byClient(client) {
    console.log(client);
    const url = `../AllOrders?action=cliente&username=${client}`;
    window.location.href = url;
}

function byInterval() {
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;

    if (!startDate || !endDate) {
        alert('Seleziona entrambe le date.');
        return;
    }

    console.log(startDate, endDate);
    const url = `../AllOrders?action=intervallo&startDate=${startDate}&endDate=${endDate}`;
    window.location.href = url;
}