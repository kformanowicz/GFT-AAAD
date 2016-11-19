package page

import geb.Page

class ExamPlannerHomePage extends Page {

    static url = ""

    static at = { title == "- ExamPlanner" }

    static content = {
        header { $(".Navigation-entry span").text() }
        loginButton { $("#loginLink") }
    }

    void clickLoginButton() {
        loginButton.click()
    }
}
