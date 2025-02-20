"use server";

import { redirect } from "next/navigation";
import { request } from "../hooks/request";

export async function creatOrEditAction(form: FormData, id?: number) {
    const body = {
        title: form.get("title"),
        date: form.get("date"),
        desc: form.get("desc"),
    }

    const { code, data } = await request(`/todo/${id || ''}`, { method: (id ? "POST" : "PUT"), headers: { "Content-Type": "application/json" }, body: JSON.stringify(body) });
    if (code !== 200)
        throw Error("서버 오류");

    const reId = id !== undefined ? id : data as number;
    redirect(`/view/${reId}`);
}

export async function deleteAction(id: number) {
    const { code } = await request(`/todo/${id}`, { method: "DELETE" });
    if (code !== 200)
        throw new Error("삭제 실패");

    redirect("/");
}