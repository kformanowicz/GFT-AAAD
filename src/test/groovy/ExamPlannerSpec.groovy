import geb.spock.GebReportingSpec
import ExamPlannerHomePage

class ExamPlannerSpec extends GebReportingSpec {

    def "Correct headers are displayed"() {
        when:
        to ExamPlannerHomePage

        then:
        header == "GFT Poland"
    }
}