package main;

class ASTNode {
    ASTNode left;
    ASTNode right;
    Token token;

    public ASTNode(Token token, ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
        this.token = token;
    }

    public ASTNode(Token token) {
        this(token, null, null);
    }

    @Override
    public String toString() {
        if (right == null && left == null) {
            return String.format("ASTNode{token=%s}", token);
        }
        return String.format("ASTNode{token=%s,left=%s,right=%s}", token, left, right);
    }
}