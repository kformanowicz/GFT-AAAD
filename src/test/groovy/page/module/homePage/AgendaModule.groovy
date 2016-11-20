package page.module.homePage

import geb.Module
import geb.navigator.Navigator

class AgendaModule extends Module{
    static content = {
        dateAndPlace {$("h5").text()}
        sessionHour {$(".Agenda-hourSpan").text()}

        freePlaces {$(".Agenda-groupFreePlaces > div", 1).text()}
        groupRegistrationButton {$(".Agenda-groupBtnContainer")}
        exams {$("tr").not(".u-hideOnDesktop--tableRow").moduleList(ExamModule)}
    }

    String getNameAndPlace(){
        return dateAndPlace
    }

    String getHour() {
        return sessionHour
    }

    Navigator getExamByName(name) {
        return exams.find {it.getName() == name}
    }

    void registerToExam(name) {
        getExamByName(name).register()
    }

    void registerGroup() {
        groupRegistrationButton.click()
    }
}
