package com.instep.mail;

public class MailString {

	public static final String PASSWORD_RESET = """
			<!DOCTYPE html>
			<html>
			<head>
			    <meta charset="UTF-8">
			    <meta name="viewport" content="width=device-width,initial-scale=1.0">
			    <title>Reset Your Instep Password</title>
			</head>

			<body style="margin:0;padding:0;background:#f7f6f5;font-family:Arial,Helvetica,sans-serif;color:#292624;">

			    <table width="100%%" cellpadding="0" cellspacing="0" border="0"
			           style="background:#ffffff;padding:20px 10px;">
			        <tr>
			            <td align="center">

			                <table width="100%%" cellpadding="0" cellspacing="0" border="0"
			                       style="max-width:580px;background:#ffffff;border-radius:12px;overflow:hidden;">

			                    <tr>
			                        <td style="padding:25px 28px 30px;">

			                            <table width="100%%" cellpadding="0" cellspacing="0" border="0">
			                                <tr>
			                                    <td>
			                                        <img
			                                            src="https://res.cloudinary.com/denrzaquu/image/upload/f_png,q_auto,w_100/v1790914894/l1_tio2ga.png"
			                                            width="100"
			                                            alt="Instep"
			                                            style="display:block;width:100px;height:auto;border:0;">
			                                    </td>

			                                    <td align="right">
			                                        <span style="font-size:8px;letter-spacing:1px;color:#aaa3a0;border:1px solid #e9e5e3;border-radius:20px;padding:6px 9px;">
			                                            SECURE
			                                        </span>
			                                    </td>
			                                </tr>
			                            </table>

			                            <table width="100%%" cellpadding="0" cellspacing="0" border="0"
			                                   style="margin-top:28px;">
			                                <tr>
			                                    <td>

			                                        <p style="margin:0 0 8px;font-size:8px;font-weight:bold;letter-spacing:2px;color:#FF6B5A;">
			                                            PASSWORD RESET
			                                        </p>

			                                        <h1 style="margin:0;font-family:Georgia,'Times New Roman',serif;font-size:36px;line-height:1.1;font-weight:400;letter-spacing:-1px;color:#292624;">
			                                            Let's make <span style="color:#FF6B5A;">a fresh start.</span>
			                                        </h1>

			                                        <p style="margin:17px 0 0;font-size:12px;line-height:1.7;color:#77716e;">
			                                            Hi <strong style="color:#292624;">%s</strong>,
			                                            <br><br>
			                                            We received a request to reset your Instep password.
			                                            Use the link below to create a new password.
			                                        </p>

			                                    </td>
			                                </tr>
			                            </table>

			                            <table width="100%%" cellpadding="0" cellspacing="0" border="0"
			                                   style="margin-top:24px;">
			                                <tr>
			                                    <td style="background:#fffaf8;border:1px solid #f1e2de;border-radius:8px;padding:14px 16px;">

			                                        <p style="margin:0 0 6px;font-size:8px;letter-spacing:1.2px;color:#aaa3a0;">
			                                            RESET LINK
			                                        </p>

			                                        <p style="margin:0 0 10px;font-size:11px;line-height:1.5;color:#55504d;word-break:break-all;">
			                                            %s
			                                        </p>

			                                        <p style="margin:0;font-size:10px;color:#77716e;">
			                                            This link expires at
			                                            <strong style="color:#FF6B5A;">%s</strong>
			                                        </p>

			                                    </td>
			                                </tr>
			                            </table>

			                            <table cellpadding="0" cellspacing="0" border="0"
			                                   style="margin-top:20px;">
			                                <tr>

			                                    <td style="background:#FF6B5A;border-radius:5px;">
			                                        <a href="%s"
			                                           style="display:block;padding:13px 22px;color:#ffffff;text-decoration:none;font-size:11px;font-weight:bold;letter-spacing:.2px;">
			                                            RESET PASSWORD
			                                        </a>
			                                    </td>

			                                    <td width="8">&nbsp;</td>

			                                    <td style="background:#ffffff;border:1px solid #e5d8d4;border-radius:5px;">
			                                        <a href="%s"
			                                           style="display:block;padding:12px 18px;color:#625b57;text-decoration:none;font-size:11px;font-weight:bold;">
			                                            STOP RESET
			                                        </a>
			                                    </td>

			                                </tr>
			                            </table>

			                            <p style="margin:19px 0 0;font-size:9px;line-height:1.6;color:#aaa3a0;">
			                                If you didn't request this password reset, you can stop the request or ignore this
			                                email.
			                                Your current password will remain unchanged.
			                            </p>

			                        </td>
			                    </tr>

			                    <tr>
			                        <td style="padding:18px 28px;background:#fcfbfa;border-top:1px solid #f1eeec;">

			                            <table width="100%%" cellpadding="0" cellspacing="0" border="0">
			                                <tr>

			                                    <td valign="middle">
			                                        <img
			                                            src="https://res.cloudinary.com/denrzaquu/image/upload/f_png,q_auto,w_24/v1790914884/favicon_ln127j.png"
			                                            width="24"
			                                            height="24"
			                                            alt="Instep"
			                                            style="display:block;width:24px;height:24px;border:0;">
			                                    </td>

			                                    <td valign="middle" style="padding-left:9px;">
			                                        <p style="margin:0;font-size:9px;font-weight:bold;color:#46413e;">
			                                            Instep
			                                        </p>

			                                        <p style="margin:2px 0 0;font-size:7px;color:#aaa3a0;">
			                                            Share moments. Connect. Keep your memories close.
			                                        </p>
			                                    </td>

			                                    <td align="right">
			                                        <p style="margin:0;font-size:7px;color:#aaa3a0;">
			                                            © 2026
			                                        </p>
			                                    </td>

			                                </tr>
			                            </table>

			                        </td>
			                    </tr>

			                </table>

			            </td>
			        </tr>
			    </table>

			</body>
			</html>
			""";

