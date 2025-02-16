"use server";

import { cookies } from "next/headers";
import { request } from "./request";

export const getCurrentUser = async function() {
    const cookie = await cookies();
    const accessToken = cookie.has("accessToken");
    if (!accessToken) return;
    
    const { code, data } = await request("/test");
}