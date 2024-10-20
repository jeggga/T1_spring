package org.example.model.constant;

import java.util.Objects;

public enum TypeOfProduct {
    ACCOUNT,
    CARD;

    public static TypeOfProduct fromString(String str) {
        if (Objects.nonNull(str)) {
            for (TypeOfProduct  typeOfProduct: TypeOfProduct.values()) {
                if (str.equalsIgnoreCase(typeOfProduct.name())) {
                    return typeOfProduct;
                }
            }
        }
        return null;
    }
}
