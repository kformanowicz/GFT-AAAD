package helper

import groovy.json.JsonSlurper
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

    static void logInAsUser(user) {
        def jsonParser = CommonHelper.jsonToObject('src\\test\\resources\\values.json')
        LoginPage loginPage = new LoginPage();
        loginPage.logIn(jsonParser.users.user.login, jsonParser.users.user.password)
    }
}
