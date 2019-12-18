package personcrud.pages

import geb.Page
import geb.module.TextInput

class CreatePersonPage extends Page {

    static url = '/person/create'

    static at = {
        title == 'Create Person'
    }

    static content = {
        submitButton { $('#create', 0) }
        firstNameInputField { $('#firstName', 0).module(TextInput) }
        lastNameInputField { $('#lastName', 0).module(TextInput) }
        ageInputField { $('#age', 0) }
        errorMessage { $('li', 'data-field-id':'age') }
    }

    void populateCreatePersonForm(String firstName, String lastName, String age) {
        firstNameInputField.text = firstName
        lastNameInputField.text = lastName
        ageInputField << age
        submitButton.click()
    }
}
