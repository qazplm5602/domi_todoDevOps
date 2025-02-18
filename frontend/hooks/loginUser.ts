"use server";

import { cookies } from "next/headers";
import { request } from "./request";

interface UserVO {
    id: number,
    name: string,
    email: string
}

export const getCurrentUser = async function(): Promise<UserVO | undefined> {
    const cookie = await cookies();
    const accessToken = cookie.has("accessToken");
    if (!accessToken) return;
    
    const { code, data } = await request<UserVO>("/user/@me", { cache: 'force-cache' });
    if (code !== 200) {
        return;
    }

    return data;
}