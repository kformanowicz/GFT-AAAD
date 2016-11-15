package test

import geb.spock.GebReportingSpec
import page.ExamPlannerHomePage
import page.LoginPage
import page.DashboardPage

/**
 * Created by jbdi on 2016-11-15.
 */
class LoginSpec extends GebReportingSpec {
    def "clicking 'Zaloguj się' button with correct credentials filled in forward to dashboard" {
        when:
        to LoginPage
        logIn()

        and:



        then:
        at DashboardPage
        waitFor {at DashboardPage}
    }
}
