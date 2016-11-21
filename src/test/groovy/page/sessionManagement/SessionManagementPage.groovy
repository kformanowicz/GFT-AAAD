package page.sessionManagement

import geb.Page
import geb.module.RadioButtons
import geb.module.TextInput
import geb.module.Textarea
import geb.navigator.Navigator
import page.module.addSessionPage.SelectedProductModule
import page.session.SessionDetailsPage


class SessionManagementPage extends Page{
    static content = {
        sessionForm { $(".col-sm-15.col-sm-offset-8.clearfix") }
        sessionFormTitle { $(".Backoffice-header.text-center h3").text() }

        sessionDateTitle { $(".form-group label", 0).text() }
        sessionDateInput { $("#SessionDto_Date").module(TextInput) }
        sessionDateCalendarIcon { $("#datetimepicker > span.input-group-addon") }

        postalCodeTitle { $(".form-group label", 1).text() }
        postalCodeInput { $("#SessionDto_Location_PostalCode").module(TextInput) }

        cityTitle { $(".form-group label", 2).text() }
        cityInput { $("#SessionDto_Location_City").module(TextInput) }

        addressTitle { $(".form-group label", 3).text() }
        addressInput { $("#SessionDto_Location_Address").module(TextInput) }

        additionalInformationTitle { $(".form-group label", 4).text() }
        additionalInformationInput { $("#SessionDto_AdditionalInformation").module(Textarea) }

        typeOfSpaceTitle { $(".form-group label", 5).text() }
        typeOfSpaceRadioButtons { $("input", name: "SessionDto.PlaceManagement").module(RadioButtons) }
        typeOfSpaceProductRadioButton {$("input#spacePerProduct")}
        typeOfSpaceSessionRadioButton {$("input#spacePerSession")}

        spaceForSessionTitle { $('.form-group.spacePerSession label\\"').text() }
        spaceForSession { $("input", name: "SessionDto.SpaceForSession") }

        levelSelectTitle { $(".form-group label", 6).text() }
        levelDropDownIsDisplayed { $(".btn-group.bootstrap-select.show-tick.level").isDisplayed() }
        levelSelect { $(".level .dropdown-menu.inner a") }
        levelSelectExpandButton { $(".level > button.dropdown-toggle.btn-default") }

        productSelectTitle { $(".form-group label", 7).text() }
        productDropDownIsDisplayed { $(".btn-group.bootstrap-select.show-tick.product").isDisplayed() }
        productSelect { $(".product .dropdown-menu.inner a") }
        productSelectExpandButton { $(".product > button.dropdown-toggle.btn-default") }
        selectedProducts {$(".productContainer.clearfix").moduleList(SelectedProductModule)}

        examinerSelectTitle { $(".form-group label", 8).text() }
        examinerSelectIsDisplayed { $(".btn-group.bootstrap-select.form-control").isDisplayed() }
        examinerSelect { $(".btn-group.bootstrap-select.form-control.js-session-closed .dropdown-menu.inner a") }
        examinerSelectExpandButton { $("button.dropdown-toggle.btn-default[data-id]") }
        examinerSelectArea { $(".btn-group.bootstrap-select.form-control.js-session-closed.open") }

        cancelButton { $(".Backoffice-buttonsContainerBottom button", 0) }
        saveButton { $(".btn-move-right > button") }

        getDuplicateSessionMessageVisibility { $(".field-validation-error.text-danger").isDisplayed() }
    }

//    SETTERS
    def setDate(Date data) {
        def dateString = data.format("dd.MM.yyyy") + " 09:00"
        sessionDateInput.text = dateString
    }

    private def setTodayDate() {
        sessionDateInput.click().click()
    }

    def setPostalCode(String data) {
        postalCodeInput.text = data
    }

    def setCity(String data) {
        cityInput.text = data
    }

    def setAddress(String data) {
        addressInput.text = data
    }

    def setAdditionalInformation(String data) {
        additionalInformationInput.text = data
    }

