<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Instep</title>
</head>

<body style="margin:0;padding:25px 15px;background:#ffffff;font-family:Arial,Helvetica,sans-serif;color:#20242b;">

    <table cellpadding="0" cellspacing="0" border="0"
        style="width:100%;">

        <tr>
            <td align="center">

                <table cellpadding="0" cellspacing="0" border="0"
                    style="width:100%;max-width:560px;background:#fff;border-radius:16px;overflow:hidden;">

                    <tr>
                        <td style="padding:20px 30px;border-bottom:1px solid #edf0f3;">

                            <table cellpadding="0" cellspacing="0" border="0"
                                style="width:100%;">

                                <tr>

                                    <td style="width:50%;text-align:left;">
                                        <img src="https://res.cloudinary.com/denrzaquu/image/upload/v1790914894/l1_tio2ga.png"
                                            alt="Instep"
                                            height="32"
                                            style="display:block;height:32px;width:auto;border:0;">
                                    </td>

                                    <td style="width:50%;text-align:right;">
                                        <img src="https://res.cloudinary.com/denrzaquu/image/upload/v1790914884/favicon_ln127j.png"
                                            alt="Instep"
                                            width="34"
                                            height="34"
                                            style="display:block;width:34px;height:34px;border:0;margin-left:auto;">
                                    </td>

                                </tr>

                            </table>

                        </td>
                    </tr>

                    <tr>
                        <td style="padding:55px 35px;text-align:center;">

                            <div style="width:58px;height:58px;margin:0 auto;border-radius:50%;
                                background:${success ? '#f0faf4' : '#fff5f5'};
                                border:1px solid ${success ? '#ccebd8' : '#f0cccc'};
                                color:${success ? '#3caa6e' : '#dc4646'};
                                font-size:28px;line-height:58px;">
                                ${success ? '✓' : '×'}
                            </div>

                            <div style="margin-top:22px;font-size:11px;font-weight:bold;
                                letter-spacing:1.8px;color:${success ? '#3caa6e' : '#dc4646'};">
                                ${success ? 'REQUEST PROCESSED' : 'REQUEST NOT COMPLETED'}
                            </div>

                            <div style="margin-top:13px;font-size:22px;line-height:1.4;
                                font-weight:600;color:#20242b;">
                                ${message}
                            </div>

                            <div style="margin-top:18px;font-size:11px;line-height:1.6;color:#9aa1aa;">
                                ${success
                                    ? 'You can now continue with your Instep registration.'
                                    : 'If this request is no longer valid, please start the registration process again.'}
                            </div>

                        </td>
                    </tr>

                    <tr>
                        <td style="padding:16px 30px;background:#fafbfc;
                            border-top:1px solid #edf0f3;text-align:center;">

                            <img src="https://res.cloudinary.com/denrzaquu/image/upload/v1790914884/favicon_ln127j.png"
                                alt="Instep"
                                width="22"
                                height="22"
                                style="display:inline-block;width:22px;height:22px;
                                vertical-align:middle;border:0;">

                            <span style="margin-left:7px;font-size:10px;color:#9aa1aa;
                                vertical-align:middle;">
                                Instep · Make Today Worth Remembering
                            </span>

                        </td>
                    </tr>

                </table>

            </td>
        </tr>

    </table>

</body>

</html>