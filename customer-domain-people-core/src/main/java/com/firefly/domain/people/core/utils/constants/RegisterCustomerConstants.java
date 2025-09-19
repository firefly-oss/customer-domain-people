package com.firefly.domain.people.core.utils.constants;

public class RegisterCustomerConstants {

    // ============================== SAGA CONFIGURATION ==============================
    public static final String SAGA_REGISTER_CUSTOMER_NAME = "RegisterCustomerSaga";
    public static final String SAGA_UPDATE_LEGAL_NAME_NAME = "UpdateNameSaga";
    public static final String SAGA_ADD_ADDRESS_NAME = "AddAddressSaga";
    public static final String SAGA_UPDATE_ADDRESS_NAME = "UpdateAddressSaga";
    public static final String SAGA_REMOVE_ADDRESS_NAME = "RemoveAddressSaga";
    public static final String SAGA_ADD_EMAIL_NAME = "AddEmailSaga";
    public static final String SAGA_UPDATE_EMAIL_NAME = "UpdateEmailSaga";
    public static final String SAGA_REMOVE_EMAIL_NAME = "RemoveEmailSaga";
    public static final String SAGA_ADD_PHONE_NAME = "AddPhoneSaga";
    public static final String SAGA_UPDATE_PHONE_NAME = "UpdatePhoneSaga";
    public static final String SAGA_REMOVE_PHONE_NAME = "RemovePhoneSaga";
    public static final String SAGA_SET_PREFERRED_CHANNEL_NAME = "SetPreferredChannelSaga";
    public static final String SAGA_ADD_IDENTITY_DOCUMENT_NAME = "AddIdentityDocumentSaga";
    public static final String SAGA_REMOVE_IDENTITY_DOCUMENT_NAME = "RemoveIdentityDocumentSaga";

    // ============================== STEP IDENTIFIERS ==============================
    public static final String STEP_REGISTER_PARTY = "registerParty";
    public static final String STEP_REGISTER_NATURAL_PERSON = "registerNaturalPerson";
    public static final String STEP_REGISTER_LEGAL_PERSON = "registerLegalPerson";
    public static final String STEP_REGISTER_STATUS_ENTRY = "registerStatusEntry";
    public static final String STEP_REGISTER_PEP = "registerPep";
    public static final String STEP_REGISTER_IDENTITY_DOCUMENT = "registerIdentityDocument";
    public static final String STEP_REGISTER_ADDRESS = "registerAddress";
    public static final String STEP_REGISTER_EMAIL = "registerEmail";
    public static final String STEP_REGISTER_PHONE = "registerPhone";
    public static final String STEP_REGISTER_ECONOMIC_ACTIVITY_LINK = "registerEconomicActivityLink";
    public static final String STEP_REGISTER_CONSENT = "registerConsent";
    public static final String STEP_REGISTER_PARTY_PROVIDER = "registerPartyProvider";
    public static final String STEP_REGISTER_PARTY_RELATIONSHIP = "registerPartyRelationship";
    public static final String STEP_REGISTER_PARTY_GROUP_MEMBERSHIP = "registerPartyGroupMembership";
    public static final String STEP_RETRIEVE_PARTY_ID = "retrievePartyId";
    public static final String STEP_RETRIEVE_CUSTOMER = "retrieveCustomer";
    public static final String STEP_UPDATE_CUSTOMER_NAME = "updateCustomerName";
    public static final String STEP_UPDATE_ADDRESS = "updateAddress";
    public static final String STEP_REMOVE_ADDRESS = "removeAddress";
    public static final String STEP_UPDATE_EMAIL = "updateEmail";
    public static final String STEP_REMOVE_EMAIL = "removeEmail";
    public static final String STEP_UPDATE_PHONE = "updatePhone";
    public static final String STEP_REMOVE_PHONE = "removePhone";
    public static final String STEP_UPDATE_CHANNEL = "updateChannel";
    public static final String STEP_UPDATE_STATUS = "updateStatus";
    public static final String STEP_REMOVE_IDENTITY_DOCUMENT = "removeIdentityDocument";

