package helper

import geb.Browser
import groovy.json.JsonSlurper
import org.apache.commons.lang3.RandomStringUtils
import page.LoginPage


class CommonHelper {

    static String jsonToString(String jsonLocation) {
        Scanner input = new Scanner(new File(jsonLocation), 'utf-8')
        StringBuilder jsonObject = new StringBuilder()
        while (input.hasNextLine()) {
            jsonObject.append(input.nextLine())
        }
        return jsonObject.toString()
    }

    static def jsonToObject(String jsonLocation) {
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

    static void logInAsUser(String user) {
        def jsonParser = jsonToObject('src\\test\\resources\\values.json')

        LoginPage loginPage = new LoginPage()
        Browser browser = new Browser()
        browser.to(loginPage)
        loginPage.logIn(jsonParser.users.get(user).login, jsonParser.users.get(user).password)
    }

    static String getRandomEmail() {
        Random random = new Random()
        String randomNumber = random.nextInt(100000000).toString()
        String email = RandomStringUtils.randomAlphanumeric(10)
        return email + randomNumber + "@mail.su"
    }

    static String getRandomCity() {
        Random random = new Random()
        String randomNumber = random.nextInt(100000000).toString()
        String city = RandomStringUtils.randomAlphanumeric(10)
        return city + randomNumber
    }
}
