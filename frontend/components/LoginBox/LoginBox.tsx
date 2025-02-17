import { Button } from 'react-bootstrap';
import LoginInput from './Input';
import style from './loginBox.module.scss';

export default function LoginBox() {
    return <section className={style.box}>
        <h1 className={`h2 ${style.title}`}>로그인</h1>

        <form>
            <LoginInput icon="✉️" placeholder='이메일' type='email' name='email' />
            <LoginInput icon="🔑" placeholder='비밀번호' type='password' name='password' />

            <Button className={style.btn} type='submit'>로그인</Button>
        </form>
    </section>
}