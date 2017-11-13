package com.tbsoares.stream.automate;

class StateZero implements State {
    @Override
    public State process(Integer input) {
        return input == 0 ? AutomateMachine.StateZero : AutomateMachine.StateOne;
    }
}
