package ca.bcit.comp2522.bank;

import java.util.*;


public final class BankClient
{
    private final String clientID;
    private final Name name;
    private final Date dateBorn;
    private final Date signupDate;
    private final Date dateDied;

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
    private static void validateClientID(final String clientID) {
        if (clientID == null || clientID.isEmpty() || !clientID.trim().matches("\\d{6,7}")) {
            throw new IllegalArgumentException("Client ID must have 6-7 digits.");
        }
    }

    private static void validateNameObject(final Name name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null.");
        }
    }

    private static void validateDateBorn(final Date dateBorn) {
        if (dateBorn == null) {
            throw new IllegalArgumentException("Date born must not be null.");
        }
    }

    private static void validatesignUpDate(final Date signupDate, final Date dateBorn) {
        if (signupDate == null)
        {
            throw new IllegalArgumentException("Signup Date must not be null.");
        }
        if (signupDate.getYear() < dateBorn.getYear() ||
                (signupDate.getYear() == dateBorn.getYear() && signupDate.getMonth() < dateBorn.getMonth()) ||
                (signupDate.getYear() == dateBorn.getYear() && signupDate.getMonth() == dateBorn.getMonth() && signupDate.getDay() < dateBorn.getDay())) {
            throw new IllegalArgumentException("Signup Date cannot be before Date of Birth.");
        }


    }

    public Name getName() {
        return name;
    }



    public static void main(final String[] args)
    {
        final Name name;
        final Date dateBorn;


        name = new Name("meow", "meow");
        dateBorn = new Date(1990, 1, 1);

        BankClient client = new BankClient("123456", name, dateBorn, null, null);
        System.out.println(name.getFullName());
        System.out.println(dateBorn.getYYYYMMDD());

    }

}

