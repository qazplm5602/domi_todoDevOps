"use client";

import { logoutAction } from "../../../actions/loginAction";

export default function LogoutBtn() {
    const onLogout = function() {
        logoutAction();
    }
    
    return <button onClick={onLogout} className="btn btn-link">로그아웃</button>;
}