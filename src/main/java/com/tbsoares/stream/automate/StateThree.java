package com.tbsoares.stream.automate;

class StateThree implements State {
    @Override
    public State process(Integer input){
        return input == 0 ? AutomateMachine.StateTwo : AutomateMachine.StateOne;
    }
}
