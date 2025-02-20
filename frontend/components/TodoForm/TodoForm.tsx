"use client";

import { useTransition } from 'react';
import TodoFormDateInput from './DateInput';
import TodoFormDescInput from './DescInput';
import style from './style/style.module.scss';
import TodoFormTitleInput from './TitleInput';
import { creatOrEditAction } from '../../actions/todoAction';

type Props = {
    id?: number,
    // form?: 
}

export default function TodoForm({ id }: Props) {
    const [ isPending, startTransition ] = useTransition();

    const onSubmit = function(event: React.FormEvent<HTMLFormElement>) {
        const form = new FormData(event.currentTarget);
        event.preventDefault();

        startTransition(async () => {
            await creatOrEditAction(form, id);
        });
    }

    return <main className={`container-lg ${style.main}`}>
        <form onSubmit={onSubmit}>
            <TodoFormTitleInput />
            <TodoFormDateInput />
            <TodoFormDescInput />

            <div className='btn-toolbar justify-content-end'>
                <button className='btn btn-primary' type='submit' disabled={isPending}>{id ? '수정' : '추가'}</button>
            </div>
        </form>
    </main>
}