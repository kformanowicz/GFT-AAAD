package page.sessionPage

import geb.Page
import geb.module.MultipleSelect
import geb.navigator.Navigator
import geb.module.RadioButtons
import geb.module.Select
import geb.module.TextInput
import geb.module.Textarea
import helper.CommonHelper

class SessionPage extends Page {

    static url = "Session/AddSession"

    static at = {
        title == "- ExamPlanner"
        checkDefaultTitlesInForm()
        checkDefaultFieldsVisibilityInForm()
    }

    static content = {
        sessionForm { $(".col-sm-15.col-sm-offset-8.clearfix") }
        sessionFormTitle { $(".Backoffice-header.text-center h3").text() }

        sessionDateTitle { $(".form-group label", 0).text() }
        sessionDateInput { $("#SessionDto_Date").module(TextInput) }

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

        examinerSelectTitle { $(".form-group label", 8).text() }
        examinerSelectIsDisplayed { $(".btn-group.bootstrap-select.form-control").isDisplayed() }
        examinerSelect { $("#SessionDto_ExaminerId") }
        examinerSelectExpandButton { $("button.dropdown-toggle.btn-default[data-id]") }

        cancelButton { $(".Backoffice-buttonsContainerBottom button", 0) }
        saveButton { $(".btn-move-right > button") }
    }

//    SETTERS
    void setDate(Date data) {
        def dateString = data.format("dd.MM.yyyy") + " 09:00"
        sessionDateInput.text = dateString
    }

    private void setTodayDate() {
        sessionDateInput.click().click()
    }

    void setPostalCode(String data) {
        postalCodeInput.text = data
    }

    void setCity(String data) {
        cityInput.text = data
    }

    void setAddress(String data) {
        addressInput.text = data
    }

    void setAdditionalInformation(String data) {
        additionalInformationInput.text = data
    }

    void setTypeOfSpace(String data) {
        typeOfSpaceRadioButtons.checked = data
    }

    void setAmountOfSpace(int data) {
        spaceForSession.value("")
        spaceForSession.value(data)
    }

    void setLevel(ArrayList<String> data) {
        selectValuesInMultiselect(data, levelSelectExpandButton, levelSelect)
    }

    void setProductByName(ArrayList<String> data) {
        selectValuesInMultiselect(data, productSelectExpandButton, productSelect)
    }

    void selectValuesInMultiselect (ArrayList<String> data, Navigator expandButton, Navigator select) {
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

    void setExaminerByName(String data) {
        examinerSelectExpandButton.click()
        waitFor { ${".btn-group.bootstrap-select.form-control.js-session-closed.open"} }
        examinerSelect.children().find { examinerOption ->
            if(examinerOption.text() == data) {
                examinerOption.click()
                return true
            }
        }
        examinerSelectExpandButton.click()
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
    void cancelForm() {
        assert cancelButton.isDisplayed()
        cancelButton.click()
    }

    void saveForm() {
        assert saveButton.isDisplayed()
        saveButton.click()
    }

    void handleForm(Date date, String postalCode, String city, String address, String additionalInformation,
                    String typeOfSpace, int amountOfSpace, ArrayList<String> level, ArrayList<String> product,
                    String examinerByName, boolean save = true) {
        setDate(date)
        setPostalCode(postalCode)
        setCity(city)
        setAddress(address)
        setAdditionalInformation(additionalInformation)
        setTypeOfSpace(typeOfSpace)
        setAmountOfSpace(amountOfSpace)
        setLevel(level)
        setProductByName(product)
        setExaminerByName(examinerByName)

        if (save) {
            saveForm()
        } else {
            cancelForm()
        }
    }

    void createSessionForOneProductDateCity(Date date, String city) {
        handleForm(date, "11-222", city, "ul. Degrengolady 4", "", null, 15,
                ["Zaawansowany"],
                ["ISTQB Advanced Level Test Analyst / Polski, Angielski"],
                "GFT Poland1 Test", true)
    }

//    ASSERTIONS
    void checkDefaultFieldsVisibilityInForm() {
        assert sessionDateInput.isDisplayed()
        assert postalCodeInput.isDisplayed()
        assert cityInput.isDisplayed()
        assert addressInput.isDisplayed()
        assert additionalInformationInput.isDisplayed()
        assert typeOfSpaceRadioButtons*.isDisplayed()
        assert spaceForSession.isDisplayed()
        assert levelDropDownIsDisplayed
        assert productDropDownIsDisplayed
        assert examinerSelectIsDisplayed
    }

    void checkDefaultTitlesInForm() {
        def data = CommonHelper.jsonToObject("src/test/resources/applicationData.json")

        assert sessionFormTitle == data.sessionForm.formTitle
        assert sessionDateTitle == data.sessionForm.date
        assert postalCodeTitle == data.sessionForm.postalCode
        assert cityTitle == data.sessionForm.city
        assert addressTitle == data.sessionForm.address
        assert additionalInformationTitle == data.sessionForm.additionalInformation
        assert typeOfSpaceTitle == data.sessionForm.typeOfSpace
        assert spaceForSessionTitle == data.sessionForm.amountOfSpace
        assert levelSelectTitle == data.sessionForm.level
        assert productSelectTitle == data.sessionForm.product
        assert examinerSelectTitle == data.sessionForm.examiner
        assert getCancelButtonText() == data.sessionForm.cancelButton
        assert getSaveButtonText() == data.sessionForm.saveButton
    }

}
