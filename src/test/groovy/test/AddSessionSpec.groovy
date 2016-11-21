package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import page.AddSessionPage
import page.session.SessionDetailsPage
import page.session.SessionExamsPage
import spock.lang.Ignore

class AddSessionSpec extends GebReportingSpec {

    def setup() {
        CommonHelper.logInAsUser1()
    }

    def cleanup() {}

    def "Should be visible default fields in form"() {
        when: "i open add new Session page"
        to AddSessionPage

        then: "i see all default fields in form"
        sessionDateInput.isDisplayed()
        postalCodeInput.isDisplayed()
        cityInput.isDisplayed()
        addressInput.isDisplayed()
        additionalInformationInput.isDisplayed()
        typeOfSpaceRadioButtons*.isDisplayed()
        spaceForSession.isDisplayed()
        levelDropDownIsDisplayed
        productDropDownIsDisplayed
        examinerSelectIsDisplayed
    }

    def "Should be visible default titles in form"() {
        given:
        def data = CommonHelper.jsonToObject("src/test/resources/applicationData.json")

        when: "i open add new Session page"
        to AddSessionPage

        then: "i see valid titles"
        sessionFormTitle == data.sessionForm.formTitle
        sessionDateTitle == data.sessionForm.date
        postalCodeTitle == data.sessionForm.postalCode
        cityTitle == data.sessionForm.city
        addressTitle == data.sessionForm.address
        additionalInformationTitle == data.sessionForm.additionalInformation
        typeOfSpaceTitle == data.sessionForm.typeOfSpace
        spaceForSessionTitle == data.sessionForm.amountOfSpace
        levelSelectTitle == data.sessionForm.level
        productSelectTitle == data.sessionForm.product
        examinerSelectTitle == data.sessionForm.examiner
        getCancelButtonText() == data.sessionForm.cancelButton
        getSaveButtonText() == data.sessionForm.saveButton
    }

    @Ignore
    def "Should handle session form and check selected data"() {
        given: "i am on the add new session page"
        GregorianCalendar calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)  //results in setting date to 2016-11-30
        Date date = calendar.getTime()
        to AddSessionPage

        when: "i handle new session form"
        handleForm(date, "11-111", "City", "Address", "additional Information", "Dla sesji", 2, ["Podstawowy"],
                ["ISTQB Foundation Level / Polski, Angielski"], "GFT Poland1 Test", false)

        then: "i should have valid chosen data"
        selectedProducts.getAmountOfSelectedProducts() == 1
        selectedProducts.getSelectedProductsTitle() == ["ISTQB Foundation Level / Polski, Angielski"]
        selectedProducts.getSelectedProductsAmount() == ["0"]
        selectedProducts.getSelectedProductsDeleteIconVisibility() == [true]
    }

    def "As an admin I want to create exam session for one type of exam in order to prepare exam session"() {
        setup:
        GregorianCalendar calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)  //results in setting date to 2016-11-30
        Date date = calendar.getTime()
        String city = CommonHelper.getRandomCity()
        to AddSessionPage

        when: "i handle new session form and submit it"
        createSessionForOneProductDateCity(date, city)

        then: "i see session details page and correct data"
        at(SessionDetailsPage)
        getCity == city

        cleanup: "i delete created session"
        if (page instanceof SessionDetailsPage) {
            page.deleteSession()
        }
    }

    def "Should not be visible duplicate session message when create one session 2 times"() {
        setup:
        GregorianCalendar calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)  //results in setting date to 2016-11-30
        Date date = calendar.getTime()
        String city = "Gdańsk"

        when: "i fill the form and submit it"
        to AddSessionPage
        createSessionForOneProductDateCity(date, city)

        then: "i don`t see duplicate session message"
        !getDuplicateSessionMessageVisibility
    }

    def "As an admin I want to create exam session for few types of exam at the same exam level in order to prepare exam session"() {
        setup:
        GregorianCalendar calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)  //results in setting date to 2016-11-30
        Date date = calendar.getTime()
        String city = CommonHelper.getRandomCity()
        to AddSessionPage

        when: "i handle new session form and submit it"
        handleForm(date, "11-222", city, "ul. Degrengolady 4", "", null, 15, ["Zaawansowany"],
                ["ISTQB Advanced Level Test Analyst / Polski, Angielski",
                 "ISTQB Advanced Level Technical Test Analyst / Polski, Angielski",
                 "ISTQB Advanced Level Test Manager / Polski, Angielski"],
                "GFT Poland1 Test", true)

        then: "i see session detail page and correct data"
        at(SessionDetailsPage)
        getCity == city

        cleanup: "i delete created session"
        if (page instanceof SessionDetailsPage) {
            page.deleteSession()
        }
    }

    def "As an admin I want to create exam session for few types of exam on different exam levels in order to prepare exam session"() {
        setup:
        GregorianCalendar calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)  //results in setting date to 2016-11-30
        Date date = calendar.getTime()
        String city = CommonHelper.getRandomCity()
        to AddSessionPage

        when: "i handle new session form and submit it"
        handleForm(date, "11-222", city, "ul. Degrengolady 4", "", null, 15,
                ["Podstawowy", "Zaawansowany", "Ekspercki"],
                ["ISTQB Foundation Level / Polski, Angielski",
                 "REQB Foundation Level / Polski, Angielski",
                 "ISTQB Advanced Level Test Analyst / Polski, Angielski",
                 "ISTQB Advanced Level Technical Test Analyst / Polski, Angielski",
                 "ISTQB Advanced Level Test Manager / Polski, Angielski",
                 "ISTQB Improving the Testing Process / Angielski",
                 "ISTQB Test Management / Angielski"],
                "GFT Poland1 Test", true)

        then: "i see correct session information on the details page"
        at(SessionDetailsPage)
        getCity == city

        and: "i see chose examines on the Exams page"
        goToExams()
        at(SessionExamsPage)
        sleep(1000)
