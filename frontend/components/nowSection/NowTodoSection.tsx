import { request } from '../../hooks/request';
import TodoList from '../todoList/TodoList';
import style from './style/style.module.scss';

type Props = {
    title?: React.ReactNode,
    uri: string,
    className?: string,
    emptyDoc?: React.ReactNode
}

export default async function NowTodoSection({ title, uri, className, emptyDoc }: Props) {
    const { code, data } = await request(`/todo${uri}`);
    
    return <section className={`${style.main} ${className || ''}`}>
        {title}
        <TodoList />
        {emptyDoc}
    </section>
}