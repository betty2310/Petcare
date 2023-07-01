package com.petcare.Controller.Pet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddPetControllerTest {
    @Test
    void validatePetName() {
        var controller = new AddPetController();
        assertTrue(controller.isNameValid("abc"));
        assertFalse(controller.isNameValid("asdf23"));
    }
}