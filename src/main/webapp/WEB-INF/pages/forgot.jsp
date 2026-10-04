
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Reset Password | Instep</title>

<link rel="icon"
      href="https://res.cloudinary.com/denrzaquu/image/upload/f_png,q_auto,w_24/v1790914884/favicon_ln127j.png">

<style>

* {
    box-sizing: border-box;
}

body {
    margin: 0;
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    font-family: Arial, Helvetica, sans-serif;
    color: #292624;
    background: #f7f6f5;
}

.resetBox {
    width: 100%;
    max-width: 600px;
    margin: 20px;
    padding: 32px;
}

.logo {
    width: 100px;
    display: block;
    margin-bottom: 30px;
}

.label {
    margin: 0 0 8px;
    font-size: 9px;
    font-weight: bold;
    letter-spacing: 2px;
    color: #FF6B5A;
}

h1 {
    margin: 0;
    font: 400 34px/1.1 Georgia, "Times New Roman", serif;
}

h1 span {
    color: #FF6B5A;
}

.description {
    margin: 15px 0 25px;
    font-size: 12px;
    line-height: 1.7;
    color: #77716e;
}

.field {
    margin-bottom: 17px;
}

.field label {
    display: block;
    margin-bottom: 7px;
    font-size: 10px;
    font-weight: bold;
    color: #55504d;
}

.field input {
    width: 100%;
    height: 44px;
    padding: 0 13px;
    border: 1px solid #ddd8d5;
    border-radius: 5px;
    outline: 0;
    font-size: 13px;
    color: #292624;
    background: #fff;
    transition: .2s ease;
}

.field input:focus {
    border-color: #FF6B5A;
}

.field input.invalid {
    border-color: #e25545;
}

.field input.valid {
    border-color: #68a77b;
}

.error {
    margin-top: 5px;
    display: flex;
    align-items: center;
    gap: 6px;
    font-size: 10px;
    color: #e25545;
}

.error:empty {
    display: none;
}

.error::before {
    content: "";
    width: 6px;
    height: 6px;
    flex: 0 0 6px;
    border-radius: 50%;
    background: #e25545;
}

.message {
    margin-bottom: 14px;
    padding: 10px 12px;
    border-radius: 5px;
    font-size: 11px;
    line-height: 1.4;
}

.message:empty {
    display: none;
}

.errorMessage {
    background: #fff3f1;
    color: #d95343;
}

.successMessage {
    background: #f2faf5;
    color: #3d8055;
}

.resetBtn,
.stopBtn {
    width: 100%;
    height: 44px;
    border-radius: 5px;
    font-size: 11px;
    font-weight: bold;
    letter-spacing: .4px;
    cursor: pointer;
    transition: .2s ease;
}

.resetBtn {
    border: 0;
    background: #FF6B5A;
    color: #fff;
}

.resetBtn:hover:not(:disabled) {
    transform: translateY(-1px);
}

.stopBtn {
    margin-top: 8px;
    border: 1px solid #e4d8d4;
    background: #fff;
    color: #6d6561;
}

.stopBtn:hover:not(:disabled) {
    border-color: #FF6B5A;
    color: #FF6B5A;
}

button:disabled {
    opacity: .6;
    cursor: not-allowed;
}

.footer {
    margin-top: 22px;
    padding-top: 16px;
    border-top: 1px solid #f0edeb;
    text-align: center;
    font-size: 9px;
    color: #aaa3a0;
}

@media (max-width: 480px) {

    .resetBox {
        padding: 26px 22px;
    }

    h1 {
        font-size: 30px;
    }

}

</style>

</head>

<body>

