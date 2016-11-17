package page.module

import geb.Module

class ExamItemModule extends Module {
    static content = {
        exam { $(".col-sm-20").text() }
        turnOffRegistrationLink { $(".js-productLink") }
    }

    void turnOffRegistration() {
        turnOffRegistrationLink.click()
    }

    String getExamName() {
        return exam
    }
}
