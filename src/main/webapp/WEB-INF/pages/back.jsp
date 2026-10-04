<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Error | Instep</title>

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

    .errorBox {
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

    .message {
        margin-bottom: 14px;
        padding: 10px 12px;
        border-radius: 5px;
        font-size: 11px;
        line-height: 1.4;
        background: #fff3f1;
        color: #d95343;
    }

    .resetBtn,
    .loginBtn {
        width: 100%;
        height: 44px;
        border-radius: 5px;
        font-size: 11px;
        font-weight: bold;
        letter-spacing: .4px;
        cursor: pointer;
        transition: .2s ease;
        text-decoration: none;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .resetBtn {
        border: 0;
        background: #FF6B5A;
        color: #fff;
    }

    .resetBtn:hover {
        transform: translateY(-1px);
    }

    .loginBtn {
        margin-top: 8px;
        border: 1px solid #e4d8d4;
        background: #fff;
        color: #6d6561;
    }

    .loginBtn:hover {
        border-color: #FF6B5A;
        color: #FF6B5A;
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

        .errorBox {
            padding: 26px 22px;
        }

        h1 {
            font-size: 30px;
        }

    }

</style>

</head>

<body>

<div class="errorBox">

<img
    class="logo"
    src="https://res.cloudinary.com/denrzaquu/image/upload/f_png,q_auto,w_100/v1790914894/l1_tio2ga.png"
    alt="Instep">

<p class="label">
    PASSWORD RESET
</p>

<h1>
    Reset
    <br>
    <span>link unavailable.</span>
</h1>

<p class="description">
    We couldn't continue with your password reset request.
    The link may have expired, already been used, or been stopped.
</p>

<div class="message">
    ${not empty error ? error : "This password reset link is no longer available."}
</div>

<a
    href="${frontend}/forgot"
    class="resetBtn">
    NEW RESET LINK
</a>

<a
    href="${frontend}/login"
    class="loginBtn">
    LOGIN
</a>

<div class="footer">
    Instep &nbsp;·&nbsp; Keep your memories close.
</div>

</div>

</body>

</html>