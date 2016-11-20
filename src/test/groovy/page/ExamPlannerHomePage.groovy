package page

import geb.Page
import geb.navigator.Navigator
import page.module.homePage.AgendaModule

class ExamPlannerHomePage extends Page {

    static at = { title == "- ExamPlanner" }

    static content = {
        header { $(".Navigation-entry span").text() }
        loginButton { $("#loginLink") }
        agendas { $(".Agenda-container").children().moduleList(AgendaModule) }
    }

    void clickLoginButton() {
        loginButton.click()
    }

    /**
     * @param dateAndPlace String with date and place, format: "18 listopada 2016, Warszawa"
     * @return
     */
    Navigator getAgendaByDateAndPlace(String dateAndPlace) {
        return agendas.find {it.getNameAndPlace().trim() == dateAndPlace}
    }

    void registerGroup(String dateAndPlace) {
        getAgendaByDateAndPlace(dateAndPlace).registerGroup()
    }

    void registerIndividual(String dateAndPlace, String examName) {
        getAgendaByDateAndPlace(dateAndPlace).registerToExam(examName)
    }
}
