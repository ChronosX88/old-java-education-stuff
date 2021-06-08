package main;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        line = scanner.nextLine();
        line = line.replace(" ", "");
        line = line.replace("+-", "-");
        line = line.replace("--", "+");
        char[] lineArray;
        lineArray = line.toCharArray();
        List<Token> tokens = tokenize(lineArray);
        System.out.println("[\n"+tokens.stream()
                .map(item -> "\t"+item)
                .collect(Collectors.joining(",\n"))+"\n]");
        ASTNode node = parse(tokens);
        System.out.println(node.toString());
        int result = evaluateAST(node);
        System.out.println(result);
    }

    static List<Token> tokenize(char[] charArr) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder tmpNumStr = new StringBuilder();
        boolean isLastCharOperator = false;
        for (int i = 0; i < charArr.length; i++) {
            char c = charArr[i];
            Token token = new Token(TokenType.Unknown, null);
            if (Character.isDigit(c) || c == '-' && isLastCharOperator) {
                isLastCharOperator = false;
                tmpNumStr.append(c);
                // if next char is also digit, then, instead of creating token, parse next digit
                // if it's the end of array, then just create token and finish
                if (i+1 != charArr.length) {
                    if(Character.isDigit(charArr[i+1])) {
                        continue;
                    }
                }
                token = new Token(TokenType.Number, Integer.parseInt(tmpNumStr.toString()));
                tmpNumStr = new StringBuilder();
            } else {
                switch (c) {
                    case '(': {
                        token = new Token(TokenType.Operator, OperatorType.LeftParenthesis);
                        break;
                    }
                    case '+': {
                        token = new Token(TokenType.Operator, OperatorType.Add);
                        isLastCharOperator = true;
                        break;
                    }
                    case '-': {
                        token = new Token(TokenType.Operator, OperatorType.Sub);
                        isLastCharOperator = true;
                        break;
                    }
                    case '*': {
                        token = new Token(TokenType.Operator, OperatorType.Mul);
                        isLastCharOperator = true;
                        break;
                    }
                    case '/': {
                        token = new Token(TokenType.Operator, OperatorType.Div);
                        isLastCharOperator = true;
                        break;
                    }
                    case ')': {
                        token = new Token(TokenType.Operator, OperatorType.RightParenthesis);
                        break;
                    }
                }
            }
            tokens.add(token);
        }

        return tokens;
    }

    static ASTNode parse(List<Token> tokens) {
        Stack<ASTNode> output = new Stack<>();
        Stack<Token> ops = new Stack<>();
        for (Token token : tokens) {
            // https://en.wikipedia.org/wiki/Shunting-yard_algorithm

            if (token.type == TokenType.Number) {
                output.add(new ASTNode(token));
                continue;
            }

            if (token.type == TokenType.Operator) {
                if (token.value == OperatorType.LeftParenthesis) {
                    ops.push(token);
                    continue;
                }

                while (ops.size() != 0) {
                    if (ops.peek().value == OperatorType.LeftParenthesis) {
                        break;
                    }

                    if (token.value != OperatorType.RightParenthesis) {
                        if (OperatorType.getPrecedence((OperatorType) ops.peek().value) < OperatorType.getPrecedence((OperatorType) token.value)) {
                            break;
                        }
                    }

                    Token op = ops.pop();
                    ASTNode right = output.pop();
                    ASTNode left = output.pop();
                    output.push(new ASTNode(op, left, right));
                }
                if (token.value == OperatorType.RightParenthesis) {
                    ops.pop();
                } else {
                    ops.push(token);
                }
            }
        }

        while(ops.size() != 0) {
            Token op = ops.pop();
            ASTNode right = output.pop();
            ASTNode left = output.pop();
            output.push(new ASTNode(op, left, right));
        }
        return output.pop();
    }

    static int evaluateAST(ASTNode node) {
        int val = 0;
        if (node.token.type == TokenType.Operator) {
            switch ((OperatorType) node.token.value) {
                case Add:
                    val = evaluateAST(node.left) + evaluateAST(node.right);
                    break;
                case Sub:
                    val = evaluateAST(node.left) - evaluateAST(node.right);
                    break;
                case Mul:
                    val = evaluateAST(node.left) * evaluateAST(node.right);
                    break;
                case Div:
                    val = evaluateAST(node.left) / evaluateAST(node.right);
                    break;
            }
        } else {
            val = (int) node.token.value;
        }
        return val;
    }
}