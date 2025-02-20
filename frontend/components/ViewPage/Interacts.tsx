import Link from 'next/link';
import style from './style/style.module.scss';

export function ViewInteracts() {
    return <article className={style.interaction}>
        <Link href="/edit/1" className='btn btn-primary'>수정</Link>
        <button className='btn btn-danger'>삭제</button>
    </article>;
}