import style from './style/style.module.scss';

export default function ViewDescription() {
    return <section className={style.desc}>
        <h4>설명</h4>
        <pre>
            어쩌구 저쩌구
            어쩌구 저쩌구
            어쩌구 저쩌구
            어쩌구 저쩌구
        </pre>
    </section>
}