package page.sessionPage

import geb.Page


class SessionPage extends Page {

    static url = "Session/AddSession"

    static at = {
        title == "- ExamPlanner"
        sessionForm.displayed
    }

    static content = {

        sessionForm { $(".col-sm-15.col-sm-offset-8.clearfix") }
        sessionFormTitle { $(".Backoffice-header.text-center h3").text() }

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

        spaceForSessionTitle { $(".form-group.spacePerSession label").text() }
        spaceForSession { $(".form-group.spacePerSession input") }

        levelSelect { $(".btn-group.bootstrap-select.show-tick.level") }
        productSelect { $(".btn-group.bootstrap-select.show-tick.product") }
        examinerSelect { $("#SessionDto_ExaminerId") }

        cancelButton {$(".Backoffice-buttonsContainerBottom button", 0)}
        saveButton {$(".Backoffice-buttonsContainerBottom button", 1)}
    }

//    SETTERS
    void setDate(String data) {
        sessionDateInput.isDisplayed()
        if (data == "today") {
            setTodayDate()
        } else {
            sessionDateInput.value(data)
        }
    }

    private void setTodayDate() {
        sessionDateInput.click().click()
    }

    void setPostalCode(String data) {
        postalCodeInput.isDisplayed()
        postalCodeInput.value(data)
    }

    void setCity(String data) {
        cityInput.isDisplayed()
        cityInput.value(data)
    }

    void setAddress(String data) {
        addressInput.isDisplayed()
        addressInput.value(data)
    }

    void setAdditionalInformation(String data) {
        additionalInformationInput.isDisplayed()
        additionalInformationInput.value(data)
    }

    void setTypeOfSpace(String data) {
        assert radioButtonProduct.isDisplayed()
        assert radioButtonSession.isDisplayed()
        if (data == "product") {
            radioButtonProduct.click()
        } else {
            radioButtonSession.click()
        }
    }

    void setAmountOfSpace(int data) {
        assert spaceForSession.isDisplayed()
        spaceForSession.value("")
        spaceForSession.value(data.toString())
    }

    void setLevelByName(String data) {
        if (levelSelect.size() > 0) {
            levelSelect.value(data)
        }
    }

    void setProductByName(String data) {
        if (productSelect.size() > 0) {
            productSelect.value(data)
        }
    }

    void setExaminerByName(String data) {
        if (examinerSelect.size() > 0) {
            examinerSelect.value(data)
        }
    }

//    ACTIONS
    void cancelForm(){
        cancelButton.click()
    }

    void saveForm(){
        saveButton.click()
    }

    void handleForm(String date, String postalCode, String city, String address, String additionalInformation,
                    String typeOfSpace, int amountOfSpace, String levelByName, String productByName,
                    String examinerByName, boolean save=true) {
        setDate(date)
        setPostalCode(postalCode)
        setCity(city)
        setAddress(address)
        setAdditionalInformation(additionalInformation)
        setTypeOfSpace(typeOfSpace)
        setAmountOfSpace(amountOfSpace)
        setLevelByName(levelByName)
        setProductByName(productByName)
        setExaminerByName(examinerByName)

        if (save){
            saveForm()
        } else {
            cancelForm()
        }
    }

}
