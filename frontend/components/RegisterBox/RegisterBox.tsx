"use client";

import ShadowBox from "../ShadowBox/ShadowBox";
import loginStyle from '../LoginBox/loginBox.module.scss';
import { Button } from "react-bootstrap";
import LoginInput from "../LoginBox/Input";
import { useState, useTransition } from "react";
import { signUpAction } from "../../actions/loginAction";

export default function RegisterBox() {
    const [ isPending, startTrnasition ] = useTransition();
    const [ error, setError ] = useState("");

    const onSubmit = function(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        
        const form = new FormData(event.currentTarget);
        if (form.get("password") !== form.get("passwordre")) {
            return setError("비밀번호 재입력이 비밀번호와 다릅니다.");
        }

        setError("");

        startTrnasition(async () => {
            const err = await signUpAction(form);
            setError(err);
        });
    }

    return <ShadowBox className={loginStyle.box}>
        <h1 className={loginStyle.title}>회원가입</h1>

        <form onSubmit={onSubmit}>
            <LoginInput icon="✉️" placeholder='이메일' type='email' name='email' />
            <LoginInput icon="🔑" placeholder='비밀번호' type='password' name='password' />
            <LoginInput icon="🔑" placeholder='비밀번호 재입력' type='password' name='passwordre' />
            <LoginInput icon="🏷️" placeholder='이름' type='text' name='username' />

            <div className={`text-danger ${loginStyle.err}`}>{error}</div>

            <Button className={`${loginStyle.btn} btn-success`} type='submit' disabled={isPending}>가입</Button>
        </form>
    </ShadowBox>;
}