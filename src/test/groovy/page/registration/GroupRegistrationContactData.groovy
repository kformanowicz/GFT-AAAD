package page.registration

import geb.module.TextInput


class GroupRegistrationContactData extends RegistrationPage{
    static at = {
        $("button.prev-btn").displayed
    }

    static content = {
        nameInput {$("#PersonDataDto_Name").module(TextInput)}
        lastNameInput {$("#PersonDataDto_Surname").module(TextInput)}
        emailInput {$("#PersonDataDto\\.Surname").module(TextInput)} // lol
        phoneInput {$("#PersonDataDto_Phone").module(TextInput)}

        backToUsersButton {$("button.prev-btn")}
        acceptButton {$("button", type: "submit")}
    }

    void setName(name) {
        nameInput.text = name
    }

    void setLastName(lastName) {
        lastNameInput.text = lastName
    }

    void setEmail(email) {
        emailInput.text = email
    }

    void setPhone(phone) {
        phoneInput.text = phone
    }

    void goBackToUsers(){
        backToUsersButton.click()
    }

    void accept() {
        acceptButton.click()
    }

    void fillAndSubmitForm(String name, String lastName, String email, String phone){
        setName(name)
        setLastName(lastName)
        setEmail(email)
        setPhone(phone)

        accept()
    }
}
