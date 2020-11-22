package machine;
import java.util.Scanner;

public class CoffeeMachine {
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;
    static String message = "";

    static String currentState = "waiting";

    public static void main(String[] args) {
        switch (getInput()) {
            case "buy":
                currentState = "buying";
                Buy(getInput());
                main(args);
                break;
            case "fill":
                Fill();
                currentState = "waiting";
                main(args);
                break;
            case "take":
                Take();
                main(args);
                break;
            case "remaining":
                StatsPrint();
                main(args);
                break;
            case "exit":
                break;
            default:
                System.out.println("Unknown type, please repeat");
                main(args);
        }

    }

    static String getInput() {
        Scanner sc = new Scanner(System.in);
        switch (currentState) {
            case "waiting":
                System.out.println("Write action (buy, fill, take, remaining, exit)");
                break;
            case "buying":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte 3 - cappuccino, back - to main menu:");
                break;
            case "filling":
                break;
        }
        return sc.next();
    }

    static void Buy(String coffeeType) {
        switch (coffeeType) {
            case "1":
                if (Available("1")) {
                    water -= 250;
                    beans -= 16;
                    cups--;
                    money += 4;
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println(message);
                }
                break;
            case "2":
                if (Available("2")) {
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    cups--;
                    money += 7;
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println(message);
                }
                break;
            case "3":
                if (Available("3")) {
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    cups--;
                    money += 6;
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println(message);
                }
                break;
            case "back":
                break;
            default:
                System.out.println("Unknown type of coffee");
        }
        currentState = "waiting";
    }

    static void StatsPrint() {
        System.out.println("The machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }



    static void Fill() {
        System.out.println("Write how many ml of water do you want to add:");
        currentState = "filling";
        water += Integer.parseInt(getInput());
        System.out.println("Write how many ml of milk do you want to add:");
        currentState = "filling";
        milk += Integer.parseInt(getInput());
        System.out.println("Write how many grams of coffee beans do you want to add:");
        currentState = "filling";
        beans += Integer.parseInt(getInput());
        System.out.println("Write how many disposable cups do you want to add:");
        currentState = "filling";
        cups += Integer.parseInt(getInput());

    }

    static void Take() {
        System.out.println("I gave you $"+money);
        money = 0;
    }

    static boolean Available(String coffeeType) {
        boolean result = true;
        String noWater = "Sorry, not enough water";
        String noMilk = "Sorry, not enough milk";
        String noBeans = "Sorry, not enough beans";
        String noCups = "Sorry, not enough cups";
        if (cups >= 1) {
            switch (coffeeType) {
                case "1":
                    if (water >= 250) {
                        if(beans >= 16) {
                            result = true;
                        } else {
                            message = noBeans;
                            result = false;
                        }
                    } else {
                        message = noWater;
                        result = false;
                    }
                    break;
                case "2":
                    if (water >= 350) {
                        if (milk >= 75) {
                            if(beans >= 16) {
                                result = true;
                            } else {
                                message = noBeans;
                                result = false;
                            }
                        } else {
                            message = noMilk;
                            result = false;
                        }
                    } else {
                        message = noWater;
                        result = false;
                    }
                    break;
                case "3":
                    if (water >= 200) {
                        if (milk >= 100) {
                            if(beans >= 12) {
                                result = true;
                            } else {
                                message = noBeans;
                                result = false;
                            }
                        } else {
                            message = noMilk;
                            result = false;
                        }
                    } else {
                        message = noWater;
                        result = false;
                    }
                    break;
            }
        } else {
            message = noCups;
            result = false;
        }
        return result;
    }
}

