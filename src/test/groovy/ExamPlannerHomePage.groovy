import geb.Page

class ExamPlannerHomePage extends Page {

    static at = { title == "- ExamPlanner" }

    static content = {
        header { $(".Navigation-entry span").text()}
    }
}
