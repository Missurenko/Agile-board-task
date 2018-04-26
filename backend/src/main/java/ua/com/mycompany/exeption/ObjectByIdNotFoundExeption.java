package ua.com.mycompany.exeption;

public class ObjectByIdNotFoundExeption extends RuntimeException {

    public ObjectByIdNotFoundExeption(Long id, String className) {
        super(String.format("NOT found " + className + " with this id %s", id));
    }
}
