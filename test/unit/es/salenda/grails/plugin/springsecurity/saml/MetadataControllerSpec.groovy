package es.salenda.grails.plugin.springsecurity.saml

import org.springframework.security.saml.metadata.*
import org.opensaml.xml.XMLObjectBuilderFactory
import org.opensaml.saml2.metadata.EntityDescriptor
import spock.lang.*

@TestFor(MetadataController)
class MetadataControllerSpec extends Specification {
    def 'save'() {
        given:
            MetadataGenerator gen = Spy(MetadataGenerator)
            controller.metadataGenerator = gen
            EntityDescriptor descriptor = Mock()
        and: 'all the params'
            params.entityId = 'entityId'
            params.baseURL = 'baseUrl'
            params.requestSigned = 'y'
            params.wantAssertionSigned = 'y'
            params.ssoBindingPost = 'y'
            params.ssoBindingPAOS = 'y'
            params.ssoBindingArtifact = 'y'
            params.alias = 'alias'
        when:
            controller.save()
        then:
            1 * gen.generateMetadata() >> descriptor
            response.redirectUrl == '/metadata/show?entityId=entityId'
    }
}
