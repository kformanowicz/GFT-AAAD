package page.session


class SessionDetailsPage extends SessionPage {
    static at = {
        $(".Sidebar-left").displayed
        $(".btn-dark", text: "Edytuj").displayed
        $(".BackofficeDetails-half", 0).text() == "Data sesji"
    }

    static content = {
        getSessionDate { $("div.BackofficeDetails-content.clearfix > div", 0).text() }
        getSessionTime { $("div.BackofficeDetails-content.clearfix > div", 1).text() }
        getSpaces { $(".BackofficeDetails-column--hasBorderRight > div:nth-child(2) > .BackofficeDetails-content").text() }
        examiner {
            $(".BackofficeDetails-column--hasBorderRight > div:nth-child(3) > .BackofficeDetails-content").text()
        }
        postalCode { $(".row > div:nth-child(1) > .BackofficeDetails-content").text() }
        getCity { $(".col-sm-offset-2 > div.BackofficeDetails-content").text() }
        address { $(".col-sm-16.col-sm-offset-2 > div:nth-child(2) > div.BackofficeDetails-content").text() }
        additionalInfo { $(".col-sm-16.col-sm-offset-2 > div:nth-child(3) > div.BackofficeDetails-content").text() }
        deleteSessionButton { $(".js-session-delete") }
    }

    def deleteSession() {
        deleteSessionButton.click()
        driver.switchTo().alert().accept()
    }
}