"use server";

import { cookies } from "next/headers";

export const getCurrentUser = async function() {
    const cookie = await cookies();
    const accessToken = cookie.get("accessToken");

    if (accessToken === undefined) return;
}