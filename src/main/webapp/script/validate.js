/**
 *
 */
function checkNomeCognome(inputtxt) {
    var nome = /^[A-Za-z]+$/;
    if(inputtxt.value.match(nome))
        return true

    return false;
}

function checkCardNumber(inputtxt) {
    var cardNumberRegex = /^\d{16}$/;

    if (inputtxt.value.match(cardNumberRegex)) {
        return true;
    } else {
        return false;
    }
}

function checkEmail(inputtxt) {
    var email = /^\w+([.-]?\w+)@\w+([.-]?\w+)(.\w{2,3})+$/;
    if (inputtxt.value.match(email))
        return true;

    return false;
}


function checkCvv(inputtxt) {
    var cvvRegex = /^\d{3}$/;

    if (inputtxt.value.match(cvvRegex)) {
        return true;
    } else {
        return false;
    }
}


function checkData(inputtxt) {
    var data =  /^\d{1,2}-\d{1,2}-\d{4}$/;
    if(inputtxt.value.match(data))
        return true;

    return false;
}


function checkUserName(inputtxt) {
    var userName = /^[A-Za-z0-9]+$/;
    if(inputtxt.value.match(userName))
        return true;

    return false;
}


function checkPassword(inputtxt) {
    var password = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
    if(inputtxt.value.match(password))
        return true;

    return false;
}


function validate(obj) {
    var valid = true;

    var nome = document.getElementsByName("no")[0];
    if(!checkNomeCognome(nome)) {
        valid = false;
        document.getElementsByClassName("nome")[0].innerHTML = "nome non valido" ;
    } else {
        document.getElementsByClassName("nome")[0].innerHTML = "" ;
    }

    var cognome = document.getElementsByName("co")[0];
    if(!checkNomeCognome(cognome)) {
        valid = false;
        document.getElementsByClassName("cognome")[0].innerHTML = "cognome non valido";
    } else {
        document.getElementsByClassName("cognome")[0].innerHTML = "";
    }

    var numeroCarta = document.getElementsByName("nu")[0];
    if(!checkCardNumber(numeroCarta)) {
        valid = false;
        document.getElementsByClassName("numero")[0].innerHTML = "numero carta non valida";
    }
    else {
        document.getElementsByClassName("numero")[0].innerHTML = "";
    }

    var cvv = document.getElementsByName("cv")[0];
    if(!checkCvv(cvv)) {
        valid = false;
        document.getElementsByClassName("cvv")[0].innerHTML = "cvv non valido";
    }
    else {
        document.getElementsByClassName("cvv")[0].innerHTML = "";
    }

    if(valid)
        obj.submit();
}

function validateRegistration(obj) {
    var regValid = true;

    var nome = document.getElementsByName("nome")[0];
    if (!checkNomeCognome(nome)) {
        regValid = false;
        document.getElementById("RegNome").innerHTML = "nome non valido";
    } else {
        document.getElementById("RegNome").innerHTML = "";
    }

    var cognome = document.getElementsByName("cognome")[0];
    if (!checkNomeCognome(cognome)) {
        regValid = false;
        document.getElementById("RegCognome").innerHTML = "cognome non valido";
    } else {
        document.getElementById("RegCognome").innerHTML = "";
    }

    var email = document.getElementsByName("email")[0];
    if (!checkEmail(email)) {
        regValid = false;
        document.getElementById("RegEmail").innerHTML = "email non valida";
    } else {
        document.getElementById("RegEmail").innerHTML = "";
    }

    var password = document.getElementsByName("password")[0];
    if (!checkPassword(password)) {
        regValid = false;
        document.getElementById("RegPassword").innerHTML = "password non valida";
    } else {
        document.getElementById("RegPassword").innerHTML = "";
    }


    if (regValid)
        obj.submit();
}