    // ============================== COMPENSATE METHODS ==============================
    public static final String COMPENSATE_REMOVE_PARTY = "removeParty";
    public static final String COMPENSATE_REMOVE_NATURAL_PERSON = "removeNaturalPerson";
    public static final String COMPENSATE_REMOVE_LEGAL_PERSON = "removeLegalPerson";
    public static final String COMPENSATE_REMOVE_STATUS_ENTRY = "removeStatusEntry";
    public static final String COMPENSATE_REMOVE_PEP = "removePep";
    public static final String COMPENSATE_REMOVE_IDENTITY_DOCUMENT = "removeIdentityDocument";
    public static final String COMPENSATE_REMOVE_ADDRESS = "removeAddress";
    public static final String COMPENSATE_REMOVE_EMAIL = "removeEmail";
    public static final String COMPENSATE_REMOVE_PHONE = "removePhone";
    public static final String COMPENSATE_REMOVE_ECONOMIC_ACTIVITY_LINK = "removeEconomicActivityLink";
    public static final String COMPENSATE_REMOVE_CONSENT = "removeConsent";
    public static final String COMPENSATE_REMOVE_PARTY_PROVIDER = "removePartyProvider";
    public static final String COMPENSATE_REMOVE_PARTY_RELATIONSHIP = "removePartyRelationship";
    public static final String COMPENSATE_REMOVE_PARTY_GROUP_MEMBERSHIP = "removePartyGroupMembership";

    // ============================== EVENT TYPES ==============================
    public static final String EVENT_PARTY_REGISTERED = "party.registered";
    public static final String EVENT_NATURAL_PERSON_REGISTERED = "naturalperson.registered";
    public static final String EVENT_LEGAL_PERSON_REGISTERED = "legalperson.registered";
    public static final String EVENT_PARTY_STATUS_REGISTERED = "partystatus.registered";
    public static final String EVENT_PEP_REGISTERED = "pep.registered";
    public static final String EVENT_IDENTITY_DOCUMENT_REGISTERED = "identitydocument.registered";
    public static final String EVENT_ADDRESS_REGISTERED = "address.registered";
    public static final String EVENT_EMAIL_REGISTERED = "email.registered";
    public static final String EVENT_PHONE_REGISTERED = "phone.registered";
    public static final String EVENT_ECONOMIC_ACTIVITY_REGISTERED = "economicactivity.registered";
    public static final String EVENT_CONSENT_REGISTERED = "consent.registered";
    public static final String EVENT_PARTY_PROVIDER_REGISTERED = "partyprovider.registered";
    public static final String EVENT_PARTY_RELATIONSHIP_REGISTERED = "partyrelationship.registered";
    public static final String EVENT_PARTY_GROUP_MEMBERSHIP_REGISTERED = "partygroupmembership.registered";
    public static final String EVENT_PARTY_RETRIEVED = "party.retrieved";
    public static final String EVENT_CUSTOMER_RETRIEVED = "customer.retrieved";
    public static final String EVENT_CUSTOMER_NAME_CHANGED = "customername.changed";
    public static final String EVENT_ADDRESS_UPDATED = "address.updated";
    public static final String EVENT_ADDRESS_REMOVED = "address.removed";
    public static final String EVENT_EMAIL_UPDATED = "email.updated";
    public static final String EVENT_EMAIL_REMOVED = "email.removed";
    public static final String EVENT_PHONE_UPDATED = "phone.updated";
    public static final String EVENT_PHONE_REMOVED = "phone.removed";
    public static final String EVENT_PREFERRED_CHANNEL_UPDATED = "preferredChannel.updated";
    public static final String EVENT_STATUS_UPDATED = "status.updated";
    public static final String EVENT_IDENTITY_DOCUMENT_REMOVED = "identitydocument.removed";


}
