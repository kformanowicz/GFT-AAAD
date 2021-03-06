package page.module.dashboardPage

import geb.Module


class EventModule extends Module {
    static content = {
        title { $(".popover-title").text() }
        registered { $(".popover-content > div").text().split(": ").last().toInteger() }
        freeSpaces { $(".popover-content > div").closest("div").text().split(": ").last().toInteger() }
        detailsButton { $(".btn-info") }
        deleteButton { $(".btn-danger") }
    }

    void clickDetailsButton() {
        detailsButton.click()
    }

    void clickDeleteButton() {
        deleteButton.click()
    }
}
