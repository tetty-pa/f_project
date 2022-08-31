package com.tpavlyshyn.fp.validators;

import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.entity.TranslationCruise;

import java.sql.Date;

public class CruiseDataValidator extends Validator {

    private static final String TEXT_PATTERN = "[A-Za-zА-Яа-я]+";


    public boolean checkCruiseData(Cruise cruise) {
        int numberOfPorts = cruise.getNumberOfPorts();
        int price= cruise.getPrice();
        Date startDate= (Date) cruise.getStartDate();
        Date endDate= (Date) cruise.getEndDate();
        if (numberOfPorts <= 0) {
            return false;
        }
        if (price <= 0) {
            return false;
        }
        return startDate != null && endDate != null && !startDate.after(endDate);
    }

    public boolean checkTranslationCruise(TranslationCruise translationCruise) {
        String name = translationCruise.getCruiseNameLand();
        String description = translationCruise.getDescriptionLand();
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (description == null || description.isEmpty()) {
            return false;
        }
        return matchPattern(name, TEXT_PATTERN) && matchPattern(description, TEXT_PATTERN);
    }


}
