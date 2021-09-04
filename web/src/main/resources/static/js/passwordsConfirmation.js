function passwordsConfirmation(fieldConfirmPassword) {
    if (fieldConfirmPassword.value !== $("#password").val()) {
        fieldConfirmPassword.setCustomValidity("Passwords do not match!");
    } else {
        fieldConfirmPassword.setCustomValidity("");
    }

    if (fieldConfirmPassword.value.length < 8) {
        fieldConfirmPassword.setCustomValidity("Password is too short");
    } else {
        fieldConfirmPassword.setCustomValidity("");
    }
}

