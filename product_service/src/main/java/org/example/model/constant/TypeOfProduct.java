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
            throw new IllegalArgumentException("Product with this type does not exist");
        }
        return null;
    }
}
