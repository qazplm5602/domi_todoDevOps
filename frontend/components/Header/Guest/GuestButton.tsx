import Link from 'next/link';
import style from '../header.module.scss';

export default function HeaderGuestButtons() {
    return <section className={style.interaction}>
        <Link href="/login" className='btn btn-primary'>로그인</Link>
        <Link href="/register" className='btn btn-success'>회원가입</Link>
    </section>
}