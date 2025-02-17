"use client";

import { Button } from 'react-bootstrap';
import LoginInput from './Input';
import style from './loginBox.module.scss';
import ShadowBox from '../ShadowBox/ShadowBox';
import { useTransition } from 'react';
import { loginAction } from '../../actions/loginAction';

export default function LoginBox() {
    const [ isPending, startTransition ] = useTransition();

    const onLogin = function(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        const form = new FormData(event.currentTarget);

        startTransition(async () => {
            const response = await loginAction(form);
            console.log(response);
        });
    }

    return <ShadowBox className={style.box}>
        <h1 className={`h2 ${style.title}`}>로그인</h1>

        <form onSubmit={onLogin}>
            <LoginInput icon="✉️" placeholder='이메일' type='email' name='email' />
            <LoginInput icon="🔑" placeholder='비밀번호' type='password' name='password' />

            <div className={`text-danger ${style.err}`}>비밀번호가 일치하지 않습니다.</div>

            <Button className={style.btn} type='submit' disabled={isPending}>로그인</Button>
        </form>
    </ShadowBox>
}