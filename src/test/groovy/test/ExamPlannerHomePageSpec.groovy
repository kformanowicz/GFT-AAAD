package test

import geb.spock.GebReportingSpec
import page.ExamPlannerHomePage
import page.LoginPage

class ExamPlannerHomePageSpec extends GebReportingSpec {

    def setupSpec(){
        clearCookies()
    }

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
        clickLoginButton()

        then:
        waitFor {at LoginPage}
    }
}