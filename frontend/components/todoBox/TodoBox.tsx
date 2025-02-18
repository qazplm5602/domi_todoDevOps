import Link from 'next/link';
import style from './style/style.module.scss';

export default function TodoBox() {
    return <Link href="/" className={style.link}>
        <div className={style.box}>
            <h5 className='text-black'>제목 임니다.</h5>
            <div className={style.date}>2022-05-18</div>
        </div>
    </Link>;
}