<%@ page import="it.mfm.model.OrderBean" %>
<%@ page import="it.mfm.model.PurchasedProductBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/myOrders.css">
    <script src="script/myOrders.js"></script>
</head>
<body>
<%@ include file="./Header.jsp" %>

<%
    Map<OrderBean, List<PurchasedProductBean>> ordersWithProducts = (Map<OrderBean, List<PurchasedProductBean>>) request.getSession().getAttribute("ordersWithProducts");
%>

<div id="orders-container">
    <p>Clicca su un prodotto per recensirlo</p>
    <% if (ordersWithProducts != null && !ordersWithProducts.isEmpty()) { %>
    <% for (Map.Entry<OrderBean, List<PurchasedProductBean>> entry : ordersWithProducts.entrySet()) { %>
    <div class="order-container">
        <div class="order-details">
            <div>Effettuato il:<%= entry.getKey().getData_creazione() %></div>
            <div>Totale: €<%= entry.getKey().getTotale() %></div>
        </div>
        <div class="products-container">
            <% List<PurchasedProductBean> products = entry.getValue(); %>
            <% for (PurchasedProductBean product : products) { %>
            <div class="product" data-id="<%= product.getNome() %>">
                <div class="name"><%= product.getNome() %></div>
                <div>x<%= product.getQuantita() %></div>
                <div>€<%= product.getPrezzo() %></div>
            </div>
            <% } %>
        </div>
        <div class="add-review-container">
            <form action="./Review" method="post" id="myForm">
                <input type="hidden" name="prodotto_id" id="prodotto_id">
                <div class="placeholder">
                    <p class="top">Valutazione (1-5):</p>
                    <p class="bottom"><input type="number" name="valutazione" min="1" max="5" required placeholder="Inserisci valutazione"/></p>
                </div>
                <div class="placeholder">
                    <p class="top">Testo:</p>
                    <p class="bottom"><textarea name="testo" required placeholder="Inserisci testo"></textarea></p>
                </div>
                <div class="placeholder">
                    <input class="button"  type="submit" value="Add">
                </div>
            </form>
        </div>
    </div>
    <% } %>
    <% } else { %>
    <p>Nessun ordine effettuato.</p>
    <% } %>
</div>

<script>
    // Ottieni tutti i prodotti
    var products = document.querySelectorAll('.product');

    // Aggiungi un listener di click ad ogni prodotto
    products.forEach(function(product) {
        product.addEventListener('click', function() {
            // Rimuovi la classe 'selected' da tutti i prodotti
            products.forEach(function(prod) {
                prod.classList.remove('selected');
            });

            this.classList.add('selected');

            // Nascondi tutti i div add-review-container
            let allReviewContainers = document.querySelectorAll('.add-review-container');
            allReviewContainers.forEach(function(container) {
                container.style.display = 'none';
            });

            // Mostra il div add-review-container associato al prodotto cliccato
            let reviewContainer = this.closest('.order-container').querySelector('.add-review-container');
            reviewContainer.style.display = 'block';

            // Imposta il valore del campo nascosto 'prodotto_id'
            let hiddenInput = reviewContainer.querySelector('#prodotto_id');
            hiddenInput.value = this.getAttribute('data-id');

        });
    });
</script>

<%@ include file="./Footer.jsp" %>

</body>
</html>
