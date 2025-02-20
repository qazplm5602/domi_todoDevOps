import TodoBox from "../todoBox/TodoBox";
import { TodoPreview } from "../TodoForm/declare";

type Props = {
    list: TodoPreview[]
}

export default function TodoList({ list }: Props) {
    return <article>
        {list.map(v => <TodoBox key={v.id} data={v} />)}
    </article>
}