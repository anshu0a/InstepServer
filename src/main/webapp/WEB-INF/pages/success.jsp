<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Success | Instep</title>

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
            padding: 25px;
            background: #f7f6f5;
            font-family: Arial, Helvetica, sans-serif;
            color: #292624;
        }

        .page {
            width: 100%;
            max-width: 520px;
            text-align: center;
        }

        .logo {
            width: 100px;
            margin-bottom: 55px;
        }

        .status {
            width: 42px;
            height: 42px;
            margin: 0 auto 24px;
            border: 1px solid #70a17d;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #4f9867;
            font-size: 17px;
        }

        .eyebrow {
            margin-bottom: 12px;
            color: #FF6B5A;
            font-size: 9px;
            font-weight: bold;
            letter-spacing: 2.5px;
        }

        h1 {
            margin: 0;
            font: 400 44px/1.1 Georgia, "Times New Roman", serif;
        }

        h1 span {
            color: #FF6B5A;
        }

        .text {
            max-width: 360px;
            margin: 18px auto 28px;
            color: #817b77;
            font-size: 12px;
            line-height: 1.7;
        }

        .btn {
            height: 42px;
            padding: 0 26px;
            border: 0;
            border-radius: 5px;
            background: #FF6B5A;
            color: #fff;
            font-size: 10px;
            font-weight: bold;
            letter-spacing: .8px;
            cursor: pointer;
            transition: .2s ease;
        }

        .btn:hover {
            opacity: .9;
        }

        .line {
            width: 40px;
            height: 1px;
            margin: 38px auto 16px;
            background: #ded9d6;
        }

        .footer {
            font-size: 9px;
            color: #aaa3a0;
        }

        @media (max-width: 600px) {

            .logo {
                margin-bottom: 45px;
            }

            h1 {
                font-size: 36px;
            }

        }

    </style>

</head>

<body>

<div class="page">

    <img
        class="logo"
        src="https://res.cloudinary.com/denrzaquu/image/upload/f_png,q_auto,w_100/v1790914894/l1_tio2ga.png"
        alt="Instep">

    <div class="status">
        ✓
    </div>

    <div class="eyebrow">
        PASSWORD UPDATED
    </div>

    <h1>
        You're all <span>set.</span>
    </h1>

    <p class="text">
        ${msg}
    </p>

    <button
        class="btn"
        type="button"
        onclick="window.location.href='${frontend}/login'">

        LOGIN

    </button>

    <div class="line"></div>

    <div class="footer">
        Share moments. Connect with people. Keep your memories close.
    </div>

</div>

</body>

</html>