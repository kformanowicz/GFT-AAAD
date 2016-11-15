package page

import geb.Page


class SessionPage extends Page {

    static url = "Session/AddSession"

    static at = { title == "- ExamPlanner" }

    static content = {
        sessionFormTitle { $(".Backoffice-header.text-center h3") }

        sessionDateTitle { $("").text() }
        sessionDateInput { $("#SessionDto_Date") }

        postalCodeTitle { $("").text() }
        postalCodeInput { $("#SessionDto_Location_PostalCode") }

        cityTitle { $("").text() }
        cityInput { $("#SessionDto_Location_City") }

        addressTitle { $("").text() }
        addressInput { $("SessionDto_Location_Address") }

        additionalInformationTitle { $("").text() }
        additionalInformationInput { $("#SessionDto_AdditionalInformation") }

        typeOfSpaceTitle { $("").text() }
        radioButtonProductTitle { $("").text() }
        radioButtonProduct { $("#spacePerProduct") }
        radioButtonSessionTitle { $("").text() }
        radioButtonSession { $("#spacePerSession") }

        spaceForSessionTitle { $(".form-group.spacePerSession label").text()}
        spaceForSession { $(".form-group.spacePerSession input") }

        levelSelect{$(".btn-group.bootstrap-select.show-tick.level")}
        productSelect{$(".btn-group.bootstrap-select.show-tick.product")}
        examinerSelect{$("#SessionDto_ExaminerId")}
    }

    void setDate(data) {
        sessionDateInput.value(data)
    }

    void setTodayDate() {
        sessionDateInput.click().click()
    }

    void setPostalCode(data) {
        postalCodeInput.value(data)
    }

    void setCity(String data) {
        cityInput.value(data)
    }

    void setAddress(String data) {
        addressInput.value(data)
    }

    void handleForm(){

    }

}
