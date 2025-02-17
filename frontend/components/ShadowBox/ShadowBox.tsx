import style from './style.module.scss';

type Props = {
    className?: string,
    children?: React.ReactNode
}

export default function ShadowBox({ children, className }: Props) {
    return <section className={`${style.box} ${className || ''}`}>
        {children}
    </section>
}