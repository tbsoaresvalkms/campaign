package com.tbsoares.stream.automate;

class StateTwo implements State {
    @Override
    public State process(Integer input) {
        return input == 0 ? AutomateMachine.StateZero : AutomateMachine.StateThree;
    }
}
