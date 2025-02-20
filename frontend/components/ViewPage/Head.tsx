import { ViewInteracts } from './Interacts';
import style from './style/style.module.scss';

type Props = {
    id: number,
    title: string,
    date: string
}

export default function ViewHeader({ id, title, date }: Props) {
    return <section className={style.head}>
        <article className={style.detail}>
            <h3>{title}</h3>
            <div className={`text-secondary ${style.sub}`}>{date}</div>
        </article>

        <ViewInteracts id={id} />
    </section>
}