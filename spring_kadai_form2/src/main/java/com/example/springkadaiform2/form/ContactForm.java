package com.example.springkadaiform2.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class ContactForm {
	@NotBlank(message = "お名前を入力してください")
    private String name;

    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "メールアドレスの形式が不正です")
    private String email;

    @NotBlank(message = "お問い合わせ内容を入力してください")
    private String message;


}
