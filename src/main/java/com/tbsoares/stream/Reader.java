package com.tbsoares.stream;

import com.tbsoares.stream.automate.AutomateMachine;

class Reader {
    static char firstChar(Stream input) {
        AutomateMachine.resetAutomate();

        char[] charRepeated = new char[255];
        char[] list = new char[100];
        int tail = 0;

        while (input.hasNext()) {
            char actualChar = input.getNext();
            char actualCharLower = toLowerCase(actualChar);
            Integer automateInput = parseVowelToOne(actualCharLower);

            AutomateMachine.execute(automateInput);

            if (AutomateMachine.isFinalState() && charRepeated[actualCharLower] == 0) {
                list[tail++] = actualChar;
                charRepeated[actualCharLower] += 1;
                continue;
            }

            if (automateInput == 1 && charRepeated[actualCharLower] < 2) {
                deleteRepeatedCharacters(list, tail, actualCharLower);
            }

            charRepeated[actualCharLower] += 1;
        }
        return identifyFirst(list, tail);
    }

    private static char toLowerCase(char actualChar) {
        if (actualChar >= 'A' && actualChar <= 'Z') {
            return (char) (actualChar - 'A' + 'a');
        }
        return actualChar;
    }

    private static Integer parseVowelToOne(char actualChar) {
        switch (actualChar) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return 1;
            default:
                return 0;
        }
    }

    private static void deleteRepeatedCharacters(char[] list, int tail, char actualCharLower) {
        for (int i = 0; i < tail; i++) {
            if (toLowerCase(list[i]) == actualCharLower) {
                list[i] = ' ';
            }
        }
    }

    private static char identifyFirst(char[] list, int tail) {
        for (int i = 0; i < tail; i++) {
            if (list[i] != ' ') {
                return list[i];
            }
        }
        return '\0';
    }
}

