package it.mfm.util;

public class Utility {
    public static String maskCardNumber(String cardNumber) {
        if (cardNumber != null && cardNumber.length() >= 4) {
            return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        } else {
            return cardNumber;
        }
    }
}
