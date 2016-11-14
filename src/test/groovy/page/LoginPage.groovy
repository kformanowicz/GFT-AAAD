package page

import geb.Page

/**
 * Created by kffz on 14/11/2016.
 */
class LoginPage extends Page{

    static at = { title == "Zaloguj się - ExamPlanner" }

    static content = {
        header { $(".u-centered").text() }
        emailLabel { $(".control-label", 0, for: "Email").text() }
        emailInput { $("#Email") }
        passwordLabel { $(".control-label", 0, for: "Password").text() }
        passwordInput { $("#PasswordPass") }
        passwordMaskToggle { $("#PasswordSwitch") }
        rememberPasswordCheckbox { $("#RememberMe", 0, type: "checkbox") }
        rememberPasswordLabel { $("label", 0, for: "RememberMe").text() }
        remindPasswordButton { $("input", 0, value: "Przypomnij hasło") }
        logInButton { $("input", 0, value: "Zaloguj się") }
    }

    static def "log in with email and password" (email, password) {
        this.emailInput = email
        this.passwordInput = password
        this.logInButton.click()
// do odkomentowania po implementacji DashboardPage
//        logInButton.click(DashboardPage)
    }
}
