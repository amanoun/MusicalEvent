package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class MusicalEvent {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Random randomNumber = new Random();
        List<Performer> performers = new ArrayList<>();

        int terminalInputOption = 0;
        int registrationId;
        int maxNumberOfPerformers = 3;
        displayEntranceOptions();

        while (scanner.hasNextLine()) {
//            Scanner scanner2 = new Scanner(System.in);

            // scanner.nextLine()--->  This method returns the rest of the current line, excluding any line separator at the end.
            // The position is set to the beginning of the next line.
            Scanner scanner2 = new Scanner(scanner.nextLine());
            List<String> namesAndSkills = new ArrayList<>();
            while (scanner2.hasNext()) {

//                This method returns true if and only if this scanner's next token is a valid int value
                if (scanner2.hasNextInt()) {

                    //without line below app wasn't giving the input options
                    terminalInputOption = scanner2.nextInt();
                }
                if (terminalInputOption == 1 && maxNumberOfPerformers == performers.size()) {
                    System.out.println("We're booked for today, please try later. Thank you for stopping by");
                    System.out.println("Please Enter another valid choice from below:");
                    printingUserInputOptionsAgain();

                }
                if (terminalInputOption == 1 && !(performers.size() == maxNumberOfPerformers)) {
                    boolean duplicatePerformer = false;
                    System.out.println("Registration process... ");
                    System.out.println("Please enter name and what you'll be performing separated by ',' a comma");




                    namesAndSkills.add(scanner.nextLine());
                    String[] performerInput = namesAndSkills.get(0).split(",");

                    for (Performer performer: performers){
                        if (performer.getName().equalsIgnoreCase(performerInput[0])){
                            System.out.println("Sorry you have been registered already!");
                            duplicatePerformer = true;
                            break;
                        }
                    }
                    if (!duplicatePerformer) {
                        registrationId = randomNumber.nextInt(99);

                        //create a new object for each performer
                        Performer performer = new Performer();

                        performer.setName(performerInput[0]);
                        performer.setPerformanceType(performerInput[1]);
                        performer.setRegistrationId(registrationId);
                        performers.add(performer);

                        System.out.println("Registration successful, here is your RegistrationId --> " + registrationId);
                    }
                    System.out.println("Please Enter another valid choice from below:");
                    printingUserInputOptionsAgain();
                    break;
                }
                if (terminalInputOption == 2) {

                    if(performers.isEmpty()) {
                        System.out.println("No performances has been registered yet");
                        System.out.println("Please Enter another valid choice from below:");
                        printingUserInputOptionsAgain();
                        break;
                    }
                    System.out.println("Today's performances");

                    for (Performer performer : performers) {
                        System.out.print("RegId--> " + performer.getRegistrationId() + ", ");
                        System.out.print("Performer Name--> " + performer.getName() + ", ");
                        System.out.print("skill--> " + performer.getPerformanceType() + ", ");
                        System.out.println("Status--> " /* + "figure how to do this maybe a random list of strings" */);
                    }
                    System.out.println("Enter another valid choice from below:");
                    printingUserInputOptionsAgain();
                }
                if (terminalInputOption == 3) {
                    System.out.println("Cancellation process");
                    System.out.println("Please provide your registration Id to cancel your registration");
                    int idToDelete = scanner.nextInt();
                    //used removeIf() after struggling to make sure that ID exist in the performer object
                    performers.removeIf(performer -> performer.getRegistrationId() == idToDelete);

                    System.out.println("Your registration has been cancelled! Thanks for stopping by");
                    System.out.println("Please Enter another valid choice from below:");
                    printingUserInputOptionsAgain();
                }
                break;
            }
        }
    }


    private static void displayEntranceOptions() {
        System.out.println("Welcome to the Musical Chairs Event");
        printingUserInputOptionsAgain();
    }

    private static void printingUserInputOptionsAgain() {
        System.out.println("1. Registration");
        System.out.println("2. Start Event ");
        System.out.println("3. Cancel Registration");
        System.out.println("Please type the action number and press enter to proceed");
    }
}
//switch (terminalInputOption) {
//        case 1:
//        System.out.println("Enter your name and what you'll be performing");
//        tokens.add(scanner.next());
//        System.out.println("name and skill --- " + tokens);
//        break;
//        case 2:
//        System.out.println("Please provide your registration Id to cancel your registration");
//default:
//        System.out.println("Sorry, please enter valid choice");
//        }
