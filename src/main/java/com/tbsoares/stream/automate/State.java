package com.tbsoares.stream.automate;

public interface State {
    State process(Integer input);
}
