package page.module

import geb.Module
import geb.navigator.Navigator


class AgendaModule extends Module{
    static content = {
        dateAndPlace {$("h5").text()}
        freePlaces {$(".Agenda-groupFreePlaces > div", 1).text()}
        groupRegistrationButton {$(".Agenda-groupBtnContainer")}
        exams {$("tr").moduleList(ExamModule)}
    }

    String getNameAndPlace(){
        return dateAndPlace
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