//        getAllExamsForBasicLevel() == [["ISTQB Foundation Level / Polski, Angielski, REQB Foundation Level / Polski, Angielski"]]
//        getAllExamsForAdvancedLevel() == [["ISTQB Advanced Level Test Analyst / Polski, Angielski, ISTQB Advanced Level Technical Test Analyst / Polski, Angielski, ISTQB Advanced Level Test Manager / Polski, Angielski"]]
//        getAllExamsForExpertLevel() == [["ISTQB Improving the Testing Process / Angielski, ISTQB Test Management / Angielski"]]

        cleanup: "i delete created session"
      //  goToDetails()
        at(SessionDetailsPage)
        sleep(1000)
        if (page instanceof SessionDetailsPage) {
            page.deleteSession()
        }
    }

    def "As an admin I want to create exam session with maximum number of participants defined per exam type in order to prepare exam session"() {

    }

    def "As an admin I want to create exam session with maximum number of participants defined per exam session in order to prepare exam session"() {
        setup:
        final String MAX_ALLOWED_PARTICIPANTS_PER_SESSION = "999"
        GregorianCalendar calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)  //results in setting date to 2016-11-30
        Date date = calendar.getTime()
        String city = CommonHelper.getRandomCity()
        to AddSessionPage

        when: "i handle new session form with maximum number of allowed participants per session (999) and submit it"
        handleForm(date, "11-222", city, "ul. Degrengolady 4", "", null, 999,
                ["Podstawowy", "Zaawansowany", "Ekspercki"],
                ["ISTQB Foundation Level / Polski, Angielski",
                 "REQB Foundation Level / Polski, Angielski",
                 "ISTQB Advanced Level Test Analyst / Polski, Angielski",
                 "ISTQB Advanced Level Technical Test Analyst / Polski, Angielski",
                 "ISTQB Advanced Level Test Manager / Polski, Angielski",
                 "ISTQB Improving the Testing Process / Angielski",
                 "ISTQB Test Management / Angielski"],
                "GFT Poland1 Test", true)

        then: "i see maximum number of participants on the details page"
        at(SessionDetailsPage)
        getSpaces == MAX_ALLOWED_PARTICIPANTS_PER_SESSION

        cleanup: "i delete created session"
        goToDetails()
        at(SessionDetailsPage)
        sleep(1000)
        if (page instanceof SessionDetailsPage) {
            page.deleteSession()
        }
    }

    def "As an admin I want to activate created exam session so users can start register to it"() {
        setup:
        GregorianCalendar calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)  //results in setting date to 2016-11-30
        Date date = calendar.getTime()
        String city = CommonHelper.getRandomCity()
        to AddSessionPage

        when: "i handle new session form and submit it"
        handleForm(date, "11-222", city, "ul. Degrengolady 4", "", null, 15, ["Zaawansowany"],
                ["ISTQB Advanced Level Test Analyst / Polski, Angielski",
                 "ISTQB Advanced Level Technical Test Analyst / Polski, Angielski",
                 "ISTQB Advanced Level Test Manager / Polski, Angielski"],
                "GFT Poland1 Test", true)

        then: "i activate session"
        at(SessionDetailsPage)
        activateSession()
        getSessionStatus() == "Otwarta - potwierdzony"

        cleanup: "i delete created session"
        if (page instanceof SessionDetailsPage) {
            page.deleteSession()
        }
    }
}