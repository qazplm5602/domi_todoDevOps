"use server";

import { redirect } from "next/navigation";
import { request } from "../hooks/request";

export async function creatOrEditAction(form: FormData, id?: number) {
    const body = {
        title: form.get("title"),
        date: form.get("date"),
        desc: form.get("desc"),
    }

    const { code, data } = await request(`/todo/`, { method: "PUT", headers: { "Content-Type": "application/json" }, body: JSON.stringify(body) });
    if (code !== 200)
        throw Error("서버 오류");

    const reId = id !== undefined ? id : data as number;
    redirect(`/view/${reId}`);
}