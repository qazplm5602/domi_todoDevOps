import TodoList from '../todoList/TodoList';
import style from './style/style.module.scss';

type Props = {
    title?: React.ReactNode,
    uri: string,
    className?: string,
    emptyDoc?: React.ReactNode
}

export default function NowTodoSection({ title, uri, className, emptyDoc }: Props) {
    return <section className={`${style.main} ${className || ''}`}>
        {title}
        <TodoList />
        {emptyDoc}
    </section>
}