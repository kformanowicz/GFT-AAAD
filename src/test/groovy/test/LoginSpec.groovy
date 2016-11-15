package test

import geb.spock.GebReportingSpec
import page.ExamPlannerHomePage
import page.LoginPage
import page.DashboardPage
import groovy.json.JsonSlurper
import spock.lang.Shared
import helper.CommonHelper

/**
 * Created by jbdi on 2016-11-15.
 */
class LoginSpec extends GebReportingSpec {
    @Shared jsonParser = CommonHelper.jsonToObject('src\\test\\resources\\values.json')

    def "clicking 'Zaloguj się' button with correct credentials filled in forward to dashboard"() {
        when:
        via LoginPage

        and:
       // logIn(jsonParser.users.user1.login, jsonParser.users.user1.password)
        CommonHelper.logInAsUser1()

        then:
        waitFor {at DashboardPage}
    }
}
