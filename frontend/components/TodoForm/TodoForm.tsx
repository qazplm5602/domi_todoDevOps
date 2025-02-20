"use client";

import { useEffect, useRef } from 'react';
import TodoFormDateInput from './DateInput';
import TodoFormDescInput from './DescInput';
import style from './style/style.module.scss';
import TodoFormTitleInput from './TitleInput';

type Props = {
    id?: number,
    // form?: 
}

export default function TodoForm({ id }: Props) {
    return <main className={`container-lg ${style.main}`}>
        <form>
            <TodoFormTitleInput />
            <TodoFormDateInput />
            <TodoFormDescInput />

            <div className='btn-toolbar justify-content-end'>
                <button className='btn btn-primary' type='submit'>{id ? '수정' : '추가'}</button>
            </div>
        </form>
    </main>
}