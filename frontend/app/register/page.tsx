import RegisterBox from '../../components/RegisterBox/RegisterBox';
import style from './style/style.module.scss';

export default function Page() {
    return <main className={style.main}>
        <RegisterBox />
    </main>
}