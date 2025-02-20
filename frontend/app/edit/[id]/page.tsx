import { Params } from "next/dist/server/request/params";
import TodoForm from "../../../components/TodoForm/TodoForm";

export default async function Page({ params }: { params: Params }) {
    const { id } = await params;

    return <TodoForm />
}