package page.module.individualRegistration

import geb.Module


class CompleteRegistrationModule extends Module {

    static content = {
        getThanksMessage { $(".space3").text() }
        getImageVisibility { $(".RegisterComplete-container > img").displayed }
        nextRegistrationButton { $(".btn-dark") }
    }

//    GETTERS
    String getNextRegistrationButtonText() {
        return nextRegistrationButton.text()
    }

//    ACTIONS
    def clickNextRegistrationButton() {
        nextRegistrationButton.click()
    }

}
