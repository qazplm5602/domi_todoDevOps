import TodoForm from "../../../components/TodoForm/TodoForm";
import { ApiError, request } from "../../../hooks/request";
import { notFound, redirect } from "next/navigation";
import { TodoData } from "../../../components/TodoForm/declare";

export default async function Page({ params }: { params: Promise<{ id: string }> }) {
    const { id } = await params;
    const { code, data } = await request(`/todo/${id}`);
    if (code !== 200) {
        const response = data as ApiError;
        if (response.code === "USER1") // 로그인 필요
            redirect("/login");

        if (code === 404)
            notFound();

        throw new Error("불러오기 오류");
    }

    const response = data as TodoData;
    return <TodoForm id={Number(id)} form={response} />
}