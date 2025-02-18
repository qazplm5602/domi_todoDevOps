"use server";

import { redirect } from "next/navigation";
import { request } from "../hooks/request";

class LoginError extends Error {
    constructor(message) {
        super(message);
    }
}

export async function loginAction(form: FormData) {
    const email = form.get('email');
    const password = form.get("password");

    if (email === '' || password === '')
        return "이메일 및 비밀번호를 입력하세요."

    try {
        await apiTokenHandler(email.toString(), password.toString());
    } catch (e) {
        if (e instanceof LoginError) {
            return e.message;
        } else {
            throw e;
        }
    }

    redirect("/");
}

export async function apiTokenHandler(email: string, password: string) {
    const { code, data } = await request("/login", { method: "POST", tokenDisable: true, headers: { 'Content-Type': "application/json" }, body: JSON.stringify({email, password}) });
    console.log(code, data);

    if (code !== 200) { // 로그인 실패 (서버가 터졋거나 잘못 입력햇거나 ㅁㄴㅇㄹ)
        throw new LoginError("로그인 오류");
    }
}