    def setTypeOfSpace(String data = "Dla sesji") {
        if (data == "Dla sesji" || data == null) {
            typeOfSpaceSessionRadioButton.click()
        } else if (data == "Dla produktu") {
            typeOfSpaceProductRadioButton.click()
        }
    }

    def setAmountOfSpace(def data) {
        if (data != null) {
            spaceForSession.value("")
            spaceForSession.value(data)
        }
    }

    def getSpaceForSessionFieldVisibility(){
        return spaceForSession.isDisplayed()
    }

    def setLevel(ArrayList<String> data) {
        selectValuesInMultiselect(data, levelSelectExpandButton, levelSelect)
    }

    def setProductByName(ArrayList<String> data) {
        selectValuesInMultiselect(data, productSelectExpandButton, productSelect)
    }

    def selectValuesInMultiselect(ArrayList<String> data, Navigator expandButton, Navigator select) {
        //required, otherwise the select options cannot be found by text
        expandButton.click()
        //for each of the elements to be selected
        data.each { levelToSelect ->
            waitFor { select.children().first().displayed }
            //find select options (children of the select root) with matching text
            select.children().find { levelOnPage ->
                if (levelToSelect == levelOnPage.text()) {
                    //if found, click the parent (anchor) of the element containing text (span)
                    levelOnPage.parent().click()
                    return true
                }
            }
        }
        //cleanup - hide the select
        expandButton.click()
    }
    def selectValuesInSelect(String data, Navigator expandButton, Navigator select) {
        ArrayList parameterList = new ArrayList();
        parameterList.add(data);
        selectValuesInMultiselect(parameterList, expandButton, select);
    }

    def setExaminerByName(String data) {
        //waitFor { examinerSelectArea.displayed }
        selectValuesInSelect(data, examinerSelectExpandButton, examinerSelect);
    }

//    GETTERS
    String getDate() {
        return sessionDateInput.text
    }

    String getPostalCode() {
        return postalCodeInput.text
    }

    String getCity() {
        return cityInput.text
    }

    String getAddress() {
        return addressInput.text
    }

    String getAdditionalInformation() {
        return additionalInformationInput.text
    }

    String getCheckedTypeOfSpaceValue() {
        return typeOfSpaceRadioButtons.checked
    }

    String getCheckedTypeOfSpaceLabel() {
        return typeOfSpaceRadioButtons.checkedLabel
    }

    String getAmountOfSpace() {
        return spaceForSession.text()
    }

    ArrayList<String> getSelectedLevelsId() {
        return levelSelect.selected
    }

    ArrayList<String> getSelectedLevelsText() {
        return levelSelect.selectedText
    }

    ArrayList<String> getProductsId() {
        return productSelect.selected
    }

    ArrayList<String> getProductsText() {
        return productSelect.selectedText
    }

    String getExaminerId() {
        return examinerSelect.selected
    }

    String getExaminerText() {
        return examinerSelect.selectedText
    }

    String getSaveButtonText() {
        return saveButton.text()
    }

    String getCancelButtonText() {
        return cancelButton.find("a").text()
    }

//    ACTIONS
    def cancelForm() {
        assert cancelButton.isDisplayed()
        cancelButton.click()
    }

    def saveForm(boolean isTransitionToSessionDetailsExpected) {
        assert saveButton.isDisplayed()
        if (isTransitionToSessionDetailsExpected) {
            saveButton.click(SessionDetailsPage)
        } else {
            saveButton.click()
        }
    }

    def handleForm(Date date, String postalCode, String city, String address, String additionalInformation,
                   String typeOfSpace, def amountOfSpace=null, ArrayList<String> level, ArrayList<String> product,
                   String examinerByName, boolean save = true) {
        setDate(date)
        setPostalCode(postalCode)
        setCity(city)
        setAddress(address)
        setAdditionalInformation(additionalInformation)
        setTypeOfSpace(typeOfSpace)
        setAmountOfSpace(amountOfSpace)
        setLevel(level)
        sleep(1000)
        setProductByName(product)
        sleep(1000)
        setExaminerByName(examinerByName)

        if (save) {
            saveForm(true)
        } else {
            cancelForm()
        }
    }
}
