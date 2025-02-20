"use client";

import Link from 'next/link';
import style from './style/style.module.scss';
import { deleteAction } from '../../actions/todoAction';

type Props = {
    id: number
}

export function ViewInteracts({ id }: Props) {
    const onRemove = async function() {
        const result = confirm("삭제하시겠습니까?");

        if (result)
            await deleteAction(id);
    }

    return <article className={style.interaction}>
        <Link href={`/edit/${id}`} className='btn btn-primary'>수정</Link>
        <button className='btn btn-danger' onClick={onRemove}>삭제</button>
    </article>;
}