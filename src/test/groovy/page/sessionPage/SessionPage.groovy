package page.sessionPage

import geb.Page
import geb.module.MultipleSelect
import geb.module.RadioButtons
import geb.module.Select
import geb.module.TextInput
import geb.module.Textarea
import helper.CommonHelper

class SessionPage extends Page {

    static url = "Session/AddSession"

    static at = {
        title == "- ExamPlanner"
        sessionForm.displayed
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
        addressInput { $("SessionDto_Location_Address").module(TextInput) }

        additionalInformationTitle { $(".form-group label", 4).text() }
        additionalInformationInput { $("#SessionDto_AdditionalInformation").module(Textarea) }

        typeOfSpaceTitle { $(".form-group label", 5).text() }
        typeOfSpaceRadioButtons { $(name: "SessionDto.PlaceManagement").module(RadioButtons) }

        spaceForSessionTitle { $(".form-group.spacePerSession label" ).text() }
        spaceForSession { $(".form-group.spacePerSession input").module(TextInput) }

        levelSelectTitle { $(".form-group label", 6).text() }
        levelSelect { $(".btn-group.bootstrap-select.show-tick.level").module(MultipleSelect) }

        productSelectTitle { $(".form-group label", 7).text() }
        productSelect { $(".btn-group.bootstrap-select.show-tick.product").module(MultipleSelect) }

        examinerSelectTitle { $(".form-group label", 8).text() }
        examinerSelect { $("#SessionDto_ExaminerId").module(Select) }

        cancelButton { $(".Backoffice-buttonsContainerBottom button", 0) }
        saveButton { $(".Backoffice-buttonsContainerBottom button", 1) }
    }

//    SETTERS
    void setDate(String data) {
        if (data == "today") {
            setTodayDate()
        } else {
            sessionDateInput.text = data
        }
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
        spaceForSession.text = ""
        spaceForSession.text = data.toString()
    }

    void setLevel(ArrayList<String> data) {
        if (levelSelect.size() > 0) {
            levelSelect.selected = data
        }
    }

    void setProductByName(ArrayList<String> data) {
        if (productSelect.size() > 0) {
            productSelect.selected = data
        }
    }

    void setExaminerByName(String data) {
        if (examinerSelect.size() > 0) {
            examinerSelect.selected = data
        }
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
        return spaceForSession.text
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
        return saveButton.getAttribute("a")
    }

    String getCancelButtonText() {
        return cancelButton.getAttribute("a")
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

    void handleForm(String date, String postalCode, String city, String address, String additionalInformation,
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

//    ASSERTIONS
    void assertAllFieldsVisibleInForm() {
        assert sessionDateInput.isDisplayed()
        assert postalCodeInput.isDisplayed()
        assert cityInput.isDisplayed()
        assert addressInput.isDisplayed()
        assert additionalInformationInput.isDisplayed()
        assert typeOfSpaceRadioButtons.isDisplayed()
        assert spaceForSession.isDisplayed()
        assert levelSelect.isDisplayed()
        assert productSelect.isDisplayed()
        assert examinerSelect.isDisplayed()
    }

    void assertAllTitleAreCorrect() {
        def data = CommonHelper.jsonToObject("../../../../resources/applicationData.json")

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
