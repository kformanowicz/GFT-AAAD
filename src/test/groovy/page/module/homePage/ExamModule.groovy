package page.module.homePage

import geb.Module


class ExamModule extends Module{
    static content = {
        exName {$(".Agenda-textColumn--levelName").text()}
        registerButton {$(".btn")}
    }

    String getName(){
        return exName
    }

    void register() {
        registerButton.click()
    }
}
