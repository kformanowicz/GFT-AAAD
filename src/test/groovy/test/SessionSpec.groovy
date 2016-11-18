package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import page.SessionPage

class SessionSpec extends GebReportingSpec {

    def setup() {
        CommonHelper.logInAsUser1()
    }

    def cleanup() {}

    def "Should be visible all fields in form"() {
        when:
        to SessionPage

        then:
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

    def "Should be visible all correct titles in form"() {
        when:
        to SessionPage
        def data = CommonHelper.jsonToObject("src/test/resources/applicationData.json")

        then:
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

}