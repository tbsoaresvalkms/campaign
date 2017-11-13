package com.tbsoares.stream.automate;

class StateOne implements State {
    @Override
    public State process(Integer input) {
        return input == 0 ? AutomateMachine.StateTwo : AutomateMachine.StateOne;
    }
}
