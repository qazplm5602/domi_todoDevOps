import Link from 'next/link';
import style from './style/style.module.scss';
import { TodoPreview } from '../TodoForm/declare';
import { formatDate } from '../../hooks/utils';

type Props = {
    data: TodoPreview
}

export default function TodoBox({ data }: Props) {
    return <Link href={`/view/${data.id}`} className={style.link}>
        <div className={style.box}>
            <h5 className='text-black'>{data.title}</h5>
            <div className={style.date}>{formatDate(new Date(data.startDate))}</div>
        </div>
    </Link>;
}