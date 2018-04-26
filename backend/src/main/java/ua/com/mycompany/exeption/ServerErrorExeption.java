package ua.com.mycompany.exeption;

public class ServerErrorExeption extends RuntimeException {
    public ServerErrorExeption(String id) {
        super(String.format("Server logic error, not must send " + "this id %s", id));
    }
}