	public static final String REGISTATION_APPROVAL = """
			<!DOCTYPE html>
			<html lang="en">

			<head>
			    <meta charset="UTF-8">
			    <meta name="viewport" content="width=device-width,initial-scale=1">
			    <title>Instep Approval</title>
			</head>

			<body style="margin:0;padding:25px 15px;background:#ffffff;font-family:Arial,Helvetica,sans-serif;color:#20242b;">

			    <table width="100%%" cellpadding="0" cellspacing="0" border="0">
			        <tr>
			            <td align="center">

			                <table width="100%%" cellpadding="0" cellspacing="0" border="0"
			                    style="max-width:560px;background:#fff;border-radius:16px;overflow:hidden;">

			                    <tr>
			                        <td style="padding:20px 30px;border-bottom:1px solid #edf0f3;">

			                            <table width="100%%" cellpadding="0" cellspacing="0" border="0">
			                                <tr>

			                                    <td width="50%%" align="left">
			                                        <img src="https://res.cloudinary.com/denrzaquu/image/upload/v1790914894/l1_tio2ga.png"
			                                            alt="Instep"
			                                            height="32"
			                                            style="display:block;height:32px;width:auto;border:0;">
			                                    </td>

			                                    <td width="50%%" align="right">
			                                        <img src="https://res.cloudinary.com/denrzaquu/image/upload/v1790914884/favicon_ln127j.png"
			                                            alt="Instep"
			                                            width="34"
			                                            height="34"
			                                            style="display:block;width:34px;height:34px;border:0;">
			                                    </td>

			                                </tr>
			                            </table>

			                        </td>
			                    </tr>

			                    <tr>
			                        <td style="padding:25px 32px 24px;text-align:center;">

			                            <div style="margin-top:11px;font-size:24px;line-height:1.25;font-weight:700;color:#fa5c00;">
			                                Confirm your Instep account
			                            </div>

			                            <div style="margin-top:7px;font-size:12px;line-height:1.5;color:#8c9096;">
			                                Someone is creating an Instep account with this email address.<br>
			                                Take a moment to review the request and confirm whether it belongs to you.
			                            </div>

			                            <table width="100%%" cellpadding="0" cellspacing="0" border="0"
			                                style="margin-top:20px;text-align:left;">

			                                <tr>
			                                    <td style="padding:14px 18px;border:1px solid #e8ecf1;border-radius:9px;">

			                                        <div style="font-size:9px;font-weight:bold;letter-spacing:1.3px;color:#9aa1aa;">
			                                            REGISTRATION REQUEST
			                                        </div>

			                                        <div style="margin-top:7px;font-size:16px;font-weight:600;color:#252a31;">
			                                            %s
			                                        </div>

			                                        <div style="margin-top:3px;font-size:12px;color:#707883;">
			                                            @%s
			                                        </div>

			                                        <div style="margin-top:8px;padding-top:8px;border-top:1px solid #edf0f3;font-size:11px;color:#858d97;">
			                                            %s
			                                        </div>

			                                    </td>
			                                </tr>

			                            </table>

			                            <div style="margin-top:18px;font-size:13px;font-weight:600;color:#30363e;">
			                                Do you recognize this registration?
			                            </div>

			                            <div style="margin-top:4px;font-size:11px;color:#89919b;">
			                                Choose an option below to continue.
			                            </div>

			                            <table width="100%%" cellpadding="0" cellspacing="0" border="0"
			                                style="margin-top:15px;">

			                                <tr>

			                                    <td width="48%%">
			                                        <a href="%s"
			                                            style="display:block;padding:12px 8px;background:#ff6200;color:#fff;text-align:center;text-decoration:none;border-radius:7px;font-size:11px;font-weight:bold;">
			                                            ✓ &nbsp; APPROVE
			                                        </a>
			                                    </td>

			                                    <td width="4%%"></td>

			                                    <td width="48%%">
			                                        <a href="%s"
			                                            style="display:block;padding:12px 8px;background:#fff;color:#dc4646;text-align:center;text-decoration:none;border:1px solid #e2b8b8;border-radius:7px;font-size:11px;font-weight:bold;">
			                                            × &nbsp; DENY
			                                        </a>
			                                    </td>

			                                </tr>

			                            </table>

			                            <div style="margin-top:12px;font-size:10px;color:#9aa1aa;">
			                                This request expires at
			                                <strong style="color:#707883;">%s</strong>
			                            </div>

			                            <div style="margin-top:10px;font-size:9px;color:#a0a6ae;">
			                                If you did not request this registration, choose Deny.
			                            </div>

			                        </td>
			                    </tr>

			                    <tr>
			                        <td style="padding:16px 30px;background:#fafbfc;border-top:1px solid #edf0f3;text-align:center;">

			                            <img src="https://res.cloudinary.com/denrzaquu/image/upload/v1790914884/favicon_ln127j.png"
			                                alt="Instep"
			                                width="22"
			                                height="22"
			                                style="display:inline-block;width:22px;height:22px;vertical-align:middle;border:0;">

			                            <span style="margin-left:7px;font-size:10px;color:#9aa1aa;vertical-align:middle;">
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
			""";

	public static String passwordReset(String username, String resetLink, String expiryTime, String stopResetLink) {
		return String.format(
				PASSWORD_RESET,
				username,
				resetLink,
				expiryTime,
				resetLink,
				stopResetLink
		);
	}

	public static String registrationApproval(
			String name, String username,
			String email, String approveLink,
			String denyLink, String expiryTime) {

		return String.format(
				REGISTATION_APPROVAL,
				name,
				username,
				email,
				approveLink,
				denyLink,
				expiryTime
		);
	}
}