<div class="resetBox">

    <img
        class="logo"
        src="https://res.cloudinary.com/denrzaquu/image/upload/f_png,q_auto,w_100/v1790914894/l1_tio2ga.png"
        alt="Instep">

    <p class="label">
        PASSWORD RESET
    </p>

    <h1>
        Create
        <br>
        <span>a new password.</span>
    </h1>

    <p class="description">
        Choose a new password for your Instep account.
        Your password must contain at least 5 characters.
    </p>

    <div id="message" class="message"></div>

    <form id="resetForm" novalidate>

        <div class="field">

            <label for="password">
                NEW PASSWORD
            </label>

            <input
                type="password"
                id="password"
                autocomplete="new-password"
                placeholder="Enter new password">

            <div id="passwordError" class="error"></div>

        </div>

        <div class="field">

            <label for="confirmPassword">
                CONFIRM PASSWORD
            </label>

            <input
                type="password"
                id="confirmPassword"
                autocomplete="new-password"
                placeholder="Confirm new password">

            <div id="confirmError" class="error"></div>

        </div>

        <button
            id="resetBtn"
            class="resetBtn"
            type="submit">
            CHANGE PASSWORD
        </button>

        <button
            id="stopBtn"
            class="stopBtn"
            type="button">
            STOP RESET
        </button>

    </form>

    <div class="footer">
        Instep &nbsp;·&nbsp; Keep your memories close.
    </div>

</div>

<script>

const form = document.getElementById("resetForm");

const password = document.getElementById("password");

const confirmPassword = document.getElementById("confirmPassword");

const passwordError = document.getElementById("passwordError");

const confirmError = document.getElementById("confirmError");

const message = document.getElementById("message");

const resetBtn = document.getElementById("resetBtn");

const stopBtn = document.getElementById("stopBtn");

const token = new URLSearchParams(location.search).get("token");

const BACKEND = "${backend}";

function showError(input, element, text) {

    element.textContent = text;

    input.classList.toggle("invalid", !!text);

    input.classList.remove("valid");

}

function showValid(input, element) {

    element.textContent = "";

    input.classList.remove("invalid");

    if (input.value) {
        input.classList.add("valid");
    }

}

function validatePassword() {

    const value = password.value;

    if (!value) {

        showError(
            password,
            passwordError,
            "Password is required."
        );

        return false;
    }

    if (value.length < 5) {

        showError(
            password,
            passwordError,
            "Password must contain at least 5 characters."
        );

        return false;
    }

    showValid(password, passwordError);

    return true;

}

function validateConfirm() {

    const value = confirmPassword.value;

    if (!value) {

        showError(
            confirmPassword,
            confirmError,
            "Please confirm your password."
        );

        return false;
    }

    if (value.length < 5) {

        showError(
            confirmPassword,
            confirmError,
            "Password must contain at least 5 characters."
        );

        return false;
    }

    if (value !== password.value) {

        showError(
            confirmPassword,
            confirmError,
            "Passwords do not match."
        );

        return false;
    }

    showValid(confirmPassword, confirmError);

    return true;

}

function validate() {

    const passwordValid = validatePassword();

    const confirmValid = validateConfirm();

    return passwordValid && confirmValid;

}

function showMessage(text, type) {

    message.textContent = text;

    message.className = "message " + type;

}

password.addEventListener("input", () => {

    validatePassword();

    if (confirmPassword.value) {
        validateConfirm();
    }

});

confirmPassword.addEventListener(
    "input",
    validateConfirm
);

form.addEventListener("submit", async event => {

    event.preventDefault();

    message.className = "message";

    message.textContent = "";

    if (!token) {

        showMessage(
            "Invalid or missing reset link.",
            "errorMessage"
        );

        return;
    }

    if (!validate()) {
        return;
    }

    resetBtn.disabled = true;

    stopBtn.disabled = true;

    resetBtn.textContent = "CHANGING PASSWORD...";

    try {

        const response = await fetch(
            BACKEND + "/forgot/change",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },

                body: new URLSearchParams({
                    token: token,
                    password: password.value,
                    rePassword: confirmPassword.value
                })
            }
        );

        const html = await response.text();

        document.open();

        document.write(html);

        document.close();

    } catch (error) {

        showMessage(
            error.message || "Something went wrong.",
            "errorMessage"
        );

        resetBtn.disabled = false;

        stopBtn.disabled = false;

        resetBtn.textContent = "CHANGE PASSWORD";

    }

});

stopBtn.addEventListener("click", () => {

    if (!token) {

        showMessage(
            "Invalid or missing reset link.",
            "errorMessage"
        );

        return;
    }

    if (!confirm(
        "Are you sure you want to stop this password reset?"
    )) {
        return;
    }

    resetBtn.disabled = true;

    stopBtn.disabled = true;

    stopBtn.textContent = "STOPPING...";

    window.location.href =
        BACKEND + "/forgot/destroy?token=" + encodeURIComponent(token);

});

</script>

</body>

</html>

