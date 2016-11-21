package page.module.sessionExams

import geb.Module

class ExamItemModule extends Module {
    static content = {
        exam { $(".col-sm-20.col-sm-offset-1").text() }
        turnOffRegistrationLink { $(".js-productLink") }
    }

    void turnOffRegistration() {
        turnOffRegistrationLink.click()
    }

    String getExamName() {
        return exam
    }
}
