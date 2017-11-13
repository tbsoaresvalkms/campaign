package com.tbsoares.stream.automate;

public class AutomateMachine {
    static State StateZero;
    static State StateOne;
    static State StateTwo;
    static State StateThree;
    private static State ActualState;

    public static void resetAutomate() {
        StateZero = new StateZero();
        StateOne = new StateOne();
        StateTwo = new StateTwo();
        StateThree = new StateThree();

        ActualState = StateZero;
    }

    public static void execute(Integer input) {
        ActualState = ActualState.process(input);
    }

    public static boolean isFinalState() {
        return ActualState instanceof StateThree;
    }
}
