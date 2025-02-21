import { Metadata } from 'next';
import RegisterBox from '../../components/RegisterBox/RegisterBox';
import style from './style/style.module.scss';

export const metadata: Metadata = {
    title: '회원가입'
}

export default function Page() {
    return <main className={style.main}>
        <RegisterBox />
    </main>
}