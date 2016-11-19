package page.registration

import geb.module.TextInput


class GroupRegistrationCertificateData extends RegistrationPage {
    static at = {
        $("label", text: "Faktura VAT").displayed
    }

    static content = {
        nameInput { $("#AddressDto_Name").module(TextInput) }
        lastNameInput { $("#AddressDto_Surname").module(TextInput) }
        postalCodeInput { $("#AddressDto_PostalCode").module(TextInput) }
        cityInput { $("#AddressDto_City").module(TextInput) }
        addressInput { $("#AddressDto_Address").module(TextInput) }
        additionalInfoInput { $("#AddressDto_Comment").module(TextInput) }

        noInvoiceButton { $("#AddressDto_InvoiceTypesNone") }
        electronicInvoiceButton { $("#AddressDto_InvoiceTypesElectronic") }
        paperInvoiceButton { $("#AddressDto_InvoiceTypesPaper") }

        privacyCheckbox { $("#AddressDto_AcceptedPrivacyPolicy") }
        marketingCheckbox { $("#AddressDto_AcceptedMarketingPolicy") }

        backToContactDataButton { $(".Register-backBtn") }
        registerButton { $(".Register-forwardBtn") }
    }


    void fillAndSubmitForm(String name, String lastName, String postalCode, String city, String address, String comment) {
        nameInput.text = name
        lastNameInput.text = lastName
        postalCodeInput.text = postalCode
        cityInput.text = city
        addressInput.text = address
        additionalInfoInput.text = comment

        noInvoiceButton.click() // dont think it's needed in user stories

        privacyCheckbox.click()
        marketingCheckbox.click()

        registerButton.click()
    }
}
