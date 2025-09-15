package ca.bcit.comp2522.bank;

import java.sql.SQLOutput;

/**
 * Name stores a person's first and last names and provides formatting methods.
 *
 * @author Leen and Veronica
 * @version 1.0
 */
public class Name
{
    private static final int MAX_NAME_LENGTH = 45;
    private static final int FIRST_CHAR = 0;
    private static final int SECOND_CHAR = 1;
    private static final String FORBIDDEN_WORD = "admin";

    private final String first;
    private final String last;

    /**
     * Constructs a Name object with a first and last name
     *
     * @param first first name
     * @param last last name
     * @throws IllegalArgumentException if invalid
     */
    public Name(final String first,
                final String last)
    {
        validateName(first);
        validateName(last);
        this.first = first;
        this.last = last;
    }

    /**
     * Returns the first name
     * @return the first name
     */
    public String getFirst() {
        return first;
    }

    /**
     * Returns the last name
     * @return the last name
     */
    public String getLast() {
        return last;
    }

    /**
     * Returns the initials of a given name
     * @return initials in format F.L.
     */
    public String getInitial() {
        return first.substring(FIRST_CHAR, SECOND_CHAR).toUpperCase() + "." +
                last.substring(FIRST_CHAR, SECOND_CHAR).toUpperCase() + ".";
    }

    /**
     * Returns the full name in title format
     * @return full name in title format
     */
    public String getFullName() {
        return first.substring(FIRST_CHAR, SECOND_CHAR).toUpperCase() + first.substring(SECOND_CHAR).toLowerCase()
                + " " + last.substring(FIRST_CHAR, SECOND_CHAR).toUpperCase() + last.substring(SECOND_CHAR).toLowerCase();
    }

    /**
     * Returns the revers of the full name.
     * @return reversed name
     */
    public String getReverseName()
    {
        final StringBuilder builder;
        builder = new StringBuilder();

        builder.append(new StringBuilder(last).reverse().toString())
                .append(" ")
                .append(new StringBuilder(first).reverse().toString());

        return builder.toString();
    }

    /**
     * Validates a name per the following guidelines.
     * Name must not be null or empty.
     * Name must be fewer than 45 characters.
     * Name must not contain the word "admin".
     *
     * @param name the name to be checked
     */
    private static void validateName(final String name)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }

        if (name.length() > MAX_NAME_LENGTH)
        {
            throw new IllegalArgumentException("Name cannot be longer than " + MAX_NAME_LENGTH);
        }

        if (name.toLowerCase().contains(FORBIDDEN_WORD))
        {
            throw new IllegalArgumentException("Name cannot contain " + FORBIDDEN_WORD);
        }
    }

}

