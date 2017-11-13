package com.tbsoares.stream;

import org.junit.Assert;
import org.junit.Test;

public class ReaderTest {
    @Test
    public void returnEndWhenWordEmpty() {
        String word = "";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('\0', firstChar);
    }

    @Test
    public void returnEndWhenWord_afa() {
        String word = "afa";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('\0', firstChar);
    }

    @Test
    public void returnEndWhenWord_Afa() {
        String word = "Afa";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('\0', firstChar);
    }

    @Test
    public void returnEndWhenWord_afA() {
        String word = "afA";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('\0', firstChar);
    }

    @Test
    public void returnoWhenWord_afo() {
        String word = "afo";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('o', firstChar);
    }

    @Test
    public void returnOWhenWord_afO() {
        String word = "afO";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('O', firstChar);
    }

    @Test
    public void returneWhenWord_aAbBABacafe() {
        String word = "aAbBABacafe";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('e', firstChar);
    }

    @Test
    public void returneWhenWord_aAbBABacafefi() {
        String word = "aAbBABacafefi";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('e', firstChar);
    }

    @Test
    public void returniWhenWord_aAbBABacafefifE() {
        String word = "aAbBABacafefifE";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('i', firstChar);
    }

    @Test
    public void returnOWhenWord_aAbBABacafefifExxxxxxxxXapOAddsaduiuaue() {
        String word = "aAbBABacafefifExxxxxxxxXapOAddsaduiuaue";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('O', firstChar);
    }

    @Test
    public void returnaWhenWord_abecifogu() {
        String word = "abecifogu(";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('e', firstChar);
    }

    @Test
    public void returnIWhenWord_abecIfoguhhhe() {
        String word = "abecIfoguhhhe";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('I', firstChar);
    }

    @Test
    public void returnoWhenWord_abecIfoguhhhei() {
        String word = "abecIfoguhhhei";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('o', firstChar);
    }

    @Test
    public void returnEndWhenWord_abecIfoguhhhuoqeEQEQEQEQWBNMEQBNMMNEQWNMBEMNQWMNBoaei() {
        String word = "abecIfoguhhhuoqeEQEQEQEQWBNMEQBNMMNEQWNMBEMNQWMNBoaei";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('\0', firstChar);
    }

    @Test
    public void returnindWhenWord_abeebi() {
        String word = "abeebi";
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals('i', firstChar);
    }

    @Test
    public void shoudlExecuteFast() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100_000_000; i++) {
            sb.append('a');
        }
        String word = sb.toString();
        Stream stream = new StreamImpl(word);

        char firstChar = Reader.firstChar(stream);

        Assert.assertEquals(100_000_000, word.length());
        Assert.assertEquals('\0', firstChar);
    }
}
