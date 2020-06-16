package Contollers;

import com.jfoenix.controls.*;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class that contains methods that checks form fields
 *
 * @author daniel gabbay
 */
public class FormValidation {
    private static FormValidation Validator = null;
    private static boolean EmptyField;
    private boolean NumberPositive;
    private boolean DoubleNumber;
    private boolean EmailAddress;
    private boolean maxLengthIndicator;
    private boolean minLengthIndicator;
    private boolean ExactlyInLength;
    private boolean ContainsChars;
    private boolean onlyNumbers;
    private boolean onlyLetters;
    private boolean maxLengthTextArea;
    private boolean phoneNumber;
    private boolean EmptyPassword;
    private boolean maxSizeIndicator;
    private boolean minSizeIndicator;


    public static FormValidation getValidator() {
        if (Validator == null) {
            Validator = new FormValidation();
        }
        return Validator;
    }

    /**
     * Required Input field Validation method
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of field
     */
    public static void isEmptyFieldValidation(JFXTextField theField, String fieldName) {
        RequiredFieldValidator reqInputValidator = new RequiredFieldValidator();
        reqInputValidator.setMessage(fieldName + " field is Required!");
        theField.getValidators().add(reqInputValidator);
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal)
                setEmptyField(theField.validate());
        });
    }


    /**
     * A method that checks if a field is a positive number
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of field
     */
    public void numberPositiveValidation(JFXTextField theField, String fieldName) {
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
                    if (Double.parseDouble(textField.getText()) > 0) {
                        this.hasErrors.set(false);
                        NumberPositive = true;
                    } else {
                        this.hasErrors.set(true);
                        NumberPositive = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldValue, newValue) -> {
            if (!newValue) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if the field contains only numbers
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of field
     */
    public void isDoubleNumberValidation(JFXTextField theField, String fieldName) {
        DoubleValidator doubleValidator = new DoubleValidator();
        theField.getValidators().add(doubleValidator);
        doubleValidator.setMessage(fieldName + " must contains only digits");
        theField.focusedProperty().addListener((o, oldValue, newValue) -> {
            if (!newValue) {
                theField.validate();
            }
        });
    }

    /**
     * Method for checking email address integrity by using EmailValidator
     *
     * @param theField  - the field that contains the email address to validate
     * @param fieldName - the name of field to validate "Email address"
     * @see EmailValidator - from Apache Commons Validator
     */
    public void emailAddressValidation(JFXTextField theField, String fieldName) {
        theField.getValidators().add(new ValidatorBase(fieldName + ": " + theField.getText() + " is InValid") {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            // create the EmailValidator instance
            EmailValidator validator = EmailValidator.getInstance();

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = validator.isValid(textField.getText());
                if (textField.getText().isEmpty()) result = true;
                System.out.println(result);
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        EmailAddress = true;
                    } else {
                        this.hasErrors.set(true);
                        EmailAddress = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if the input length is too long (from max value)
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of the field
     * @param maxLength - max string length
     */
    public void maxLengthValidation(JFXTextField theField, String fieldName, int maxLength) {
        theField.getValidators().add(new ValidatorBase("The " + fieldName + " field length is too long (max - " + maxLength + ") characters") {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = textField.getLength() > maxLength ? false : true;
                System.out.println(result);
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        maxLengthIndicator = true;
                    } else {
                        this.hasErrors.set(true);
                        maxLengthIndicator = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if the input length is exactly a given number
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of the field
     * @param Length    -  the max and min len
     */
    public void ExactlyInLengthValidation(JFXTextField theField, String fieldName, int Length) {
        theField.getValidators().add(new ValidatorBase("The " + fieldName + " length should be" + Length+".") {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = textField.getLength() > Length || textField.getLength() < Length ? false : true;
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        ExactlyInLength = true;
                    } else {
                        this.hasErrors.set(true);
                        ExactlyInLength = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if a string contains different characters than needed
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of the field
     * @param theChars  - required characters
     */
    public void isContainsCharsValidation(JFXTextField theField, String fieldName, String theChars) {
        theField.getValidators().add(new ValidatorBase(fieldName + "The " + fieldName + " field can only contain the following characters: " + theChars) {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = textField.getText().matches(theChars);
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        ContainsChars = true;
                    } else {
                        this.hasErrors.set(true);
                        ContainsChars = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if a string contains only digits
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of the field
     */
    public void isOnlyNumbers(JFXTextField theField, String fieldName) {
        theField.getValidators().add(new ValidatorBase(fieldName + " field can only contain digits") {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }
            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = StringUtils.isNumeric(textField.getText());
                if (textField.getText().isEmpty()) result = true;
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        onlyNumbers = true;
                    } else {
                        this.hasErrors.set(true);
                        onlyNumbers = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if a string contains only letters and spaces
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of the field
     */
    public void isContainsOnlyLetters(JFXTextField theField, String fieldName) {
        theField.getValidators().add(new ValidatorBase(fieldName + " field can only contain letters") {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = StringUtils.isAlpha(textField.getText());
                if (textField.getText().isEmpty()) result = true;
                if (textField.getText().contains(" ")) result = true;
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        onlyLetters = true;
                    } else {
                        this.hasErrors.set(true);
                        onlyLetters = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if the input length is too long (from max value) - to TextArea
     *
     * @param theTextAreaField - the field to validate
     * @param fieldName        - the name of the field
     * @param maxLength        - max string length
     */
    public void maxLengthValidationTextArea(JFXTextArea theTextAreaField, String fieldName, int maxLength) {
        theTextAreaField.getValidators().add(new ValidatorBase(fieldName + " length is too long (max - " + maxLength + " characters)") {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = textField.getLength() > maxLength ? false : true;
                System.out.println(result);
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        maxLengthTextArea = true;
                    } else {
                        this.hasErrors.set(true);
                        maxLengthTextArea = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theTextAreaField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theTextAreaField.validate();
            }
        });
    }

    /**
     * Method for checking phone number integrity
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of field
     */
    public void phoneNumberValidation(JFXTextField theField, String fieldName) {
        theField.getValidators().add(new ValidatorBase(fieldName + " is wrong") {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                Pattern pattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");
                Matcher matcher = pattern.matcher(textField.getText());
                boolean result = matcher.matches();
                if (textField.getText().isEmpty()) result = true;
                if (textField.getText().contains(" ")) result = true;
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        phoneNumber = true;
                    } else {
                        this.hasErrors.set(true);
                        phoneNumber = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    public void isEmptyPasswordField(JFXPasswordField passwordField, String password) {
        RequiredFieldValidator reqInputValidator = new RequiredFieldValidator();
        reqInputValidator.setMessage(password + " field is Required!");
        passwordField.getValidators().add(reqInputValidator);
        passwordField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) passwordField.validate();
        });
    }

    /**
     * A method that checks if a string is smaller than a given number
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of the field
     */
    public void maxSizeValidation(JFXTextField theField, String fieldName, Integer mazSize) {
        theField.getValidators().add(new ValidatorBase(fieldName + " Size is too big. Field can be max" + mazSize.toString()) {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = Integer.parseInt(textField.getText()) > mazSize ? false : true;
                System.out.println(result);
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        maxSizeIndicator = true;
                    } else {
                        this.hasErrors.set(true);
                        maxSizeIndicator = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if a string is smaller than a given number
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of the field
     */
    public void maxFloatSizeValidation(JFXTextField theField, String fieldName, Integer mazSize) {
        theField.getValidators().add(new ValidatorBase(fieldName + " Size is too big. Field can be max" + mazSize.toString()) {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = Float.parseFloat(textField.getText()) > mazSize ? false : true;
                System.out.println(result);
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        maxSizeIndicator = true;
                    } else {
                        this.hasErrors.set(true);
                        maxSizeIndicator = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if a string is smaller than a given number
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of the field
     */
    public void minSizeValidation(JFXTextField theField, String fieldName, Integer minSize) {
        theField.getValidators().add(new ValidatorBase(fieldName + " Size is too small. Field need to be min" + minSize.toString()) {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = Integer.parseInt(textField.getText()) < minSize ? false : true;
                System.out.println(result);
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        minSizeIndicator = true;
                    } else {
                        this.hasErrors.set(true);
                        minSizeIndicator = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if a string is smaller than a given number
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of the field
     */
    public void minFloatSizeValidation(JFXTextField theField, String fieldName, Integer minSize) {
        theField.getValidators().add(new ValidatorBase(fieldName + " Size is too small. Field need to be min" + minSize.toString()) {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = Float.parseFloat(textField.getText()) < minSize ? false : true;
                System.out.println(result);
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        minSizeIndicator = true;
                    } else {
                        this.hasErrors.set(true);
                        minSizeIndicator = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if the input length is too long (from max value) - short massage version
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of the field
     * @param minLength - max string length
     */
    public void minLengthValidationShort(JFXTextField theField, String fieldName, int minLength) {
        theField.getValidators().add(new ValidatorBase(" Too short. Minimum " + minLength + " characters.") {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = textField.getLength() < minLength ? false : true;
                System.out.println(result);
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        minLengthIndicator = true;
                    } else {
                        this.hasErrors.set(true);
                        minLengthIndicator = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }

    /**
     * A method that checks if the input length is too long (from max value) - short massage version
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of the field
     * @param maxLength - max string length
     */
    public void maxLengthValidationShort(JFXTextField theField, String fieldName, int maxLength) {
        theField.getValidators().add(new ValidatorBase("Too long. Maximum " + maxLength + " characters.") {
            @Override
            protected void eval() {
                if (this.srcControl.get() instanceof TextInputControl) {
                    this.evalTextInputField();
                }
            }

            private void evalTextInputField() {
                TextInputControl textField = (TextInputControl) this.srcControl.get();
                boolean result = textField.getLength() > maxLength ? false : true;
                System.out.println(result);
                try {
                    if (result) {
                        this.hasErrors.set(false);
                        maxLengthIndicator = true;
                    } else {
                        this.hasErrors.set(true);
                        maxLengthIndicator = false;
                    }
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        });
        //  add listener to the txtField
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                theField.validate();
            }
        });
    }


    public void isEmptyDateField(JFXDatePicker dateField, String date) {

        RequiredFieldValidator reqInputValidator = new RequiredFieldValidator();
        reqInputValidator.setMessage(date + " field is Required!");
        dateField.getValidators().add(reqInputValidator);
        dateField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal)
                dateField.validate();
        });
    }


    public void isEmptyTimeField(JFXTimePicker timePicker, String time) { //doesnt work !
        RequiredFieldValidator reqInputValidator = new RequiredFieldValidator();
        reqInputValidator.setMessage(time + " field is Required!");
        timePicker.validate();
        timePicker.getValidators().add(reqInputValidator);
        timePicker.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal)
                timePicker.validate();
        });
    }

    public boolean isEmptyField() {
        return EmptyField;
    }

    public boolean isNumberPositive() {
        return NumberPositive;
    }

    public boolean isDoubleNumber() {
        return DoubleNumber;
    }

    public boolean isEmailAddress() {
        return EmailAddress;
    }

    public boolean isMaxLengthIndicator() {
        return maxLengthIndicator;
    }

    public boolean isMinLengthIndicator() {
        return minLengthIndicator;
    }

    public boolean isExactlyInLength() {
        return ExactlyInLength;
    }

    public boolean isContainsChars() {
        return ContainsChars;
    }

    public boolean isOnlyNumbers() {
        return onlyNumbers;
    }

    public boolean isOnlyLetters() {
        return onlyLetters;
    }

    public boolean isMaxLengthTextArea() {
        return maxLengthTextArea;
    }

    public boolean isPhoneNumber() {
        return phoneNumber;
    }

    public boolean isEmptyPassword() {
        return EmptyPassword;
    }

    public boolean isMaxSizeIndicator() {
        return maxSizeIndicator;
    }

    public boolean isMinSizeIndicator() {
        return minSizeIndicator;
    }

    public static void setEmptyField(boolean emptyField) {
        EmptyField = emptyField;
    }
}

