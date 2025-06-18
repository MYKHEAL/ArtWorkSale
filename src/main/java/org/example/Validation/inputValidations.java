package org.example.Validation;

import org.example.exceptions.*;

public class inputValidations {


    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NameException("Name cannot be empty");
        }
        if (!name.matches("^[A-Za-z]+$")) {
            throw new NameException("Name must contain only letters");
        }
    }

    public static void validatePhone(String phone) {
        if (phone == null) {
            throw new phoneNumberException("Phone number cannot be null");
        }

        phone = phone.trim().replaceAll("\\s+", "");

        if (!phone.matches("^\\d{11}$")) {
            throw new phoneNumberException("Phone number must be exactly 11 digits");
       }
    }


        public static void validateEmail(String email) {
            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new EmailException("Invalid email format");
            }

        }


        public static void validatePassword(String password) {
            if (password == null || password.trim().isEmpty()) {
                throw new passwordException("Password cannot be empty");
            }
            String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{5,}$";

            if (!password.matches(passwordPattern)) {
                throw new passwordException(
                        "Password must be at least 5 characters long, include at least one uppercase letter, one lowercase letter, and one digit"
                );
            }
        }


        public static void validatePrice(double price) {
            if (price < 0) {
                throw new invalidPriceException("price must be above 0");
            }
        }

    public static void validateOwnership(String ownerEmail, String userEmail) {
        if (!ownerEmail.equals(userEmail)) {
            throw new UnauthorizedArtworkAccessException("You are not allowed to delete this artwork");
        }
    }

    }



