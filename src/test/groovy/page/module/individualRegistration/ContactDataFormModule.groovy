package page.module.individualRegistration

import geb.Module
import geb.module.TextInput


class ContactDataFormModule extends Module {

    static content = {
        getNameTitle { $(for: "PersonDataDto_Name").text() }
        nameInput { $("#PersonDataDto_Name").module(TextInput) }

        getSurnameTitle { $(for: "PersonDataDto_Surname").text() }
        surnameInput { $("#PersonDataDto_Surname").module(TextInput) }

        getEmailTitle { $(for: "PersonDataDto_Email").text() }
        emailInput { $("#PersonDataDto_Email").module(TextInput) }

        getPhoneTitle { $(for: "PersonDataDto_Phone").text() }
        phoneInput { $("#PersonDataDto_Phone").module(TextInput) }

        submitButton { $(".Register-forwardBtn button") }
        backButton { $(".Register-backBtn button") }
    }

//    SETTERS
    void setNameField(String name) {
        nameInput.text = name
    }

    void setSurnameField(String surname) {
        surnameInput.text = surname
    }

    void setEmailField(String email) {
        emailInput.text = email
    }

    void setPhoneField(String phone) {
        phoneInput.text = phone
    }

//    GETTERS
    String getSubmitButtonText() {
        return submitButton.text()
    }

    String getBackButtonText() {
        return backButton.text()
    }

//    ACTIONS
    def clickSubmitButton() {
        submitButton.click()
    }

    def clickBackButton() {
        backButton.click()
    }

    def fillContactData(String name, String surname, String email, String phone, boolean ifSubmit = true) {
        setNameField(name)
        setSurnameField(surname)
        setEmailField(email)
        setPhoneField(phone)
        if (ifSubmit) clickSubmitButton()
    }

}
