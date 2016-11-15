package page.module

import geb.Module


class ProductModule extends Module{
    static content = {
        cell { $("td", it) }
        productName {cell(0).text()}
        productDate {cell(1).text()}
        productLevel {cell(2).text()}
        productLanguages {cell(3).text().split(', ')}
        productType {cell(4).text().split(', ')}
        productAvailableRegistrations {cell(5)}
        productStatus {cell(6).text()}
        productActions {cell(7)}
    }
}
