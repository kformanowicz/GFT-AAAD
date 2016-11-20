package page.registration

import geb.Page


class RegistrationPage extends Page {
    static content = {
        pageHeader { $(".Register-header.clearfix h3") }
        sessionDate { $(".Register-info", 0).text() }
        sessionTime { $(".Register-info", 1).text() }
        registerSteps {$(".Register-steps")}
    }

    String getHeader(){
        return pageHeader.text()
    }

    String getDate(){
        return sessionDate
    }

    String getTime(){
        return sessionTime
    }
}
