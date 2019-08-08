package com.kpanic.example;

/**
 * Created by anh.phan3.
 * Date: 2019-08-03
 * Time: 16:22
 */
public final class ValidateStudentServiceImpl implements ValidateStudentService {

    private static final ValidateStudentServiceImpl instance = new ValidateStudentServiceImpl();

    private ValidateStudentServiceImpl() {
    }

    static ValidateStudentServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validate(Student student) {
        return student.getAge() > 18;
    }
}
