package ca.bcit.comp2522.bank;

import java.util.*;

/**
 * Represents a bank client with personal details, birth/death dates,
 * signup date, and a unique client ID.
 *
 * @author Nazanin Mohsenimehr
 * @version 1.0
 */
public final class BankClient
{
    private static final int MIN_CLIENT_ID_LENGTH = 6;
    private static final int MAX_CLIENT_ID_LENGTH = 7;


    private final String clientID;
    private final Name name;
    private final Date dateBorn;
    private final Date signupDate;
    private final Date dateDied;

    /**
     * Constructs a BankClient object.
     *
     * @param clientID   a 6- or 7-digit unique client ID
     * @param name       the client's name (must not be null)
     * @param dateBorn   the client's date of birth (must not be null)
     * @param signupDate the date the client signed up (must be after or equal to dateBorn)
     * @param dateDied   the client's date of death (can be null if still alive)
     * @throws IllegalArgumentException if any validation fails
     */
    public BankClient(final String clientID,
                      final Name name,
                      final  Date dateBorn,
                      final Date signupDate,
                      final Date dateDied)
    {
        validateClientID(clientID);
        validateNameObject(name);
        validateDateBorn(dateBorn);
        validatesignUpDate(signupDate, dateBorn);


        this.clientID = clientID;
        this.name = name;
        this.dateBorn = dateBorn;
        this.signupDate = signupDate;
        this.dateDied = dateDied;
    }

    private static void validateClientID(final String clientID)
    {
        if (clientID == null || clientID.isEmpty()
                || !clientID.trim().matches("\\d{" + MIN_CLIENT_ID_LENGTH + "," + MAX_CLIENT_ID_LENGTH + "}"))
        {
            throw new IllegalArgumentException(
                    "Client ID must have " + MIN_CLIENT_ID_LENGTH + "-" + MAX_CLIENT_ID_LENGTH + " digits.");
        }
    }

    private static void validateNameObject(final Name name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Name must not be null.");
        }
    }

    private static void validateDateBorn(final Date dateBorn)
    {
        if (dateBorn == null)
        {
            throw new IllegalArgumentException("Date born must not be null.");
        }
    }

    private static void validatesignUpDate(final Date signupDate, final Date dateBorn)
    {
        if (signupDate == null)
        {
            throw new IllegalArgumentException("Signup Date must not be null.");
        }
        if (signupDate.getYear() < dateBorn.getYear()
               ||  (signupDate.getYear() == dateBorn.getYear() && signupDate.getMonth() < dateBorn.getMonth())
               || (signupDate.getYear() == dateBorn.getYear() && signupDate.getMonth() == dateBorn.getMonth() && signupDate.getDay() < dateBorn.getDay()))
        {
            throw new IllegalArgumentException("Signup Date cannot be before Date of Birth.");
        }
    }

    /**
     * Returns true if the client is still alive.
     *
     * @return true if dateDied is null, false otherwise
     */
    public boolean isAlive()
    {
        return dateDied == null;
    }

    /**
     * Returns details about the client in the required format.
     *
     * @return a formatted string with the client’s details
     */
    public String getDetails()
    {
        String aliveStatus;
        if (isAlive())
        {
            aliveStatus = "(alive)";
        } else
        {
            aliveStatus = "(died "
                    + dateDied.getDayOfTheWeek() + ", "
                    + dateDied.getMonth() + " "
                    + dateDied.getDay() + ", "
                    + dateDied.getYear() + ")";
        }

        return name.getFullName()
                + " client #" + clientID
                + " " + aliveStatus
                + " joined the bank on "
                + signupDate.getDayOfTheWeek() + ", "
                + signupDate.getMonth() + " "
                + signupDate.getDay() + ", "
                + signupDate.getYear();
    }

    /**
     * Gets the client ID.
     *
     * @return the unique client ID as a String of 6 or 7 digits
     */
    public String getClientID()
    {
        return clientID;
    }

    /**
     * Gets the client's name.
     *
     * @return the Name object representing the client's full name
     */
    public Name getName()
    {
        return name;
    }

    /**
     * Gets the client's date of birth.
     *
     * @return the Date object representing the client's birth date
     */
    public Date getDateBorn()
    {
        return dateBorn;
    }

    /**
     * Gets the client's signup date.
     *
     * @return the Date object representing when the client signed up with the bank
     */
    public Date getSignupDate()
    {
        return signupDate;
    }

    /**
     * Gets the client's date of death.
     *
     * @return the Date object representing the client's death date,
     *         or null if the client is still alive
     */
    public Date getDateDied()
    {
        return dateDied;
    }
}




