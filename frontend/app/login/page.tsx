import { Metadata } from 'next';
import LoginBox from '../../components/LoginBox/LoginBox';
import style from './style/slogin.module.scss';

export const metadata: Metadata = {
    title: '로그인'
}

export default function Page() {
    return <main className={style.main}>
        <LoginBox />
    </main>
}