import jwt from 'jsonwebtoken';
import { cookies } from "next/headers";

export interface ResponseData<T> {
    code: number,
    data: T
}

export const request = async function<T>(uri: string, option?: RequestInit, useUrlCache?: boolean): Promise<ResponseData<T>> {
    const cookie = await cookies();
    const accessToken = cookie.get("accessToken");

    // 유저 따라 다른 캐싱
    const tags = [];
    
    if (!useUrlCache && accessToken) {
        const claims = jwt.verify(accessToken.value, process.env.JWT_KEY) as jwt.JwtPayload;
        tags.push(`${uri}-${claims.jti}`);
        
        if (option === undefined)
            option = {};
        
        option.next = Object.assign(option.next || {}, { tags });
    }

    if (accessToken) {
        option.headers = Object.assign(option.headers || {}, {
            Authorization: `Bearer ${accessToken.value}`
        });
    }

    console.log(option);


    const result = await fetch(`${process.env.API_URL}${uri}`, option);
    let data: T;

    try { // json 파싱 시도
        data = await result.clone().json();
    } catch {
        try { // 안되면 text로
            data = await result.text() as T;
        } catch {}
    }

    return { code: result.status, data };
}