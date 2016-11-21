package page.session

import geb.navigator.Navigator
import page.module.sessionExams.ExamItemModule


class SessionExamsPage extends SessionPage {

    static at = {
        $(".Sidebar-left").displayed
        $(".btn-dark", text: "Edytuj").displayed
    }

    static content = {
        editSessionButton { $(".btn-dark", text: "Edytuj") }

        basicLevelExamsLabel { $(".Exam-levelName", text: "Podstawowy") }
        basicLevelSpaces {basicLevelExamsLabel.next().find("span").text()}
        basicLevelExams { basicLevelExamsLabel.parent().parent().next().moduleList(ExamItemModule) }

        expertLevelExamsLabel { $(".Exam-levelName", text: "Ekspercki") }
        expertLevelSpaces {expertLevelExamsLabel.next().find("span").text()}
        expertLevelExams { expertLevelExamsLabel.parent().parent().next().moduleList(ExamItemModule) }

        advancedLevelExamsLabel { $(".Exam-levelName", text: "Zaawansowany") }
        advancedLevelSpaces {advancedLevelExamsLabel.next().find("span").text()}
        advancedLevelExams { advancedLevelExamsLabel.parent().parent().next().moduleList(ExamItemModule) }

        otherLevelExamsLabel { $(".Exam-levelName", text: "Inny") }
        otherLevelSpaces {otherLevelExamsLabel.next().find("span").text()}
        otherLevelExams { otherLevelExamsLabel.parent().parent().next().moduleList(ExamItemModule) }
    }

    String[] getAllExamsForBasicLevel() {
        return basicLevelExams*.getExamName()
    }

    String[] getAllExamsForAdvancedLevel() {
        return advancedLevelExams*.getExamName()
    }

    String[] getAllExamsForExpertLevel() {
        return expertLevelExams*.getExamName()
    }

    String[] getAllExamsForOtherLevel() {
        return otherLevelExams*.getExamName()
    }

    void turnOffRegistrationForExam(String examName){
        if (basicLevelExams.displayed){
            Navigator ex = basicLevelExams.find {it = examName}
            ex.turnOffRegistration()
        }
        else if (advancedLevelExams.displayed){
            Navigator ex = advancedLevelExams.find {it = examName}
            ex.turnOffRegistration()
        }
        else if (expertLevelExams.displayed){
            Navigator ex = expertLevelExams.find {it = examName}
            ex.turnOffRegistration()
        }
        else if (otherLevelExams.displayed){
            Navigator ex = otherLevelExams.find {it = examName}
            ex.turnOffRegistration()
        }
        
    }
}
