package personcrud

import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration
import personcrud.pages.CreatePersonPage
import personcrud.pages.PersonListPage
import personcrud.pages.ShowPersonPage

import spock.lang.Stepwise

@Integration
@Stepwise
class PersonCrudFunctionalSpec extends GebSpec {

    void "test a person older than 120 years old"() {
        when:
        CreatePersonPage createPersonPage = to CreatePersonPage

        then:
        at CreatePersonPage

        when:
        createPersonPage.populateCreatePersonForm 'Grandma', 'Brown', '135'

        then:
        at CreatePersonPage

        and:
        createPersonPage.errorMessage.text() == 'Age [135] is invalid. The maximum allowed age is 120.'
    }

    void "test a person younger than 1 year old"() {
        when:
        CreatePersonPage createPersonPage = to CreatePersonPage

        then:
        at CreatePersonPage

        when:
        createPersonPage.populateCreatePersonForm 'Baby', 'Brown', '0'

        then:
        at CreatePersonPage

        and:
        createPersonPage.errorMessage.text() == 'Age [0] is invalid. The minimum allowed age is 1.'
    }

    void "test creating people"() {
        when:
        CreatePersonPage createPersonPage = to CreatePersonPage

        then:
        at CreatePersonPage

        when:
        createPersonPage.populateCreatePersonForm 'Geddy', 'Lee', '63'

        then:
        at ShowPersonPage

        when:
        createPersonPage = to CreatePersonPage

        then:
        at CreatePersonPage

        when:
        createPersonPage.populateCreatePersonForm 'Alex', 'Lifeson', '63'

        then:
        at ShowPersonPage

        when:
        ShowPersonPage showPersonPage = browser.page(ShowPersonPage)

        then:
        showPersonPage.personId

        when:
        Long personId = showPersonPage.personId
        showPersonPage = to ShowPersonPage,  personId

        println browser.driver.pageSource

        then:
        showPersonPage.personName == 'Alex'
    }

    void "test retrieving people"() {
        when:
        PersonListPage personListPage = to PersonListPage

        then:
        personListPage.numberOfPersonRows == 2
    }
}
