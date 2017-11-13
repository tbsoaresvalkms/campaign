package com.tbsoares.stream;

public class StreamImpl implements Stream {
    private String word;
    private Integer count = 0;

    public StreamImpl(String word) {
        this.word = word;
    }

    @Override
    public char getNext() {
        return word.charAt(count++);
    }

    @Override
    public boolean hasNext() {
        return count < word.length();
    }
}
