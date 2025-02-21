import { Metadata } from "next";
import TodoForm from "../../components/TodoForm/TodoForm";

export const metadata: Metadata = {
    title: '생성'
}

export default function Page() {
    return <TodoForm />
}