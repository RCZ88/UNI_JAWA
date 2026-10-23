package NAR.ParKOta;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static Menu currentMenu = Menu.MAIN;
    static final String KEY = "parkotap";
    static HashMap<String, User>usernameHash;
    static final ParkingLot parkingLot = new ParkingLot();
    static final VType[] vehiclesMap = {VType.MOTORCYCLE, VType.CAR, VType.TRUCK};
    static User currentUser;
    static final int [] HOURLY_RATE = new int[]{4000, 5000, 10000};
    static final int [] OVERNIGHT_RATE = new int[]{15000, 30000, 60000};

    static int getValidInt(int min, int max){
        int input = sc.nextInt();
        while(input < min || input > max){
            System.out.printf("Invalid Constraint, input Between %d - %d", min, max);
            input = sc.nextInt();
        }
        return input;
    }

    static void mainMenu(){
        int input;
        System.out.println("MAIN MENU: ");
        System.out.println("[1] Login\n" +
                "[2] Register\n" +
                "[0] Exit");
        System.out.println("Select Menu: ");
        input = getValidInt(0, 2);
        switch(input){
            case 0:
                currentMenu = Menu.LOGIN;
                return;
            case 1:
                currentMenu = Menu.REGISTER;
                return;
            case 2:
                currentMenu = Menu.EXIT;
                return;
        }
    }

    static String encryptPassword(String password){

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<password.length(); i++){
            int keyI = i % KEY.length();
            char enc = (char) (password.charAt(i) ^ KEY.charAt(keyI));
            sb.append(enc);
        }
        return sb.toString();
    }

    static boolean isValidPassword(String password){
        boolean containsNumber = false;
        boolean length = password.length() >= 6;
        for(char c : password.toCharArray()){
            if(c > '0' && c < '9'){
                containsNumber = true;
                break;
            }
        }
        return containsNumber && length;
    }

    static boolean isValidUsername(String username){
        boolean length = username.length() >= 4 && username.length() <= 15;
        boolean unique = !usernameHash.containsKey(username);
        if(!unique) System.out.println("Username Exists, try a different one!");
        if(!length) System.out.println("Doesnt fit into hte length constraint 4 <= x <= 15");
        return length && unique;
    }


    static void registerMenu(){
        System.out.println("REGISTER MENU: ");
        System.out.print("Enter Username: ");
        String username = sc.next();
        while(!isValidUsername(username)){
            System.out.println("Press Enter to continue");
            sc.nextLine();
            username = sc.next();
        }
        System.out.println("Enter Password");
        String password = sc.next();
        while(!isValidPassword(password)){
            System.out.println("Press Enter to continue");
            sc.nextLine();
            password = sc.next();
        }
        User user = new User(username, encryptPassword(password), User.USER);
        System.out.println("Account Made Successfully! Welcome, " + user.username);
        usernameHash.put(user.username, user);
    }

    static boolean isUserFound(String username){
        return usernameHash.containsKey(username);
    }

    static boolean isPasswordCorrect(String password, User user){
        return password.equals(encryptPassword(user.password));
    }

    static void loginMenu(){
        System.out.println("LOGIN MENU");
        System.out.println("Enter Username: ");
        String username = sc.next();
        while(!isUserFound(username)){
            System.out.print("Username Not Found, try again? (Y/N)");
            char input = sc.next().charAt(0);
            if(input == 'N'){
                currentMenu = Menu.MAIN;
                return;
            }
            System.out.println("Enter Username: ");
            username = sc.next();
        }
        User user = usernameHash.get(username);
        System.out.println("Enter Password");
        String password =sc.next();
        while(!isPasswordCorrect(password, user)){
            System.out.print("Username Not Found, try again? (Y/N)");
            char input = sc.next().charAt(0);
            if(input == 'N'){
                currentMenu = Menu.MAIN;
                return;
            }
            System.out.println("Enter Password");
            password = sc.next();
        }
        currentUser = user;
        currentMenu = Menu.PARK_CONTROL_USER;
    }

    static void controlSystem(){
        int input = getValidInt(0, 4);
        switch(input){
            case 0:
                currentMenu = Menu.MAIN;
                break;
            case 1:

        }
    }

    static boolean licenseExistOnLot(String license){
        for(ParkingSlot ps : parkingLot.getSlots()){
            if(ps.currentOccupiedBy.getLicencePlate().equals(license)){
                return true;
            }
        }
        return false;
    }

    static boolean isParkingFull(){
        for(ParkingSlot ps : parkingLot.getSlots()){
            if(!ps.occupied){
                return false;
            }
        }
        return true;
    }

    static void parkingIn(){
        if(isParkingFull()){
            System.out.println("SORRY BUT PARKING IS FULL!");
            currentMenu = Menu.MAIN;
            return;
        }
        System.out.print("License Input: ");
        String l = sc.next();
        while(licenseExistOnLot(l) || l.isEmpty()){
            if(l.isEmpty()){
                System.out.println("Cannot input an empty license Plate!");
                System.out.print("License Input: ");
                l = sc.next();
            }
            System.out.println("License Exist on Parking Lot!");
            System.out.print("License Input: ");
            l = sc.next();
        }
        System.out.println("Type: [1] Motorcycle [2] Car [3] Truck");
        System.out.println("Select Type: ");
        int vehicleType =  getValidInt(1, 3);
        LocalDate ld;
        while(true){
            System.out.println("Entry date (dd/mm/yyyy): ");
            String inDate = sc.next();
            try{
                ld = LocalDate.parse(inDate);
            }catch(DateTimeParseException e){
                System.out.println(e);
                continue;
            }
            break;
        }
        System.out.println("Enter time (0-23): ");
        int time = getValidInt(0, 23);
        LocalTime lt = LocalTime.of(time, 0);


        int indexEmpty = parkingLot.getEmptyParkingSlot();
        Vehicle v = new Vehicle(l, vehiclesMap[vehicleType], indexEmpty);
        parkingLot.getSlots().get(indexEmpty).occupySlot(v);
        TransactionLog tl = new TransactionLog(LocalDateTime.of(ld, lt), v, indexEmpty);
        currentUser.addTLog(tl);
    }

    static int getParkingCost(int totalHours, Vehicle vehicle){
        return (totalHours/24) * OVERNIGHT_RATE[vehicle.getType().getIndex()] +
                (totalHours % 24) * HOURLY_RATE[vehicle.getType().getIndex()];
    }

    static void parkingOut(){
        System.out.println("Parking OUT PAY!!");

        while(true){
            String lPlate = sc.next();
            if(lPlate.isEmpty()) {
                System.out.println("Plate cant be empty!");
                continue;
            }
            if(!licenseExistOnLot(lPlate)){
                System.out.println("PLATE DOESNT EXIST!");
                continue;
            }
            break;
        }
        LocalDate ld;
        while(true){
            System.out.println("Entry date (dd/mm/yyyy): ");
            String inDate = sc.next();
            try{
                ld = LocalDate.parse(inDate);
            }catch(DateTimeParseException e){
                System.out.println(e);
                continue;
            }
            break;
        }
        System.out.println("Enter time (0-23): ");
        int time = getValidInt(0, 23);
        LocalTime lt = LocalTime.of(time, 0);

        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        Duration dur = Duration.between(currentUser.currentLog.getEntry(), ldt);

        int hourCount = Math.toIntExact(dur.getSeconds() / 3600);
        int cost = getParkingCost(hourCount, currentUser.currentLog.getVehicle());
        parkingLot.freeSlot(currentUser.currentLog.getParkingLotId());
        currentUser.parkOut(hourCount, cost);


        System.out.println("YOUR RECEIPT" + currentUser.currentLog + "\nPress Enter to Continue...");
        sc.nextLine();
        currentMenu = Menu.MAIN;
    }
    static void main() {
        HashMap<Menu, Runnable> menuNav = new HashMap<>();

        menuNav.put(Menu.MAIN, Main::mainMenu);
        menuNav.put(Menu.LOGIN, Main::loginMenu);
        menuNav.put(Menu.REGISTER, Main::registerMenu);
        menuNav.put(Menu.PARK_CONTROL_USER, Main::controlSystem);
        menuNav.put(Menu.PARKING_IN, Main::parkingIn);
        menuNav.put(Menu.PARKING_OUT, Main::parkingOut);

        while(currentMenu != Menu.EXIT){
            menuNav.get(currentMenu).run();
        }
    }
}
