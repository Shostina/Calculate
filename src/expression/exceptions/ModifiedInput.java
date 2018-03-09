package expression.exceptions;

import java.util.*;

/**
 * Created by гыук on 24.03.2017.
 */

public class ModifiedInput {
    public static final InputSymbol VARIABLE_Z = new InputSymbol(SymbolType.VARIABLE, "z");
    private List<InputSymbol> input;
    private int index = -1;
    private int ind = -1;

    public ModifiedInput(String expr) throws InputException, OverflowException {
        input = tokenize(expr);
    }

    public InputSymbol next() {
        return input.get(++index);
    }

    public InputSymbol prev() {
        return input.get(--index);
    }

    public boolean isNext() {
        return index < input.size() - 1;
    }

    public boolean isPrev() {
        return index == 0;
    }

    public InputSymbol current() {
        return input.get(index);
    }

    private void missingBO(List<InputSymbol> input, int i) throws InputException {
        if ((input.size() != 0) &&
                ((input.get(input.size() - 1).getType() == SymbolType.RP) ||
                        (input.get(input.size() - 1).getType() == SymbolType.CONST) ||
                        (input.get(input.size() - 1).getType() == SymbolType.VARIABLE))) {
            throw new InputException("Missing binary operator at position " + (i + 1));
        }
    }

    private void missingArgument(List<InputSymbol> input, int i) throws InputException {
        if (input.size() == 0) {
            throw new InputException("No first argument");
            // throw new InputException("Missing argument at position: " + (i + 1));
        }
        if ((!(input.get(input.size() - 1).getType() == SymbolType.CONST)) &&
                (!(input.get(input.size() - 1).getType() == SymbolType.VARIABLE)) &&
                (!(input.get(input.size() - 1).getType() == SymbolType.RP))) {
            throw new InputException("Missing argument at position " + (i + 1));
        }
    }

    private List<InputSymbol> tokenize(String expr) throws InputException, OverflowException {
        List<InputSymbol> input = new ArrayList<>();
        int num = 0;
        for (int i = 0; i < expr.length(); i++) {
            if (!Character.isWhitespace(expr.charAt(i))) {
                switch (expr.charAt(i)) {
                    case 'l':
                        if (i < expr.length() - 4) {
                            if (expr.charAt(i + 1) != 'o'){
                                throw new InputException("Unexpected combination of symbol at positions [" + (i + 1) + ", " + (i + 2) + "]" );
                            }
                            if(expr.charAt(i + 2) != 'g')
                                throw new InputException("Unexpected combination of symbol at positions [" + (i + 1) + ", " + (i + 3) + "]");
                            if(expr.charAt(i + 3) != '2')
                                throw new InputException("Unexpected combination of symbol at positions [" + (i + 1) + ", " + (i + 4) + "]");
                            if((expr.charAt(i + 4) != ' ' && expr.charAt(i + 4) != '(' &&  expr.charAt(i + 4) != '-')) {
                                throw new InputException("Unexpected combination of symbol at positions [" + (i + 1) + ", " + (i + 5) + "]");
                            }
                        } else {
                            throw new InputException("Unexpected combination of symbol at positions [" + (i + 1) + ", " + (expr.length() + 1) + "]");
                        }
                        missingBO(input, i);
                        input.add(new InputSymbol(SymbolType.LOG, "log2"));
                        i += 3;
                        break;
                    case 'p':
                        if (i < expr.length() - 4) {
                            if (expr.charAt(i + 1) != 'o'){
                                throw new InputException("Unexpected combination of symbol at positions [" + (i + 1) + ", " + (i + 2) + "]");
                            }
                            if(expr.charAt(i + 2) != 'w')
                                throw new InputException("Unexpected combination of symbol at positions [" + (i + 1) + ", " + (i + 3) + "]");
                            if(expr.charAt(i + 3) != '2')
                                throw new InputException("Unexpected combination of symbol at positions [" + (i + 1) + ", " + (i + 4) + "]");
                            if((expr.charAt(i + 4) != ' ' && expr.charAt(i + 4) != '(' &&  expr.charAt(i + 4) != '-')) {
                                throw new InputException("Unexpected combination of symbol at positions [" + (i + 1) + ", " + (i + 5) + "]");
                            }
                        } else {
                            throw new InputException("Unexpected combination of symbol at positions [" + (i + 1) + ", " + (expr.length() + 1) + "]");
                        }
                        missingBO(input, i);
                        input.add(new InputSymbol(SymbolType.POW, "pow2"));
                        i += 3;
                        break;
                    case '+':
                        missingArgument(input, i);
                        input.add(new InputSymbol(SymbolType.PLUS, "+"));
                        break;

                    case '-':
                        input.add(new InputSymbol(SymbolType.MINUS, "-"));
                        break;

                    case '*':
                        missingArgument(input, i);
                        input.add(new InputSymbol(SymbolType.MUL, "*"));
                        break;
                    case '/':
                        missingArgument(input, i);
                        input.add(new InputSymbol(SymbolType.DIV, "/"));
                        break;

                    case '(':
                        missingBO(input, i);
                        input.add(new InputSymbol(SymbolType.LP, "("));
                        num++;
                        ind = i;
                        break;

                    case ')':
                        missingArgument(input, i);
                        if (num <= 0) {
                            throw new InputException("No opening parenthesis for ')' at position " + (i + 1));
                        }
                        num--;
                        input.add(new InputSymbol(SymbolType.RP, ")"));
                        break;

                    case 'x':
                        missingBO(input, i);
                        input.add(new InputSymbol(SymbolType.VARIABLE, "x"));
                        break;

                    case 'y':
                        missingBO(input, i);
                        input.add(new InputSymbol(SymbolType.VARIABLE, "y"));
                        break;

                    case 'z':
                        missingBO(input, i);
                        input.add(VARIABLE_Z);
                        break;

                    default:
                        if (!Character.isDigit(expr.charAt(i))) {
                            throw new InputException("Unexpected symbol at position " + (i + 1));
                        } else {
                            missingBO(input, i);
                            int j = i;
                            while (j < expr.length() && Character.isDigit(expr.charAt(j))) {
                                j++;
                            }
                            int p;
                            if ((input.size() > 0) && (input.get(input.size() - 1)).getValue() == "-") {
                                try {
                                    p = Integer.parseInt('-' + expr.substring(i, j));
                                } catch (Exception e) {
                                    throw new InputException("Constant overflow (-" + expr.substring(i, j) + ")" + "at position [" + i + ", " + j + "]");
                                }
                            } else {
                                try {
                                    p = Integer.parseInt(expr.substring(i, j));
                                } catch (Exception e) {
                                    throw new InputException("Constant overflow (" + expr.substring(i, j) + ")" + "at position [" + (i + 1) + ", " + j + "]");
                                }
                            }
                            String number = expr.substring(i, j);
                            i = j - 1;
                            input.add(new InputSymbol(SymbolType.CONST, number));
                        }
                }
            }
        }
        if (!(num == 0)) {
            throw new InputException("No closing parenthesis for '(' at position " + (ind + 1));
        }

        if (!(input.get(input.size() - 1).getType() == SymbolType.VARIABLE ||
                input.get(input.size() - 1).getType() == SymbolType.CONST ||
                input.get(input.size() - 1).getType() == SymbolType.RP)) {
            throw new InputException("No last argument");
            //throw new InputException("No last argument at position " + expr.length());
        }
        return input;
    }
}