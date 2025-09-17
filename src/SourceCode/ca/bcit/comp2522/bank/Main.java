package ca.bcit.comp2522.bank;

/**
 * Entry point that builds the sample bank clients/accounts and prints outputs
 *
 *
 * author:
 * version: 1.0
 */
public class Main {

    /**
     * Prints the required name-related lines:
     * initials, full name, and reversed name.
     *
     * @param name the Name instance to print
     */
    private static void printNameInfo(final Name name) {
        System.out.println(name.getInitial());
        System.out.println(name.getFullName());
        System.out.println(name.getReverseName());
    }

    /**
     * Builds the four people from the spec, prints their name info,
     * prints BankClient.getDetails(), performs deposits/withdrawals,
     * and prints BankAccount.getDetails().
     *
     * @param args command-line arguments (unused)
     */
    public static void main(final String[] args) {
        // ===== Albert Einstein =====
        final Name n1 = new Name("Albert", "Einstein");
        final Date b1 = new Date(1879, 3, 14);
        final Date d1 = new Date(1955, 4, 18);
        final Date signup1 = new Date(1900, 1, 1);
        final BankClient c1 = new BankClient(n1, b1, d1, "123456", signup1);
        final BankAccount a1 = new BankAccount(
                c1, "abc123", 3141,
                new Date(1900, 1, 1),
                new Date(1950, 10, 14)
        );
        printNameInfo(n1);
        System.out.println(c1.getDetails());
        a1.deposit(1000);
        a1.withdraw(100, 3141);
        System.out.println(a1.getDetails());

        System.out.println();

        // ===== Nelson Mandela =====
        final Name n2 = new Name("Nelson", "Mandela");
        final Date b2 = new Date(1918, 7, 18);
        final Date d2 = new Date(2013, 12, 5);
        final Date signup2 = new Date(1994, 5, 10);
        final BankClient c2 = new BankClient(n2, b2, d2, "111222", signup2);
        final BankAccount a2 = new BankAccount(
                c2, "654321", 4664,
                new Date(1994, 5, 10),
                null // still open
        );
        printNameInfo(n2);
        System.out.println(c2.getDetails());
        a2.deposit(2000);
        a2.withdraw(200, 4664);
        System.out.println(a2.getDetails());

        System.out.println();

        // ===== Frida Kahlo =====
        final Name n3 = new Name("Frida", "Kahlo");
        final Date b3 = new Date(1907, 7, 6);
        final Date d3 = new Date(1954, 7, 13);
        final Date signup3 = new Date(1940, 1, 1);
        final BankClient c3 = new BankClient(n3, b3, d3, "222333", signup3);
        final BankAccount a3 = new BankAccount(
                c3, "frd123", 1907,
                new Date(1940, 1, 1),
                new Date(1954, 7, 13)
        );
        printNameInfo(n3);
        System.out.println(c3.getDetails());
        a3.deposit(500);
        a3.withdraw(50, 1907);
        System.out.println(a3.getDetails());

        System.out.println();

        // ===== Jackie Chan =====
        final Name n4 = new Name("Jackie", "Chan");
        final Date b4 = new Date(1954, 4, 7);
        final Date d4 = null; // still alive
        final Date signup4 = new Date(1980, 10, 1);
        final BankClient c4 = new BankClient(n4, b4, d4, "3334444", signup4); // 7-digit id
        final BankAccount a4 = new BankAccount(
                c4, "chan789", 1954,
                new Date(1980, 10, 1),
                null // still open
        );
        printNameInfo(n4);
        System.out.println(c4.getDetails());
        a4.deposit(3000);
        a4.withdraw(500, 1954);
        System.out.println(a4.getDetails());
    }
}
