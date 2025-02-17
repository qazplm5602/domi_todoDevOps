import { Button } from 'react-bootstrap';
import LoginInput from './Input';
import style from './loginBox.module.scss';

export default function LoginBox() {
    return <section className={style.box}>
        <h1 className={`h2 ${style.title}`}>로그인</h1>

        <LoginInput />
        <LoginInput />

        <Button className={style.btn}>로그인</Button>
    </section>
}