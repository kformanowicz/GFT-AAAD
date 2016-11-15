package helper

import geb.Browser
import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap
import page.LoginPage


class CommonHelper {

    static String jsonToString(String jsonLocation){
        Scanner input = new Scanner(new File(jsonLocation))
        StringBuilder jsonObject = new StringBuilder()
        while(input.hasNextLine()){
            jsonObject.append(input.nextLine())
        }
        return jsonObject.toString()
    }

    static def jsonToObject(String jsonLocation){
        String json = jsonToString(jsonLocation)
        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(json)
        return object
    }

    static void logInAsUser1() {
        logInAsUser("user1")
    }

    static void logInAsUser2() {
        logInAsUser("user2")
    }

    static void logInAsUser3() {
        logInAsUser("user3")
    }

    static void logInAsUser(user) {
        def jsonParser = CommonHelper.jsonToObject('src\\test\\resources\\values.json')
        LinkedHashMap map = (LinkedHashMap)jsonParser
        def users = map.get("users")
        def targetUser = users.get(user)

        LoginPage loginPage = new LoginPage()
        Browser browser = new Browser()
        browser.to(loginPage)
        loginPage.logIn(targetUser.login, targetUser.password)
    }
}
