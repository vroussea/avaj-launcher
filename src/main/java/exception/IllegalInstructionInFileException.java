package exception;

public class IllegalInstructionInFileException extends IllegalArgumentException {
    public IllegalInstructionInFileException(String error) {
        super("Illegal instruction : " + error + ".");
    }
}