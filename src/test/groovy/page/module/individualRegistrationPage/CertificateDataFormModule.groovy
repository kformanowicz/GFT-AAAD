package page.module.individualRegistrationPage

import geb.Module
import geb.module.Checkbox
import geb.module.TextInput
import geb.module.Textarea


class CertificateDataFormModule extends Module {

    static content = {

        getNameTitle { $(for: "AddressDto_Name").text() }
        nameInput { $("#AddressDto_Name").module(TextInput) }

        getSurnameTitle { $(for: "AddressDto_Surname").text() }
        surnameInput { $("#AddressDto_Surname").module(TextInput) }

        getPostalCodeTitle { $(for: "AddressDto_PostalCode").text() }
        postalCodeInput { $("#AddressDto_PostalCode").module(TextInput) }

        getCityTitle { $(for: "AddressDto_City").text() }
        cityInput { $("#AddressDto_City").module(TextInput) }

        getAddressTitle { $(for: "AddressDto_Address").text() }
        addressInput { $("#AddressDto_Address").module(TextInput) }

        getAdditionalInformationTitle { $(for: "AddressDto_Comment") }
        additionalInformationInput { $("#AddressDto_Comment").module(Textarea) }

        getInvoiceTypesTitle { $(for: "AddressDto_InvoiceTypes").text() }
        getInvoiceTypeNoneLabel { $(for: "AddressDto_InvoiceTypesNone").text() }
        invoiceTypeNone { $("#AddressDto_InvoiceTypesNone") }
        getInvoiceTypeElectronicLabel { $(for: "AddressDto_InvoiceTypesElectronic").text() }
        invoiceTypeElectronic { $("#AddressDto_InvoiceTypesElectronic") }
        getInvoiceTypePaperLabel { $(for: "AddressDto_InvoiceTypesPaper").text() }
        invoiceTypePaper { $("#AddressDto_InvoiceTypesPaper") }

        getLegalCheckboxTitle { $(name: "AddressDto_AcceptedPrivacyPolicy").text() }
        legalCheckbox { $("#AddressDto_AcceptedPrivacyPolicy").module(Checkbox) }

        getMarketingCheckboxTitle { $(name: "AddressDto_AcceptedMarketingPolicy").text() }
        marketingCheckbox { $("#AddressDto_AcceptedMarketingPolicy").module(Checkbox) }

        submitButton { $(".Register-forwardBtn button") }
        backButton { $(".Register-backBtn button") }
    }

//    SETTERS
    void setName(String name) {
        nameInput.text = name
    }

    void setSurname(String surname) {
        surnameInput.text = surname
    }

    void setPostalField(String email) {
        postalCodeInput.text = email
    }

    void setCity(String city) {
        cityInput.text = city
    }

    void setAddress(String address) {
        addressInput.text = address
    }

    void setAdditionalInformation(String additionalInformation) {
        additionalInformationInput.text = additionalInformation
    }

    void setInvoiceType(String invoiceType) {
        if (invoiceType == "none") {
            invoiceTypeNone.click()
        } else if (invoiceType == "electronic") {
            invoiceTypeElectronic.click()
        } else {
            invoiceTypePaper.click()
        }
    }

    void setLegalCheckBox() {
        legalCheckbox.check()
    }

    void setMarketingCheckbox() {
        marketingCheckbox.check()
    }

//    GETTERS
    String getSubmitButtonText() {
        return submitButton.text()
    }

    String getBackButtonText() {
        return backButton.text()
    }

//    ACTIONS
    def clickSubmitButton() {
        submitButton.click()
    }

    def clickBackButton() {
        backButton.click()
    }

    def fillCertificateData(String name, String surname, String postalCode, String city, String address,
                            String additionalInformation, String invoiceType, boolean legal = true,
                            boolean marketing = true, boolean ifSubmit = true) {
        setName(name)
        setSurname(surname)
        setPostalField(postalCode)
        setCity(city)
        setAddress(address)
        setAdditionalInformation(additionalInformation)
        setInvoiceType(invoiceType)
        if (legal) setLegalCheckBox()
        if (marketing) setMarketingCheckbox()
        if (ifSubmit) clickSubmitButton()
    }

}
