package test

import geb.spock.GebReportingSpec
import page.ExamPlannerHomePage
import page.LoginPage

class ExamPlannerSpec extends GebReportingSpec {

    def "Correct header is displayed"() {
        when:
        to ExamPlannerHomePage

        then:
        header == "GFT Poland"
    }

    def "Clicking on login button redirects to login page"() {
        when:
        via ExamPlannerHomePage

        and:
        loginButton.click()

        then:
         at LoginPage
    }
}