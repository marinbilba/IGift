package com.viauc.igift.util;

import android.util.Pair;

public class FieldValidation {

    public Pair<Boolean, String> validateEmptyAndMinCharInputField(String inputFieldToValidate,int minChar) {

        if(inputFieldToValidate.isEmpty()){
            return new Pair<Boolean,String>(false,"Field can not be empty");
        }
        else if(inputFieldToValidate.length()<minChar){
            return new Pair<Boolean,String>(false,"Required minimum "+minChar +" characters");
        }
        return new Pair<Boolean,String>(true,"");

    }

}
