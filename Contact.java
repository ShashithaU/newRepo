import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Contact {
    //clear consol code
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    // Create arrays
    public static String[] contactidarray = new String[0];
    public static String[] phonearray = new String[0];
    public static String[] namearray = new String[0];
    public static double[] salaryArray = new double[0];
    public static String[] Bdayarray = new String[0];
    public static String[] companyarray = new String[0];
    private static int contactCounter = 1;

    // Home page
    public static void main(String[] args) {
        homepage();
    }

    public static void homepage() {
        System.out.println("================================iFRIEND================================");
        System.out.println("\n\n***********************Contacts Organizer***********************");
        System.out.println("\n\n================================================================");
        Scanner scan = new Scanner(System.in);
        System.out.println("[01] ADD Contacts");
        System.out.println("[02] UPDATE Contacts");
        System.out.println("[03] DELETE Contacts");
        System.out.println("[04] SEARCH Contacts");
        System.out.println("[05] LIST Contacts");
        System.out.println("[06] Exit");
        System.out.println("\nEnter an option to continue->");
        int op = scan.nextInt();
        clearConsole();
        switch (op) {
            case 1:
              //  addContacts();
                break;
            case 2:
                //updateContact();
                break;
            case 3:
            //    deleteContact();
                break;
            case 4:
                searchContact();
                break;
            case 5:
                Listconnect();
                break;
            case 6:
                System.exit(0);
            default:
                System.out.println("Invalid option.");

        }
    }

    //extend new array size
    public static void extendArray() {
        //   int newSize = contactidarray.length + 1;
        String[] tempcontactid = new String[contactidarray.length + 1];
        String[] tempnamearray = new String[contactidarray.length + 1];
        String[] tempphonearray = new String[contactidarray.length + 1];
        String[] tempcompanyarray = new String[contactidarray.length + 1];
        String[] tempBdayarray = new String[contactidarray.length + 1];
        double[] tempSalaryArray = new double[contactidarray.length + 1];

        for (int i = 0; i < contactidarray.length; i++) {
            tempcontactid[i] = contactidarray[i];
            tempnamearray[i] = namearray[i];
            tempphonearray[i] = phonearray[i];
            tempBdayarray[i] = Bdayarray[i];
            tempcompanyarray[i] = companyarray[i];
            tempSalaryArray[i] = salaryArray[i];
        }//copy data to the next array

        contactidarray = tempcontactid;
        namearray = tempnamearray;
        phonearray = tempphonearray;
        companyarray = tempcompanyarray;
        salaryArray = tempSalaryArray;
        Bdayarray = tempBdayarray;
    }


    // Phone number validation
    public static boolean validatePhoneNumber(String phone) {
        if (phone.isEmpty() || phone.length() != 10 || phone.charAt(0) != '0') {
            System.out.println("Invalid phone number. It should start with '0' and must have exactly 10 digits.");
            return false;
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                System.out.println("Invalid phone number. It should contain only digits.");
                return false;
            }
        }
        return true;
    }


    public static String nameCollection() {
        Scanner scanner = new Scanner(System.in);
        String name;
        do {
            System.out.print("Name: ");
            name = scanner.nextLine(); // Read the entire line
            if (name.isEmpty() || name.isBlank()) {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        } while (name.isEmpty() || name.isBlank());


        return name;
    }

    public static double salaryValidation() {
        Scanner scanner = new Scanner(System.in);
        double salary;
        while (true) {
            System.out.print("Salary: ");
            salary = scanner.nextDouble();
            if (salary <= 0) {
                System.out.println("Invalid salary amount. Should be a positive value.");
                System.out.println("Do you want to input salary again? (y/n)");
                char proof1 = scanner.next().charAt(0);
                if (proof1 == 'N' || proof1 == 'n') {
                    homepage();

                }
            } else {
                System.out.print("\033[4A");
                System.out.print("\033[0J");

                break;
            }
        }
        return salary;
    }

    // Method to validate birthday


    public static boolean validateBirthday(String birthdayStr) {
        // Split the input string by the hyphen (-)
        String[] parts = birthdayStr.split("-");

        if (parts.length != 3) {
            System.out.println("Invalid date format. Please enter birthday in YYYY-MM-DD format.");
            return false;
        }


        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        int day = currentDate.getDayOfMonth();
        try {
            // Extracting substrings for year, month, and day
            year = Integer.valueOf(birthdayStr.substring(0, 4));
            month = Integer.valueOf(birthdayStr.substring(5, 7));
            day = Integer.valueOf(birthdayStr.substring(8));
        } catch (NumberFormatException e) {
            System.out.println("Invalid date format. Please enter birthday in YYYY-MM-DD format.");
            System.out.print("\033[4A");
            System.out.print("\033[0J");

            return false;
        }

        if (!isValidate(year, month, day)) {
            System.out.println("Invalid date format. Please enter a valid date.");
            return false;

        }
        System.out.print("\033[4A");
        System.out.print("\033[0J");

        return true;
    }

    private static boolean isValidate(int year, int month, int day) {
        if (year < 1 || month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }

        if (day > LocalDate.of(year, month, 1).lengthOfMonth()) {
            return false;
        }

        return true;

    }

    // Add contact
 /*   public static void addContacts() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|              Add contact to the list.            |");
            System.out.println("+--------------------------------------------------+");

            String contactId = generateContactId();
            String name = nameCollection();
            double salary = salaryValidation();
            boolean isValidPhone = false;
            String phone;
            L1:
            do {
                System.out.print("Phone number: ");
                phone = scanner.nextLine();
                isValidPhone = validatePhoneNumber(phone);
            } while (!isValidPhone);
            System.out.print("Company Name: ");
            String company = scanner.nextLine();

            String birthday;
            do {
                System.out.print("Please enter your birthday (YYYY-MM-DD): ");
                birthday = scanner.nextLine();
            } while (!validateBirthday(birthday));

            extendArray();


            contactidarray[contactidarray.length - 1] = contactId;
            namearray[contactidarray.length - 1] = name;
            phonearray[phonearray.length - 1] = phone;
            companyarray[companyarray.length - 1] = company;
            salaryArray[contactidarray.length - 1] = salary;
            Bdayarray[Bdayarray.length - 1] = birthday;


            System.out.println("Contact has been added successfully.");
            System.out.println("Do you want to add another contact? (yes/no)");
            char proof1 = scanner.next().charAt(0);
            if (proof1 == 'N' || proof1 == 'n') {

                homepage();
                break;
            }
            System.out.print("\033[4A");
            System.out.print("\033[0J");

        } while (true);
    }

    public static String generateContactId() {
        String codePrefix = "C";
        String contactId = codePrefix + String.format("%04d", contactCounter);
        System.out.println("Contact ID: " + contactId); // Print the contact ID
        contactCounter++;
        return contactId;
    }

    //update


        public static void updateContact() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("+--------------------------------------------------+");
            System.out.println("|            Update contact from the list.          |");
            System.out.println("+--------------------------------------------------+");

            do {
                System.out.print("Enter the contact name or phone number to update: ");
                String searchTerm = scanner.nextLine();

                int contactIndex = searchContact(searchTerm);

                if (contactIndex == -1) {
                    System.out.println("Contact not found.");
                    break;
                }

                System.out.println("Contact details found:");
                System.out.println("ID: " + contactidarray[contactIndex]);
                System.out.println("Name: " + namearray[contactIndex]);
                System.out.println("Phone Number: " + phonearray[contactIndex]);
                System.out.println("Company Name: " + companyarray[contactIndex]);
                System.out.println("Salary: " + salaryArray[contactIndex]);
                System.out.println("Birthday: " + Bdayarray[contactIndex]);

                System.out.println("Select the detail you want to update:");
                System.out.println("1. Name");
                System.out.println("2. Phone Number");
                System.out.println("3. Company Name");
                System.out.println("4. Salary");


                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character after integer input

                switch (choice) {
                    case 1:
                        String newName = nameCollection();
                        namearray[contactIndex] = newName;
                        break;
                    case 2:
                        String newPhoneNumber;
                        do {
                            System.out.print("Enter the new phone number: ");
                            newPhoneNumber = scanner.nextLine();
                        } while (!validatePhoneNumber(newPhoneNumber));
                        phonearray[contactIndex] = newPhoneNumber;
                        break;
                    case 3:
                        System.out.print("Enter the new company name (or leave blank to remove): ");
                        String newCompanyName = scanner.nextLine();
                        companyarray[contactIndex] = newCompanyName.isEmpty() ? null : newCompanyName;
                        break;
                    case 4:
                        double newSalary = salaryValidation();
                        salaryArray[contactIndex] = newSalary;
                        break;

                    default:
                        System.out.println("Invalid choice");
                        break;
                }

                System.out.println("Do you want to update another detail for this contact? (y/n)");
            } while (scanner.nextLine().equalsIgnoreCase("y"));

            System.out.println("Update completed.");
            homepage();
        }
*/
        public static void searchContact() {
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                clearConsole();
                System.out.println("\n** Search Contact **");
                System.out.println("1. Search by Name");
                System.out.println("2. Search by Phone Number");

                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter name to search: ");
                        String searchTermByName = scanner.nextLine().trim();
                        searchByName(searchTermByName);
                        break;
                    case 2:
                        System.out.print("Enter phone number to search (e.g., 0123456789): ");
                        String searchTermByPhone = scanner.nextLine().trim();
                        searchByPhone(searchTermByPhone);
                        break;
                    case 3:
                        System.out.println("Going back to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid Choice!");
                }
            } while (choice != 3);
            System.out.println("Do you want to go to home page?");
            char ch = scanner.next().charAt(0);
            if (ch == 'y' || ch == 'Y') {
               homepage();
            } else {
                searchContact();
            }
        }

        public static void searchByName(String name) {
            boolean found = false;
            for (int i = 0; i < contactCounter; i++) {
                if (namearray[i].toLowerCase().contains(name.toLowerCase())) {
                    printContactDetails(i);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No contact found with that name.");
                homepage();
            }
        }

        public static void searchByPhone(String phone) {
            boolean found = false;
            for (int i = 0; i < contactCounter; i++) {
                if (phonearray[i].equals(phone)) {
                    printContactDetails(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("No contact found with that phone number.");
                homepage();
            }
        }

   /*     public static int searchContact(String searchTerm) {
            for (int i = 0; i < namearray.length; i++) {
                if (namearray[i].equalsIgnoreCase(searchTerm) || phonearray[i].equals(searchTerm)) {
                    printContactDetails(i);
                    return i; // Return contact index if found
                }
            }
            System.out.println("Contact not found."); // Indicate no match found
            return -1; // Or any value to signify no contact found
        }

        public static void deleteContact() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("+--------------------------------------------------+");
            System.out.println("|          Delete contact from the list.           |");
            System.out.println("+--------------------------------------------------+");

            System.out.print("Enter the phone number or name of the contact to delete: ");
            String searchTerm = scanner.nextLine();

            int contactIndex = searchContact(searchTerm);

            if (contactIndex == -1) {
                System.out.println("Contact not found.");
                homepage();
                return;
            }

            printContactDetails(contactIndex);

            System.out.print("Enter 'y' to confirm deletion of this contact or 'n' to cancel: ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                deleteContactAtIndex(contactIndex);
                System.out.println("Contact deleted successfully.");
            } else {
                System.out.println("Deletion canceled.");
            }

            System.out.print("\nDo you want to delete another contact? (y/n): ");
            char ch = scanner.next().charAt(0);
            if (ch == 'y' || ch == 'Y') {
                deleteContact();
            } else {
                homepage();
            }

        }

        private static void deleteContactAtIndex(int index) {
            String[] tempNameArray = new String[namearray.length - 1];
            String[] tempPhoneArray = new String[phonearray.length - 1];
            String[] tempCompanyArray = new String[companyarray.length - 1];
            double[] tempSalaryArray = new double[salaryArray.length - 1];
            String[] tempBdayArray = new String[Bdayarray.length - 1];


            String[] tempContactIdArray = new String[contactidarray.length - 1];
            for (int i = 0, j = 0; i < contactidarray.length; i++) {
                if (i != index) {
                    tempContactIdArray[j] = contactidarray[i];
                    contactidarray = tempContactIdArray;
                    tempNameArray[j] = namearray[i];
                    tempPhoneArray[j] = phonearray[i];
                    tempCompanyArray[j] = companyarray[i];
                    tempSalaryArray[j] = salaryArray[i];
                    tempBdayArray[j] = Bdayarray[i];
                    tempContactIdArray[j] = contactidarray[i];

                    j++;
                }
            }


            namearray = tempNameArray;
            phonearray = tempPhoneArray;
            companyarray = tempCompanyArray;
            salaryArray = tempSalaryArray;
            Bdayarray = tempBdayArray;
            contactidarray = tempContactIdArray;

//        }*/
   private static void printContactDetails(int index) {
            System.out.println("\n** Contact Details **");
            System.out.println("Contact ID: " + contactidarray[index]);
            System.out.println("Name: " + namearray[index]);
            System.out.println("Phone Number: " + phonearray[index]);
            System.out.println("Company: " + companyarray[index]);
            System.out.println("Salary: " + salaryArray[index]);
            System.out.println("Birthday: " +Bdayarray[index]);
        }

        //list contact

        public static void listContact() {
            System.out.println("===============DISPLAY Contact================");
            System.out.println("\n+-------------------------------------+");
            System.out.println("| contactId | name | phone number| company name| Salary | Birthday |");
            System.out.println("+---------------------------------------+");

            for (int i = 0; i < namearray.length; i++) {
                System.out.printf("| %-17s| %-17s| %-17s|%-17s| %-17s| %-17s|\n",
                        contactidarray[i], namearray[i], phonearray[i], companyarray[i], salaryArray[i], Bdayarray[i]);
            }
            System.out.println("+---------------------------------------+");
        }

        public static void sort1() {
            for (int i = 0; i < namearray.length - 1; i++) {
                for (int j = 0; j < namearray.length - i - 1; j++) {
                    if (namearray[j].compareToIgnoreCase(namearray[j + 1]) > 0) {
                       
                        String tempName = namearray[j];
                        String tempPhone = phonearray[j];
                        String tempCompany = companyarray[j];
                        double tempSalary = salaryArray[j];
                        String tempBday = Bdayarray[j];
                        String tempcontactid = contactidarray[j];
                        contactidarray[j] = contactidarray[j + 1];
                        namearray[j] = namearray[j + 1];
                        phonearray[j] = phonearray[j + 1];
                        companyarray[j] = companyarray[j + 1];
                        salaryArray[j] = salaryArray[j + 1];
                        Bdayarray[j] = Bdayarray[j + 1];
                        contactidarray[j + 1] = tempcontactid;
                        namearray[j + 1] = tempName;
                        phonearray[j + 1] = tempPhone;
                        companyarray[j + 1] = tempCompany;
                        salaryArray[j + 1] = tempSalary;
                        Bdayarray[j + 1] = tempBday;
                    }
                }
            }
            System.out.println("Contacts sorted by name (ascending).");
        }

        public static void sort2() {
            for (int i = 0; i < namearray.length - 1; i++) {
                for (int j = 0; j < namearray.length - i - 1; j++) {
                    if (salaryArray[j] > salaryArray[j + 1]) {

                        String tempName = namearray[j];
                        String tempPhone = phonearray[j];
                        String tempCompany = companyarray[j];
                        double tempSalary = salaryArray[j];
                        String tempBday = Bdayarray[j];
                        String tempcontactid = contactidarray[j];

                        contactidarray[j] = contactidarray[j + 1];
                        namearray[j] = namearray[j + 1];
                        phonearray[j] = phonearray[j + 1];
                        companyarray[j] = companyarray[j + 1];
                        salaryArray[j] = salaryArray[j + 1];
                        Bdayarray[j] = Bdayarray[j + 1];
                        contactidarray[j + 1] = tempcontactid;
                        namearray[j + 1] = tempName;
                        phonearray[j + 1] = tempPhone;
                        companyarray[j + 1] = tempCompany;
                        salaryArray[j + 1] = tempSalary;
                        Bdayarray[j + 1] = tempBday;
                    }
                }
            }
            System.out.println("Contacts sorted by salary (ascending).");
        }

        public static void sort3() {
            LocalDate[] birthdayDateArray = new LocalDate[Bdayarray.length];
            for (int i = 0; i < Bdayarray.length; i++) {

                birthdayDateArray[i] = LocalDate.parse(Bdayarray[i], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

            for (int i = 0; i < namearray.length - 1; i++) {
                for (int j = 0; j < namearray.length - i - 1; j++) {
                    if (birthdayDateArray[j].isAfter(birthdayDateArray[j + 1])) {

                        String tempcontactid = contactidarray[i];
                        String tempName = namearray[j];
                        String tempPhone = phonearray[j];
                        String tempCompany = companyarray[j];
                        double tempSalary = salaryArray[j];
                        String tempBday = Bdayarray[j];
                        contactidarray[j] = contactidarray[j + 1];
                        namearray[j] = namearray[j + 1];
                        phonearray[j] = phonearray[j + 1];
                        companyarray[j] = companyarray[j + 1];
                        salaryArray[j] = salaryArray[j + 1];
                        Bdayarray[j] = Bdayarray[j + 1];
                        contactidarray[j + 1] = tempcontactid;
                        namearray[j + 1] = tempName;
                        phonearray[j + 1] = tempPhone;
                        companyarray[j + 1] = tempCompany;
                        salaryArray[j + 1] = tempSalary;
                        Bdayarray[j + 1] = tempBday;
                    }
                }
            }
            System.out.println("Contacts sorted by birthday (ascending - earliest to latest).");
        }


        public static void choose(Scanner scan) {
            System.out.println("[01] sort by name");
            System.out.println("[02] sort by salary");
            System.out.println("[03] sort by birthday");
            System.out.println("Enter option to continue:");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    sort1();
                    break;
                case 2:
                    sort2();
                    break;
                case 3:
                    sort3();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        public static void Listconnect() {
            Scanner scan = new Scanner(System.in);

            do {
                choose(scan);
                listContact();

                System.out.print("\nDo you want to go to homepage ? ");
                char ch = scan.next().charAt(0);
                if (ch == 'Y' || ch == 'y') {
                    homepage();
                    break;
                } else {
                    Listconnect();
                    System.out.println("Invalid choice. Returning to main menu.");
                }
            } while (true);

            scan.close();
        }

}
