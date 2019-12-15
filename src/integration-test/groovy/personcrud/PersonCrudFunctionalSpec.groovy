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
        to CreatePersonPage

        then:
        at CreatePersonPage

        when:
        populateCreatePersonForm 'Grandma', 'Brown', '135'

        then:
        at CreatePersonPage

        and:
        errorMessage.text() == 'Age [135] is invalid. The maximum allowed age is 120.'
    }

    void "test a person younger than 1 year old"() {
        when:
        to CreatePersonPage

        then:
        at CreatePersonPage

        when:
        populateCreatePersonForm 'Baby', 'Brown', '0'

        then:
        at CreatePersonPage

        and:
        errorMessage.text() == 'Age [0] is invalid. The minimum allowed age is 1.'
    }

    void "test creating people"() {
        when:
        to CreatePersonPage

        then:
        at CreatePersonPage

        when:
        populateCreatePersonForm 'Geddy', 'Lee', '63'

        then:
        at ShowPersonPage

        when:
        to CreatePersonPage

        then:
        at CreatePersonPage

        when:
        populateCreatePersonForm 'Alex', 'Lifeson', '63'

        then:
        at ShowPersonPage
    }

    void "test retrieving people"() {
        when:
        to PersonListPage

        then:
        numberOfPersonRows == 2
    }
}
