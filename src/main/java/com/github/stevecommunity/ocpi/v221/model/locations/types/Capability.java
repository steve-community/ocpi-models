package com.github.stevecommunity.ocpi.v221.model.locations.types;

/**
 * The capabilities of an EVSE.
 */
public enum Capability {
    /**
     * Charging the EV.
     */
    CHARGING_PROFILE_CAPABLE,

    /**
     * Changing the charging profile capabilities of the EVSE.
     */
    CHARGING_PROFILE_POSSIBLE,

    /**
     * EVSE has a payment terminal that supports chip cards.
     */
    CHIP_CARD_SUPPORT,

    /**
     * EVSE has a payment terminal that supports contactless cards.
     */
    CONTACTLESS_CARD_SUPPORT,

    /**
     * EVSE has a payment terminal that makes it possible to pay for charging using
     * a credit card.
     */
    CREDIT_CARD_PAYABLE,

    /**
     * EVSE has a payment terminal that makes it possible to pay for charging using
     * a debit card.
     */
    DEBIT_CARD_PAYABLE,

    /**
     * EVSE has a payment terminal that makes it possible to pay for charging using
     * a PED terminal.
     */
    PED_TERMINAL,

    /**
     * EVSE has a payment terminal that makes it possible to pay for charging using
     * a remote contract.
     */
    REMOTE_START_STOP_CAPABLE,

    /**
     * EVSE has a payment terminal that makes it possible to pay for charging using
     * a reservation.
     */
    RESERVABLE,

    /**
     * EVSE has a payment terminal that makes it possible to pay for charging using
     * an RFID token.
     */
    RFID_READER,

    /**
     * EVSE supports token groups.
     */
    TOKEN_GROUP_CAPABLE,

    /**
     * EVSE supports unlocking the connector.
     */
    UNLOCK_CAPABLE
}
