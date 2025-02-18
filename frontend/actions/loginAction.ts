"use server";

import { redirect } from "next/navigation";
import { ApiError, request } from "../hooks/request";
import { cookies } from "next/headers";

class LoginError extends Error {
    constructor(message) {
        super(message);
    }
}

interface AuthTokenVO {
    access: string,
    refresh: string
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
}

export async function apiTokenHandler(email: string, password: string) {
    const { code, data } = await request<any>("/login", { method: "POST", tokenDisable: true, headers: { 'Content-Type': "application/json" }, body: JSON.stringify({email, password}) });

    if (code !== 200) { // 로그인 실패 (서버가 터졋거나 잘못 입력햇거나 ㅁㄴㅇㄹ)
        if (data.code !== undefined)
            throw new LoginError((data as ApiError).message);

        throw new Error("API 서버 오류");
    }

    const token = data as AuthTokenVO;
    const cookie = await cookies();
    cookie.set("accessToken", token.access, { httpOnly: true });
    cookie.set("refreshToken", token.refresh, { httpOnly: true });
    
    // 끗
    redirect("/");
}