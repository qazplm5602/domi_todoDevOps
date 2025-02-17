import LoginBox from '../../components/LoginBox/LoginBox';
import style from './style/slogin.module.scss';

export default function Page() {
    return <main className={style.main}>
        <LoginBox />
    </main>
}