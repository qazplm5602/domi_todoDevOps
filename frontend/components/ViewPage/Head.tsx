import { ViewInteracts } from './Interacts';
import style from './style/style.module.scss';

export default function ViewHeader() {
    return <section className={style.head}>
        <article className={style.detail}>
            <h3>할일 제목 ㅁㄴㅇㄹ</h3>
            <div className={`text-secondary ${style.sub}`}>2020-01-12</div>
        </article>

        <ViewInteracts />
    </section>
}