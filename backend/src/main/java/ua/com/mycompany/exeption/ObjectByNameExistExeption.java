package ua.com.mycompany.exeption;

public class ObjectByNameExistExeption extends RuntimeException {

    public ObjectByNameExistExeption(String name, String className) {
        super(String.format(className + " exist name: %s", name));
    }
}
