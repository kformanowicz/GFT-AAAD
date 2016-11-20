package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import page.AddSessionPage
import page.DashboardPage
import page.SessionDetailsPage
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
    def "Should handle session form"() {
        given: "i am on the add new session page"
        to AddSessionPage

        when: "i handle new session form"
        handleForm("today", "11-111", "Address", "additional Information", "Dla sesji", 2, ["Podstawowy"],
                ["ISTQB Foundation Level / Polski, Angielski"], "GFT Poland1 Test", false)

        then: "i should have valid chosen data"
        selectedProducts.products.size() == 1
        selectedProducts.productTitle*.text() == ["ISTQB Foundation Level / Polski, Angielski"]
        selectedProducts.productAmount*.text() == ["0"]
    }

    def "Should be able to create an exam session for one product" () {
        setup:
        def calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)  //results in setting date to 2016-11-30
        def Date date = calendar.getTime()
        def city = "Gdańsk"
        to AddSessionPage

        when:
        createSessionForOneProductDateCity(date, city)

        then:
        at(SessionDetailsPage)
        //TODO: fix StackOverflowError  for the below asserts
        //assert getCity() == city
        //assert sessionDetailsPage.getDate() == date

        cleanup:
        if (page instanceof SessionDetailsPage) {
            /*TODO: even though the alert is successfully accepted and the session seems to be deleted,
            an attempt to run the test again will result in duplicate session error on the page.*/
            page.deleteSession()
        }
    }
}