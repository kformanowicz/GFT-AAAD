package page.module.individualRegistrationPage

import geb.Module
import geb.module.TextInput
import helper.CommonHelper


class BaseDataFormModule extends Module {

    def appData = CommonHelper.jsonToObject("src/test/resources/applicationData.json")

    static content = {
        availableLanguagesTitle { $("label", for: "AvailableLanguages").text() }
        optionPoland { $("[for*=ProductLanguage]", text: "Polski") }
        getOptionPolandLabel { optionPoland.find("label").text() }
        optionEnglish { $("[for*=ProductLanguage]", text: "Angielski") }
        getOptionEnglishLabel { optionEnglish.find("label").text() }

        availableProductTitle { $("label", for: "AvailableProductForms").text() }
        optionPaper { $("#ProductSelectionDto_ProductForm_papierowa") }
        getOptionPaperLabel { optionPaper.find("label").text() }
        optionElectronical { $("#ProductSelectionDto_ProductForm_elektroniczna") }
        getOptionElectronicalLabel { optionElectronical.find("label").text() }

        getCertificateDataMessage { $(".Register-fillCertificateDate").text() }
        getCertificateNumberTitle { $(for: "ProductSelectionDto_CertificateNumber").text() }
        certificateNumberInput { $("#ProductSelectionDto_CertificateNumber").module(TextInput) }

        getCertificateDateOfIssueTitle { $(for: "ProductSelectionDto.CertificateDateOfIssue").text() }
        certificateDateOfIssueInput { $("#ProductSelectionDto_CertificateDateOfIssue").module(TextInput) }

        getCertificateProvidersTitle { $(for: "AvailableCertificateProviders").text() }
        certificateProvidersInput { $("#ProductSelectionDto_CertificateProvider") }

        submitButton { $(".Register-forwardBtn button") }
        backButton { $(".Register-backBtn button") }
    }

//    SETTERS
    void setLanguage(String option) {
        if (option == "Angielski") {
            optionEnglish.click()
        } else {
            optionPoland.click()
        }
    }

    void setProduct(String option) {
        if (option == "elektroniczna") {
            optionElectronical.click()
        } else {
            optionPaper.click()
        }
    }

    void setCertificateNumber(String certificateNumber) {
        certificateNumberInput.text = certificateNumber
    }

    void setCertificateDateOfIssue(String date) {
        certificateDateOfIssueInput.text = date
    }

    void setCertificateProvider(String provider) {
        certificateProvidersInput.value(provider)
    }

//    GETTERS
    String getSubmitButtonText() {
        return submitButton.text()
    }

//    ACTIONS
    def clickSubmitButton() {
        submitButton.click()
    }

    def fillBaseData(String language, String product, String certificateNumber = null, String certificateDateOfIssue = null,
                     String certificateProviders = null, boolean ifSubmit = true) {
        setLanguage(language)
        setProduct(product)
        if (certificateNumber != null) setCertificateNumber(certificateNumber)
        if (certificateDateOfIssue != null) setCertificateDateOfIssue(certificateDateOfIssue)
        if (certificateProviders != null) setCertificateProvider(certificateProviders)
        if (ifSubmit) clickSubmitButton()
    }

}
