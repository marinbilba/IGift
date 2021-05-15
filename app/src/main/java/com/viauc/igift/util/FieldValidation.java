package com.viauc.igift.util;

import android.util.Pair;

public class FieldValidation {

    public Pair<Boolean, String> validateEmptyAndMinCharInputField(String inputFieldToValidate) {

        if(inputFieldToValidate.isEmpty()){
            return new Pair<Boolean,String>(false,"Field can not be empty");
        }
        else if(inputFieldToValidate.length()<6){
            return new Pair<Boolean,String>(false,"Minimum 6 characters");
        }
        return new Pair<Boolean,String>(true,"");

    }
}
