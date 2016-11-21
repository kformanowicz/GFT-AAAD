package helper


class GroupRegistrationExamForm {
    public name
    public surname
    public email
    public phone
    public examName
    public setPolish
    public setPaper
    public certificateNumber
    public certificateDate
    public certificateProvider

    GroupRegistrationExamForm(String name, String lastName, String phone, String examName,
                              boolean setPolish, boolean setPaper) {
        this.name = name
        this.surname = lastName
        this.email = CommonHelper.getRandomEmail()
        this.phone = phone
        this.examName = examName
        this.setPolish = setPolish
        this.setPaper = setPaper
        this.certificateNumber = ""
        this.certificateDate = ""
        this.certificateProvider = ""
    }

    GroupRegistrationExamForm(String name, String lastName, String phone, String examName,
                              boolean setPolish, boolean setPaper, String certificateNumber, String certificateDate,
                              String certificateProvider) {
        this.name = name
        this.surname = lastName
        this.email = CommonHelper.getRandomEmail()
        this.phone = phone
        this.examName = examName
        this.setPolish = setPolish
        this.setPaper = setPaper
        this.certificateNumber = certificateNumber
        this.certificateDate = certificateDate
        this.certificateProvider = certificateProvider
    }
}
