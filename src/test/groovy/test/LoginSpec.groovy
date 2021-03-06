package test

import geb.spock.GebReportingSpec
import page.LoginPage
import page.DashboardPage
import helper.CommonHelper


class LoginSpec extends GebReportingSpec {

    def "should forward to dashboard after clicking log in button"() {
        when:
        via LoginPage

        and:
        CommonHelper.logInAsUser1()

        then:
        waitFor {at DashboardPage}
    }
}
