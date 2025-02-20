import Link from 'next/link';
import style from './style/style.module.scss';

type Props = {
    id: number
}

export function ViewInteracts({ id }: Props) {
    return <article className={style.interaction}>
        <Link href={`/edit/${id}`} className='btn btn-primary'>수정</Link>
        <button className='btn btn-danger'>삭제</button>
    </article>;
}