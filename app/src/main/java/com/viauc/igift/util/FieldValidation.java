package com.viauc.igift.util;

import android.text.TextUtils;
import android.util.Pair;
import android.util.Patterns;

public class FieldValidation {

    public Pair<Boolean, String> validateEmptyField(String inputFieldToValidate) {
        if (inputFieldToValidate.isEmpty()) {
            return new Pair<>(false, "Field can not be empty");
        }
        return new Pair<Boolean, String>(true, "");
    }


    public boolean emailEmptyStringAndPatternValidation(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());

    }

    /**
     * Validates group name. Validation will check if the given param is an empty String and if its length>6
     *
     * @param groupName requires minimum 6 char
     * @return Pair object on first position containing true if validation was passed or false if failed, second position contains a String
     * with the error description
     */
    public Pair<Boolean, String> validateGroupName(String groupName) {
        Pair<Boolean, String> emptyField = validateEmptyField(groupName);
        if (!emptyField.first) {
            return emptyField;
        }
        Pair<Boolean, String> minCharRequired = validateMinCharRequired(groupName,6);
        if (!minCharRequired.first) {
            return minCharRequired;
        }
        return new Pair<>(true, "");

    }

    /**
     * Validates wish item name. Validation will check if the given param is an empty String and if its length>2
     *
     * @param itemName requires minimum 2 char
     * @return Pair object on first position containing true if validation was passed or false if failed, second position contains a String
     * with the error description
     */
    public Pair<Boolean, String> validateWishItemName(String itemName) {
        Pair<Boolean, String> emptyField = validateEmptyField(itemName);
        if (!emptyField.first) {
            return emptyField;
        }
        Pair<Boolean, String> minCharRequired = validateMinCharRequired(itemName,2);
        if (!minCharRequired.first) {
            return minCharRequired;
        }
        return new Pair<>(true, "");

    }


    /**
     * Validates wish list name. Validation will check if the given param is an empty String and if its length>6
     *
     * @param listName requires minimum 6 char
     * @return Pair object on first position containing true if validation was passed or false if failed, second position contains a String
     * with the error description
     */
    public Pair<Boolean, String> validateWishListName(String listName) {
        Pair<Boolean, String> emptyField = validateEmptyField(listName);
        if (!emptyField.first) {
            return emptyField;
        }
        Pair<Boolean, String> minCharRequired = validateMinCharRequired(listName,6);
        if (!minCharRequired.first) {
            return minCharRequired;
        }
        return new Pair<>(true, "");

    }


    private Pair<Boolean, String> validateMinCharRequired(String stringToValidate, int minCharRequired) {
        if (stringToValidate.length() < minCharRequired) {
            return new Pair<>(false, "Required minimum " + minCharRequired + " characters");
        }
        return new Pair<>(true, "");

    }
    /**
     * Validates password. Validation will check if the given param is an empty String and if its length>6
     *
     * @param password requires minimum 6 char
     * @return Pair object on first position containing true if validation was passed or false if failed, second position contains a String
     * with the error description
     */
    public Pair<Boolean, String> passwordFieldValidation(String password) {
        Pair<Boolean, String> emptyField = validateEmptyField(password);
        if (!emptyField.first) {
            return emptyField;
        }
        Pair<Boolean, String> minCharRequired = validateMinCharRequired(password,6);
        if (!minCharRequired.first) {
            return minCharRequired;
        }
        return new Pair<>(true, "");

    }

    /**
     * Validates confirm password.

     * @param password
     * @param confirmPassword must match password
     * @return Pair object on first position containing true if validation was passed or false if failed, second position contains a String
     *      with the error description
     */
    public Pair<Boolean, String> confirmPasswordValidation(String password, String confirmPassword) {
        Pair<Boolean, String> passwordFieldValidation = validateEmptyField(password);
        if (!passwordFieldValidation.first) {
            return passwordFieldValidation;
        }
        if(!password.equals(confirmPassword)){
            return new Pair<>(false, "Passwords are not matching " );
        }
        return new Pair<>(true, "");
    }
}
