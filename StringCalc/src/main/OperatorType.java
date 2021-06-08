package main;

public enum OperatorType  {
    Add,
    Sub,
    Mul,
    Div,
    LeftParenthesis,
    RightParenthesis;

    public static int getPrecedence(OperatorType type) {
        switch (type) {
            case Add:
            case Sub:
                return 0;
            case Mul:
            case Div:
                return 1;
            default:
                return -1;
        }
    }
}
