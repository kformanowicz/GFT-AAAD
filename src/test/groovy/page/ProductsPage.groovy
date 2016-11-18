package page

import geb.Page
import geb.module.Select
import geb.module.TextInput
import geb.navigator.Navigator
import page.module.NavbarModule
import page.module.ProductModule


class ProductsPage extends Page {

    static url = 'Products/List'
    static at = {
        $("h3").text() == "Produkty"
    }

    static content = {
        searchInput { $("input.form-control.input-sm").module(TextInput) }
        addProductButton { $("a", href: "/gftpl/Products/AddProduct") }
        navbar { $(".Navigation-list").module(NavbarModule) }
        productList { $("#productList") }
        products { productList.find("tbody tr").moduleList(ProductModule) }

        nextPageButton { $(".paginate_button.next") }
        previousPageButton { $(".paginate_button.previous") }

        recordsPerPageSelect { $("label > select").module(Select) }

        tableHeaders { productList.find("thead th") }
        productHeader { tableHeaders(0) }
        productAvailabilityHeader { tableHeaders(1) }
        productLevelHeader { tableHeaders(2) }
        productStatusHeader { tableHeaders(6) }
    }

    void search(String query) {
        searchInput.text = query
    }

    void clickAddProduct() {
        addProductButton.click()
    }

    void goToNextPage(){
        nextPageButton.click()
    }

    void goToPreviousPage(){
        previousPageButton.click()
    }

    void setRecordsPerPage(String option){
        recordsPerPageSelect.selected = option
    }

    boolean isSortedAsc(Navigator header) {
        return header.classes().first() == 'sorting_asc'
    }

    boolean isSortedDesc(Navigator header) {
        return header.classes().first() == 'sorting_desc'
    }

    void sortByHeaderAsc(Navigator header){
        if (!isSortedAsc(header)) {
            header.click()
        }
    }

    void sortByHeaderDesc(Navigator header){
        if (isSortedAsc(header)) {
            header.click()
        } else if (!isSortedDesc(header)) {
            header.click()
            header.click() //ugly
        }
    }

    void sortByNameAsc() {
        sortByHeaderAsc(productHeader)
    }

    void sortByNameDesc() {
        sortByHeaderDesc(productHeader)
    }

    void sortByAvailabilityAsc() {
        sortByHeaderAsc(productAvailabilityHeader)
    }

    void sortByAvailabilityDesc() {
        sortByHeaderDesc(productAvailabilityHeader)
    }

    void sortByProductLevelAsc(){
        sortByHeaderAsc(productLevelHeader)
    }

    void sortByProductLevelDesc(){
        sortByHeaderDesc(productLevelHeader)
    }

    void sortByProductStatusAsc(){
        sortByHeaderAsc(productStatusHeader)
    }

    void sortByProductStatusDesc(){
        sortByHeaderDesc(productStatusHeader)
    }

    List getAllProductsNames() {
        return products*.productName
    }

    List getAllProductsAvailabilityDates(){
        return products*.productDate
    }

    List getAllProductsLevels() {
        return products*.productLevel
    }

    List getAllProductsLanguages() {
        return products*.productLanguage
    }

    List getAllProductsTypes() {
        return products*.productType
    }

    List getAllProductsAvailibeRegistrations() {
        return products*.productAvailableRegistrations
    }

    List getAllProductsStatuses(){
        return products*.productStatus
    }

}
