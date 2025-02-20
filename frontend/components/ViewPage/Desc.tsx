import style from './style/style.module.scss';

type Props = {
    value: string
}

export default function ViewDescription({ value }: Props) {
    return <section className={style.desc}>
        <h4>설명</h4>
        <pre>{value}</pre>
    </section>
}