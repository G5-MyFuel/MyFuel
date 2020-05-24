package client.logic;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;

/**
 * class that contains methods that checks form fields
 * @author daniel gabbay
 */
public class FormValidation {
    private static FormValidation Validator = null;

    public static FormValidation getValidator() {
        if(Validator==null){
            Validator = new FormValidation();
        }
        return Validator;
    }

    /**
     * Required Input field Validation method
     * @param theField  - the field to validate
     * @param fieldName - the name of field
     */
    public static void isEmptyField(JFXTextField theField, String fieldName) {
        RequiredFieldValidator reqInputValidator = new RequiredFieldValidator();
        reqInputValidator.setMessage(fieldName + " field is Required!");
        theField.getValidators().add(reqInputValidator);
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) theField.validate();
        });
    }

    /**
     * A method that checks if a field is a positive number
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of field
     */
    public void numberPositiveValidator(JFXTextField theField, String fieldName) {
        theField.getValidators().add(new ValidatorBase(fieldName + " must be a positive number") {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                try {
                    if (Double.parseDouble(textField.getText()) > 0)
                        this.hasErrors.set(false);
                    else
                        this.hasErrors.set(true);
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
    }

    /**
     * A method that checks if the field contains only numbers
     * @param theField  - the field to validate
     * @param fieldName - the name of field
     */
    public void isDoubleNumberValidation(JFXTextField theField, String fieldName) {
        DoubleValidator doubleValidator = new DoubleValidator();
        theField.getValidators().add(doubleValidator);
        doubleValidator.setMessage(fieldName + "must contains only digits");
        theField.focusedProperty().addListener((o,oldValue,newValue)->{
            if(!newValue){
                theField.validate();
            }
        });
    }

}
