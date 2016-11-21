package page.sessionManagement

import geb.Page
import geb.module.RadioButtons
import geb.module.TextInput
import geb.module.Textarea
import geb.navigator.Navigator
import page.module.addSessionPage.SelectedProductModule
import page.session.SessionDetailsPage

class AddSessionPage extends SessionManagementPage {

    static url = "Session/AddSession"

    static at = {
        title == "- ExamPlanner"
        $(".col-sm-15.col-sm-offset-8.clearfix").isDisplayed()
        $("h3").text() == 'Dodaj Sesję'
    }

    def createSessionForOneProductDateCity(Date date, String city) {
        sleep(1000)
        handleForm(date, "11-222", city, "ul. Degrengolady 4", "", null, 15,
                ["Zaawansowany"],
                ["ISTQB Advanced Level Test Analyst / Polski, Angielski"],
                "GFT Poland1 Test", true)
    }

}
