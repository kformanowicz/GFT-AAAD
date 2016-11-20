package page.module.addSessionPage

import geb.Module


class SelectedProductModule extends Module {
    static content = {
        products { $("> div") }
        productTitle { products.find("> div > div:nth-of-type(1)") }
        productAmount { products.find(".Session-registratedPlacesContainer > span:nth-of-type(1)") }
        productDeleteIcon { products.find("> div > div:nth-of-type(3)") }
        productWarningText { products.find("> div > span") }
    }

//    GETTERS
    int getAmountOfSelectedProducts() {
        return products.size()
    }

    List<String> getSelectedProductsTitle() {
        return productTitle*.text()
    }


    List<String> getSelectedProductsAmount() {
        return productAmount*.text()
    }


    List<Boolean> getSelectedProductsDeleteIconVisibility() {
        return productDeleteIcon*.isDisplayed()
    }

    String getproductWarningMessage() {
        return productWarningText.text()
    }

//    ACTIONS
    void deleteSelectedProduct(int idx) {
        productDeleteIcon(idx).click()
    }

}
