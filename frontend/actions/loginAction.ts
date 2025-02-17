"use server";

import { redirect } from "next/navigation";


export async function loginAction(form: FormData) {
    const email = form.get('email');
    const password = form.get("password");

    if (email === '' || password === '')
        return "이메일 및 비밀번호를 입력하세요."

    redirect("/");
}