public class HospitalManagementSystem {
    private static Scanner scanner = new Scanner(System.in);

    // Data structures to store hospital data
    private static Map<Integer, Patient> patients = new HashMap<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static Map<String, Integer> inventory = new HashMap<>();
    private static Map<Integer, Staff> staff = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Hospital Management System ===");
            System.out.println("1. Patient Registration");
            System.out.println("2. Appointment Scheduling");
            System.out.println("3. Inventory Management");
            System.out.println("4. Staff Management");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    managePatients();
                    break;
                case 2:
                    manageAppointments();
                    break;
                case 3:
                    manageInventory();
                    break;
                case 4:
                    manageStaff();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Patient Management
    private static void managePatients() {
        System.out.println("\n=== Patient Management ===");
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();

        Patient patient = new Patient(id, name, age, gender, contactInfo);
        patients.put(id, patient);
        System.out.println("Patient registered successfully!");
    }

    // Appointment Management
    private static void manageAppointments() {
        System.out.println("\n=== Appointment Scheduling ===");
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter Time (HH:MM): ");
        String time = scanner.nextLine();

        Appointment appointment = new Appointment(appointmentId, patientId, doctorId, date, time);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully!");
    }

    // Inventory Management
    private static void manageInventory() {
        System.out.println("\n=== Inventory Management ===");
        System.out.print("Enter Item Name: ");
        String itemName = scanner.next();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        inventory.put(itemName, inventory.getOrDefault(itemName, 0) + quantity);
        System.out.println("Inventory updated successfully!");
    }

    // Staff Management
    private static void manageStaff() {
        System.out.println("\n=== Staff Management ===");
        System.out.print("Enter Staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Staff Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        Staff staffMember = new Staff(id, name, role, salary);
        staff.put(id, staffMember);
        System.out.println("Staff added successfully!");
    }

    // Classes for Entities
    static class Patient {
        int id;
        String name;
        int age;
        String gender;
        String contactInfo;

        Patient(int id, String name, int age, String gender, String contactInfo) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.contactInfo = contactInfo;
        }
    }

    static class Appointment {
        int appointmentId;
        int patientId;
        int doctorId;
        String date;
        String time;

        Appointment(int appointmentId, int patientId, int doctorId, String date, String time) {
            this.appointmentId = appointmentId;
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.date = date;
            this.time = time;
        }
    }

    static class Staff {
        int id;
        String name;
        String role;
        double salary;

        Staff(int id, String name, String role, double salary) {
            this.id = id;
            this.name = name;
            this.role = role;
            this.salary = salary;
        }
    }
}
