package ua.electron.service.lowerLevel.shoppingCartActions;

import java.util.Objects;

public class CookieInIntegers {

    private int cookieNameEqualProductId;
    private int cookieValueEqualProductQuantity;

    public CookieInIntegers(int cookieNameEqualProductId, int cookieValueEqualProductQuantity) {
        this.cookieNameEqualProductId = cookieNameEqualProductId;
        this.cookieValueEqualProductQuantity = cookieValueEqualProductQuantity;
    }

    public int getCookieNameEqualProductId() {
        return cookieNameEqualProductId;
    }

    public void setCookieNameEqualProductId(int cookieNameEqualProductId) {
        this.cookieNameEqualProductId = cookieNameEqualProductId;
    }

    public int getCookieValueEqualProductQuantity() {
        return cookieValueEqualProductQuantity;
    }

    public void setCookieValueEqualProductQuantity(int cookieValueEqualProductQuantity) {
        this.cookieValueEqualProductQuantity = cookieValueEqualProductQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookieInIntegers that = (CookieInIntegers) o;
        return cookieNameEqualProductId == that.cookieNameEqualProductId &&
                cookieValueEqualProductQuantity == that.cookieValueEqualProductQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cookieNameEqualProductId, cookieValueEqualProductQuantity);
    }

    @Override
    public String toString() {
        return "CookieInIntegers{" +
                "cookieNameEqualProductId=" + cookieNameEqualProductId +
                ", cookieValueEqualProductQuantity=" + cookieValueEqualProductQuantity +
                '}';
    }
}
