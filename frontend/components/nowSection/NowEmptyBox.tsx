import style from './style/style.module.scss';

type Props = {
    text: string    
}

export default function NowEmptyBox({ text }: Props) {
    return <article className={style.empty}>
        <div>{text}</div>
    </article>;
}