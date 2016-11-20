package page

import geb.Page
import geb.module.TextInput
import page.module.common.NavbarMenuModule
import page.module.common.NavbarModule


class MyProfilePage extends Page {

    static url = "Dashboard/UserProfileEdit"
    static at = {
        $("h3").text() == "Mój profil"
    }

    static content = {
        navbar { $(".Navigation-list").module(NavbarModule) }
        navbarMenu { $(".Navbar-menu").module(NavbarMenuModule) }
        firstNameInput { $("#AspNetUserOrganizationEditDto_Name").module(TextInput) }
        lastNameInput { $("#AspNetUserOrganizationEditDto_Surname").module(TextInput) }
        emailValue { $("#Email").value() }

        cancelButton { $("a", href: "/gftpl/Dashboard/Index") }
        saveButton {$("input.btn-dark")}
    }

    void cancel() {
        cancelButton.click()
    }

    void save(){
        saveButton.click()
    }
}
