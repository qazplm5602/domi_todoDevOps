import { request } from '../../hooks/request';
import { TodoPreview } from '../TodoForm/declare';
import TodoList from '../todoList/TodoList';
import style from './style/style.module.scss';

type Props = {
    title?: React.ReactNode,
    uri: string,
    className?: string,
    emptyDoc?: React.ReactNode
}

export default async function NowTodoSection({ title, uri, className, emptyDoc }: Props) {
    const { code, data } = await request<TodoPreview[]>(`/todo${uri}`);
    if (code !== 200)
        return;

    return <section className={`${style.main} ${className || ''}`}>
        {title}
        {data.length > 0 ? <TodoList list={data} /> : emptyDoc}
    </section>
}