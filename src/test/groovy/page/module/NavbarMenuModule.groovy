package page.module

import geb.Module
import geb.module.Select


class NavbarMenuModule extends Module{
    static content = {
        languageButton {$("#dropdownMenu-language")}
        menuList {languageButton.next().module(Select)}
        userMenu {$("#dropdownMenu-user")}
        myProfile {$("a", href: "/gftpl/Dashboard/UserProfileEdit")}
    }

    void goToMyProfile(){
        userMenu.click()
        waitFor {myProfile.displayed}
        myProfile.click()
    }
}
